package metier;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;
import java.util.Comparator;

public class Film {
    private String code;
    private String titre;
    private int annee_prod;
    private boolean com_actif;
    private String description;
    private float prix;
    private float note_moy;
    private ArrayList<Commentaire> commentaires = new ArrayList<Commentaire>();
    private ArrayList<Acteur> acteurs = new ArrayList<Acteur>();
    private ArrayList<Note> notes = new ArrayList<Note>();
    private Producteur producteur; 
    private Type_Film type;

    // Constructeurr
    public Film(String code, String titre, int annee_prod, boolean com_actif, String description, float prix, Producteur producteur, Type_Film type) {
        this.code = code;
        this.titre = titre;
        this.annee_prod = annee_prod;
        this.com_actif = com_actif;
        this.description = description;
        this.prix = prix; 
        this.producteur = producteur; 
        producteur.ajouter_film(this);
        this.note_moy = 0.0f; 
        this.type = type;
    }

    // Getters and Setters
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Type_Film getType() {
		return type;
	}

	public void setType(Type_Film type) {
		this.type = type;
	}

	public int getAnnee_prod() {
        return annee_prod;
    }

    public void setAnnee_prod(int annee_prod) {
        this.annee_prod = annee_prod;
    }

    public boolean isCom_actif() {
        return com_actif;
    }

    public void setCom_actif(boolean com_actif) {
        this.com_actif = com_actif;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }
    
    public void setNote_moy(float note_moy) {
        this.note_moy = note_moy;
    }

    public float getNote_moy() {
        return note_moy;
    }

    public Producteur getProducteur() {
        return producteur;
    }

    public void setProducteur(Producteur producteur) {
        this.producteur = producteur;
    }

    // Méthodes pour la gestion des commentaires, des acteurs et des notes
    public void ajouter_com(Commentaire commentaire) {
        this.commentaires.add(commentaire);
    }

    public void supprimer_com(Commentaire commentaire) {
        this.commentaires.remove(commentaire);
    }

    public void activer_com() {
        this.com_actif = true;
    }

    public void desactiver_com() {
        this.com_actif = false;
    }

    public void afficher_com() {
        // Vérifier si les commentaires sont désactivés pour le film
        if (!this.com_actif) {
            System.out.println("Les commentaires sont d�sactiv�s pour le film \"" + this.titre + "\".");
        } else if (this.commentaires.isEmpty()) {
            System.out.println("Aucun commentaire pour le film \"" + this.titre + "\".");
        } else {
            System.out.println("Commentaires pour le film \"" + this.titre + "\":");
            for (Commentaire commentaire : this.commentaires) {
                System.out.println("Date: " + commentaire.getDate() + " | Auteur: " + commentaire.getUtilisateur().getPrenom() + " " + commentaire.getUtilisateur().getNom());
                System.out.println("Commentaire: " + commentaire.getTexte());
                System.out.println("-------------------------------------------------");
            }
        }
    }



    public void afficher_com_filtre() {
        Scanner scanner = new Scanner(System.in);
        if (!this.com_actif) {
            System.out.println("Les commentaires sont désactivés pour le film \"" + this.titre + "\".");
        } else if (this.commentaires.isEmpty()) {
            System.out.println("Aucun commentaire pour le film \"" + this.titre + "\".");
        } else {
            System.out.println("Choisissez le mode de tri pour les commentaires :");
            System.out.println("1. Date croissante");
            System.out.println("2. Date décroissante");
            System.out.print("Entrez votre choix (1 ou 2) : ");
            
            int choix = scanner.nextInt();
            scanner.nextLine();  // Consume newline left-over
     
            // Tri des commentaires selon le choix de l'utilisateur
            switch (choix) {
                case 1:
                    Collections.sort(this.commentaires, Comparator.comparing(Commentaire::getDate));
                    break;
                case 2:
                    Collections.sort(this.commentaires, Comparator.comparing(Commentaire::getDate).reversed());
                    break;
                default:
                    System.out.println("Choix invalide. Affichage par défaut (date croissante).");
                    Collections.sort(this.commentaires, Comparator.comparing(Commentaire::getDate));
                    break;
            }
     
            System.out.println("Commentaires pour le film \"" + this.titre + "\":");
            for (Commentaire commentaire : this.commentaires) {
                System.out.println("Date: " + commentaire.getDate() + " | Auteur: " + commentaire.getUtilisateur().getNom() +" "+ commentaire.getUtilisateur().getPrenom());
                System.out.println("Commentaire: " + commentaire.getTexte());
                System.out.println("-------------------------------------------------");
            }
        }
    }

    public void ajouter_acteur(Acteur acteur) {
        this.acteurs.add(acteur);
    }

    public void supprimer_acteur(Acteur acteur) {
        this.acteurs.remove(acteur);
    }

    public void ajouter_note(Note note) {
        this.notes.add(note);
        calculer_note_moy();
    }

    // Méthode pour calculer la note moyenne basée sur les notes et afficher cette note moyenne
    public void calculer_note_moy() {
        float somme = 0;
        for (Note note : this.notes) {
            somme += note.getNote();
        }
        this.note_moy = notes.isEmpty() ? 0.0f : somme / notes.size();
        // Afficher la note moyenne
        System.out.println("La note moyenne pour le film " + this.titre + " est de : " + this.note_moy + "/10");
    }

    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Film: ").append(this.titre).append("\n");
        sb.append("Code: ").append(this.code).append("\n");
        sb.append("Thème: ").append(this.type).append("\n");
        sb.append("Année de production: ").append(this.annee_prod).append("\n");
        sb.append("Description: ").append(this.description).append("\n");
        sb.append("Prix: ").append(this.prix).append("€\n");
        sb.append("Note moyenne: ").append(this.note_moy).append("/10").append("\n");
        sb.append("Producteur: ").append(this.producteur != null ? this.producteur.getNom() : "Non spécifié").append("\n");
        sb.append("Commentaires actifs: ").append(this.com_actif ? "Oui" : "Non").append("\n");
        if (!acteurs.isEmpty()) {
            sb.append("Acteurs:\n");
            for (Acteur acteur : acteurs) {
                sb.append(" - ").append(acteur.getNom()).append("\n");
            }
        } else {
            sb.append("Pas d'acteurs.\n");
        }
        sb.append("Nombre de commentaires: ").append(this.commentaires.size()).append("\n");
        sb.append("Nombre de notes: ").append(this.notes.size()).append("\n");
        return sb.toString();
    }

	public ArrayList<Commentaire> getCommentaires() {
		return commentaires;
	}




}
       
