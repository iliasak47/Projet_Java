package metier;

import java.io.BufferedReader; 
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Map;
import java.util.Date;


public class Data {
    private static final String CHEMIN_FICHIERS_FILMS = "Projet_Java/src/data/films.csv";
    private static final String CHEMIN_FICHIERS_UTILISATEURS = "Projet_Java/src/data/utilisateurs.csv";
    private static final String CHEMIN_FICHIERS_ADMINISTRATEUR = "Projet_Java/src/data/administrateurs.csv";
    private static final String CHEMIN_FICHIERS_COMMANDES = "Projet_Java/src/data/commandes.csv";
    private static final String CHEMIN_FICHIERS_COMMENTAIRES = "Projet_Java/src/data/commentaires.csv";

    public static Map<String, Film> filmsCache = new HashMap<>(); // Cache pour stocker les films
    public static Map<String, Utilisateur> utilisateursCache = new HashMap<>();
    public static Map<String, String> titresCache = new HashMap<>(); // Permet de faire correspondance entre titre du film et code pour recherche par titre



    // ---------------------- FILMS ----------------------
   
    public static Film obtenirFilmParNom(String filmNom) {
        // Vérifiez si le titre est présent dans le cache titresCache
        String codeFilm = titresCache.get(filmNom.trim());
        if (codeFilm != null) {
            return filmsCache.get(codeFilm);  // Récupérez le film à partir du cache des codes
        } else {
            System.out.println("Aucun film trouvé avec le titre : " + filmNom);
            return null;
        }
    }
    
