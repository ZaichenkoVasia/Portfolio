package portfolio.service.domain;

import java.math.BigDecimal;

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
}
