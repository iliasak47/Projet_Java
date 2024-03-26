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
    
    public void afficher_commande() {
        System.out.println("Commande effectuée le : " + this.date);
        System.out.println("Utilisateur : " + this.utilisateur.getPrenom() + " " + this.utilisateur.getNom());
        System.out.println("-------------------------------------------------");
        System.out.println("Liste des films :");
        
        if (this.films.isEmpty()) {
            System.out.println("Aucun film dans cette commande.");
        } else {
            for (Film film : this.films) {
                System.out.println("Film : " + film.getTitre() + " - Prix : " + film.getPrix() + "€");
            }
        }

        System.out.println("-------------------------------------------------");
        System.out.println("Montant total : " + this.montant + "€");
    }


}
