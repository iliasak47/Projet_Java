package metier;

public class Administrateur extends Personne {
	private Application application;
	
	// La classe application va contenir le main et créer un objet interface
    // Interface va contenir les différents menu de notre application (la vue utilisateur ou admin par exemple)
	// La classe Administrateur à son importance car elle implique des fonctionnalités différentes 
	
    // Constructor
    public Administrateur(String nom, String prenom, String mail, String adresse, String id, String mdp, Application application) {
        super(nom, prenom, mail, adresse, id, mdp); 
        this.application = application;
    }

    public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	// Methodes
    public void ajouterFilm(Film film) {
    	application.ajouterFilm(film);
    }

    public void supprimerFilm(Film film) {
    	application.supprimerFilm(film);
    }

    public void ajouterUtilisateur(Utilisateur utilisateur) {
    	application.ajouterUtilisateur(utilisateur);
    }

    public void supprimerUtilisateur(Utilisateur utilisateur) {
    	application.supprimerUtilisateur(utilisateur);
    }

}

