package homeshop2;

public class ExpressDelivery implements Delivery {

    private String expressDelivery;

    public ExpressDelivery(String expressDelivery) {
        this.expressDelivery = expressDelivery;
    }

    @Override
    public double getPrice() {
        if (this.expressDelivery == "PARIS") {
            return 6.99;
        } else {
            return 9.99;
        }
    }

    @Override
    public String getInfo() {
        return "Livraison express";
    }
}
