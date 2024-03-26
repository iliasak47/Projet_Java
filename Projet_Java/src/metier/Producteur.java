package metier;

import java.util.ArrayList;

public class Producteur {
    private String nom;
    private String prenom;
    private ArrayList<Film> films;

    // Constructor
    public Producteur(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
        this.films = new ArrayList<Film>();
    }

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
    
    // Methodes
    public void ajouter_film(Film film) {
        this.films.add(film);
    }

    public void supprimer_film(Film film) {
        this.films.remove(film);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Producteur: ").append(this.prenom).append(" ").append(this.nom).append("\n");
        sb.append("Films produits:\n");
        
        if (films.isEmpty()) {
            sb.append("Aucun film produit.\n");
        } else {
            for (Film film : films) {
                sb.append(" - ").append(film.getTitre()).append(" (").append(film.getAnnee_prod()).append(")\n");
            }
        }

        return sb.toString();
    }
}
