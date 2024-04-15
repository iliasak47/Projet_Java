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
    
 // Il faut trouver une façon de rien mettre en argument
    public void modifierCommentaire(ArrayList<Film> films) {
        Scanner scanner = new Scanner(System.in);
        
        if (films.isEmpty()) {
            System.out.println("Il n'y a aucun film à afficher.");
            return;
        }

        System.out.println("Liste des films :");
        for (int i = 0; i < films.size(); i++) {
            System.out.println(i + " : " + films.get(i).getTitre());
        }

        System.out.println("Entrez le numéro du film pour lequel vous souhaitez modifier le commentaire :");
        int indexFilm = scanner.nextInt();
        scanner.nextLine(); // Consommer la ligne restante

        if (indexFilm < 0 || indexFilm >= films.size()) {
            System.out.println("Sélection non valide.");
            return;
        }

        Film film = films.get(indexFilm);
        
        // Vérification de l'existence des commentaires
        if (film.getCommentaires().isEmpty()) {
            System.out.println("Il n'y a pas de commentaire à modifier pour ce film.");
            return;
        }
        
        ArrayList<Commentaire> commentaires=film.getCommentaires();
        System.out.println("Liste des commentaires :");
        for (int i = 0; i < film.getCommentaires().size(); i++) {
            System.out.println(i + " : " + commentaires.get(i));
        }

        System.out.println("Entrez le numéro du commentaire que vous souhaitez modifier:");
        int indexCommentaire = scanner.nextInt();
        scanner.nextLine(); // Consommer la ligne restante

        if (indexCommentaire < 0 || indexCommentaire>= commentaires.size()) {
            System.out.println("Sélection non valide.");
            return;
        }

        Commentaire commentaire = film.getCommentaires().get(indexCommentaire); // Obtenir le  commentaire que l'on veut modifier

        System.out.println("Saisissez le nouveau commentaire pour le film " + film.getTitre() + ":");
        String texte = scanner.nextLine(); 

        commentaire.setTexte(texte);
        commentaire.setDate(new Date());

        System.out.println("Le commentaire a été mis à jour.");

        // scanner.close(); // Ne fermez pas le scanner ici si vous utilisez System.in ailleurs
    }
    
    public void choisir_film_achat() {} // ajouter un film au panier ?
    public void payer_achat() {} // acheter ce qu'il y a dans le panier et donc créer un objet commande?
    public void consulter_historique_achat() {} // consulter les objets commandes
    public void filter_historique_achat(String string) {}
    public void ajouter_achat(Commande c) {} 
    public void supprimer_achat(Commande c) {}
    public void afficher_achat() {}
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