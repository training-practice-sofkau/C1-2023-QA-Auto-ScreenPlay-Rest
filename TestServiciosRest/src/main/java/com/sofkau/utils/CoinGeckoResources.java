package com.sofkau.utils;

public enum CoinGeckoResources {

    COIN_GECKO_BASE_URL("https://api.coingecko.com/"),
    GET_EXCHANGES_RESOURCE("api/v3/exchanges/binance");

    private final String value;

    CoinGeckoResources(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
