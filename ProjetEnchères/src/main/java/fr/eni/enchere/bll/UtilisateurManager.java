package fr.eni.enchere.bll;

import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.dal.UtilisateurDAO;

public class UtilisateurManager {
	private static UtilisateurManager instance = null;
	private UtilisateurDAO utilisateurDAO;
	
	public static UtilisateurManager getInstance() {
		if (instance == null) {
			instance = new UtilisateurManager(UtilisateurDAO.getInstance());
		}
		return instance;
	}
	
	public Utilisateur login(String email, String password) {
		return utilisateurDAO.login(email,password);
		
		// TO DO METHODE UTILISATEURDAO POUR LOGIN
	}
