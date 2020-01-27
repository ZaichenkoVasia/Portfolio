package portfolio.view;

import portfolio.domain.Portfolio;
import portfolio.service.PortfolioService;
import portfolio.service.util.FileParser;
import portfolio.service.util.PortfolioFactory;

import java.util.List;

public class Demo {
    private static final int PREVIOUS_YEAR = 2019;
    private static final int CURRENT_YEAR = 2020;
    private static final String FILE_NAME_PRICE2020 = "src/main/resources/price/price2020.txt";
    private static final String FILE_NAME_PRICE2019 = "src/main/resources/price/price2019.txt";
    private static final String FILE_NAME_QUANTITY = "src/main/resources/quantity/quantity.txt";

    private static PortfolioService portfolioService = new PortfolioService();

    public static void main(String[] args) {
        Portfolio portfolio =
                PortfolioFactory.getPortfolio(PREVIOUS_YEAR, FILE_NAME_PRICE2019, FILE_NAME_QUANTITY);

        portfolioService.calculateTotalValue(portfolio);
        portfolioService.print(portfolio);

        List<Double> prices2020 = FileParser.parseFile(FILE_NAME_PRICE2020);
        portfolioService.addYearPricesOfStocks(portfolio, prices2020, CURRENT_YEAR);
        portfolioService.calculateTotalValue(portfolio);
        portfolioService.print(portfolio);
        portfolioService.differenceTotalValuesByTwoYear(portfolio, PREVIOUS_YEAR, CURRENT_YEAR);
    }
}
