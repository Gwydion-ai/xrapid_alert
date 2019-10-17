package space.xrapid.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import space.xrapid.domain.Exchange;
import space.xrapid.domain.ExchangeToExchangePayment;
import space.xrapid.repository.ExchangeToExchangePaymentRepository;

import java.util.List;

@Service
public class XrapidService {

    @Autowired
    private ExchangeToExchangePaymentRepository exchangeToExchangePaymentRepository;


    List<ExchangeToExchangePayment> findXrapidToExchangePayments(Exchange source, Exchange destination) {
        throw new UnsupportedOperationException();
    }
}
