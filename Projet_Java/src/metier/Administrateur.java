package metier;

import java.io.*;

public class Administrateur extends Personne {
	
    // Constructor
    public Administrateur(String nom, String prenom, String mail, String adresse, String id, String mdp) {
        super(nom, prenom, mail, adresse, id, mdp); 
    }

	// Methodes
    public void ajouterFilm(Film film) {
        // Chemin vers le fichier films.csv dans le projet
        String cheminFichierFilms = "Projet_Java/src/data/films.csv"; // Ajustez ce chemin si n�cessaire
        
        try (FileWriter fw = new FileWriter(cheminFichierFilms, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {

            // Formatage des donn�es du film pour le CSV
            String line = String.join(",",
                film.getCode(),
                film.getTitre(),
                String.valueOf(film.getAnnee_prod()),
                String.valueOf(film.isCom_actif()),
                film.getDescription(),
                String.format("%.2f", film.getPrix()),
                film.getProducteur().getNom(),
                film.getProducteur().getPrenom(),
                film.getType().toString()
            );

            // �criture dans le fichier CSV
            out.println(line);
            
            System.out.println("Le film a �t� ajout� avec succ�s.");

        } catch (IOException e) {
            System.out.println("Une erreur est survenue lors de l'�criture dans le fichier.");
            e.printStackTrace();
        }
    }

    public void supprimerFilm(Film film) {
    }

    public void ajouterUtilisateur(Utilisateur utilisateur) {
    	String cheminFichier = "Projet_Java/src/data/utilisateurs.csv"; // Ajustez ce chemin si n�cessaire
        
        try (FileWriter fw = new FileWriter(cheminFichier, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {

            // Formatage des donn�es du film pour le CSV
            String line = String.join(",",
                utilisateur.getNom(),
                utilisateur.getPrenom(),
                utilisateur.getMail(),
                utilisateur.getAdresse(),
                utilisateur.getId(),
                utilisateur.getMdp(),
                String.valueOf(utilisateur.getDate_naissance()),
                utilisateur.getPhrase_secrete(),
                String.valueOf(utilisateur.isAbonne())
            );

            // �criture dans le fichier CSV
            out.println(line);
            
            System.out.println("L'utilisateur a �t� ajout� avec succ�s.");

        } catch (IOException e) {
            System.out.println("Une erreur est survenue lors de l'�criture dans le fichier.");
            e.printStackTrace();
        }
    }

    public void supprimerUtilisateur(Utilisateur utilisateur) {
    	
    }
    
    public boolean sAuthentifier(String motDePasse) {
	    return this.mdp.equals(motDePasse);
	}
    
    public void activer_com(Film film) {	
    	
    }
    
    public void desactiver_com(Film film) {}
    
    


}

