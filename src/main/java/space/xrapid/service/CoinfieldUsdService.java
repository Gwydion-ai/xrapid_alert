package space.xrapid.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import space.xrapid.domain.Exchange;
import space.xrapid.domain.Trade;
import space.xrapid.domain.coinfield.Trades;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class CoinfieldUsdService implements TradeService {

    private String apiUrl = "https://api.coinfield.com/v1/trades/{market}?limit=1000&timestamp={timestamp}&order_by=desc";

    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public List<Trade> fetchTrades(OffsetDateTime begin) {
        HttpEntity<String> entity = getEntity();

        ResponseEntity<Trades> response = restTemplate.exchange(apiUrl.replace("{market}", getMarket())
                        .replace("{timestamp}", String.valueOf(begin.toEpochSecond())),
                HttpMethod.GET, entity, Trades.class);


        return getTrades(begin, response);
    }

    private List<Trade> getTrades(OffsetDateTime begin, ResponseEntity<Trades> response) {
        return response.getBody().getTrades().stream()
                .filter(filterTradePerDate(begin))
                .sorted(Comparator.comparing(space.xrapid.domain.coinfield.Trade::getTimestamp))
                .map(this::mapTrade)
                .collect(Collectors.toList());
    }

    private Trade mapTrade(space.xrapid.domain.coinfield.Trade trade) {
        return Trade.builder().amount(Double.valueOf(trade.getVolume()))
                .exchange(getExchange())
                .timestamp(OffsetDateTime.parse(trade.getTimestamp().replace("Z", "+00:00"), DateTimeFormatter.ISO_OFFSET_DATE_TIME).toEpochSecond() * 1000)
                .dateTime(OffsetDateTime.parse(trade.getTimestamp().replace("Z", "+00:00"), DateTimeFormatter.ISO_OFFSET_DATE_TIME))
                .orderId(trade.getId())
                .rate(Double.valueOf(trade.getPrice()))
                .side("buy")
                .build();
    }

    private Predicate<space.xrapid.domain.coinfield.Trade> filterTradePerDate(OffsetDateTime begin) {
        return p -> begin.minusMinutes(2).isBefore(OffsetDateTime.parse(p.getTimestamp().replace("Z", "+00:00"), DateTimeFormatter.ISO_OFFSET_DATE_TIME));
    }

    protected String getMarket() {
        return "xrpusd";
    }

    @Override
    public Exchange getExchange() {
        return Exchange.COINFIELD_USD;
    }

}
