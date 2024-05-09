// ----------------TEST CHARGEMENT DONNEES---------------- 
    	
    	// int a = 0;
    
    	// Charger tous les films
        /*System.out.println("Com chargés :");
        for (Film film : Data.filmsCache.values()) {
            System.out.println(film);
            System.out.println(a);
            a++;       
        }
    	// int i = 0;
        // Charger tous les utilisateurs
        /*System.out.println("Utilisateurs chargés :");
        for (Utilisateur utilisateur : Data.utilisateursCache.values()) {
            System.out.println(utilisateur);
            System.out.println(i);
            i++;
        }*/

        // Charger toutes les commandes et les associer à leurs utilisateurs
        //System.out.println("Commandes chargées et associées aux utilisateurs :");
        /*for (Utilisateur utilisateur : Data.utilisateursCache.values()) {
            System.out.println("Utilisateur: " + utilisateur.getNom() + ", id : " + utilisateur.getId());
            for (Commentaire c : Data.lireCommentairesUtilisateur(utilisateur.getId())) {
                System.out.println(c);
                System.out.println(a);
                a++;
            }
        }*/
        
        /*for (Film film : Data.filmsCache.values()) {
            System.out.println("Film: " + film.getTitre() + ", id : " + film.getCode());
            for (Commentaire c : Data.lireCommentairesFilm(film.getCode())) {
                System.out.println(c);
                System.out.println(a);
                a++;
            }
        }
        // Utilisateur utilisateur = Data.utilisateursCache.get("user001");       
        //System.out.println(utilisateur);
        // utilisateur.consulter_historique_achats();

    	
    	
    	
    	// ---------------------- TEST DES FONCTIONS ADMINISTRATEURS ----------------------------------------------
    	
    	// Producteur producteur = new Producteur("Christopher", "Nolan");
    	// Utilisateur utilisateur1 = new Utilisateur("Adam", "Halladja", "jnkne@example.com", "788 Main St", "X", "X", new Date(), "adam123");
    	// Film film1 = new Film("FILM006", "Le Prestige", 2000, true, "Description du film blablabla", 10.99f,producteur, Type_Film.Action);
    	// Administrateur a = new Administrateur("John", "Doe", "john.doe@example.com", "123 Main St", "userID1", "mdp123");
    	// a.supprimerUtilisateur(utilisateur1);
        // a.ajouterFilm(film1);
        // a.ajouterUtilisateur(utilisateur1);
    	// a.activer_com(film1);
    	// a.desactiver_com(film1);
    	// film1.afficher_com();
    	// a.supprimerFilm(film1);
    	// a.modifierFilm(film1);
    	
                
        // ---------------------- CREATTION DE SIMULATION -------------------

        // CrÃ©ation d'un producteur pour les films de test
        // Producteur producteur = new Producteur("Christopher", "Nolan");
 
        // CrÃ©ation de quelques films
        // Film film1 = new Film("FILM001", "Inception", 2010, true, "Un voleur qui infiltre les rÃªves", 10.99f, producteur, Type_Film.Action);
        // Film film2 = new Film("FILM002", "Interstellar", 2014, true, "Une aventure spatiale Ã©pique", 15.99f, producteur, Type_Film.Drame);
        
        // CrÃ©ation d'un utilisateur et ajout de films dans son panier
        // Utilisateur utilisateur = new Utilisateur("John", "Doe", "john.doe@example.com", "123 Main St", "userID1", "mdp123", new Date(), "Secret");
        // utilisateur.s_abonner();
        // utilisateur.ajouter_film_panier(film1);
        // utilisateur.ajouter_film_panier(film2);
 
        // ---------------------- SIMULATION INTERACTION UTILISATEUR ACHAT ---------------------- 
        
    	// System.out.println("Test de la methode choisir_film_achat()");
        // utilisateur.choisir_film_achat();
        // utilisateur.consulter_historique_achats(); 
        
        // --------------------------- TEST DE LA FONCTION FILTRE COMMENTAIRE ----------------
        // Ajout d'un commentaire1 au film1
        // String texte1 = "Le film ...1";
        // String texte2 = "Le film ...2";
        // String texte3 = "Le film ...3";
        // Commentaire c1 = utilisateur.commenter(texte1, film1);
        // Commentaire c2 = utilisateur.commenter(texte2, film1);
        // Commentaire c3 = utilisateur.commenter(texte3, film1);
        // utilisateur.modifier_com(c1, film1);
        // film1.afficher_com();
        // film1.afficher_com_filtre();
    	
    	// ---------------------- TEST DE LA FONCTION GENERER STAT D'ADMIN ----------------------
    	// Administrateur a = new Administrateur("John", "Doe", "john.doe@example.com", "123 Main St", "userID1", "mdp123");
    	// a.genererStatistiquesUtilisateurs();
    	// a.genererStatistiquesFilms();
    	// a.genererStatistiquesCommandes();
    	
    	// ---------------------- TEST DE LA FONCTION LireFilms DE LA CLASSE DATA ----------------------
    	// ArrayList<Film> listeFilms = Data.lireFilms("src/data/films.csv");
    	// for (Film film : listeFilms) {
    	    //System.out.println(film);
    	//}
    	
    	// ---------------------- TEST DES FONCTIONS DE LA CLASSE DATA ----------------------
    	//Utilisateur u = Data.getUtilisateurById("user001", "Projet_Java/src/data/utilisateurs.csv");
    	
    	// utilisateur1.afficherVitrine();
    	// System.out.println(utilisateur1);
    	
    	 /*ArrayList<Film> listeFilmsUtilisateur = Data.lireFilmsUtilisateur("user001", "src/data/commandes.csv", "src/data/films.csv");
    	 for (Film film : listeFilmsUtilisateur) {
     	    System.out.println(film);
     	}*/
    	//u.afficherVitrine();
    	
    	