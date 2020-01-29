package portfolio.domain;

import java.util.*;

public class Portfolio {
    private Map<Stock, Integer> stockToQuantity;
    private String year;

    public Portfolio(Map<Stock, Integer> stockToQuantity, String year) {
        this.stockToQuantity = stockToQuantity;
        this.year = year;
    }

    public Map<Stock, Integer> getStockToQuantity() {
        return stockToQuantity;
    }

    public void print() {
        System.out.println("Portfolio " + year + ":");
        for (Stock stock : stockToQuantity.keySet()) {
            System.out.println(stock.toString() + ", quantity: " + stockToQuantity.get(stock));
        }
    }
}
