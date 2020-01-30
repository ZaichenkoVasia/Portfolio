package portfolio.service.domain;

import java.math.BigDecimal;
import java.util.Objects;

public class Stock {
    private String year;
    private String isin;
    private BigDecimal price;

    public Stock(String year, String isin, BigDecimal price) {
        this.year = year;
        this.isin = isin;
        this.price = price;
    }

    public String getYear() {
        return year;
    }

    public String getIsin() {
        return isin;
    }

    public void setIsin(String isin) {
        this.isin = isin;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "isin='" + isin + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stock stock = (Stock) o;
        return Objects.equals(year, stock.year) &&
                Objects.equals(isin, stock.isin) &&
                Objects.equals(price, stock.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, isin, price);
    }
}
