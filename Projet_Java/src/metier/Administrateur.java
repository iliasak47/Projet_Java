package metier;

import java.io.*; 
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.Scanner; 


public class Administrateur extends Personne {
	private static final String CHEMIN_FICHIERS_FILMS = "Projet_Java/src/data/films.csv";
    private static final String CHEMIN_FICHIERS_UTILISATEURS = "Projet_Java/src/data/utilisateurs.csv";
    private static final String CHEMIN_FICHIERS_COMMANDES = "Projet_Java/src/data/commandes.csv";
	
    Scanner scanner = new Scanner(System.in);

    // Constructor
    public Administrateur(String nom, String prenom, String mail, String adresse, String id, String mdp) {
        super(nom, prenom, mail, adresse, id, mdp); 
    }

	// Methodes
    public Film creerFilm() {

        System.out.print("Entrez le code du film: ");
        String code = scanner.nextLine();
        
        System.out.print("Entrez le titre du film: ");
        String titre = scanner.nextLine();
        
        System.out.print("Entrez l'année de production du film: ");
        int annee_prod = scanner.nextInt();
        scanner.nextLine();  // Consume the remaining newline
        
        System.out.print("Les commentaires sont-ils activés pour ce film? (oui/non): ");
        boolean com_actif = scanner.nextLine().equalsIgnoreCase("oui");
        
        System.out.print("Entrez la description du film: ");
        String description = scanner.nextLine();
        
        System.out.print("Entrez le prix du film: ");
        float prix = scanner.nextFloat();
        scanner.nextLine();  // Consume the remaining newline
        
        System.out.println("Entrez les informations du producteur.");
        System.out.print("Nom du producteur: ");
        String nomProd = scanner.nextLine();
        
        System.out.print("Prénom du producteur: ");
        String prenomProd = scanner.nextLine();
        
        Producteur producteur = new Producteur(nomProd, prenomProd);
        
        System.out.print("Entrez le type de film: ");
        String type = scanner.nextLine();
        
        Type_Film typeFilm = Type_Film.valueOf(type);  // Assuming Type_Film is an enum
        
        Film nouveauFilm = new Film(code, titre, annee_prod, com_actif, description, prix, producteur, typeFilm);
        
        return nouveauFilm;
        
    }
 
    public void ajouterFilm(Film film) {
        // Chemin vers le fichier films.csv dans le projet
        String cheminFichierFilms = CHEMIN_FICHIERS_FILMS; // Ajustez ce chemin si nï¿½cessaire
        
        try (FileWriter fw = new FileWriter(cheminFichierFilms, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {

            // Formatage des donnï¿½es du film pour le CSV
            String line = String.join(",",
                film.getCode(),
                film.getTitre(),
                String.valueOf(film.getAnnee_prod()),
                String.valueOf(film.isCom_actif()),
                film.getDescription(),
                String.format(Locale.US, "%.2f", film.getPrix()), // convertit string en float avec un point pour le décimale
                String.format(Locale.US, "%.1f", film.getNote_moy()),
                film.getProducteur().getNom(),
                film.getProducteur().getPrenom(),
                film.getType().toString()
            );

            // écriture dans le fichier CSV
            out.println(line);
            

            // Mise à jour du Cache
            Data.filmsCache.put(film.getCode(), film);

            System.out.println("Le film a été ajouté avec succès.");

        } catch (IOException e) {
            System.out.println("Une erreur est survenue lors de l'écriture dans le fichier.");
            e.printStackTrace();
        }
    }

    public void supprimerFilm(Film film) {
        String cheminFichierFilms = CHEMIN_FICHIERS_FILMS;
        File inputFile = new File(cheminFichierFilms);
        File tempFile = new File(inputFile.getAbsolutePath() + ".tmp");

        System.out.println("Début de la suppression du film...");

        if (!inputFile.exists()) {
            System.out.println("Le fichier n'existe pas : " + cheminFichierFilms);
            return;
        }

        boolean found = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                String trimmedLine = currentLine.trim();
                String[] data = trimmedLine.split(",");
                if (data.length < 2) {
                    System.out.println("Ligne mal formatée ou incorrecte dans le fichier CSV: " + trimmedLine);
                    continue;
                }
                if (data[0].equals(film.getCode())) {
                    found = true;
                    System.out.println("Film trouvé, suppression : " + trimmedLine);
                    continue;
                }
                writer.write(currentLine + System.getProperty("line.separator"));
            }

            if (!found) {
                System.out.println("Film avec le code " + film.getCode() + " n'a pas été trouvé dans le fichier.");
            }

        } catch (IOException e) {
            System.out.println("Une erreur est survenue lors de l'accès ou de la modification du fichier.");
            e.printStackTrace();
        } finally {
            try {
                if (found && inputFile.delete()) {
                    if (!tempFile.renameTo(inputFile)) {
                        System.out.println("Impossible de renommer le fichier temporaire.");
                    } else {
                        Data.filmsCache.remove(film.getCode()); // Mise à jour du cache
                        System.out.println("Le film a été supprimé avec succès.");
                    }
                } else {
                    System.out.println("Impossible de supprimer le fichier original.");
                }
            } finally {
                if (tempFile.exists()) {
                    tempFile.delete(); // Assurez-vous de supprimer le fichier temporaire
                }
            }
        }
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
        
        // Mise à jour du cache
        Data.filmsCache.put(film.getCode(), film);
        Data.titresCache.put(film.getTitre(), film.getCode());
    }

