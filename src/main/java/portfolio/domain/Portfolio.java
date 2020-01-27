package portfolio.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Portfolio {
    private List<Stock> stocks = new ArrayList<>();
    private Map<Integer, Double> yearToTotalValues = new HashMap<>();
    private int year;

    public Portfolio() {
    }

    public Portfolio(List<Stock> stocks, int year) {
        this.stocks = new ArrayList<>(stocks);
        this.year = year;
        calculateTotalValue();
    }

    public void addStock(Stock stock) {
        stocks.add(stock);
        calculateTotalValue(stock);
    }

    public void calculateTotalValue() {
        double totalValue = 0;
        for (Stock stock : stocks) {
            totalValue += stock.getPrice() * stock.getQuantity();
        }
        yearToTotalValues.put(year, totalValue);
    }

    public void calculateTotalValue(Stock stock) {
        double totalValue = yearToTotalValues.getOrDefault(year, 0.0);
        totalValue += stock.getPrice() * stock.getQuantity();
        yearToTotalValues.put(year, totalValue);
    }

    public void addYearPricesOfStocks(List<Double> prices, int year) {
        for (int i = 0; i < stocks.size(); i++) {
            stocks.get(i).setPrice(prices.get(i));
        }
        this.year = year;
        calculateTotalValue();
    }

    private void showTotalValue(int year) {
        System.out.println("Total Value of Portfolio for " + year + " is "
                + yearToTotalValues.get(year));
    }

    public void differenceTotalValuesByTwoYear(int firstYear, int secondYear) {
        double firstTotalValue = yearToTotalValues.get(firstYear);
        double secondTotalValue = yearToTotalValues.get(secondYear);
        double difference = secondTotalValue - firstTotalValue;
        printDifferenceTotalValues(firstYear, secondYear, difference);
    }

    private void printDifferenceTotalValues(int firstYear, int secondYear, double difference) {
        showTotalValue(firstYear);
        showTotalValue(secondYear);
        String differenceOutput = difference > 0 ?
                "Difference: +" + difference + "(congratulations!)"
                : "Difference: " + difference + "(don`t worry, will be lucky next year!)";
        System.out.println(differenceOutput);
        System.out.println("-----------------------------------\n");
    }

    public void print() {
        System.out.println("Portfolio " + year + " :");
        for (Stock stock : stocks) {
            System.out.println(stock);
        }
        showTotalValue(year);
        System.out.println("-----------------------------------\n");
    }
}
