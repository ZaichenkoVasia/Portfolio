package portfolio.service;

import portfolio.service.domain.Portfolio;
import portfolio.service.domain.Stock;

import java.math.BigDecimal;
import java.util.Map;

public class PortfolioService {

    public BigDecimal calculateTotalValue(Portfolio portfolio) {
        BigDecimal totalValue = BigDecimal.ZERO;
        Map<Stock, Integer> stocks = portfolio.getStockToQuantity();
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
}
