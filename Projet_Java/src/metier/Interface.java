package metier;

import java.util.Scanner; 
import java.util.InputMismatchException;


public class Interface {

    private Scanner scanner;
    private Utilisateur utilisateurCourant;
    private Administrateur administrateurCourant;

    public Interface() {
        this.scanner = new Scanner(System.in);
    }

    public void menu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bienvenue! Veuillez choisir votre mode de connexion:");
        System.out.println("1. Utilisateur");
        System.out.println("2. Administrateur");
        System.out.print("Entrez votre choix (1 ou 2): ");

        int choix = scanner.nextInt();

        switch (choix) {
            case 1:
                authentifierUtilisateur();
                break;
            case 2:
                authentifierAdministrateur();
                break;
            default:
                System.out.println("Choix invalide. Veuillez entrer 1 ou 2.");
                menu();  // R�affiche le menu si le choix est invalide
                break;
        }
    }

    private void authentifierUtilisateur() {
        System.out.println("Connexion utilisateur:");
        System.out.print("Entrez votre ID: ");
        String id = scanner.nextLine();
        System.out.print("Entrez votre mot de passe: ");
        String mdp = scanner.nextLine();

        Utilisateur utilisateur = Data.obtenirUtilisateurParIdetMdp(id, mdp);
        if (utilisateur != null) {
            utilisateurCourant = Data.utilisateursCache.get(id);
            System.out.println("Connexion r�ussie.");
            System.out.println("Bienvenue " + utilisateurCourant.getPrenom() + " " + utilisateurCourant.getNom() + " !");
            menuUtilisateur();
        } else {
            System.out.println("Utilisateur non trouv� ou mot de passe incorrect.");
        }
    }
    
    private void authentifierAdministrateur() {
        System.out.println("Connexion administrateur:");
        System.out.print("Entrez votre ID: ");
        String id = scanner.nextLine();
        System.out.print("Entrez votre mot de passe: ");
        String mdp = scanner.nextLine();

        Administrateur administrateur = Data.obtenirAdminParIdetMdp(id, mdp);
        if (administrateur != null) {
            administrateurCourant = administrateur;
            System.out.println("Connexion r�ussie.");
            System.out.println("Bienvenue " + administrateurCourant.getPrenom() + " " + administrateurCourant.getNom() + " !");
            menuAdministrateur();
        } else {
            System.out.println("Administrateur non trouv� ou mot de passe incorrect.");
        }
    }

    private void menuUtilisateur() {
        int choix;
        do {
            afficherMenuUtilisateur();
            choix = scanner.nextInt();
            scanner.nextLine(); // Nettoie le buffer d'entr�e
            gererChoixUtilisateur(choix);
        } while (choix != 13);
    }

    private void menuAdministrateur() {
        int choix;
        do {
            afficherMenuAdministrateur();
            choix = scanner.nextInt();
            scanner.nextLine(); // Nettoie le buffer d'entr�e
            gererChoixAdministrateur(choix);
        } while (choix != 11);
    }
    
    private void afficherMenuUtilisateur() {
        System.out.println("\nMenu Utilisateur:");
        System.out.println("1. Commenter un film");
        System.out.println("2. Noter un film");
        System.out.println("3. Afficher mes commentaires pour un film");
        System.out.println("4. Ajouter un film au panier");
        System.out.println("5. Acheter les films dans le panier");
        System.out.println("6. Consulter l'historique des achats avec un filtre");
        System.out.println("7. G�rer l'abonnement");
        System.out.println("8. Afficher ma vitrine");
        System.out.println("9. Afficher les informations d'un film");
        System.out.println("10. Afficher les commentaires d'un film avec un filtre");
        System.out.println("11. Afficher mes informations");
        System.out.println("12. Modifier mes commentaires");
        System.out.println("13. Quitter");
        System.out.print("Entrez votre choix: ");
    }
    
    private void afficherMenuAdministrateur() {
        System.out.println("\nMenu Administrateur:");
        System.out.println("1. Ajouter un film");
        System.out.println("2. Supprimer un film");
        System.out.println("3. Modifier un film");
        System.out.println("4. Ajouter un utilisateur");
        System.out.println("5. Supprimer un utilisateur");
        System.out.println("6. Activer les commentaires d'un film");
        System.out.println("7. D�sactiver les commentaires d'un film");
        System.out.println("8. G�n�rer des statistiques sur les utilisateurs");
        System.out.println("9. G�n�rer des statistiques sur les ventes");
        System.out.println("10. G�n�rer des statistiques sur les films");
        System.out.println("11. Quitter");
        System.out.print("Entrez votre choix: ");
    }

    private void gererChoixUtilisateur(int choix) {
        switch (choix) {
            case 1:
                System.out.print("Entrez le titre du film que vous souhaitez commenter: ");
                String titreCommenter = scanner.nextLine();
                Film filmCommenter = Data.obtenirFilmParNom(titreCommenter);
                if (filmCommenter != null) {
                    System.out.print("Entrez votre commentaire pour le film " + titreCommenter + ": ");
                    String texte = scanner.nextLine();
                    Commentaire c = utilisateurCourant.commenter(texte, filmCommenter);
                } else {
                    System.out.println("Film non trouv�.");
                }
                break;
            case 2:
                System.out.print("Entrez le titre du film que vous souhaitez noter: ");
                String titreNoter = scanner.nextLine();
                Film filmNoter = Data.obtenirFilmParNom(titreNoter);
                if (filmNoter != null) {
                    System.out.print("Entrez la note pour le film " + titreNoter + " (0-10): ");
                    float note = scanner.nextFloat();
                    scanner.nextLine();  // Consomme le retour � la ligne                    
                    if (note >= 0 && note <= 10) {
                        utilisateurCourant.noter(filmNoter, note);
                        System.out.println("Votre note a �t� ajout�e.");
                        filmNoter.afficher_note_moy();
                    } else {
                        System.out.println("Note invalide. Veuillez saisir une note entre 0 et 10.");
                    }
                } else {
                    System.out.println("Film non trouv�.");
                }

                break;
            case 3:
                System.out.print("Entrez le titre du film dont vous souhaitez afficher les commentaires: ");
                String titreAfficherCom = scanner.nextLine();
                Film filmAfficherCom = Data.obtenirFilmParNom(titreAfficherCom);
                if (filmAfficherCom != null) {
                    utilisateurCourant.afficher_com(filmAfficherCom);
                } else {
                    System.out.println("Film non trouv�.");
                }
                break;
            case 4:
                System.out.print("Entrez le titre du film � ajouter � votre panier: ");
                String titreAjouterPanier = scanner.nextLine();
                Film filmAjouterPanier = Data.obtenirFilmParNom(titreAjouterPanier);
                if (filmAjouterPanier != null) {
                    utilisateurCourant.ajouter_film_panier(filmAjouterPanier);
                    System.out.println("Film ajout� � votre panier.");
                } else {
                    System.out.println("Film non trouv�.");
                }
                break;
            case 5:
                utilisateurCourant.choisir_film_achat();
                break;
            case 6:
                utilisateurCourant.consulter_historique_achats_filtres();
                break;
            case 7:
                System.out.println("Voulez-vous vous abonner ou vous d�sabonner? (abonner/d�sabonner): ");
                String action = scanner.nextLine();
                if ("abonner".equalsIgnoreCase(action)) {
                    utilisateurCourant.s_abonner();
                } else if ("d�sabonner".equalsIgnoreCase(action)) {
                    utilisateurCourant.se_desabonner();
                } else {
                    System.out.println("Action invalide. Veuillez taper 'abonner' ou 'd�sabonner'.");
                }
                break;
            case 8:
                utilisateurCourant.afficherVitrine();
                break;
            case 9:
            	System.out.print("Entrez le titre du film dont vous souhaiter conna�tre les informations: ");
                String titreInfo = scanner.nextLine();
                Film filmInfo = Data.obtenirFilmParNom(titreInfo);
                if (filmInfo != null) {
                    System.out.println(filmInfo);
                } else {
                    System.out.println("Film non trouv�.");
                }
                break;
            case 10:
            	System.out.print("Entrez le titre du film dont vous souhaitez afficher les commentaires filtr�s: ");
                String titreAfficherComFiltre = scanner.nextLine();
                Film filmAfficherComFiltre = Data.obtenirFilmParNom(titreAfficherComFiltre);
                if (filmAfficherComFiltre != null) {
                    utilisateurCourant.afficher_com_filtre(filmAfficherComFiltre);
                } else {
                    System.out.println("Film non trouv�.");
                }
                break;
            case 11:
                System.out.println("---------------- Mes Infos ----------------");
                System.out.println(utilisateurCourant);
                break;
            case 12:
                if (!utilisateurCourant.hasCommentaires()) {  // V�rifie si l'utilisateur a des commentaires
                    System.out.println("Vous n'avez aucun commentaire � modifier.");
                } else {
                    utilisateurCourant.afficherMesCommentaires();  // Affiche les commentaires avec index
                    System.out.print("Entrez le num�ro du commentaire que vous souhaitez modifier : ");
                    try {
                        int choixCommentaire = scanner.nextInt();
                        scanner.nextLine();  // Nettoie le buffer
                        System.out.print("Entrez le nouveau texte pour le commentaire : ");
                        String nouveauTexte = scanner.nextLine();
                        utilisateurCourant.modifierCommentaire(choixCommentaire - 1, nouveauTexte);
                        System.out.println("Commentaire modifi� avec succ�s.");
                    } catch (InputMismatchException e) {
                        System.out.println("Veuillez entrer un num�ro valide.");
                        scanner.nextLine();  // Nettoie le buffer s'il y a eu une erreur
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Num�ro de commentaire invalide.");
                    }
                }
                break;
            case 13:
                System.out.println("D�connexion.");
                break;
            default:
                System.out.println("Choix invalide. Veuillez r�essayer.");
                break;
        }
    }

    private void gererChoixAdministrateur(int choix) {
        switch (choix) {
            case 1:
                Film filmNouveau = administrateurCourant.creerFilm();
                administrateurCourant.ajouterFilm(filmNouveau);
                break;
            case 2:
                System.out.print("Entrez le titre du film � supprimer: ");
                String titreSupprimer = scanner.nextLine();
                Film filmSupprimer = Data.obtenirFilmParNom(titreSupprimer);
                if(filmSupprimer == null) {
                    System.out.print("Aucun film avec le titre '" + titreSupprimer + "' trouv�.\n");
                } else {
                    administrateurCourant.supprimerFilm(filmSupprimer);
                }
                break;
            case 3:
                System.out.print("Entrez le titre du film � modifier: ");
                String titreModifier = scanner.nextLine();
                Film filmModifier = Data.obtenirFilmParNom(titreModifier);
                if(filmModifier == null) {
                    System.out.print("Aucun film avec le titre '" + titreModifier + "' trouv�.\n");
                } else {
                    administrateurCourant.modifierFilm(filmModifier);
                }
                break;
            case 4:
                Utilisateur utilisateurNouveau = administrateurCourant.creerUtilisateur();
                System.out.println("Utilisateur ajout� avec succ�s.");
                administrateurCourant.ajouterUtilisateur(utilisateurNouveau);
                break;
            case 5:
                System.out.print("Entrez l'id de l'utilisateur � supprimer: ");
                String idSupprimer = scanner.nextLine();
                Utilisateur utilisateurSupprimer = Data.obtenirUtilisateurParId(idSupprimer);
                if(utilisateurSupprimer == null) {
                    System.out.print("Aucun utilisateur avec l'id " + idSupprimer + " trouv�.\n");
                } else {
                    administrateurCourant.supprimerUtilisateur(utilisateurSupprimer);
                }
                break;
            case 6:
                System.out.print("Pour quel film souhaitez-vous activer les commentaires ? ");
                String titreComm = scanner.nextLine();
                Film filmComm = Data.obtenirFilmParNom(titreComm);
                if(filmComm == null) {
                    System.out.print("Aucun film avec le titre '" + titreComm + "' trouv�.\n");
                } else {
                    administrateurCourant.activer_com(filmComm);
                }
                break;
            case 7:
                System.out.print("Pour quel film souhaitez-vous d�sactiver les commentaires ? ");
                String titreCommD = scanner.nextLine();
                Film filmCommD = Data.obtenirFilmParNom(titreCommD);
                if(filmCommD == null) {
                    System.out.print("Aucun film avec le titre '" + titreCommD + "' trouv�.\n");
                } else {
                    administrateurCourant.desactiver_com(filmCommD);
                }
                break;

            case 8:
            	administrateurCourant.genererStatistiquesUtilisateurs();
                break;
            case 9:
            	administrateurCourant.genererStatistiquesCommandes();
                break;
            case 10:
            	administrateurCourant.genererStatistiquesFilms();
                break;
            case 11:
                System.out.println("D�connexion.");
                break;
            default:
                System.out.println("Choix invalide. Veuillez r�essayer.");
                break;
        }
    }

    




}
