package metier;

public class User {
    protected String nom;
    protected String prenom;
    protected String mail;
    protected String adresse;
    protected Compte compte;
	
    public User(String nom, String prenom, String mail, String adresse, Compte compte) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.mail = mail;
		this.adresse = adresse;
		this.compte = compte;
	}

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

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public Compte getCompte() {
		return compte;
	}

	public void setCompte(Compte compte) {
		this.compte = compte;
	}
    
    
}
