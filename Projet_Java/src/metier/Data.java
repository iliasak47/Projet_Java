package metier;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;
import java.util.List;


public class Data {
	
	public static Film getFilmById(String filmId, String cheminFichierFilms) {
	    try (BufferedReader br = new BufferedReader(new FileReader(cheminFichierFilms))) {
	        String line;
	        while ((line = br.readLine()) != null) {
	            String[] filmData = line.split(",");
	            if (filmData[0].trim().equals(filmId)) {
	                // Créez votre objet Film ici en utilisant les données récupérées
	                String code = filmData[0].trim();
	                String titre = filmData[1].trim();
	                int anneeProd = Integer.parseInt(filmData[2].trim());
	                boolean comActif = Boolean.parseBoolean(filmData[3].trim());
	                String description = filmData[4].trim();
	                float prix = Float.parseFloat(filmData[5].trim());
	                float noteMoy = Float.parseFloat(filmData[6].trim());
	                String producteurNom = filmData[7].trim();
	                String producteurPrenom = filmData[8].trim();
	                Type_Film type = Type_Film.valueOf(filmData[9].trim());
	                
	                Producteur producteur = new Producteur(producteurNom, producteurPrenom);
	                Film film = new Film(code, titre, anneeProd, comActif, description, prix, producteur, type);
	                film.setNote_moy(noteMoy);
	                return film;  // Retournez l'instance du film
	            }
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
	        System.out.println("Erreur de format dans le fichier CSV.");
	    }
	    return null;  // Si le film n'est pas trouvé ou en cas d'erreur
	}

    public static ArrayList<Film> lireFilms(String cheminFichier) {
        ArrayList<Film> films = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(cheminFichier))) {
            String ligne;
            // Sauter l'en-tête du fichier CSV
            br.readLine();

            while ((ligne = br.readLine()) != null) {
                String[] detailsFilm = ligne.split(",");
                // Créer un nouveau film avec les détails et l'ajouter à la liste
                // Assurez-vous que l'ordre et le type des données correspondent à ceux attendus par le constructeur de votre classe Film
                Film film = new Film(
                    detailsFilm[0], // code
                    detailsFilm[1], // titre
                    Integer.parseInt(detailsFilm[2]), // anneeProd
                    Boolean.parseBoolean(detailsFilm[3]), // comActif
                    detailsFilm[4], // description
                    Float.parseFloat(detailsFilm[5]), // prix
                    new Producteur(detailsFilm[7], detailsFilm[8]), // producteur
                    Type_Film.valueOf(detailsFilm[9]) // type
                );
                film.setNote_moy(Float.parseFloat(detailsFilm[6])); // noteMoy
                films.add(film);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return films;
    }
    
    public static Utilisateur getUtilisateurById(String utilisateurId, String cheminFichierUtilisateurs) {
        try (BufferedReader br = new BufferedReader(new FileReader(cheminFichierUtilisateurs))) {
            String line;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            
            while ((line = br.readLine()) != null) {
                String[] utilisateurData = line.split(",");
                if (utilisateurData[4].trim().equals(utilisateurId)) {
                    // Extraction des données
                    String nom = utilisateurData[0].trim();
                    String prenom = utilisateurData[1].trim();
                    String email = utilisateurData[2].trim();
                    String adresse = utilisateurData[3].trim();
                    String id = utilisateurData[4].trim();
                    String motDePasse = utilisateurData[5].trim();
                    Date dateNaissance = sdf.parse(utilisateurData[6].trim());
                    String phraseSecrete = utilisateurData[7].trim();
                    boolean abonne = Boolean.parseBoolean(utilisateurData[8].trim());
                    // Supposons que les ID des commandes sont séparés par des "|"
                    String[] idCommandes = utilisateurData[9].trim().split("\\|");

                    // Création de l'utilisateur
                    Utilisateur utilisateur = new Utilisateur(nom, prenom, email, "", id, motDePasse, dateNaissance, phraseSecrete);
                    utilisateur.setAbonne(abonne);
                    // Vous pouvez ajouter ici la logique pour gérer les commandes de l'utilisateur si nécessaire
                    
                    return utilisateur;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            System.out.println("Erreur de format de date dans le fichier CSV.");
        }
        return null; // Si l'utilisateur n'est pas trouvé ou en cas d'erreur
    }
    
    public static ArrayList<Commande> lireCommandesUtilisateur(String cheminFichier, String idUtilisateur) {
        ArrayList<Commande> commandesUtilisateur = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        try (BufferedReader br = new BufferedReader(new FileReader(cheminFichier))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data[1].equals(idUtilisateur)) {
                    Date date = dateFormat.parse(data[2]);
                    float montant = Float.parseFloat(data[3]);
                    String[] filmsIds = data[4].split("\\|");
                    
                    Utilisateur utilisateur = getUtilisateurById(idUtilisateur, cheminFichier);
                    Commande commande = new Commande(date, utilisateur);
                    commande.setMontant(montant); // Supposons que vous ayez un setter pour le montant

                    for (String filmId : filmsIds) {
                        Film film = getFilmById(filmId, cheminFichier);
                        if (film != null) {
                            commande.ajouter_Film(film);
                        }
                    }

                    commandesUtilisateur.add(commande);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return commandesUtilisateur;
    }
    
    public static ArrayList<Film> lireFilmsUtilisateur(String idUtilisateur, String cheminFichierCommandes, String cheminFichierFilms) {
        ArrayList<Film> filmsUtilisateur = new ArrayList<>();
        Set<String> codesFilms = new HashSet<>(); // Pour éviter les doublons

        // Lire les commandes et accumuler les codes des films
        try (BufferedReader br = new BufferedReader(new FileReader(cheminFichierCommandes))) {
            String line;
            
            while ((line = br.readLine()) != null) {
                String[] commandeData = line.split(";");
                if (commandeData[1].trim().equals(idUtilisateur)) {
                    String[] filmsAchetes = commandeData[4].trim().split("\\|");
                    codesFilms.addAll(Arrays.asList(filmsAchetes));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return filmsUtilisateur; // Retourne la liste vide en cas d'erreur
        }

        // Lire les films et ajouter ceux qui ont été achetés par l'utilisateur
        try (BufferedReader brFilms = new BufferedReader(new FileReader(cheminFichierFilms))) {
            String lineFilm;

            while ((lineFilm = brFilms.readLine()) != null) {
                String[] filmData = lineFilm.split(",");
                if (codesFilms.contains(filmData[0].trim())) {
                    // Création de l'objet Film en supposant un constructeur avec ces paramètres
                    Film film = new Film(
                            filmData[0].trim(), // code
                            filmData[1].trim(), // titre
                            Integer.parseInt(filmData[2].trim()), // anneeProd
                            Boolean.parseBoolean(filmData[3].trim()), // comActif
                            filmData[4].trim(), // description
                            Float.parseFloat(filmData[5].trim()), // prix
                            new Producteur(filmData[7].trim(), filmData[8].trim()), // producteur
                            Type_Film.valueOf(filmData[9].trim()) // type
                    );
                    film.setNote_moy(Float.parseFloat(filmData[6].trim())); // noteMoy
                    filmsUtilisateur.add(film);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return filmsUtilisateur; // Retourne la liste des films
    }

    
    

}

