public class Datacenter implements AvecClimatisation {

    int temperature;

    public Datacenter(int temperature) {
        this.temperature = temperature;
    }

    @Override
    public void refroidir() {
        this.temperature = 20;
    }
}
