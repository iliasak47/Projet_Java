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
        String cheminFichierFilms = "src/data/films.csv"; // Ajustez ce chemin si n�cessaire
        
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
                String.format("%.2f", film.getNote_moy()),
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
        String cheminFichierFilms = "src/data/films.csv";
        File inputFile = new File(cheminFichierFilms);
        File tempFile = new File(inputFile.getAbsolutePath() + ".tmp");

        System.out.println("Début de la suppression du film...");

        if (!inputFile.exists()) {
            System.out.println("Le fichier n'existe pas : " + cheminFichierFilms);
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String currentLine;
            boolean found = false;

            while ((currentLine = reader.readLine()) != null) {
                String trimmedLine = currentLine.trim();
                String[] data = trimmedLine.split(",");
                // Vérifiez que vous avez le bon nombre de colonnes dans le CSV
                if (data.length < 2) {
                    System.out.println("Ligne mal formatée ou incorrecte dans le fichier CSV: " + trimmedLine);
                    continue;
                }
                if (data[0].equals(film.getCode())) {
                    found = true;
                    System.out.println("Film trouvé, suppression : " + trimmedLine);
                    continue; // Ne pas écrire la ligne dans le fichier temporaire
                }
                writer.write(currentLine + System.getProperty("line.separator"));
            }

            if (!found) {
                System.out.println("Film avec le code " + film.getCode() + " n'a pas été trouvé dans le fichier.");
            }

            // Important de fermer le flux avant de renommer le fichier
            writer.close();
            reader.close();

            if (!tempFile.renameTo(inputFile)) {
                System.out.println("Impossible de renommer le fichier temporaire.");
            } else {
                System.out.println("Le film a été supprimé avec succès.");
            }
        } catch (IOException e) {
            System.out.println("Une erreur est survenue lors de l'accès ou de la modification du fichier.");
            e.printStackTrace();
        } finally {
            if (tempFile.exists()) {
                System.out.println("Suppression du fichier temporaire.");
                tempFile.delete(); // Supprime le fichier temporaire dans tous les cas
            }
        }
    }
    

    public void ajouterUtilisateur(Utilisateur utilisateur) {
    	String cheminFichier = "src/data/utilisateurs.csv"; // Ajustez ce chemin si n�cessaire
        
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
	    String cheminFichierUtilisateurs = "src/data/utilisateurs.csv"; // Assurez-vous que le chemin est correct
	    File inputFile = new File(cheminFichierUtilisateurs);
	    File tempFile = new File(inputFile.getAbsolutePath() + ".tmp");

	    System.out.println("Début de la suppression de l'utilisateur...");

	    if (!inputFile.exists()) {
	        System.out.println("Le fichier n'existe pas : " + cheminFichierUtilisateurs);
	        return;
	    }

	    boolean isDeleted = false;

	    try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
	         BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

	        String currentLine;

	        while ((currentLine = reader.readLine()) != null) {
	            // Supposons que l'ID utilisateur est unique et qu'il est le premier élément de chaque ligne
	            String trimmedLine = currentLine.trim();
	            String[] userDetails = trimmedLine.split(",");
	            if (userDetails[4].equals(utilisateur.getId())) {
	                // Utilisateur trouvé, ne pas l'écrire dans le fichier temporaire
	                isDeleted = true;
	                continue;
	            }
	            // Utilisateur non trouvé, écrire dans le fichier temporaire
	            writer.write(currentLine + System.getProperty("line.separator"));
	        }

	        writer.close();
	        reader.close();

	        // Supprime l'ancien fichier et renomme le temporaire
	        if (isDeleted && tempFile.renameTo(inputFile)) {
	            System.out.println("L'utilisateur a été supprimé avec succès.");
	        } else if (!isDeleted) {
	            System.out.println("L'utilisateur n'a pas été trouvé dans le fichier.");
	        } else {
	            System.out.println("Impossible de renommer le fichier temporaire.");
	        }
	    } catch (IOException e) {
	        System.out.println("Une erreur est survenue lors de la lecture ou de l'écriture dans le fichier.");
	        e.printStackTrace();
	    } finally {
            if (tempFile.exists()) {
                System.out.println("Suppression du fichier temporaire.");
                tempFile.delete(); // Supprime le fichier temporaire dans tous les cas
            }
        }
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
    
    // METHODE SUR LES STATISTIQUES 
    


}

