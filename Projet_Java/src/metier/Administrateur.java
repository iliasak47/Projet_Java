package metier;

public class Administrateur extends Personne {
	private Application application;
	
    // Constructeur
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

