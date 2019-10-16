package space.xrapid.domain;

import lombok.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Data
public class ExchangeToExchangePayment extends Payment {

    private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'");

    private OffsetDateTime dateTime;
    private Long timestamp;
    private Exchange source;
    private String sourceAddress;
    private Exchange destination;
    private Double amount;
    private String transactionHash;
    private String trxHash;
    private XrpTrade toFiatTrade;
    private Long tag;

    private Currency destinationCurrencry;

    public String getDateAsString() {
        return dateFormat.format(timestamp);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Time : ").append(dateFormat.format(timestamp)).append(", ");
        sb.append("Amount : ").append(this.amount).append(", ");
        sb.append("Exchange Source : ").append(this.source == null ? "UNKNOWN" : this.source).append(", ");
        sb.append("Address Source : ").append(sourceAddress).append(", ");
        sb.append("Destination : ").append(this.destination).append(", ");
        sb.append("Trx Hash : ").append(this.destination).append(", ");


        return sb.toString();
    }
}
