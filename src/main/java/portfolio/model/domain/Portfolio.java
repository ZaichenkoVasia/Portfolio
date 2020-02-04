package portfolio.model.domain;

import java.math.BigDecimal;
import java.util.*;

public class Portfolio {
    private long id;
    private Map<Stock, BigDecimal> stockToQuantity;
    private String year;

    public Portfolio(long id, Map<Stock, BigDecimal> stockToQuantity, String year) {
        this.id = id;
        this.stockToQuantity = stockToQuantity;
        this.year = year;
    }

    public Map<Stock, BigDecimal> getStockToQuantity() {
        return stockToQuantity;
    }

    public void print() {
        System.out.println("Portfolio " + year + ":");
        for (Stock stock : stockToQuantity.keySet()) {
            System.out.println(stock.toString() + ", quantity: " + stockToQuantity.get(stock));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Portfolio portfolio = (Portfolio) o;
        return id == portfolio.id &&
                Objects.equals(stockToQuantity, portfolio.stockToQuantity) &&
                Objects.equals(year, portfolio.year);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, stockToQuantity, year);
    }
}
