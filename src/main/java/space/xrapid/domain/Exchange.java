package space.xrapid.domain;

import java.util.Arrays;

import static space.xrapid.domain.Currency.*;

public enum Exchange {

    BITSTAMP("bitstamp", true, USD, true,
            "rrpNnNLKrartuEqfJGpqyDwPj1AFPg9vn1",
            "rGFuMiw48HdbnrUbkRYuitXTmfrDBNTCnX",
            "rDsbeomae4FXwgQTJp9Rs64Qg9vDiTCdBv",
            "rvYAfWj5gh67oV6fW32ZzP3Aw4Eubs59B",
            "rUobSiUpYH2S97Mgb4E7b7HuzQj2uzZ3aD",
            "rPEPPER7kfTD9w2To4CQk6UCfuHM9c6GDY"),

    BITSTAMP_EUR("bitstamp", true, EUR, false,
            "rrpNnNLKrartuEqfJGpqyDwPj1AFPg9vn1",
            "rGFuMiw48HdbnrUbkRYuitXTmfrDBNTCnX",
            "rDsbeomae4FXwgQTJp9Rs64Qg9vDiTCdBv",
            "rvYAfWj5gh67oV6fW32ZzP3Aw4Eubs59B",
            "rUobSiUpYH2S97Mgb4E7b7HuzQj2uzZ3aD",
            "rPEPPER7kfTD9w2To4CQk6UCfuHM9c6GDY"),

    BITSO("bitso", true, MXN, true,
            "rG6FZ31hDHN1K5Dkbma3PSB5uVCuVVRzfn",
            "rHZaDC6tsGN2JWGeXhjKL6664RNCq5hu4B",
            "raXLsnnJVaLMDixEoXHXe56WQXKczbD8ub",
            "rGfGdVYLDSbji5mqfMvdpx4c8JyyfqVFgf",
            "rfEu1Wnr7LxStoFx8DBdzgr8M16FBUbH3K",
            "rLSn6Z3T8uCxbcd1oxwfGQN1Fdn5CyGujK"),

    BX_IN("bx.in.th", true, BAHT, false, "rp7Fq2NQVRJxQJvUZ4o8ZzsTSocvgYoBbs"),

    SBI_TRADE("sbi_trade", true, YEN, false, "rDDyH5nfvozKZQCwiBrWfcE528sWsBPWET",
            "rKcVYzVK1f4PhRFjLhWP7QmteG5FpPgRub"),

    CEX_IO("cex.io", true, USD, false, "rE1sdh25BJQ3qFwngiTBwaq3zPGGYcrjp1"),
    CEX_IO_EUR("cex.io", true, EUR, false, "rE1sdh25BJQ3qFwngiTBwaq3zPGGYcrjp1"),
    CEX_IO_GBP("cex.io", true, GBP, false, "rE1sdh25BJQ3qFwngiTBwaq3zPGGYcrjp1"),


    COINFIELD_USD("coinfield", false, USD, false, "rK7D3QnTrYdkp1fGKKzHFNXZpqN8dUCfaf"),
    COINFIELD_GBP("coinfield", false, GBP, false, "rK7D3QnTrYdkp1fGKKzHFNXZpqN8dUCfaf"),
    COINFIELD_AED("coinfield", false, AED, false, "rK7D3QnTrYdkp1fGKKzHFNXZpqN8dUCfaf"),
    COINFIELD_JPY("coinfield", true, JPY, false, "rK7D3QnTrYdkp1fGKKzHFNXZpqN8dUCfaf"),
    COINFIELD_CAD("coinfield", false, CAD, false, "rK7D3QnTrYdkp1fGKKzHFNXZpqN8dUCfaf"),
    COINFIELD_EUR("coinfield", false, EUR, false, "rK7D3QnTrYdkp1fGKKzHFNXZpqN8dUCfaf"),


    BINANCE_RUB("binance", false, RUB, false, "rEb8TK3gBgk5auZkwc6sHnwrGVJH8DuaLh", "rJb5KsHsDHF1YS5B5DU6QCkH5NsPaKQTcy", "rEy8TFcrAPvhpKrwyrscNYyqBGUkE9hKaJ"),
    BINANCE_US("binanceus", true, USD, false, "rEb8TK3gBgk5auZkwc6sHnwrGVJH8DuaLh", "rJb5KsHsDHF1YS5B5DU6QCkH5NsPaKQTcy", "rEy8TFcrAPvhpKrwyrscNYyqBGUkE9hKaJ"),

    WAZIRX("Wazirx", true, INR, false, "rwuAm7XdcP3SBwgJrVthCvCzU7kETJUUit", "rJXcrnAS8XoBwjvd5VrShrLMY8buPuiuC5"),

