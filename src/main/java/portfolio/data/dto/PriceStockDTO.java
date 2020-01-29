package portfolio.data.dto;

import java.math.BigDecimal;
import java.util.Objects;

public class PriceStockDTO {
    private String year;
    private String isin;
    private BigDecimal price;

    public PriceStockDTO(String year, String isin, BigDecimal price) {
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

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PriceStockDTO)) return false;
        PriceStockDTO that = (PriceStockDTO) o;
        return Objects.equals(year, that.year) &&
                Objects.equals(isin, that.isin) &&
                Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, isin, price);
    }
}
