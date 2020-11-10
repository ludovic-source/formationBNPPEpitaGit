class Velo extends Vehicule {

    Velo() {
        super(2);
    }

    @Override
    public String toString() {
        return "Bonjour, je suis un véhicule à "+ this.nbreRoues + " roues";
    }

}
