package metier;

import java.io.*;
import java.util.Scanner;

public class Interface {
    private Scanner scanner;

    public Interface() {
        this.scanner = new Scanner(System.in);
    }

    public void authentifier() {
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
                authentifier(); // Récursivité pour corriger le choix
                break;
        }
    }

    private void authentifierUtilisateur() {
        System.out.println("Connexion utilisateur:");
        verifierIdentifiants("utilisateur.csv");
    }

    private void authentifierAdministrateur() {
        System.out.println("Connexion administrateur:");
        verifierIdentifiants("administrateur.csv");
    }

    private void verifierIdentifiants(String fichier) {
        System.out.print("Entrez votre ID: ");
        String id = scanner.nextLine();
        System.out.print("Entrez votre mot de passe: ");
        String mdp = scanner.nextLine();

        try {
            File file = new File(fichier);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] credentials = line.split(",");
                if (credentials[0].equals(id) && credentials[1].equals(mdp)) {
                    if (fichier.equals("utilisateur.csv")) {
                        menuUtilisateur();
                    } else {
                        menuAdministrateur();
                    }
                    return;
                }
            }
            System.out.println("Identifiant ou mot de passe incorrect.");
            reader.close();
        } catch (IOException e) {
            System.out.println("Erreur lors de l'accès au fichier " + fichier);
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
