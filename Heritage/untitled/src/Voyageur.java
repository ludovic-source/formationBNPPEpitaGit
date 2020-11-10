import java.util.Objects;

public class Voyageur {

    int numeroDeVoyageur;
    int numeroDeSiege;
    String nom;
    String classe; // première / business / eco;

    public Voyageur(int numeroDeVoyageur, int numeroDeSiege, String nom, String classe) {
        this.numeroDeVoyageur = numeroDeVoyageur;
        this.numeroDeSiege = numeroDeSiege;
        this.nom = nom;
        this.classe = classe;
    }

    public int getNumeroDeVoyageur() {
        return this.numeroDeVoyageur;
    }

    public int getNumeroDeSiege() {
        return this.numeroDeSiege;
    }

    public String getNom() {
        return nom;
    }

    public String getClasse() {
        return this.classe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Voyageur voyageur = (Voyageur) o;
        return numeroDeSiege == voyageur.numeroDeSiege;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numeroDeSiege);
    }
}
