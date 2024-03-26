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

    // Getter and setter for note
    public float getNote() {
        return note;
    }

    public void setNote(float note) {
        this.note = note;
    }

    // Getter and setter for film
    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    // Getter and setter for utilisateur
    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
}
