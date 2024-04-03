package metier;
// test changement 1 ilias ak
import java.util.Date;

public class Abonne extends Utilisateur {
    private float taux_reduction;

    // Constructor
    public Abonne(String nom, String prenom, String mail, String adresse, Compte compte, Date date_naissance, String phrase_secrete, boolean abonne, float taux_reduction) {
        super(nom, prenom, mail, adresse, compte, date_naissance, phrase_secrete, abonne);
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

