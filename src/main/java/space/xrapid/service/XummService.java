package space.xrapid.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import space.xrapid.domain.xumm.*;
import space.xrapid.domain.xumm.webhook.WebHook;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
public class XummService {
    private final String apiBase = "https://xumm.app/api/v1/platform/payload";

    @Value("${xumm.api.key}")
    private String apiKey;

    @Value("${xumm.api.secret}")
    private String secret;

    @Value("${xumm.api.destination}")
    private String destination;

    private Map<String, String> status = new HashMap<>();

    private RestTemplate restTemplate = new RestTemplate();

    public PaymentRequestInformation requestPayment(double amount, String currency, String instruction) {

        HttpHeaders headers = getHeaders();

        ResponseEntity<Response> response = restTemplate.exchange(apiBase,
                HttpMethod.POST, new HttpEntity(buildPayment(amount, currency, instruction), headers), Response.class);

        System.out.println(response.getBody().getUuid());

        return PaymentRequestInformation.builder().paymentId(response.getBody().getUuid()).qrCodeUrl(response.getBody().getRefs().getQrPng()).build();
    }

    public String verifyPayment(String id) {
        if (!status.containsKey(id)) {
            return "WAITING";
        }

        return status.get(id);
    }

    public void updatePaymentStatus(WebHook webHook) {
        String id = webHook.getPayloadResponse().getPayloadUuidv4();
        if (webHook.getPayloadResponse().getSigned() == null || !webHook.getPayloadResponse().getSigned()) {
            status.put(id, "REJECTED");
        } else if (webHook.getPayloadResponse().getSigned() != null && webHook.getPayloadResponse().getSigned()) {
            status.put(id, "SIGNED");
        }
    }

    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");

        headers.add("x-api-key", apiKey);
        headers.add("x-api-secret", secret);
        headers.add("authorization", "Bearer ");
        return headers;
    }

    private Payload buildPayment(double amount, String currency, String instruction) {
        return
                Payload.builder().txjson(XummPayment.builder()
                        .transactionType("Payment")
                        .destination(destination)
                        .amount(Amount.builder()
                                .issuer(destination)
                                .value(amount)
                                .currency(currency).build()).build())
                        .customMeta(CustomMeta.builder()
                                .instruction(instruction).build())
                        .build();
    }


    private void buildHeaders(HttpHeaders headers) {
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");

        headers.add("x-api-key", apiKey);
        headers.add("x-api-secret", secret);
        headers.add("authorization", "Bearer ");
    }
}
