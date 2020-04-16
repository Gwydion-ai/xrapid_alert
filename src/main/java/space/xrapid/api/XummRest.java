package space.xrapid.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import space.xrapid.domain.ApiKey;
import space.xrapid.domain.xumm.PaymentRequestInformation;
import space.xrapid.domain.xumm.webhook.WebHook;
import space.xrapid.service.ApiKeyService;
import space.xrapid.service.RateService;
import space.xrapid.service.XummService;

import javax.ws.rs.*;

@Path("/xumm")
@Slf4j
public class XummRest {

    @Autowired
    private XummService xummService;

    @Autowired
    private ApiKeyService apiKeyService;

    @Autowired
    private RateService rateService;

    @GET
    @Produces("application/json")
    public PaymentRequestInformation requestPayment() {
        double rate = rateService.getXrpUsdRate();
        return xummService.requestPayment(Math.ceil(100 / rate), "XRP", "Your key will be available after payment confirmation. The key will be valid 365 days.");
    }

    @GET
    @Path("/{id}")
    public ApiKey getApiKey(@PathParam("id") String id) {
        String status = xummService.verifyPayment(id);
        if ("SIGNED".equals(status)) {
            return apiKeyService.generateApiKey(365);
        } else {
            return ApiKey.builder().key(status).build();
        }
    }




    @POST
    @Consumes({"application/json"})
    @Path("/webhooks")
    public void notif(WebHook webHook) {
        try {
            log.info(new ObjectMapper().writeValueAsString(webHook));
        } catch (Exception e) {
        }

        xummService.updatePaymentStatus(webHook);
    }
}
