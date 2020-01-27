package portfolio.service.util;

import portfolio.domain.Portfolio;
import portfolio.domain.Stock;
import portfolio.service.exception.IncorrectInitValueRuntimeException;

import java.util.ArrayList;
import java.util.List;

public final class PortfolioFactory {
    private static final String DIFFERENT_SIZE_OF_PRICES_AND_QUANTITIES = "Different size of prices and quantities";

    private PortfolioFactory() {
    }

    public static Portfolio getPortfolio(int year, String nameFilePrice, String nameFileQuantity) {
        List<Stock> stocks = initStocks(nameFilePrice, nameFileQuantity);
        return new Portfolio(stocks, year);
    }

    private static List<Stock> initStocks(String nameFilePrice, String nameFileQuantity) {
        List<Double> prices = FileParser.parseFile(nameFilePrice);
        List<Double> quantities = FileParser.parseFile(nameFileQuantity);

        if (prices.size() != quantities.size()) {
            throw new IncorrectInitValueRuntimeException(DIFFERENT_SIZE_OF_PRICES_AND_QUANTITIES);
        }
        ArrayList<Stock> stocks = new ArrayList<>();
        for (int i = 0; i < quantities.size(); i++) {
            stocks.add(new Stock(quantities.get(i), prices.get(i)));
        }
        return stocks;
    }
}
