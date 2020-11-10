public class Train extends Vehicule {

    Train() {
        super(100);
    }

    void direBonjour() {
        System.out.println("Bonjour, je suis un véhicule à "+ this.nbreRoues + " roues");
        System.out.println("TchooTchoo !");
    }

}
