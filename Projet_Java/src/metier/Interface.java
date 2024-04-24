package metier;

import java.io.*;
import java.util.Scanner;

public class Interface {
    private Scanner scanner;

    public Interface() {
        this.scanner = new Scanner(System.in);
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
                menu(); // Récursivité pour corriger le choix
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
            System.out.println("Erreur lors de l'accès au fichier " + cheminFichier);
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
        // Implémentation à venir
    }

    private void menuAdministrateur() {
        System.out.println("Bienvenue au menu administrateur!");
        // Implémentation à venir
    }


    
}
