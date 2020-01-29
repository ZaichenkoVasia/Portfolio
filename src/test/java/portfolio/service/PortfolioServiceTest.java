package portfolio.service;

import org.junit.Before;
import org.junit.Test;
import portfolio.domain.Portfolio;
import portfolio.service.util.FileParser;
import portfolio.service.util.PortfolioFactory;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class PortfolioServiceTest {
//    private static final int PREVIOUS_YEAR = 2019;
//    private static final int CURRENT_YEAR = 2020;
//    private static final String FILE_NAME_PRICE2020 = "src/main/resources/price/price2020.txt";
//    private static final String FILE_NAME_PRICE2019 = "src/main/resources/price/price2019.txt";
//    private static final String FILE_NAME_QUANTITY = "src/main/resources/quantity/quantity.txt";
//    private static final double TOTAL_VALUE_2019 = 27.400000000000002;
//    private static final double TOTAL_VALUE_2020 = 30.400000000000002;
//    private static final double DIFFERENCE_TOTAL_VALUES_2019_2020 = 3.0;
//
//    private PortfolioService portfolioService;
//    private Portfolio portfolio;
//
//    @Before
//    public void setUp(){
//        portfolio = PortfolioFactory.getPortfolio(PREVIOUS_YEAR, FILE_NAME_PRICE2019, FILE_NAME_QUANTITY);
//        portfolioService = new PortfolioService();
//    }
//
//    @Test
//    public void shouldCalculateTotalValue() {
//        double actual = portfolioService.calculateTotalValue(portfolio);
//        double expected = TOTAL_VALUE_2019;
//        assertThat(actual, is(expected));
//    }
//
//    @Test
//    public void shouldChangeYearPricesOfStocks() {
//        List<Double> prices2020 = FileParser.parseFile(FILE_NAME_PRICE2020);
//        portfolioService.addYearPricesOfStocks(portfolio, prices2020, CURRENT_YEAR);
//        double actual = portfolioService.calculateTotalValue(portfolio);
//        double expected = TOTAL_VALUE_2020;
//        assertThat(actual, is(expected));
//    }
//
//    @Test
//    public void shouldFindDifferenceTotalValuesByTwoYear() {
//        portfolioService.calculateTotalValue(portfolio);
//        List<Double> prices2020 = FileParser.parseFile(FILE_NAME_PRICE2020);
//        portfolioService.addYearPricesOfStocks(portfolio, prices2020, CURRENT_YEAR);
//        double actual = portfolioService.differenceTotalValuesByTwoYear(portfolio, PREVIOUS_YEAR, CURRENT_YEAR);
//        double expected = DIFFERENCE_TOTAL_VALUES_2019_2020;
//        assertThat(actual, is(expected));
//    }
}
