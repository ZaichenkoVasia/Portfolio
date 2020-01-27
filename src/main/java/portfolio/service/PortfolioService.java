package portfolio.service;

import portfolio.domain.Portfolio;
import portfolio.domain.Stock;

import java.util.List;

public class PortfolioService {

    public void calculateTotalValue(Portfolio portfolio) {
        double totalValue = 0;
        List<Stock> stocks = portfolio.getStocks();
        for (Stock stock : stocks) {
            totalValue += stock.getPrice() * stock.getQuantity();
        }
        portfolio.saveTotalValueToYear(totalValue);
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

    public void differenceTotalValuesByTwoYear(Portfolio portfolio, int firstYear, int secondYear) {
        double firstTotalValue = portfolio.getTotalValuesByYear(firstYear);
        double secondTotalValue = portfolio.getTotalValuesByYear(secondYear);
        double difference = secondTotalValue - firstTotalValue;
        showTotalValue(portfolio, firstYear);
        showTotalValue(portfolio, secondYear);
        String differenceOutput = difference > 0 ?
                "Difference: +" + difference + "(congratulations!)"
                : "Difference: " + difference + "(don`t worry, will be lucky next year!)";
        System.out.println(differenceOutput);
        System.out.println("-----------------------------------\n");

    }

    private void showTotalValue(Portfolio portfolio, int year) {
        System.out.println("Total Value of Portfolio for " + year + " is "
                + portfolio.getTotalValuesByYear(year));
    }
}
