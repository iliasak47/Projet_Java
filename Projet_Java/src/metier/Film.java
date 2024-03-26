package packag;


import java.util.ArrayList;

public class Film {
    private String code;
    private String titre;
    private String theme;
    private int annee_prod;
    private boolean com_actif;
    private String description;
    private float prix;
    private float note_moy;
    private ArrayList<Commentaire> commentaires;
    private ArrayList<Acteur> acteurs;
    private ArrayList<Note> notes;
    private Producteur producteur; 

    // Constructor
    public Film(String code, String titre, String theme, int annee_prod, boolean com_actif, String description, float prix, Producteur producteur) {
        this.code = code;
        this.titre = titre;
        this.theme = theme;
        this.annee_prod = annee_prod;
        this.com_actif = com_actif;
        this.description = description;
        this.prix = prix;
        this.commentaires = new ArrayList<Commentaire>(); // Initialisation précise
        this.acteurs = new ArrayList<Acteur>(); // Initialisation précise
        this.notes = new ArrayList<Note>(); // Initialisation précise
        this.producteur = producteur; // Initialisation de l'attribut producteur
        this.note_moy = 0.0f; // Initialisation de la note moyenne à 0
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

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
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

    public float getNote_moy() {
        return note_moy;
    }

    // Note moyenne ne devrait pas avoir de setter, car elle est calculée

    public ArrayList<Commentaire> getCommentaires() {
        return new ArrayList<>(commentaires);
    }

    public ArrayList<Acteur> getActeurs() {
        return new ArrayList<>(acteurs);
    }

    public ArrayList<Note> getNotes() {
        return new ArrayList<>(notes);
    }

    public void setNotes(ArrayList<Note> notes) {
        this.notes = new ArrayList<>(notes);
        calculer_note_moy();
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
            System.out.println("Les commentaires sont désactivés pour le film \"" + this.titre + "\".");
        } else if (this.commentaires.isEmpty()) {
            System.out.println("Aucun commentaire pour le film \"" + this.titre + "\".");
        } else {
            System.out.println("Commentaires pour le film \"" + this.titre + "\":");
            for (Commentaire commentaire : this.commentaires) {
                String auteur = "Anonyme";
                if (commentaire.getUtilisateur() != null) {
                    auteur = commentaire.getUtilisateur().getPrenom() + " " + commentaire.getUtilisateur().getNom();
                }
                System.out.println("Date: " + commentaire.getDate() + " | Auteur: " + auteur);
                System.out.println("Commentaire: " + commentaire.getTexte());
                System.out.println("-------------------------------------------------");
            }
        }
    }

    public void afficher_com_filtres(String string) {
        // Implémentation pour afficher les commentaires filtrés
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

    // Méthode pour calculer la note moyenne basée sur les notes
    public void calculer_note_moy() {
        float somme = 0;
        for (Note note : this.notes) {
            somme += note.getNote();
        }
        this.note_moy = notes.isEmpty() ? 0.0f : somme / notes.size();
    }
}
       