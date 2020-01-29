package portfolio.service.domain;

import java.util.*;

public class Portfolio {
    private Map<Stock, Double> stockToQuantity;
    private String year;

    public Portfolio(Map<Stock, Double> stockToQuantity, String year) {
        this.stockToQuantity = stockToQuantity;
        this.year = year;
    }

    public Map<Stock, Double> getStockToQuantity() {
        return stockToQuantity;
    }

    public void print() {
        System.out.println("Portfolio " + year + ":");
        for (Stock stock : stockToQuantity.keySet()) {
            System.out.println(stock.toString() + ", quantity: " + stockToQuantity.get(stock));
        }
    }
}
