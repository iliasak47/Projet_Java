package metier;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;
import java.util.Comparator;
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
	public boolean sAuthentifier(String motDePasse) {
	    return this.mdp.equals(motDePasse);
	}

    public void noter(Film film, float note) {
    	Note n = new Note(note, film, this);
    	film.ajouter_note(n);
    	this.ajouter_note(n);
    }
    
    public void filter_com(Film film, String string) {}
    
    public void modifier_com(Commentaire commentaire, Film film) { 
        Scanner scanner = new Scanner(System.in); 
        System.out.println("Saisissez le nouveau commentaire pour le film " + film.getTitre() + ":");
        String texte = scanner.nextLine(); 
        commentaire.setTexte(texte);
        commentaire.setDate(new Date());
        scanner.close(); 
        System.out.println("Le commentaire a ete mis a jour.");
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
            System.out.println("Vous êtes déjà  abonné");
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
            System.out.println(index++ + ". " + film.getTitre() + " - " + film.getPrix() + "â‚¬");
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
                System.out.println("Numero invalide : " + (filmIndex + 1));
            }
        }
 
        if (filmsAchetes.isEmpty()) {
            System.out.println("Aucun film valide selectionne.");
            return;
        }
 
        // CrÃ©ation de la commande
        Commande nouvelleCommande = new Commande(new Date(), this); // date et montant seront mis Ã  jour
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
        this.ajouter_achat(nouvelleCommande);
        System.out.println("Commande creee avec succes. Montant total : " + total + "euros");
        
        // Retirer les films achetÃ©s du panier 
        for (Film film : filmsAchetes) {
            this.supprimer_film_panier(film);
        }
    }

    
    public void consulter_historique_achats_filtres() {
        if (achats.isEmpty()) {
            System.out.println("Aucun achat enregistré pour " + this.prenom + " " + this.nom);
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Choisissez le mode de tri pour l'historique des achats :");
        System.out.println("1. Date croissante");
        System.out.println("2. Date décroissante");
        System.out.println("3. Montant croissant");
        System.out.println("4. Montant décroissant");
        System.out.print("Entrez votre choix (1, 2, 3 ou 4) : ");
        int choix = scanner.nextInt();

        // Tri des commandes selon le choix de l'utilisateur
        switch (choix) {
            case 1:
                Collections.sort(achats, Comparator.comparing(Commande::getDate));
                break;
            case 2:
                Collections.sort(achats, Comparator.comparing(Commande::getDate).reversed());
                break;
            case 3:
                Collections.sort(achats, Comparator.comparing(Commande::getMontant));
                break;
            case 4:
                Collections.sort(achats, Comparator.comparing(Commande::getMontant).reversed());
                break;
            default:
                System.out.println("Choix invalide. Affichage par défaut (date croissante).");
                Collections.sort(achats, Comparator.comparing(Commande::getDate));
                break;
        }

        System.out.println("Historique des achats pour " + this.prenom + " " + this.nom + " :");
        for (Commande c : achats) {
            System.out.println(c + "\n");
        }
    }
    
    public void ajouter_achat(Commande c) {
    	this.achats.add(c);
    } 
    public void supprimer_achat(Commande c) {
    	this.achats.remove(c);
    }
    
    public void consulter_historique_achats() {
    	System.out.println("Historique des achats pour " + this.prenom + " " + this.nom + " :");
    	for (Commande c : this.achats) {
    		System.out.println(c + "\n");
    	}
    }
        
    public void ajouter_note(Note note) {
    	this.notes.add(note);
    }
    
    public void supprimer_note(Note note) {
    	this.notes.remove(note);
    }

    
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