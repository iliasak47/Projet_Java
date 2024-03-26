package packag;
// ess ou pas dero
import java.util.ArrayList;

public class Acteur {
    private String nom;
    private String prenom;
    private ArrayList<Film> films;

    // Constructor
    public Acteur(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
        this.films = new ArrayList<Film>();
    }

    // Methods from the class diagram
    public void ajouter_film(Film film) {}
    public void supprimer_film(Film film) {}

    // Getters and setters
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public ArrayList<Film> getFilms() {
        return new ArrayList<Film>(films);
    }

    public void setFilms(ArrayList<Film> films) {
        this.films = new ArrayList<Film>(films);
    }
}