    public static Film obtenirFilmParId(String filmId) {
        if (filmsCache.containsKey(filmId)) {
            return filmsCache.get(filmId); // Retourne le film du cache s'il existe déjà
        }

        try (BufferedReader br = new BufferedReader(new FileReader(CHEMIN_FICHIERS_FILMS))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] filmData = line.split(",");
                if (filmData[0].trim().equals(filmId)) {
                    Film film = creerFilmDepuisDonnees(filmData);
                    filmsCache.put(film.getCode(), film); // Stocke le film dans le cache
                    return film;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null; // Si le film n'est pas trouvé ou en cas d'erreur
    }

    private static Film creerFilmDepuisDonnees(String[] filmData) {
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
        Note note = new Note(noteMoy, film, null); // sert à ajouter la note au film, pas besoin d'un utilisateur
        film.ajouter_note(note);
        return film;
    }

    public static ArrayList<Film> lireFilms() {
        ArrayList<Film> films = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(CHEMIN_FICHIERS_FILMS))) {
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

    public static ArrayList<Film> lireFilmsUtilisateur(String idUtilisateur) {
        ArrayList<Film> filmsUtilisateur = new ArrayList<>();
        Set<String> codesFilms = new HashSet<>(); // Pour éviter les doublons

        // Lire les commandes et accumuler les codes des films
        try (BufferedReader br = new BufferedReader(new FileReader(CHEMIN_FICHIERS_COMMANDES))) {
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
        try (BufferedReader brFilms = new BufferedReader(new FileReader(CHEMIN_FICHIERS_FILMS))) {
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

    public static void chargerTousLesFilms() {
        try (BufferedReader br = new BufferedReader(new FileReader(CHEMIN_FICHIERS_FILMS))) {
            String ligne;
            br.readLine();  // Ignorer l'en-tête
            while ((ligne = br.readLine()) != null) {
                String[] detailsFilm = ligne.split(",");
                Film film = creerFilmDepuisDonnees(detailsFilm);
                filmsCache.put(film.getCode(), film);  // Stocker dans le cache des codes
                titresCache.put(film.getTitre(), film.getCode());  // Stocker dans le cache des titres
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // ---------------------- UTILISATEURS ----------------------
    
    private static Utilisateur creerUtilisateurDepuisDonnees(String[] utilisateurData) {
        if (utilisateurData.length < 9) {
            System.out.println("Données d'utilisateur incomplètes : " + Arrays.toString(utilisateurData));
            return null;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            String nom = utilisateurData[0].trim();
            String prenom = utilisateurData[1].trim();
            String email = utilisateurData[2].trim();
            String adresse = utilisateurData[3].trim();
            String id = utilisateurData[4].trim();
            String motDePasse = utilisateurData[5].trim();
            Date dateNaissance = sdf.parse(utilisateurData[6].trim());
            String phraseSecrete = utilisateurData[7].trim();
            boolean abonne = Boolean.parseBoolean(utilisateurData[8].trim());

            Utilisateur utilisateur = new Utilisateur(nom, prenom, email, adresse, id, motDePasse, dateNaissance, phraseSecrete);
            utilisateur.setAbonne(abonne);

            return utilisateur;
        } catch (ParseException e) {
            System.out.println("Erreur de format de date pour l'utilisateur : " + Arrays.toString(utilisateurData));
            return null;
        }
    }

    public static Utilisateur obtenirUtilisateurParId(String utilisateurId) {
        try (BufferedReader br = new BufferedReader(new FileReader(CHEMIN_FICHIERS_UTILISATEURS))) {
            String line;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            
            while ((line = br.readLine()) != null) {
                String[] utilisateurData = line.split(",");
                if (utilisateurData[4].trim().equals(utilisateurId)) {
                    Date dateNaissance = sdf.parse(utilisateurData[6].trim());
                    Utilisateur utilisateur = new Utilisateur(
                        utilisateurData[0].trim(),
                        utilisateurData[1].trim(),
                        utilisateurData[2].trim(),
                        utilisateurData[3].trim(),
                        utilisateurId,
                        utilisateurData[5].trim(),
                        dateNaissance,
                        utilisateurData[7].trim()
                    );
                    utilisateur.setAbonne(Boolean.parseBoolean(utilisateurData[8].trim()));
                    return utilisateur;
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return null; // Si l'utilisateur n'est pas trouvé ou en cas d'erreur
    }
    
    public static Utilisateur obtenirUtilisateurParIdetMdp(String id, String mdp) {
        File fichierUtilisateurs = new File(CHEMIN_FICHIERS_UTILISATEURS);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try (BufferedReader br = new BufferedReader(new FileReader(fichierUtilisateurs))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data[4].equals(id) && data[5].equals(mdp)) {
                    Date dateNaissance = dateFormat.parse(data[6]);
                    Utilisateur utilisateur = new Utilisateur(data[0], data[1], data[2], data[3], id, mdp, dateNaissance, data[7]);
                    utilisateur.setAbonne(Boolean.parseBoolean(data[8]));
                    return utilisateur;
                }
            }
        } catch (IOException | ParseException e) {
            System.out.println("Erreur lors de la vérification des identifiants: " + e.getMessage());
        }
        return null;
    }
    
    public static void chargerTousLesUtilisateurs() {
        try (BufferedReader br = new BufferedReader(new FileReader(CHEMIN_FICHIERS_UTILISATEURS))) {
            String ligne;
            br.readLine(); // Ignorer l'en-tête
            while ((ligne = br.readLine()) != null) {
                String[] detailsUtilisateur = ligne.split(",");
                if (detailsUtilisateur.length >= 9) {
                    Utilisateur utilisateur = creerUtilisateurDepuisDonnees(detailsUtilisateur);
                    if (utilisateur != null) {
                        utilisateursCache.put(utilisateur.getId(), utilisateur);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

   // ---------------------- COMMANDES ----------------------
    
    public static ArrayList<Commande> lireCommandesUtilisateur(String idUtilisateur) {
        ArrayList<Commande> commandesUtilisateur = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        try (BufferedReader br = new BufferedReader(new FileReader(CHEMIN_FICHIERS_COMMANDES))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(";");
                if (data.length < 5) {
                    System.out.println("Données insuffisantes dans la ligne: " + line);
                    continue;
                }
                if (data[1].trim().equals(idUtilisateur)) {
                    Date date = dateFormat.parse(data[2]);
                    float montant = Float.parseFloat(data[3]);
                    String[] filmsIds = data[4].split("\\|");
                    
                    Utilisateur utilisateur = obtenirUtilisateurParId(idUtilisateur);
                    if (utilisateur == null) {
                        System.out.println("Utilisateur non trouvé pour l'ID: " + idUtilisateur);
                        continue;
                    }
                    Commande commande = new Commande(date, utilisateur);
                    commande.setMontant(montant);

                    for (String filmId : filmsIds) {
                        Film film = obtenirFilmParId(filmId);
                        if (film != null) {
                            commande.ajouter_Film(film);
                        }
                    }

                    commandesUtilisateur.add(commande);
                }
            }
        } catch (ParseException e) {
            System.out.println("Erreur de parsing de date");
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Erreur de format numérique");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Erreur de lecture du fichier");
            e.printStackTrace();
        }

        return commandesUtilisateur;
    }

    private static Commande creerCommandeDepuisDonnees(String[] data) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date date = dateFormat.parse(data[2]); // Tente de parser la date
            float montant = Float.parseFloat(data[3]); // Tente de parser le montant
            Utilisateur utilisateur = utilisateursCache.get(data[1]); // Obtient l'utilisateur correspondant
            
            if (utilisateur == null) {
                System.out.println("Utilisateur non trouvé pour la commande avec ID: " + data[1]);
                return null; // Retourne null si aucun utilisateur correspondant n'est trouvé
            }

            Commande commande = new Commande(date, utilisateur);
            commande.setMontant(montant);

            // Parcourir les IDs de films et les ajouter à la commande si disponibles
            String[] filmIds = data[4].split("\\|");
            for (String filmId : filmIds) {
                Film film = filmsCache.get(filmId.trim());
                if (film != null) {
                    commande.ajouter_Film(film);
                } else {
                    System.out.println("Film non trouvé pour ID: " + filmId + " lors de la création de la commande.");
                }
            }

            return commande;
        } catch (ParseException e) {
            System.out.println("Erreur de format de date pour la commande: " + Arrays.toString(data));
            e.printStackTrace();
            return null;
        } catch (NumberFormatException e) {
            System.out.println("Erreur de format de nombre (montant ou ID film) pour la commande: " + Arrays.toString(data));
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            System.out.println("Erreur inattendue lors de la création de la commande: " + Arrays.toString(data));
            e.printStackTrace();
            return null;
        }
    }

    public static void chargerToutesLesCommandes() {
        try (BufferedReader br = new BufferedReader(new FileReader(CHEMIN_FICHIERS_COMMANDES))) {
            String ligne;
            br.readLine(); // Ignorer l'en-tête
            while ((ligne = br.readLine()) != null) {
                String[] detailsCommande = ligne.split(";");
                if (detailsCommande.length > 4) {
                    Utilisateur utilisateur = utilisateursCache.get(detailsCommande[1]);
                    if (utilisateur != null) {
                        Commande commande = creerCommandeDepuisDonnees(detailsCommande);
                        utilisateur.ajouter_achat(commande);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // ---------------------- COMMENTAIRES ----------------------

    public static ArrayList<Commentaire> lireCommentairesUtilisateur(String idUtilisateur) {
        ArrayList<Commentaire> commentairesUtilisateur = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        try (BufferedReader br = new BufferedReader(new FileReader(CHEMIN_FICHIERS_COMMENTAIRES))) {
            String line;
            br.readLine(); // Ignorer l'en-tête
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length < 5) {
                    System.out.println("Données insuffisantes dans la ligne: " + line);
                    continue;
                }
                if (data[3].trim().equals(idUtilisateur)) {
                    Date date = dateFormat.parse(data[2]);
                    Utilisateur utilisateur = utilisateursCache.get(idUtilisateur);
                    Film film = filmsCache.get(data[4].trim());
                    if (utilisateur == null || film == null) {
                        System.out.println("Utilisateur ou film non trouvé pour le commentaire.");
                        continue;
                    }
                    Commentaire commentaire = new Commentaire(data[1], date, utilisateur, film);
                    commentairesUtilisateur.add(commentaire);
                }
            }
        } catch (ParseException e) {
            System.out.println("Erreur de parsing de date");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Erreur de lecture du fichier");
            e.printStackTrace();
        }

        return commentairesUtilisateur;
    }
    
    public static ArrayList<Commentaire> lireCommentairesFilm(String idFilm) {
        ArrayList<Commentaire> commentairesFilm = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        try (BufferedReader br = new BufferedReader(new FileReader(CHEMIN_FICHIERS_COMMENTAIRES))) {
            String line;
            br.readLine(); // Ignorer l'en-tête
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length < 5) {
                    System.out.println("Données insuffisantes dans la ligne: " + line);
                    continue;
                }
                if (data[4].trim().equals(idFilm)) {
                    Date date = dateFormat.parse(data[2]);
                    Utilisateur utilisateur = utilisateursCache.get(data[3].trim());
                    Film film = filmsCache.get(idFilm);
                    if (utilisateur == null || film == null) {
                        System.out.println("Utilisateur ou film non trouvé pour le commentaire.");
                        continue;
                    }
                    Commentaire commentaire = new Commentaire(data[1], date, utilisateur, film);
                    commentairesFilm.add(commentaire);
                }
            }
        } catch (ParseException e) {
            System.out.println("Erreur de parsing de date");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Erreur de lecture du fichier");
            e.printStackTrace();
        }

        return commentairesFilm;
    }
    
    private static Commentaire creerCommentaireDepuisDonnees(String[] data) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date date = dateFormat.parse(data[2]);
            Utilisateur utilisateur = utilisateursCache.get(data[3].trim());
            Film film = filmsCache.get(data[4].trim());
            
            if (utilisateur == null || film == null) {
                System.out.println("Utilisateur ou film non trouvé pour le commentaire.");
                return null;
            }

            Commentaire commentaire = new Commentaire(data[1], date, utilisateur, film);
            return commentaire;
        } catch (ParseException e) {
            System.out.println("Erreur de format de date pour le commentaire: " + Arrays.toString(data));
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            System.out.println("Erreur inattendue lors de la création du commentaire: " + Arrays.toString(data));
            e.printStackTrace();
            return null;
        }
    }

    public static void chargerTousLesCommentaires() {
        try (BufferedReader br = new BufferedReader(new FileReader(CHEMIN_FICHIERS_COMMENTAIRES))) {
            String ligne;
            br.readLine(); // Ignorer l'en-tête
            while ((ligne = br.readLine()) != null) {
                String[] detailsCommentaire = ligne.split(",");
                if (detailsCommentaire.length > 4) {
                    Utilisateur utilisateur = utilisateursCache.get(detailsCommentaire[3].trim());
                    Film film = filmsCache.get(detailsCommentaire[4].trim());
                    if (utilisateur != null && film != null) {
                        Commentaire commentaire = creerCommentaireDepuisDonnees(detailsCommentaire);
                        utilisateur.ajouter_com(commentaire); 
                        film.ajouter_com(commentaire);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ---------------------- ADMINISTRATEUR ----------------------
    
    public static Administrateur obtenirAdminParIdetMdp(String id, String mdp) {
        File fichierAdmin = new File(CHEMIN_FICHIERS_ADMINISTRATEUR);

        try (BufferedReader br = new BufferedReader(new FileReader(fichierAdmin))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data[4].equals(id) && data[5].equals(mdp)) {
                    Administrateur administrateur = new Administrateur(data[0], data[1], data[2], data[3], id, mdp);
                    return administrateur;
                }
            }
        } catch (IOException e) {
            System.out.println("Erreur lors de la vérification des identifiants: " + e.getMessage());
        }
        return null;
    }
    
    
    
    
    
}
