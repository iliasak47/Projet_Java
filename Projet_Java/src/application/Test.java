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
    	
    	
    	
    	//------------------LECTURE DES DONNEES---------------
    	
    	// Lecture de nos donn�es (Utilisateurs, Films, Acteurs, Producteurs, Administrateurs, Commentaire (fichier � faire))
    	
    	// method pour issu de la classe Data pour lire nos donn�es 
    	
    	// appel de la method pour lire Utilisateurs 
    	
    	// appel de la method pour lire ... 
    	
    
    	// ----------------MENU-----------------------
    
    	// Interface 
    	Interface i = new Interface();
    	i.menu();
    	
    	
    	
    	
    	
    	// ---------------------- TEST DES FONCTIONS ADMINISTRATEURS ----------------------------------------------
    	
    	// Producteur producteur = new Producteur("Christopher", "Nolan");
    	// Utilisateur utilisateur1 = new Utilisateur("Adam", "Halladja", "jnkne@example.com", "788 Main St", "X", "X", new Date(), "adam123");
    	// Film film1 = new Film("FILM006", "Le Prestige", 2000, true, "Description du film blablabla", 10.99f,producteur, Type_Film.Action);
    	// Administrateur a = new Administrateur("John", "Doe", "john.doe@example.com", "123 Main St", "userID1", "mdp123");
    	// a.supprimerUtilisateur(utilisateur1);
        // a.ajouterFilm(film1);
        // a.ajouterUtilisateur(utilisateur1);
    	// a.activer_com(film1);
    	// a.desactiver_com(film1);
    	// film1.afficher_com();
    	// a.supprimerFilm(film1);
    	
                
        // ---------------------- CREATTION DE SIMULATION -------------------

        // Création d'un producteur pour les films de test
        // Producteur producteur = new Producteur("Christopher", "Nolan");
 
        // Création de quelques films
        // Film film1 = new Film("FILM001", "Inception", 2010, true, "Un voleur qui infiltre les rêves", 10.99f, producteur, Type_Film.Action);
        // Film film2 = new Film("FILM002", "Interstellar", 2014, true, "Une aventure spatiale épique", 15.99f, producteur, Type_Film.Drame);
        
        // Création d'un utilisateur et ajout de films dans son panier
        // Utilisateur utilisateur = new Utilisateur("John", "Doe", "john.doe@example.com", "123 Main St", "userID1", "mdp123", new Date(), "Secret");
        // utilisateur.ajouter_film_panier(film1);
        // utilisateur.ajouter_film_panier(film2);
 
    	
        // ---------------------- SIMULATION INTERACTION UTILISATEUR ACHAT ---------------------- 
        
    	// System.out.println("Test de la methode choisir_film_achat()");
        // utilisateur.choisir_film_achat();
        // utilisateur.choisir_film_achat();
        // utilisateur.consulter_historique_achats_filtres(); 
 
    	
    	
    }
}