package metier;

public class Administrateur extends User {
    private String id; 

    // Constructeur
    public Administrateur(String nom, String prenom, String mail, String adresse, Compte compte, String id) {
        super(nom, prenom, mail, adresse, compte); 
        this.id = id;
    }

    // Getters et Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    // Methodes
    public void ajouterFilm(Film film) {    }

    public void supprimerFilm(Film film) {    }

    public void ajouterUtilisateur(Utilisateur utilisateur) {    }

    public void supprimerUtilisateur(Utilisateur utilisateur) {    }

}

