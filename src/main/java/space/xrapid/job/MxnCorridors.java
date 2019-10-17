package space.xrapid.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import space.xrapid.service.BitsoService;
import space.xrapid.service.TradeService;

@Component
public class MxnCorridors extends XrapidCorridors {

    @Autowired
    private BitsoService bitsoService;

    @Override
    protected TradeService getTradeService() {
        return bitsoService;
    }
}
