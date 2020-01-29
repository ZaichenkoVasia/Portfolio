package portfolio.domain;

import java.math.BigDecimal;

public class Stock {
    private String isin;
    private BigDecimal price;

    public Stock(String isin, BigDecimal price) {
        this.isin = isin;
        this.price = price;
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
