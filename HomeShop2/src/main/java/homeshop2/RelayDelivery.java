package homeshop2;

public class RelayDelivery implements Delivery {

    private int relayDelivery;

    public RelayDelivery(int relayDelivery) {
        this.relayDelivery = relayDelivery;
    }

    @Override
    public double getPrice() {
        if (this.relayDelivery <= 22) {
            return 0;
        } else {
            if (this.relayDelivery >= 23 && this.relayDelivery <= 47) {
                return 2.99;
            } else {
                return 4.99;
            }
        }
    }

    @Override
    public String getInfo() {
        return "Livraison en point relais";
    }
}
