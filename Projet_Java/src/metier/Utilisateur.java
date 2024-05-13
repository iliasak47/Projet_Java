package metier;

import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Utilisateur extends Personne {
    private ArrayList<Film> panier = new ArrayList<Film>();
    private ArrayList<Commentaire> commentaires = new ArrayList<Commentaire>();
    private ArrayList<Commande> achats = new ArrayList<Commande>();
    private ArrayList<Note> notes = new ArrayList<Note>();
    private Date date_naissance;
    private String phrase_secrete;
    private boolean abonne;
 
    // Constructor
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
   
    public void noter(Film film, float note) {
        Note n = new Note(note, film, this);
        film.ajouter_note(n);
        this.ajouter_note(n);
    }
  
    public void modifierCommentaire(int index, String nouveauTexte) {
        if (index >= 0 && index < commentaires.size()) {
            Commentaire commentaire = commentaires.get(index);
            commentaire.setTexte(nouveauTexte);
            commentaire.setDate(new Date());  // Met à jour la date du commentaire modifié
        } else {
            throw new IndexOutOfBoundsException("Index de commentaire invalide");
        }
    }
    
    public void afficherMesCommentaires() {
        if (commentaires.isEmpty()) {
            System.out.println("Vous n'avez aucun commentaire.");
            return;
        }
        int index = 1;
        System.out.println("----------- Mes Commentaires -----------");
        for (Commentaire commentaire : commentaires) {
            System.out.println(index++ + ". " + commentaire.getTexte() + " (Film: " + commentaire.getFilm().getTitre() + ")");
        }
    }
    
    public boolean hasCommentaires() {
        return !this.commentaires.isEmpty();  // Retourne true si la liste des commentaires n'est pas vide
    }
    
    public Commentaire commenter(String texte, Film film) {
        // Vérifie si les commentaires sont actifs pour le film
        if (!film.isCom_actif()) {
            System.out.println("Les commentaires ne sont pas activés pour ce film.");
            return null;
        }

        Commentaire c = new Commentaire(texte, new Date(), this, film);
        this.commentaires.add(c);
        film.ajouter_com(c);
        System.out.println("Votre commentaire a été ajouté.");
        return c;
    }

    public void ajouter_com(Commentaire commentaire) {
    	this.commentaires.add(commentaire);
    }
    
    public void supprimer_com(Commentaire commentaire) {
    	this.commentaires.remove(commentaire);
    }

    public void afficher_com(Film film) {
        film.afficher_com();
    } 
    
    public void afficher_com_filtre(Film film) {
    	film.afficher_com_filtre();
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
        } else {
            setAbonne(true);
            System.out.println("Vous êtes désormais abonné");
        }
    }

    public void se_desabonner() {
        if (!this.abonne) {
            System.out.println("Vous n'êtes pas abonné");
        } else {
            setAbonne(false);
            System.out.println("Vous êtes désormais désabonné");
        }
    }
    
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
        System.out.println("Entrez les numéros des films que vous souhaitez acheter (séparés par des espaces) :");
        String input = scanner.nextLine();
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

        Commande nouvelleCommande = new Commande(new Date(), this);
        for (Film film : filmsAchetes) {
            nouvelleCommande.ajouter_Film(film);
        }

        float total = 0;
        for (Film film : filmsAchetes) {
            total += film.getPrix();
        }

        if (this.isAbonne()){
            total = total * 0.8f;  // 20% de réduction pour les abonnés
        }

        nouvelleCommande.setMontant(total);
        this.ajouter_achat(nouvelleCommande);

        System.out.println("Commande créée avec succès");
        System.out.println(nouvelleCommande);

        for (Film film : filmsAchetes) {
            this.supprimer_film_panier(film);
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
    
    public void ajouter_note(Note note) {
    	this.notes.add(note);
    }
    
    public void supprimer_note(Note note) {
    	this.notes.remove(note);
    }
    
    public void afficherVitrine() {
        // Lire tous les films et les films déjà achetés
        ArrayList<Film> tousLesFilms = Data.lireFilms();
        ArrayList<Film> filmsAchetes = Data.lireFilmsUtilisateur(this.id);
        
        // Déterminer le type de film le plus fréquent
        HashMap<Type_Film, Integer> typeCompteur = new HashMap<>();
        for (Film film : filmsAchetes) {
            typeCompteur.put(film.getType(), typeCompteur.getOrDefault(film.getType(), 0) + 1);
        }
        Type_Film typeMajoritaire = Collections.max(typeCompteur.entrySet(), Map.Entry.comparingByValue()).getKey();

        // Sélectionner les films pour la vitrine
        ArrayList<Film> selectionVitrine = new ArrayList<>();
        int compteurTypeMajoritaire = 0;

        for (Film film : tousLesFilms) {
            boolean dejaAchete = false;
            for (Film filmAchete : filmsAchetes) {
                if (film.getCode().equals(filmAchete.getCode())) {
                    dejaAchete = true;
                    break;
                }
            }
            if (!dejaAchete) {
                if (film.getType().equals(typeMajoritaire) && compteurTypeMajoritaire < 3) {
                    selectionVitrine.add(film);
                    compteurTypeMajoritaire++;
                } else if (selectionVitrine.size() < 10) {
                    selectionVitrine.add(film);
                }
            }
            if (selectionVitrine.size() == 10) break;
        }
            
        String titre = "Films Sélectionnés pour la Vitrine";
        int totalWidth = 73; 
        int paddingLength = (totalWidth - titre.length()) / 2;
        String padding = " ".repeat(paddingLength);
        System.out.println(" ");
        System.out.println(padding + titre);
        System.out.println("*".repeat(totalWidth));
        System.out.println(String.format("| %-30s | %-15s | %-6s | %-6s |", "Titre", "Type", "Année", "Prix"));
        System.out.println("-".repeat(totalWidth));

        
        for (Film film : selectionVitrine) {
            System.out.printf("| %-30s | %-15s | %-6d | %-6.2f€ |\n",
                film.getTitre(), film.getType(), film.getAnnee_prod(), film.getPrix());
        }

        System.out.println("-".repeat(totalWidth));
       
    }
    

    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nom: ").append(this.nom).append("\n");
        sb.append("Prénom: ").append(this.prenom).append("\n");
        sb.append("Mail: ").append(this.mail).append("\n");
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        sb.append("Date de naissance: ").append(formatter.format(this.date_naissance)).append("\n");
        sb.append("ID: ").append(this.id).append("\n");
        sb.append("Abonné: ").append(this.abonne ? "Oui" : "Non").append("\n");
        return sb.toString();
    }
}
