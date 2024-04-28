package metier;

import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;

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
    
    public void genererStatistiquesUtilisateurs() {
        String cheminFichier = "src/data/utilisateurs.csv";
        int nombreTotal = 0;
        int nombreAbonnes = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(cheminFichier))) {
            String ligne;
            reader.readLine(); // Ignorer les en-têtes si votre fichier en a
            
            while ((ligne = reader.readLine()) != null) {
                String[] detailsUtilisateur = ligne.split(",");
                nombreTotal++;
                if (detailsUtilisateur.length > 8 && Boolean.parseBoolean(detailsUtilisateur[8])) {
                    nombreAbonnes++;
                }
            }
        } catch (IOException e) {
            System.out.println("Erreur lors de la lecture du fichier: " + e.getMessage());
            e.printStackTrace();
            return;
        }
        
        // Calcul des pourcentages
        double pourcentageAbonnes = (double) nombreAbonnes / nombreTotal * 100;
        double pourcentageNonAbonnes = 100.0 - pourcentageAbonnes; 

        // Affichage formaté des résultats
        System.out.println("Statistiques des Utilisateurs:");
        System.out.println("Nombre total d'utilisateurs: " + nombreTotal);
        System.out.println(String.format("Nombre d'utilisateurs abonnés: %d (%.2f%%)", nombreAbonnes, pourcentageAbonnes));
        System.out.println(String.format("Nombre d'utilisateurs non abonnés: %d (%.2f%%)", nombreTotal - nombreAbonnes, pourcentageNonAbonnes));
    }
    
    public void genererStatistiquesFilms() {
        String cheminFichierFilms = "src/data/films.csv";
        Map<String, Integer> filmsParType = new HashMap<>();
        float prixTotal = 0;
        float noteTotal = 0;
        int nombreFilms = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(cheminFichierFilms))) {
            String ligne;
            
            // Sauter la ligne d'en-tête
            reader.readLine();

            while ((ligne = reader.readLine()) != null) {
                String[] detailsFilm = ligne.split(",");
                // Supposons que la structure du fichier CSV est la suivante:
                // code, titre, annee_prod, com_actif, description, prix, note_moy, producteur_nom, producteur_prenom, type_film
                if (detailsFilm.length > 9) {
                    String type = detailsFilm[9];
                    float prix = Float.parseFloat(detailsFilm[5]);
                    float note = Float.parseFloat(detailsFilm[6]);

                    filmsParType.put(type, filmsParType.getOrDefault(type, 0) + 1);
                    prixTotal += prix;
                    noteTotal += note;
                    nombreFilms++;
                }
            }

            if (nombreFilms > 0) {
                float prixMoyen = prixTotal / nombreFilms;
                float noteMoyenne = noteTotal / nombreFilms;

                System.out.println("Statistiques des Films:");
                System.out.println("Nombre total de films: " + nombreFilms);
                System.out.println("Prix moyen des films: " + String.format("%.2f", prixMoyen) + "€");
                System.out.println("Note moyenne des films: " + String.format("%.2f", noteMoyenne) + "/10");
                System.out.println("Répartition des films par type:");
                filmsParType.forEach((type, count) -> System.out.println(type + ": " + count + " film(s)"));
            } else {
                System.out.println("Aucun film trouvé dans le fichier.");
            }

        } catch (IOException e) {
            System.out.println("Erreur lors de la lecture du fichier: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void genererStatistiquesCommandes() {
        String cheminFichierCommandes = "src/data/commandes.csv"; // Assurez-vous que ce chemin est correct
        File fichierCommandes = new File(cheminFichierCommandes);
        if (!fichierCommandes.exists()) {
            System.out.println("Le fichier de commandes n'existe pas.");
            return;
        }

        int nombreCommandes = 0;
        float totalMontant = 0f;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try (BufferedReader reader = new BufferedReader(new FileReader(fichierCommandes))) {
            String line = reader.readLine(); // Lire l'en-tête si nécessaire, sinon commenter cette ligne
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length > 1) {
                    float montant = Float.parseFloat(data[1]); // Assumer que le montant est à l'index 1
                    totalMontant += montant;
                    nombreCommandes++;
                }
            }
        } catch (IOException e) {
            System.out.println("Erreur lors de la lecture du fichier: " + e.getMessage());
            e.printStackTrace();
            return;
        }

        float moyenneMontant = nombreCommandes > 0 ? totalMontant / nombreCommandes : 0;
        
        System.out.println("Statistiques des commandes :");
        System.out.println("Nombre total de commandes : " + nombreCommandes);
        System.out.println("Montant total des commandes : " + totalMontant + "€");
        System.out.println("Montant moyen par commande : " + moyenneMontant + "€");
    }
    
    public void modifierFilm(Film film) {
        if (film == null) {
            System.out.println("Le film fourni est null, aucune modification possible.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Vous modifiez le film : " + film.getTitre());
        System.out.println("Que souhaitez-vous modifier ?");
        System.out.println("1. Titre");
        System.out.println("2. Description");
        System.out.println("3. Année de production");
        System.out.println("4. Prix");
        System.out.print("Entrez votre choix (1-4): ");
        int choix = scanner.nextInt();
        scanner.nextLine(); // consomme la ligne restante après un nextInt()

        switch (choix) {
            case 1:
                System.out.print("Entrez le nouveau titre : ");
                String nouveauTitre = scanner.nextLine();
                film.setTitre(nouveauTitre);
                break;
            case 2:
                System.out.print("Entrez la nouvelle description : ");
                String nouvelleDescription = scanner.nextLine();
                film.setDescription(nouvelleDescription);
                break;
            case 3:
                System.out.print("Entrez la nouvelle année de production : ");
                int nouvelleAnneeProd = scanner.nextInt();
                film.setAnnee_prod(nouvelleAnneeProd);
                break;
            case 4:
                System.out.print("Entrez le nouveau prix : ");
                float nouveauPrix = scanner.nextFloat();
                film.setPrix(nouveauPrix);
                break;
            default:
                System.out.println("Choix non valide.");
                break;
        }

        System.out.println("Le film a été mis à jour avec succès.");
        // Afficher les informations mises à jour du film
        System.out.println(film);
        //scanner.close(); // Attention : ne fermez pas le scanner si vous l'utilisez ailleurs dans l'application
    }

}

