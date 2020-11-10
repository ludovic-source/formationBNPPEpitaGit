public class Maison implements AvecChauffage, AvecClimatisation {

    int temperature;

    @Override
    public void chauffer() {
        this.temperature += 1;
    }

    @Override
    public void refroidir() {
        this.temperature -= 1;
    }

}
