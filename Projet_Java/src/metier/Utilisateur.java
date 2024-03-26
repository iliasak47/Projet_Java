package metier;

import java.util.ArrayList;
import java.util.Date;

public class Utilisateur extends User {
    private ArrayList<Film> panier;
    private ArrayList<Commentaire> commentaires;
    private ArrayList<Commande> achats;
    private Date date_naissance;
    private String phrase_secrete;
    private boolean abonne;

    // Constructor
    public Utilisateur(String nom, String prenom, String mail, String adresse, Compte compte, Date date_naissance, String phrase_secrete, boolean abonne) {
        super(nom, prenom, mail, adresse, compte);
        this.date_naissance = date_naissance;
        this.phrase_secrete = phrase_secrete;
        this.abonne = abonne;
        this.panier = new ArrayList<Film>();
        this.commentaires = new ArrayList<Commentaire>();
        this.achats = new ArrayList<Commande>();
    }

    // Getter Setter
	public ArrayList<Film> getPanier() {
		return panier;
	}

	public void setPanier(ArrayList<Film> panier) {
		this.panier = panier;
	}

	public ArrayList<Commentaire> getCommentaires() {
		return commentaires;
	}

	public void setCommentaires(ArrayList<Commentaire> commentaires) {
		this.commentaires = commentaires;
	}

	public ArrayList<Commande> getAchats() {
		return achats;
	}

	public void setAchats(ArrayList<Commande> achats) {
		this.achats = achats;
	}

	public Date getDate_naissance() {
		return date_naissance;
	}

	public void setDate_naissance(Date date_naissance) {
		this.date_naissance = date_naissance;
	}

	public String getPhrase_secrete() {
		return phrase_secrete;
	}

	public void setPhrase_secrete(String phrase_secrete) {
		this.phrase_secrete = phrase_secrete;
	}

	public boolean isAbonne() {
		return abonne;
	}

	public void setAbonne(boolean abonne) {
		this.abonne = abonne;
	}

    // Method 
    public void s_authentifier() {}
    public void noter(Film film, int note) {}
    public void filter_com(Film film, String string) {}
    public void modifier_com(Commentaire commentaire, Film film) {}
    public void ajouter_com(Commentaire commentaire, Film film) {}
    public void afficher_com(Film film) {}
    public void supprimer_com(Commentaire commentaire, Film film) {}
    public void ajouter_film_panier(Film film) {}
    public void supprimer_film_panier(Film film) {}
    public void s_abonner() {}
    public void se_desabonner() {}
    public void choisir_film_achat() {}
    public void payer_achat() {}
    public void consulter_historique_achat() {}
    public void filter_historique_achat(String string) {}
    public void ajouter_achat(Commande c) {}
    public void supprimer_achat(Commande c) {}
    public void afficher_achat() {}
    public void afficher_achat_filtres(String string) {}
    public void ajouter_note(Note note, Film film) {}
    
}