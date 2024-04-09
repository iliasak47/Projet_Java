package metier;

import java.util.Date;

public class Commentaire {
    private String texte;
    private Date date;
    private Utilisateur utilisateur; 
    private Film film;

    // Constructeur
    public Commentaire(String texte, Date date, Utilisateur utilisateur, Film film) {
        this.texte = texte;
        this.date = date;
        this.utilisateur = utilisateur; 
        this.film = film;
    }

    // Getter and setter for texte
    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    // Getter and setter for date
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    // Getter and setter for utilisateur
    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Commentaire sur le film \"").append(film.getTitre()).append("\" par ");
        sb.append(utilisateur != null ? utilisateur.getPrenom() + " " + utilisateur.getNom() : "Anonyme");
        sb.append(" le ").append(date).append(":\n");
        sb.append(texte);
        return sb.toString();
    }
}
