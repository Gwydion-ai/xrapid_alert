package space.xrapid.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import space.xrapid.domain.ripple.Payment;
import space.xrapid.service.ExchangeToExchangePaymentService;
import space.xrapid.service.RateService;
import space.xrapid.service.TradeCacheService;
import space.xrapid.service.XrpLedgerService;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Comparator;
import java.util.List;

@Slf4j
@EnableScheduling
@Component
public class Scheduler {

    @Autowired
    private List<InboundXrapidCorridors> inboundCorridors;

    @Autowired
    private List<OutboundXrapidCorridors> outboundCorridors;

    @Autowired
    private TradeCacheService tradeCacheService;

    @Autowired
    private XrpLedgerService xrpLedgerService;

    @Autowired
    private ExchangeToExchangePaymentService exchangeToExchangePaymentService;

    @Autowired
    private RateService rateService;

    @Autowired
    protected SimpMessageSendingOperations messagingTemplate;


    private OffsetDateTime lastWindowEnd;
    private OffsetDateTime windowStart;
    private OffsetDateTime windowEnd;

    @Scheduled(fixedDelay = 30000)
    public void odl() throws Exception {

        OffsetDateTime lastWindowEndRollback = lastWindowEnd;
        OffsetDateTime windowStartRollback = windowStart;
        OffsetDateTime windowEndRollback = windowEnd;

        try {
            tradeCacheService.reset();

            updatePaymentsWindows();

            double rate = rateService.getXrpUsdRate();

            log.info("Fetching payment from XRP Ledger from {} to {}", windowStart.minusMinutes(5), windowEnd);
            List<Payment> payments = xrpLedgerService.fetchPayments(windowStart.minusMinutes(5), windowEnd);
            log.info("{} payments fetched from XRP Ledger", payments.size());


            inboundCorridors.stream()
                    .sorted(Comparator.comparing(InboundXrapidCorridors::getPriority))
                    .forEach(c -> c.searchXrapidPayments(payments, windowStart, rate));

            outboundCorridors.forEach(c -> {
                c.searchXrapidPayments(payments, rate);
            });

            messagingTemplate.convertAndSend("/topic/stats", exchangeToExchangePaymentService.calculateStats());

        } catch (Exception e) {
            log.error("", e);
            lastWindowEnd = lastWindowEndRollback;
            windowStart = windowStartRollback;
            windowEnd = windowEndRollback;

            Thread.sleep(120000);
        }

    }

    private void updatePaymentsWindows() {
        windowEnd = OffsetDateTime.now(ZoneOffset.UTC);
        windowStart = windowEnd.minusMinutes(350);

        if (lastWindowEnd != null) {
            windowStart = lastWindowEnd;
        }

        lastWindowEnd = windowEnd;
    }
}
