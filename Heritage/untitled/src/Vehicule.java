class Vehicule {

    int nbreRoues;

    Vehicule(int nbreRoues) {
        this.nbreRoues = nbreRoues;
    }

    void direBonjour() {
        System.out.println("Bonjour, je suis un véhicule à "+ this.nbreRoues + " roues");
    }


}
