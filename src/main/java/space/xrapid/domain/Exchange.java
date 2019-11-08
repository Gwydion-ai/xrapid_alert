package space.xrapid.domain;

import java.util.Arrays;

import static space.xrapid.domain.Currency.*;

public enum Exchange {

    BITSTAMP("bitstamp", true, USD,
            "rrpNnNLKrartuEqfJGpqyDwPj1AFPg9vn1",
            "rGFuMiw48HdbnrUbkRYuitXTmfrDBNTCnX",
            "rDsbeomae4FXwgQTJp9Rs64Qg9vDiTCdBv",
            "rvYAfWj5gh67oV6fW32ZzP3Aw4Eubs59B",
            "rUobSiUpYH2S97Mgb4E7b7HuzQj2uzZ3aD",
            "rPEPPER7kfTD9w2To4CQk6UCfuHM9c6GDY"),

    BITSO("bitso",true, MXN,
            "rG6FZ31hDHN1K5Dkbma3PSB5uVCuVVRzfn",
            "rHZaDC6tsGN2JWGeXhjKL6664RNCq5hu4B",
            "raXLsnnJVaLMDixEoXHXe56WQXKczbD8ub",
            "rGfGdVYLDSbji5mqfMvdpx4c8JyyfqVFgf",
            "rfEu1Wnr7LxStoFx8DBdzgr8M16FBUbH3K",
            "rLSn6Z3T8uCxbcd1oxwfGQN1Fdn5CyGujK"),

    BX_IN("bx.in.th", false, BAHT, "rp7Fq2NQVRJxQJvUZ4o8ZzsTSocvgYoBbs"),

    SBI_VC("sbi_vc", true, YEN,
            "rHtqTcp3SvjxbhpTYMFTUWX5B1mny9KWeE",
            "rwB2UA47taZQCfi4po3tw8vwBpt6Y6eioZ",
            "rJLEjF9wbrHU2H5YSJKn6S7hyyq8gne7su",
            "rEFtdHuyxgUjDL4t3gBsesQwHtnDy2W8rC",
            "r4dYaiK2rcV9ypXZjwvwH6uLoeCTrUPceW",
            "rMEFmh7nvypDmk6xJ3xcuqhYhKQGtXEmyK",
            "rN5wo76mqvdNvShMbi5zwsnHWm2KVaqW4m",
            "rLzFjyD1gYJx3XQc1tEj9pNa4DD1SY6DeR",
            "r9x4D7c2nfa3UJefLR4fpN31zZQvByHSLQ"),

    COIN_PH("coin.ph", true, PHP, "rU2mEJSLqBRkYLVTv55rFTgQajkLTnT6mA"),

    COINBENE("coinbene", false, BRL, "r9CcrhpV7kMcTu1SosKaY8Pq9g5XiiHLvS"),

    BITTREX("bittrex", true, USD, "rPVMhWBsfF9iMXYj3aAzJVkPDTFNSyWdKy", "rK7LSKygRUu9y9xcuhkWbcMRKRF5HVWwVL"),

    MERCADO("mercado", false, BRL, "rnW8je5SsuFjkMSWkgfXvqZH3gLTpXxfFH", "rHLndqCyNeEKY2PoDmSvUf5hVX5mgUZteB"),

    BRAZILIEX("braziliex", false, BRL,
            "__UNKNOW__"),

    BITCOIN_TRADE("bitcoin_trade", false, BRL, "r4ZQiz7r4vnM6tAMLu1NhxcDa7TNMdFLGt"),


    BITREX("bitrex", true, USD,
            "rPVMhWBsfF9iMXYj3aAzJVkPDTFNSyWdKy"),

    DCEX("dcex", true, USD, "r9W22DnkmktvdSdsdWS5CXJAxfWVRtbDD9", "rHXvKUCTzsu2CB8Y5tydaG7B2ABc4CCBYz"),

    BTC_MARKETS("btc_market", true, AUD, "r94JFtstbXmyG21h3RHKcNfkAHxAQ6HSGC", "rL3ggCUKaiR1iywkGW6PACbn3Y8g5edWiY", "rU7xJs7QmjbiyxpEozNYUFQxaRD5kueY7z"),

    BITKUB("bitkub", true, BAHT, "rpXTzCuXtjiPDFysxq8uNmtZBe9Xo97JbW");


    private String name;
    private Currency localFiat;
    private String[] addresses;
    private boolean confirmed;

    Exchange(String name, boolean confirmed, Currency localFiat, String... addresses) {
        this.name = name;
        this.addresses = addresses;
        this.localFiat = localFiat;
        this.confirmed = confirmed;
    }

    public static Exchange byAddress(String address) {
        return Arrays.stream(Exchange.values())
                .filter(adr -> Arrays.asList(adr.addresses).contains(address)).findAny().orElse(null);
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

    public boolean isConfirmed() {return confirmed;}
}
