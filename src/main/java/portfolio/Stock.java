package portfolio;

public class Stock {

    private double quantity;

    private double price;

    public Stock() {
    }

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
}
