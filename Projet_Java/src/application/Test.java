package application;

import metier.*;

import java.util.Date;

public class Test {
    public static void main(String[] args) {
        // Création d'un producteur (en supposant que la classe Producteur est correctement définie)
        Producteur producteur = new Producteur("Christopher", "Nolan");

        // Création d'un film
        Film film = new Film("FILM123", "Inception", "Science-fiction", 2010, true, "Un voleur, qui s'infiltre dans les rêves...", 10.0f, producteur);
        
         
        // Création d'utilisateurs
        Utilisateur utilisateur1 = new Utilisateur("John", "Doe", "john.doe@example.com", "123 Main St", null, new Date(), "Phrase secrète");
        Utilisateur utilisateur2 = new Utilisateur("Jane", "Smith", "jane.smith@example.com", "456 Elm St", null, new Date(), "Une autre phrase secrète");

        // Ajout de commentaires au film
        //Commentaire commentaire1 = new Commentaire("Un film incroyable avec beaucoup de rebondissements !", new Date(), utilisateur1, film);
        String texte = "Commentaire de test";
        utilisateur1.ajouter_com(texte, film);
        
        // Affichage des commentaires avant modification
        System.out.println("Affichage des commentaires avant modification :");
        film.afficher_com();

        // Modification d'un commentaire
        System.out.println("\nModification d'un commentaire : ");
        utilisateur1.modifier_com(commentaire1, film);

        // Affichage des commentaires après modification
        System.out.println("\nAffichage des commentaires après modification :");
        film.afficher_com();
        
    }
}
    
    
