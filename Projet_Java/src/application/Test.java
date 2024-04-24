package application;

import metier.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {
    public static void main(String[] args) {
 
    	// Création d'un producteur pour les films de test
        Producteur producteur = new Producteur("Christopher", "Nolan");
 
        // Création de quelques films
        Film film1 = new Film("FILM001", "Inception", 2010, true, "Un voleur qui infiltre les rêves", 10.99f, producteur, Type_Film.Action);
        Film film2 = new Film("FILM002", "Interstellar", 2014, true, "Une aventure spatiale épique", 15.99f, producteur, Type_Film.Drame);
 
        // Création d'un utilisateur et ajout de films dans son panier
        Utilisateur utilisateur = new Utilisateur("John", "Doe", "john.doe@example.com", "123 Main St", "userID1", "mdp123", new Date(), "Secret");
        utilisateur.ajouter_film_panier(film1);
        utilisateur.ajouter_film_panier(film2);
 
        // Simuler une interaction avec l'utilisateur pour tester la méthode choisir_film_achat
        System.out.println("Test de la méthode choisir_film_achat()");
        utilisateur.choisir_film_achat();
        utilisateur.choisir_film_achat();
        utilisateur.consulter_historique_achats_filtres();
 
        // Puisqu'il s'agit d'une interaction de console, nous supposons ici que l'utilisateur tape des entrées valides
        // Pour des tests automatisés, envisager l'utilisation de frameworks de tests comme JUnit avec des mocks pour System.in
    }
}