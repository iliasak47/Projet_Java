package application;

import metier.*; 
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class Test {
    public static void main(String[] args) {
    	
    	
    	
    	//------------------LECTURE DES DONNEES---------------
    	
    	Data.chargerTousLesFilms();
        Data.chargerTousLesUtilisateurs();
        Data.chargerToutesLesCommandes();
        Data.chargerTousLesCommentaires();
    	
    
    	// ----------------MENU-----------------------    	
        
    	Interface i = new Interface();
    	i.menu();
	    	
    }
}