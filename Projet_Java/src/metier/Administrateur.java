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
        String cheminFichierFilms = "Projet_Java/src/data/films.csv"; // Ajustez ce chemin si n�cessaire
        File inputFile = new File(cheminFichierFilms);
        File tempFile = new File(inputFile.getAbsolutePath() + ".tmp");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                // trim newline when comparing with lineToRemove
                String trimmedLine = currentLine.trim();
                String[] data = trimmedLine.split(",");
                if (data[0].equals(film.getCode())) continue; // Si le code du film correspond, on ne l'�crit pas dans le fichier temporaire
                writer.write(currentLine + System.getProperty("line.separator"));
            }
            
            boolean successful = tempFile.renameTo(inputFile);

            if (successful) {
                System.out.println("Le film a �t� supprim� avec succ�s.");
            } else {
                System.out.println("Une erreur est survenue lors de la suppression du film.");
            }
        } catch (IOException e) {
            System.out.println("Une erreur est survenue lors de l'acc�s au fichier.");
            e.printStackTrace();
        } finally {
            tempFile.delete(); // Efface le fichier temporaire dans tous les cas
        }
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
    	if (film.isCom_actif()) {
    		System.out.println("Les commentaires sont d�j� activ�s pour ce film");
    	}
    	else {
    		film.setCom_actif(true);
    		System.out.println("Les commentaires sont d�sormais activ�s pour ce film");
    	}
    }
    
    public void desactiver_com(Film film) {
    	if (film.isCom_actif()) {
    		film.setCom_actif(false);
    		System.out.println("Les commentaires sont d�sormais d�sactiv�s pour ce film");
    	}
    	else {
    		System.out.println("Les commentaires sont d�j� d�sactiv�s pour ce film");
    	}
    }
    
    


}

