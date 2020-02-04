package portfolio.model.service;

import portfolio.model.data.FileParser;
import portfolio.model.domain.Portfolio;
import portfolio.model.domain.Stock;
import portfolio.model.service.exception.IncorrectInitValueRuntimeException;

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
        Map<Stock, BigDecimal> stocks = portfolio.getStockToQuantity();
        for (Stock stock : stocks.keySet()) {
            BigDecimal stockPrice = stock.getPrice();
            BigDecimal portfolioStockPrice = stockPrice.multiply(stocks.get(stock));
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

    public Portfolio getPortfolio(String year, long id) {
        Map<Stock, BigDecimal> stockToQuantity = fileParser.parseCSVQuantityFile(year, id);
        if (stockToQuantity == null) {
            throw new IncorrectInitValueRuntimeException(INCORRECT_INIT_VALUES);
        }
        return new Portfolio(id, stockToQuantity, year);
    }

    public Set<String> getAvailableYears(){
        return fileParser.findAvailableYears();
    }
}
