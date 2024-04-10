package metier;

import java.util.ArrayList;

public class Application {
    private ArrayList<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
    private ArrayList<Film> films = new ArrayList<Film>();
   
    
    
    // Methodes pour g�rer les utilisateurs et les films
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


// IL FAUT SUPPRIMER CETTE CLASSE (à voir équipe)
// LA CLASSE APPLICATION DOIT ETRE DANS LE MAIN
