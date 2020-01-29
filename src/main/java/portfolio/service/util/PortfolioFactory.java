package portfolio.service.util;

import portfolio.domain.Portfolio;
import portfolio.domain.Stock;
import portfolio.service.exception.IncorrectInitValueRuntimeException;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public final class PortfolioFactory {
    private static final String INCORRECT_INIT_VALUES = "Init values of price or quantity are incorrect";

    private PortfolioFactory() {
    }

    public static Portfolio getPortfolio(String year) {
        Map<String, BigDecimal> prices = FileParser.parseCSVPriceFile(year);
        Map<String, Integer> quantities = FileParser.parseCSVQuantityFile(year);
        if (prices == null || quantities == null) {
            throw new IncorrectInitValueRuntimeException(INCORRECT_INIT_VALUES);
        }
        Map<Stock, Integer> stockToQuantity = new HashMap<>();
        for (String priceKey : prices.keySet()) {
            if (quantities.containsKey(priceKey)) {
                Stock stock = new Stock(priceKey, prices.get(priceKey));
                stockToQuantity.put(stock, quantities.get(priceKey));
            }
        }
        return new Portfolio(stockToQuantity, year);
    }
}
