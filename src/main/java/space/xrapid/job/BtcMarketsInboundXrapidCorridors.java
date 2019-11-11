package space.xrapid.job;

import org.springframework.stereotype.Component;
import space.xrapid.domain.Exchange;

@Component
public class BtcMarketsInboundXrapidCorridors extends InboundXrapidCorridors {

    @Override
    protected int getPriority() {
        return 0;
    }

    @Override
    protected Exchange getDestinationExchange() {
        return Exchange.BTC_MARKETS;
    }
}
