package metier;

import java.io.*;
import java.util.Scanner;

public class Interface {
    private Scanner scanner;

    public Interface() {
        this.scanner = new Scanner(System.in);
    }
    
    public Film obtenirFilm(String titreRecherche) {
        String cheminFichierFilms = "src/data/films.csv"; // Assurez-vous que ce chemin est correct
        File fichierFilms = new File(cheminFichierFilms);

        try (BufferedReader br = new BufferedReader(new FileReader(fichierFilms))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                // Assumer que le titre est à l'index 1, ajustez selon votre structure CSV
                if (data.length > 1 && data[1].equalsIgnoreCase(titreRecherche)) {
                    // Supposer que votre fichier CSV a le format suivant:
                    // code,titre,annee_prod,com_actif,description,prix,producteur_nom,producteur_prenom,type_film
                    String code = data[0];
                    String titre = data[1];
                    int annee_prod = Integer.parseInt(data[2]);
                    boolean com_actif = Boolean.parseBoolean(data[3]);
                    String description = data[4];
                    float prix = Float.parseFloat(data[5]);
                    float note = Float.parseFloat(data[6]);
                    Producteur producteur = new Producteur(data[7], data[8]);
                    Type_Film type = Type_Film.valueOf(data[9]);
                    
                    Film film = new Film(code, titre, annee_prod, com_actif, description, prix, producteur, type);
                    film.setNote_moy(note);
                    
                    return film;
             
                }
            }
        } catch (IOException e) {
            System.out.println("Erreur lors de la lecture du fichier: " + e.getMessage());
            e.printStackTrace();
        }

        return null; // Aucun film trouvé
    }

    public void menu() {
        System.out.println("Bienvenue! Veuillez choisir votre type de connexion:");
        System.out.println("1. Utilisateur");
        System.out.println("2. Administrateur");
        System.out.print("Entrez votre choix (1 ou 2): ");
        int choix = scanner.nextInt();
        scanner.nextLine(); // Nettoyer le buffer

        switch (choix) {
            case 1:
                authentifierUtilisateur();
                break;
            case 2:
                authentifierAdministrateur();
                break;
            default:
                System.out.println("Choix invalide. Veuillez choisir 1 ou 2.");
                menu(); // R�cursivit� pour corriger le choix
                break;
        }
    }

    private void authentifierUtilisateur() {
        System.out.println("Connexion utilisateur:");
        verifierIdentifiants("utilisateur");
    }

    private void authentifierAdministrateur() {
        System.out.println("Connexion administrateur:");
        verifierIdentifiants("administrateur.csv");
    }

    private void verifierIdentifiants(String typeUtilisateur) {
        String cheminFichier = typeUtilisateur.equals("utilisateur") ? "Projet_Java/src/data/utilisateurs.csv" : "Projet_Java/src/data/administrateurs.csv";

        System.out.print("Entrez votre ID: ");
        String id = scanner.nextLine();
        System.out.print("Entrez votre mot de passe: ");
        String mdp = scanner.nextLine();

        boolean identifiantsCorrects = false;
        try (BufferedReader br = new BufferedReader(new FileReader(cheminFichier))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] credentials = line.split(",");
                if (credentials[4].equals(id) && credentials[5].equals(mdp)) {
                    identifiantsCorrects = true;
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Erreur lors de l'acc�s au fichier " + cheminFichier);
            e.printStackTrace();
        }

        if (identifiantsCorrects) {
            if (typeUtilisateur.equals("utilisateur")) {
                menuUtilisateur();
            } else {
                menuAdministrateur();
            }
        } else {
            System.out.println("Identifiant ou mot de passe incorrect.");
        }
    }

    private void menuUtilisateur() {
        System.out.println("Bienvenue au menu utilisateur!");
        int choix;

        do {
            System.out.println("\nMenu Utilisateur:");
            System.out.println("1. Commenter un film");
            System.out.println("2. Noter un film");
            System.out.println("3. Afficher les commentaires d'un film");
            System.out.println("4. Ajouter un film au panier");
            System.out.println("5. Acheter les films dans le panier");
            System.out.println("6. Consulter l'historique des achats");
            System.out.println("7. Gérer l'abonnement");
            System.out.println("8. Quitter");
            System.out.print("Entrez votre choix: ");
            choix = scanner.nextInt();
            scanner.nextLine();  // Consume newline left-over

            switch (choix) {
                case 1:
                    // Demander à l'utilisateur de saisir le titre du film qu'il souhaite commenter
                    System.out.print("Entrez le titre du film que vous souhaitez commenter: ");
                    String titre = scanner.nextLine();

                    // Faire appel à la méthode obtenirFilm(titre) qui va retourner l'objet film
                    Film film = obtenirFilm(titre);
                    
                    if (film == null) {
                        System.out.println("Film non trouvé.");
                        break;
                    }

                    // Demander à l'utilisateur de saisir le commentaire (qui sera un texte)
                    System.out.print("Entrez votre commentaire pour le film " + titre + ": ");
                    String texte = scanner.nextLine();

                    // Faire appel à la méthode commenter(texte, film) de la classe Utilisateur
                    // utilisateur.commenter(texte, film);

                    System.out.println("Votre commentaire a été ajouté.");
                    break;

                case 2:
                    // à faire
                    break;
                case 3:
                    // à faire
                    break;
                case 4:
                    // à faire 
                    break;
                case 5:
                    // 
                    break;
                case 6:
                    //.. 
                    break;
                case 7:
                    // ..
                    break;
                case 8:
                    System.out.println("Déconnexion.");
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
                    break;
            }
        } while (choix != 8);
    }

    private void menuAdministrateur() {
        System.out.println("Bienvenue au menu administrateur!");
        // Impl�mentation � venir
    }


    
}
