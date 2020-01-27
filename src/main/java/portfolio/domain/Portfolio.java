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
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<Stock> getStocks() {
        return stocks;
    }

    public double getTotalValuesByYear(int year) {
        return yearToTotalValues.get(year);
    }

    public void saveTotalValueToYear(double totalValue) {
        yearToTotalValues.put(year, totalValue);
    }

    public void print() {
        System.out.println("Portfolio " + year + " :");
        for (Stock stock : stocks) {
            System.out.println(stock);
        }
        System.out.println("Total Value of Portfolio for " + year + " is "
                + getTotalValuesByYear(year));
        System.out.println("-----------------------------------\n");
    }
}
