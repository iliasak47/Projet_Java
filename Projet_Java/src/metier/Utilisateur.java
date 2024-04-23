package metier;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;

public class Utilisateur extends Personne {
    private ArrayList<Film> panier = new ArrayList<Film>();
    private ArrayList<Commentaire> commentaires = new ArrayList<Commentaire>();
    private ArrayList<Commande> achats = new ArrayList<Commande>();
    private ArrayList<Note> notes = new ArrayList<Note>();
    private Date date_naissance;
    private String phrase_secrete;
    private boolean abonne;
 
    // Constructor Test
    public Utilisateur(String nom, String prenom, String mail, String adresse, String id, String mdp, Date date_naissance, String phrase_secrete) {
        super(nom, prenom, mail, adresse, id, mdp);
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
    
    public void modifier_com(Commentaire commentaire, Film film) { 
        Scanner scanner = new Scanner(System.in); 
        System.out.println("Saisissez le nouveau commentaire pour le film " + film.getTitre() + ":");
        String texte = scanner.nextLine(); 
        commentaire.setTexte(texte);
        commentaire.setDate(new Date());
        scanner.close(); 
        System.out.println("Le commentaire a été mis à jour.");
    }
    
    public Commentaire commenter(String texte, Film film) { 
    	Commentaire c = new Commentaire(texte, new Date(), this, film);
    	this.commentaires.add(c);
    	film.ajouter_com(c);
    	return c;
    }
    
    public void afficher_com(Film film) {
    	film.afficher_com();
    } 
    
    public void ajouter_com(Commentaire commentaire) {
    	this.commentaires.add(commentaire);
    }
    
    public void supprimer_com(Commentaire commentaire) {
    	this.commentaires.remove(commentaire);
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
    
 
    
    
    // creer un objet
    public void choisir_film_achat() {
        if (panier.isEmpty()) {
            System.out.println("Votre panier est vide.");
            return;
        }
 
        Scanner scanner = new Scanner(System.in);
        System.out.println("Liste des films dans votre panier :");
        int index = 1;
        for (Film film : panier) {
            System.out.println(index++ + ". " + film.getTitre() + " - " + film.getPrix() + "€");
        }
 
        ArrayList<Film> filmsAchetes = new ArrayList<>();
        String input;
        System.out.println("Entrez les numéros des films que vous souhaitez acheter (séparés par des espaces) :");
        input = scanner.nextLine();
        String[] choixFilms = input.split(" ");
 
        for (String choix : choixFilms) {
            int filmIndex = Integer.parseInt(choix) - 1;
            if (filmIndex >= 0 && filmIndex < panier.size()) {
                filmsAchetes.add(panier.get(filmIndex));
            } else {
                System.out.println("Numéro invalide : " + (filmIndex + 1));
            }
        }
 
        if (filmsAchetes.isEmpty()) {
            System.out.println("Aucun film valide sélectionné.");
            return;
        }
 
        // Création de la commande
        Commande nouvelleCommande = new Commande(new Date(), this); // date et montant seront mis à jour
        for (Film film : filmsAchetes) {
            nouvelleCommande.ajouter_Film(film);
        }
 
        // Calcul du montant total de la commande
        float total = 0;
        for (Film film : filmsAchetes) {
            total += film.getPrix();
        }
        nouvelleCommande.setMontant(total);
 
        // Ajout de la commande aux achats
        achats.add(nouvelleCommande);
        System.out.println("Commande créée avec succès. Montant total : " + total + "€");
    }

    public void payer_achat() {} // acheter ce qu'il y a dans le panier et donc créer un objet commande?
    public void consulter_historique_achat() {} // consulter les objets commandes
    public void filter_historique_achat(String string) {}
    public void ajouter_achat(Commande c) {} 
    public void supprimer_achat(Commande c) {}
    public void afficher_achat() {
    	
    }
    public void afficher_achat_filtres(String string) {}
    public void ajouter_note(Note note, Film film) {}
    
    

    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nom: ").append(this.nom).append("\n");
        sb.append("Prenom: ").append(this.prenom).append("\n");
        sb.append("Mail : ").append(this.mail).append("\n");
        sb.append("Date de naissance : ").append(this.date_naissance).append("\n");
        sb.append("Id : ").append(this.id).append("\n");
        return sb.toString();
    }
    
    
    
}