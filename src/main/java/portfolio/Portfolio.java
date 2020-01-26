package portfolio;

import java.util.ArrayList;
import java.util.HashMap;

public class Portfolio {
    private ArrayList<Stock> stocks = new ArrayList<>();
    private HashMap<Integer, Double> yearToTotalValue = new HashMap<>();
    private int year;

    public Portfolio() {
    }

    public Portfolio(ArrayList<Stock> stocks, int year) {
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
        yearToTotalValue.put(year, totalValue);
    }

    public void calculateTotalValue(Stock stock) {
        double totalValue = yearToTotalValue.getOrDefault(year, 0.0);
        totalValue += stock.getPrice() * stock.getQuantity();
        yearToTotalValue.put(year, totalValue);
    }

    public void updatePriceOfStocks(ArrayList<Double> prices, int year) {
        for (int i = 0; i < stocks.size(); i++) {
            stocks.get(i).setPrice(prices.get(i));
        }
        this.year = year;
        calculateTotalValue();
    }

    private void showTotalValueOfPortfolio(int year) {
        System.out.println("Total Value of Portfolio for " + year + " is "
                + yearToTotalValue.get(year));
    }

    public void differenceTotalValuesByTwoYear(int firstYear, int secondYear) {
        double firstTotalValue = yearToTotalValue.get(firstYear);
        double secondTotalValue = yearToTotalValue.get(secondYear);
        double difference = secondTotalValue - firstTotalValue;
        showTotalValueOfPortfolio(firstYear);
        showTotalValueOfPortfolio(secondYear);
        String differenceOutput = difference > 0 ?
                "Difference: +" + difference + "(congratulations!)"
                : "Difference: " + difference + "(don`t worry, will be lucky next year!)";
        System.out.println(differenceOutput);
        System.out.println("-----------------------------------\n");
    }

    public void showPortfolio() {
        System.out.println("Portfolio " + year + " :");
        for (Stock stock : stocks) {
            System.out.println(stock);
        }
        showTotalValueOfPortfolio(year);
        System.out.println("-----------------------------------\n");
    }
}
