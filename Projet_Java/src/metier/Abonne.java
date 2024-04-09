package metier;
// test changement 1 ilias 
import java.util.Date;

public class Abonne extends Utilisateur {
    private float taux_reduction;

    // Constructor
    public Abonne(String nom, String prenom, String mail, String adresse, String id, String mdp, Date date_naissance, String phrase_secrete, boolean abonne, float taux_reduction) {
        super(nom, prenom, mail, adresse, id, mdp, date_naissance, phrase_secrete);
        this.taux_reduction = taux_reduction;
    }

    // Getter and Setter
    public float getTaux_reduction() {
        return taux_reduction;
    }

    public void setTaux_reduction(float taux_reduction) {
        this.taux_reduction = taux_reduction;
    }

}

