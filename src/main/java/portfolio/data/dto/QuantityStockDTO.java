package portfolio.data.dto;

import java.util.Objects;

public class QuantityStockDTO {
    private String year;
    private String isin;
    private Double quantity;

    public QuantityStockDTO(String year, String isin, Double quantity) {
        this.year = year;
        this.isin = isin;
        this.quantity = quantity;
    }

    public String getYear() {
        return year;
    }

    public String getIsin() {
        return isin;
    }

    public Double getQuantity() {
        return quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof QuantityStockDTO)) return false;
        QuantityStockDTO that = (QuantityStockDTO) o;
        return Objects.equals(year, that.year) &&
                Objects.equals(isin, that.isin) &&
                Objects.equals(quantity, that.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, isin, quantity);
    }
}
