class Principale {

    public static void main(String[] args) {

        Velo velo = new Velo();
        velo.direBonjour();
        System.out.println(velo);
        System.out.println(velo.toString());   // aussi valable

        Train train = new Train();
        train.direBonjour();

    }

}
