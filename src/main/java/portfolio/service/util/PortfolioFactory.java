package portfolio.service.util;

import portfolio.domain.Portfolio;
import portfolio.domain.Stock;
import portfolio.service.exception.IncorrectInitValueRuntimeException;

import java.util.ArrayList;
import java.util.List;

public final class PortfolioFactory {
    private static final String DIFFERENT_SIZE_OF_PRICES_AND_QUANTITIES = "Different size of prices and quantities";
    private static final String FILE_NAME_PRICE2019 = "src/main/resources/price/price2019.txt";
    private static final String FILE_NAME_QUANTITY = "src/main/resources/quantity/quantity.txt";

    private PortfolioFactory() {
    }

    public static Portfolio getPortfolio(int year){
        List<Stock> stocks = initStocks();
        return new Portfolio(stocks, year);
    }

    private static List<Stock> initStocks() {
        List<Double> prices2019 = FileParser.parseFile(FILE_NAME_PRICE2019);
        List<Double> quantities = FileParser.parseFile(FILE_NAME_QUANTITY);

        if (prices2019.size() != quantities.size()) {
            throw new IncorrectInitValueRuntimeException(DIFFERENT_SIZE_OF_PRICES_AND_QUANTITIES);
        }
        ArrayList<Stock> stocks = new ArrayList<>();
        for (int i = 0; i < quantities.size(); i++) {
            stocks.add(new Stock(quantities.get(i), prices2019.get(i)));
        }
        return stocks;
    }
}
