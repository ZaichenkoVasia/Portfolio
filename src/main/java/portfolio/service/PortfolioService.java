package portfolio.service;

import portfolio.domain.Portfolio;
import portfolio.domain.Stock;

import java.util.List;

public class PortfolioService {

    // TODO: rework calculation operations to work with BigDecimal
    public double calculateTotalValue(Portfolio portfolio) {
        double totalValue = 0;
        List<Stock> stocks = portfolio.getStocks();
        for (Stock stock : stocks) {
            totalValue += stock.getPrice() * stock.getQuantity();
        }
        portfolio.saveTotalValueToYear(totalValue);
        return portfolio.getTotalValuesByYear(portfolio.getYear());
    }

    public void addYearPricesOfStocks(Portfolio portfolio, List<Double> prices, int year) {
        List<Stock> stocks = portfolio.getStocks();
        for (int i = 0; i < stocks.size(); i++) {
            stocks.get(i).setPrice(prices.get(i));
        }
        portfolio.setYear(year);
        calculateTotalValue(portfolio);
    }

    public void print(Portfolio portfolio) {
        portfolio.print();
    }

    public double differenceTotalValuesByTwoYear(Portfolio portfolio, int firstYear, int secondYear) {
        double firstTotalValue = portfolio.getTotalValuesByYear(firstYear);
        double secondTotalValue = portfolio.getTotalValuesByYear(secondYear);
        return secondTotalValue - firstTotalValue;
    }
}
