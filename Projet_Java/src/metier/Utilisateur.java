package metier;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;

public class Utilisateur extends User {
    private ArrayList<Film> panier = new ArrayList<Film>();
    private ArrayList<Commentaire> commentaires = new ArrayList<Commentaire>();
    private ArrayList<Commande> achats = new ArrayList<Commande>();
    private ArrayList<Note> notes = new ArrayList<Note>();
    private Date date_naissance;
    private String phrase_secrete;
    private boolean abonne;

    // Constructor Test
    public Utilisateur(String nom, String prenom, String mail, String adresse, Compte compte, Date date_naissance, String phrase_secrete) {
        super(nom, prenom, mail, adresse, compte);
        this.date_naissance = date_naissance;
        this.phrase_secrete = phrase_secrete;
        this.abonne = false;
    }

    // Getter Setter
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
    
    public void noter(Film film, float note) {
    	Note n = new Note(note, film, this);
    	film.ajouter_note(n);
    	this.notes.add(n);
    }
    
    public void filter_com(Film film, String string) {}
    
    public void modifier_com(Commentaire commentaire, Film film) { // à refaire
        Scanner scanner = new Scanner(System.in); 
        System.out.println("Saisissez le nouveau commentaire pour le film " + film.getTitre() + ":");
        String texte = scanner.nextLine(); 
        commentaire.setTexte(texte);
        commentaire.setDate(new Date());
        scanner.close(); 
        System.out.println("Le commentaire a été mis à jour.");
    }
    
    public void ajouter_com(String texte, Film film, Date date) { // à revoir
    	Commentaire c = new Commentaire(texte, date, this, film);
    	this.commentaires.add(c);
    	film.ajouter_com(c);
    }
    
    public void afficher_com(Film film) {
    	film.afficher_com();
    } 
    
    public void supprimer_com(Commentaire commentaire, Film film) {
    	this.commentaires.remove(commentaire);
    	film.supprimer_com(commentaire);
    }
    
    public void ajouter_film_panier(Film film) {
    	this.panier.add(film);
    }
    
    public void supprimer_film_panier(Film film) {
    	this.panier.remove(film);
    }
    
    public void s_abonner() {
    	if (this.abonne) {
            System.out.println("Vous êtes déjà abonné");
        }
   	 	else {
            setAbonne(true);
            System.out.println("Vous êtes désormais abonné");
        }
    }
    
    public void se_desabonner() {
    	if (!this.abonne) {
            System.out.println("Vous n'êtes pas abonné");
        }
   	 	else {
            setAbonne(false);
            System.out.println("Vous êtes désormais désabonné");
        }
    }
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