    public Utilisateur creerUtilisateur() {
        System.out.print("Entrez le nom de l'utilisateur : ");
        String nom = scanner.nextLine();

        System.out.print("Entrez le prénom de l'utilisateur : ");
        String prenom = scanner.nextLine();

        System.out.print("Entrez le mail de l'utilisateur : ");
        String mail = scanner.nextLine();

        System.out.print("Entrez l'adresse de l'utilisateur : ");
        String adresse = scanner.nextLine();

        System.out.print("Entrez l'identifiant de l'utilisateur : ");
        String id = scanner.nextLine();

        System.out.print("Entrez le mot de passe de l'utilisateur : ");
        String mdp = scanner.nextLine();

        System.out.print("Entrez la date de naissance de l'utilisateur (format jj/mm/aaaa) : ");
        Date date_naissance = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            date_naissance = sdf.parse(scanner.nextLine());
        } catch (Exception e) {
            System.out.println("Format de date invalide. Utilisez le format jj/mm/aaaa.");
            return null;  // Sortie anticipée si la date est invalide
        }

        System.out.print("Entrez la phrase secrète de l'utilisateur : ");
        String phrase_secrete = scanner.nextLine();

        Utilisateur nouvelUtilisateur = new Utilisateur(nom, prenom, mail, adresse, id, mdp, date_naissance, phrase_secrete);
        
