package metier;

public class Note {
    private float note;
    private Film film; 
    private Utilisateur utilisateur; 

    // Constructor
    public Note(float note, Film film, Utilisateur utilisateur) {
        this.note = note;
        this.film = film;
        this.utilisateur = utilisateur;
    }

    // Getter and setter 
    public float getNote() {
        return note;
    }

    public void setNote(float note) {
        this.note = note;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
    
    @Override
    public String toString() {
        String filmTitle = film != null ? film.getTitre() : "Non spécifié";
        String userName = utilisateur != null ? utilisateur.getPrenom() + " " + utilisateur.getNom() : "Anonyme";
        return "Note: " + note + "/10\n" +
               "Film: " + filmTitle + "\n" +
               "Donnée par: " + userName + "\n";
    }
}
