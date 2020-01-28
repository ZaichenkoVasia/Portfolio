package portfolio.domain;

public class Stock {
    // TODO: add ID of stock and describe it in file
    //TODO quantity to map in portfolio
    private double quantity;
    private double price;

    public Stock(double quantity, double price) {
        this.quantity = quantity;
        this.price = price;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
