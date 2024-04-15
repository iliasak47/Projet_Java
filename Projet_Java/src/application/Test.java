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
        // Chemin vers le fichier producteurs.csv dans le projet
        String cheminFichierProducteurs = "src/data/producteurs.csv";
        
        // Création d'une liste qui va contenir tous nos producteurs
        ArrayList<Producteur> producteurs = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(cheminFichierProducteurs))) {
            String line;
            br.readLine(); // Cette ligne est utilisée pour ignorer l'en-tête du fichier CSV
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                producteurs.add(new Producteur(values[0], values[1]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // Chemin vers le fichier films.csv dans le projet
        String cheminFichierFilms = "src/data/films.csv"; // Ajustez ce chemin si nécessaire
        
        // Création d'une liste qui va contenir tous nos films
        ArrayList<Film> films = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(cheminFichierFilms))) {
            String line;
            br.readLine(); // Cette ligne est utilisée pour ignorer l'en-tête du fichier CSV
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                // Créer une nouvelle instance de Producteur pour chaque film
                Producteur producteur = new Producteur(values[7], values[8]);
                // Créer une nouvelle instance de Film
                Film film = new Film(values[0], values[1], Integer.parseInt(values[2]), Boolean.parseBoolean(values[3]),values[4], Float.parseFloat(values[5]), producteur, Type_Film.valueOf(values[9]));
                films.add(film);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Affichage des films pour tester si le fichier a été lu correctement
        // for(Film film : films) {
        	// System.out.println(film.toString()); 
        // }
        
        // Chemin vers le fichier utilisateurs.csv dans le projet
        String cheminFichierUtilisateurs = "src/data/utilisateurs.csv"; // Ajustez ce chemin si nécessaire
        
        // Création d'une liste qui va contenir nos utilisateurs 
        ArrayList<Utilisateur> utilisateurs = new ArrayList<>();
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // Supposer que la date est au format yyyy-MM-dd
        
        try (BufferedReader br = new BufferedReader(new FileReader(cheminFichierUtilisateurs))) {
            String line;
            br.readLine(); // Cette ligne est utilisée pour ignorer l'en-tête du fichier CSV
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                Date dateN = sdf.parse(values[4]); // Parse la date d'inscription
                Utilisateur utilisateur = new Utilisateur(values[0], values[1], values[2], values[3], null, null, dateN, values[5]);
                utilisateurs.add(utilisateur);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (java.text.ParseException e) {
            System.out.println("Erreur de format de date dans le fichier CSV.");
            e.printStackTrace();
        }
        
        // On récupère le premier element de chaque liste 
        // Producteur producteur1 = producteurs.get(0); 
        Film film1 = films.get(0);
        Utilisateur utilisateur1 = utilisateurs.get(0);
        
        // Affichage du Film et Utilisateur récupéré
        // System.out.println(producteur1.toString()); 
        System.out.println(film1.toString()); 
        System.out.println(utilisateur1.toString()); 
        
        // Test des méthodes Ajouter Modifier 
        
        // Ajout d'un commentaire1 au film1
        String texte = "Le film est incroyable";
        Commentaire commentaire1 = utilisateur1.commenter(texte, film1);
        
        // Affichage des commentaires avant modification
        System.out.println("\nAffichage des commentaires avant modification :");
        film1.afficher_com();
        
        // Modifier un commentaire 
        // utilisateur1.modifier_com(commentaire1, film1);
        utilisateur1.modifierCommentaire(films);
        
        
        // Affichage des commentaires avant modification
        System.out.println("\nAffichage des commentaires après modification :");
        film1.afficher_com();
        
    }
}