package portfolio.service;

import portfolio.data.FileParser;
import portfolio.service.domain.Portfolio;
import portfolio.service.domain.Stock;
import portfolio.service.exception.IncorrectInitValueRuntimeException;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

public class PortfolioService {
    private static final String INCORRECT_INIT_VALUES = "Init values of price or quantity are incorrect";
    private FileParser fileParser;

    public PortfolioService(FileParser fileParser) {
        this.fileParser = fileParser;
    }

    public BigDecimal calculateTotalValue(Portfolio portfolio) {
        BigDecimal totalValue = BigDecimal.ZERO;
        Map<Stock, Double> stocks = portfolio.getStockToQuantity();
        for (Stock stock : stocks.keySet()) {
            BigDecimal stockPrice = stock.getPrice();
            BigDecimal portfolioStockPrice = stockPrice.multiply(BigDecimal.valueOf(stocks.get(stock)));
            totalValue = totalValue.add(portfolioStockPrice);
        }
        return totalValue;
    }

    public BigDecimal differenceTotalValuesByTwoYear(Portfolio firstPortfolio, Portfolio secondPortfolio) {
        BigDecimal firstTotalValue = calculateTotalValue(firstPortfolio);
        BigDecimal secondTotalValue = calculateTotalValue(secondPortfolio);
        return secondTotalValue.subtract(firstTotalValue);
    }

    public void print(Portfolio portfolio) {
        portfolio.print();
    }

    public Portfolio getPortfolio(String year) {
        Map<Stock, Double> stockToQuantity = fileParser.parseCSVQuantityFile(year);
        if (stockToQuantity == null) {
            throw new IncorrectInitValueRuntimeException(INCORRECT_INIT_VALUES);
        }
        return new Portfolio(stockToQuantity, year);
    }

    public Set<String> getAvailableYears(){
        return fileParser.findAvailableYears();
    }
}
