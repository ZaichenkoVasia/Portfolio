package portfolio;

import portfolio.exception.IncorrectInitValueRuntimeException;
import portfolio.util.FileParser;

import java.util.ArrayList;

public class Demo {
    private static final String FILE_NAME_PRICE2019 = "src/main/resources/price/price2019.txt";
    private static final String FILE_NAME_PRICE2020 = "src/main/resources/price/price2020.txt";
    private static final String FILE_NAME_QUANTITY = "src/main/resources/quantity/quantity.txt";
    private static final int PREVIOUS_YEAR = 2019;
    private static final int CURRENT_YEAR = 2020;

    public static void main(String[] args) {
        ArrayList<Stock> stocks = initStocks();

        Portfolio portfolio = new Portfolio(stocks, PREVIOUS_YEAR);
        portfolio.showPortfolio();

        ArrayList<Double> prices2020 = FileParser.parseFile(FILE_NAME_PRICE2020);

        portfolio.updatePriceOfStocks(prices2020, CURRENT_YEAR);
        portfolio.showPortfolio();
        portfolio.differenceTotalValuesByTwoYear(CURRENT_YEAR, PREVIOUS_YEAR);
    }

    private static ArrayList<Stock> initStocks() {
        ArrayList<Double> prices2019 = FileParser.parseFile(FILE_NAME_PRICE2019);
        ArrayList<Double> quantities = FileParser.parseFile(FILE_NAME_QUANTITY);

        if (prices2019.size() != quantities.size()) {
            throw new IncorrectInitValueRuntimeException("Different size of prices and quantities");
        }
        ArrayList<Stock> stocks = new ArrayList<>();
        for (int i = 0; i < quantities.size(); i++) {
            stocks.add(new Stock(quantities.get(i), prices2019.get(i)));
        }
        return stocks;
    }
}
