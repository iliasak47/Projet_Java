package metier;

import java.util.ArrayList;

public class Application {
    private ArrayList<Utilisateur> utilisateurs;
    private ArrayList<Film> films;

    public Application() {
        this.utilisateurs = new ArrayList<Utilisateur>();
        this.films = new ArrayList<Film>();
    }

    // Getters
    public ArrayList<Utilisateur> getUtilisateurs() {
        return new ArrayList<>(utilisateurs); // Retourne une copie pour pr�server l'encapsulation
    }

    public ArrayList<Film> getFilms() {
        return new ArrayList<>(films); // Retourne une copie pour pr�server l'encapsulation
    }

    // Setters
    public void setUtilisateurs(ArrayList<Utilisateur> utilisateurs) {
        this.utilisateurs = new ArrayList<>(utilisateurs); // Cr�e une nouvelle liste � partir de la liste fournie
    }

    public void setFilms(ArrayList<Film> films) {
        this.films = new ArrayList<>(films); // Cr�e une nouvelle liste � partir de la liste fournie
    }

    // M�thodes pour g�rer les utilisateurs et les films
    public void ajouterUtilisateur(Utilisateur utilisateur) {
        this.utilisateurs.add(utilisateur);
    }

    public void supprimerUtilisateur(Utilisateur utilisateur) {
        this.utilisateurs.remove(utilisateur);
    }

    public void ajouterFilm(Film film) {
        this.films.add(film);
    }

    public void supprimerFilm(Film film) {
        this.films.remove(film);
    }

}