        return nouvelUtilisateur;
    }
    
    public void ajouterUtilisateur(Utilisateur utilisateur) {
    	String cheminFichier = CHEMIN_FICHIERS_UTILISATEURS; // Ajustez ce chemin si nï¿½cessaire
        
        try (FileWriter fw = new FileWriter(cheminFichier, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {

        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = sdf.format(utilisateur.getDate_naissance()); // Formatter la date de naissance

            // Formatage des données de l'utilisateur pour le CSV
            String line = String.join(",",
                utilisateur.getNom(),
                utilisateur.getPrenom(),
                utilisateur.getMail(),
                utilisateur.getAdresse(),
                utilisateur.getId(),
                utilisateur.getMdp(),
                formattedDate, // Utilisation de la date formatée
                utilisateur.getPhrase_secrete(),
                String.valueOf(utilisateur.isAbonne())
            );

            // écriture dans le fichier CSV
            out.println(line);
            
            Data.utilisateursCache.put(utilisateur.getId(), utilisateur);
            System.out.println("L'utilisateur a été ajouté avec succès.");

        } catch (IOException e) {
            System.out.println("Une erreur est survenue lors de l'écriture dans le fichier.");
            e.printStackTrace();
        }
    }

    public void supprimerUtilisateur(Utilisateur utilisateur) {
        String cheminFichierUtilisateurs = CHEMIN_FICHIERS_UTILISATEURS;
        File inputFile = new File(cheminFichierUtilisateurs);
        File tempFile = new File(inputFile.getAbsolutePath() + ".tmp");

        System.out.println("Début de la suppression de l'utilisateur...");

        if (!inputFile.exists()) {
            System.out.println("Le fichier n'existe pas : " + cheminFichierUtilisateurs);
            return;
        }

        boolean found = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                String trimmedLine = currentLine.trim();
                String[] userDetails = trimmedLine.split(",");
                if (userDetails[4].equals(utilisateur.getId())) {
                    found = true;
                    System.out.println("Utilisateur trouvé, suppression : " + trimmedLine);
                    continue;
                }
                writer.write(currentLine + System.getProperty("line.separator"));
            }

        } catch (IOException e) {
            System.out.println("Une erreur est survenue lors de l'accès ou de la modification du fichier.");
            e.printStackTrace();
        } finally {
            try {
                if (found && inputFile.delete()) {
                    if (!tempFile.renameTo(inputFile)) {
                        System.out.println("Impossible de renommer le fichier temporaire.");
                    } else {
                        Data.utilisateursCache.remove(utilisateur.getId()); // Mise à jour du cache
                        System.out.println("L'utilisateur a été supprimé avec succès.");
                    }
                } else {
                    System.out.println("Impossible de supprimer le fichier original.");
                }
            } finally {
                if (tempFile.exists()) {
                    System.out.println("Suppression du fichier temporaire.");
                    tempFile.delete(); // Assurez-vous de supprimer le fichier temporaire
                }
            }
        }
    }

    	    
    public void activer_com(Film film) {	
    	if (film.isCom_actif()) {
    		System.out.println("Les commentaires sont déjà activés pour ce film");
    	}
    	else {
    		film.setCom_actif(true);
    		System.out.println("Les commentaires sont désormais activés pour ce film");
    	}
    }
    
    public void desactiver_com(Film film) {
    	if (film.isCom_actif()) {
    		film.setCom_actif(false);
    		System.out.println("Les commentaires sont désormais désactivés pour ce film");
    	}
    	else {
    		System.out.println("Les commentaires sont déjà désactivés pour ce film");
    	}
    }
    
    public void genererStatistiquesUtilisateurs() {
        String cheminFichier = CHEMIN_FICHIERS_UTILISATEURS;
        int nombreTotal = 0;
        int nombreAbonnes = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(cheminFichier))) {
            String ligne;
            reader.readLine(); // Ignorer les en-tÃªtes si votre fichier en a
            
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

        // Affichage formatÃ© des rÃ©sultats
        System.out.println("Statistiques des Utilisateurs:");
        System.out.println("Nombre total d'utilisateurs: " + nombreTotal);
        System.out.println(String.format("Nombre d'utilisateurs abonnés: %d (%.2f%%)", nombreAbonnes, pourcentageAbonnes));
        System.out.println(String.format("Nombre d'utilisateurs non abonnés: %d (%.2f%%)", nombreTotal - nombreAbonnes, pourcentageNonAbonnes));
    }
    
    public void genererStatistiquesFilms() {
        String cheminFichierFilms = CHEMIN_FICHIERS_FILMS;
        Map<String, Integer> filmsParType = new HashMap<>();
        float prixTotal = 0;
        float noteTotal = 0;
        int nombreFilms = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(cheminFichierFilms))) {
            String ligne;
            
            // Sauter la ligne d'en-tÃªte
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
        String cheminFichierCommandes = CHEMIN_FICHIERS_COMMANDES; // Assurez-vous que ce chemin est correct
        File fichierCommandes = new File(cheminFichierCommandes);
        if (!fichierCommandes.exists()) {
            System.out.println("Le fichier de commandes n'existe pas.");
            return;
        }

        int nombreCommandes = 0;
        float totalMontant = 0f;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try (BufferedReader reader = new BufferedReader(new FileReader(fichierCommandes))) {
            String line = reader.readLine(); // Lire l'en-tÃªte si nÃ©cessaire, sinon commenter cette ligne
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(";");
                if (data.length > 1) {
                    float montant = Float.parseFloat(data[3]); // Assumer que le montant est Ã  l'index 1
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
}

