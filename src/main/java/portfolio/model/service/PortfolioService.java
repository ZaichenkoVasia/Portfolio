package portfolio.model.service;

import portfolio.model.data.FileParser;
import portfolio.model.domain.Portfolio;
import portfolio.model.domain.Share;
import portfolio.model.domain.Stock;
import portfolio.model.service.exception.IncorrectInitValueRuntimeException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PortfolioService {
    private static final String INCORRECT_INIT_VALUES = "Init values of price or quantity are incorrect";
    private FileParser fileParser;

    public PortfolioService(FileParser fileParser) {
        this.fileParser = fileParser;
    }

    public BigDecimal calculateTotalValue(String year, long id) {
        BigDecimal totalValue = BigDecimal.ZERO;
//        Map<Stock, BigDecimal> stocks = portfolio.getStockToQuantity();
//        for (Stock stock : stocks.keySet()) {
//            BigDecimal stockPrice = stock.getPrice();
//            BigDecimal portfolioStockPrice = stockPrice.multiply(stocks.get(stock));
//            totalValue = totalValue.add(portfolioStockPrice);
//        }
        return totalValue;
    }

    public BigDecimal differenceTotalValuesByTwoYear(Portfolio firstPortfolio, Portfolio secondPortfolio) {
//        BigDecimal firstTotalValue = calculateTotalValue(firstPortfolio);
//        BigDecimal secondTotalValue = calculateTotalValue(secondPortfolio);
//        return secondTotalValue.subtract(firstTotalValue);
        return null;
    }

    public void print(Portfolio portfolio) {
        portfolio.print();
    }

    public Set<String> getAvailableYears(){
        return fileParser.findAvailableYears();
    }
}