    KRAKEN_USD("kraken", false, USD, false, "rLHzPsX6oXkzU2qL12kHCH8G8cnZv1rBJh"),
    KRAKEN_EUR("kraken", false, EUR, false, "rLHzPsX6oXkzU2qL12kHCH8G8cnZv1rBJh"),
    KRAKEN_JPY("kraken", false, JPY, false, "rLHzPsX6oXkzU2qL12kHCH8G8cnZv1rBJh"),
    KRAKEN_CAD("kraken", false, CAD, false, "rLHzPsX6oXkzU2qL12kHCH8G8cnZv1rBJh"),

    COIN_PH("coin.ph", true, PHP, false, "rU2mEJSLqBRkYLVTv55rFTgQajkLTnT6mA"),

    COINBENE("coinbene", true, BRL, false, "r9CcrhpV7kMcTu1SosKaY8Pq9g5XiiHLvS"),

    INDEP_RESERVE("independent reserve", true, NZD, false, "r33hypJXDs47LVpmvta7hMW9pR8DYeBtkW"),

    MERCADO("mercado", true, BRL, false, "rnW8je5SsuFjkMSWkgfXvqZH3gLTpXxfFH", "rHLndqCyNeEKY2PoDmSvUf5hVX5mgUZteB"),

    BRAZILIEX("braziliex", true, BRL, false,
            "__UNKNOW__"),

    BITCOIN_TRADE("bitcoin_trade", true, BRL, false, "r4ZQiz7r4vnM6tAMLu1NhxcDa7TNMdFLGt"),

    LIQUID("liquid", true, JPY, true, "rHQ6kEtVUPk6mK9XKnjRoudenoHzJ8ZL9p", "rMbWmirwEtRr7pNmhN4d4ysTMBvBxdvovs"),


    BITREX("bitrex", false, USD, false,
            "_TO_DELETE_"),

    EXMO("exmo", true, UAH, false, "rUocf1ixKzTuEe34kmVhRvGqNCofY1NJzV", "rUCjhpLHCcuwL1oyQfzPVeWHsjZHaZS6t2", "rsTv5cJK2EMJhYqUUni4sYBonVk7KqTxZg", "rLJPjRYGDVVEjv4VrJtouzqzyJ51YtdZKY"),

    BITTREX("bitrex", true, USD, false,
            "rPVMhWBsfF9iMXYj3aAzJVkPDTFNSyWdKy"),

    DCEX("dcex", false, USD, false, "r9W22DnkmktvdSdsdWS5CXJAxfWVRtbDD9", "rHXvKUCTzsu2CB8Y5tydaG7B2ABc4CCBYz"),

    BTC_MARKETS("btc_market", true, AUD, true, "r94JFtstbXmyG21h3RHKcNfkAHxAQ6HSGC", "rL3ggCUKaiR1iywkGW6PACbn3Y8g5edWiY", "rU7xJs7QmjbiyxpEozNYUFQxaRD5kueY7z"),

    BITBANK("bitbank", true, JPY, true, "rLbKbPyuvs4wc1h13BEPHgbFGsRXMeFGL6", "rw7m3CtVHwGSdhFjV4MyJozmZJv3DYQnsA", "rwggnsfxvCmDb3YP9Hs1TaGvrPR7ngrn7Z"),

    BITKUB("bitkub", true, BAHT, false, "rpXTzCuXtjiPDFysxq8uNmtZBe9Xo97JbW"),

    INDODAX("indodax", true, IDR, true, "KUZ3ZFwzgaDGjKBysADByzxvohQ3C" ,"rDDrTcmnCxeTV1hycGdXiaEynYcU1QnSUg", "rB46Pb2mxdCk2zn68MNwZnFQ7Wv2Kjtddr"),

    QUIDAX("quidax", true, NGN, true, "rMuC8SpD8GP5a1uXma2jZyHVY5wxeCK7bV", "rnNQs4WAKUHes7kJtqqRiU3Wq9q1pHuDEt");


    private String name;
    private Currency localFiat;
    private String[] addresses;
    private boolean confirmed;
    private boolean maxTolerence;

    Exchange(String name, boolean confirmed, Currency localFiat, boolean maxTolerence, String... addresses) {
        this.name = name;
        this.maxTolerence = maxTolerence;
        this.addresses = addresses;
        this.localFiat = localFiat;
        this.confirmed = confirmed;
    }

    public static Exchange byAddress(String address) {
        return Arrays.stream(Exchange.values())
                .filter(adr -> Arrays.asList(adr.addresses).contains(address)).findAny().orElse(null);
    }

    public static Exchange byAddress(String address, Currency fiat) {
        if (fiat == null) {
            return byAddress(address);
        } else {
            return Arrays.stream(Exchange.values())
                    .filter(exchange -> Arrays.asList(exchange.addresses).contains(address))
                    .filter(exchange -> exchange.getLocalFiat().equals(fiat))
                    .findAny().orElse(null);
        }
    }

    public String getName() {
        return name;
    }

    public Currency getLocalFiat() {
        return localFiat;
    }

    public String[] getAddresses() {
        return addresses;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public boolean isMaxTolerence() {
        return maxTolerence;
    }
}
