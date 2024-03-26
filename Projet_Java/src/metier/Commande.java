package metier;

import java.util.ArrayList;
import java.util.Date;

public class Commande {
    private Date date;
    private float montant;
    private Utilisateur utilisateur; 
    private ArrayList<Film> films; 

    // Constructor
    public Commande(Date date, float montant, Utilisateur utilisateur) {
        this.date = date;
        this.montant = 0.0f;
        this.utilisateur = utilisateur;
        this.films = new ArrayList<Film>(); 
        for(Film f : films) {
        	this.montant += f.getPrix();
        }
    }

    // Getter and setter for date
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    // Getter and setter for montant
    public float getMontant() {
        return montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    // Getter and setter for utilisateur
    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    // Getter and setter for films
    public ArrayList<Film> getFilms() {
        return new ArrayList<>(films); // Retourner une copie pour l'encapsulation
    }

    public void setFilms(ArrayList<Film> films) {
        this.films = new ArrayList<>(films); // Assigner une nouvelle liste basée sur la liste fournie
    }

    // Méthodes pour ajouter et supprimer des films
    public void ajouter_Film(Film film) {
        this.films.add(film);
    }

    public void supprimer_Film(Film film) {
        this.films.remove(film);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Commande effectuée le : ").append(this.date).append("\n");
        sb.append("Utilisateur : ").append(this.utilisateur.getPrenom()).append(" ").append(this.utilisateur.getNom()).append("\n");
        sb.append("-------------------------------------------------\n");
        sb.append("Liste des films :\n");
        
        if (this.films.isEmpty()) {
            sb.append("Aucun film dans cette commande.\n");
        } else {
            for (Film film : this.films) {
                sb.append("Film : ").append(film.getTitre()).append(" - Prix : ").append(film.getPrix()).append("€\n");
            }
        }

        sb.append("-------------------------------------------------\n");
        sb.append("Montant total : ").append(this.montant).append("€\n");
        return sb.toString();
    }



}
