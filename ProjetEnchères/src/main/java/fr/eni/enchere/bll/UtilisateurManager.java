package fr.eni.enchere.bll;

<<<<<<< HEAD
import fr.eni.enchere.bo.Utilisateur;
=======
import java.sql.SQLException;

import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.dal.DAOFactory;
>>>>>>> branch 'main' of https://github.com/NathanCorroyer/ProjetEncheres.git
import fr.eni.enchere.dal.UtilisateurDAO;

public class UtilisateurManager {
	private static UtilisateurManager instance = null;
	private UtilisateurDAO utilisateurDAO;
	
	private UtilisateurManager(UtilisateurDAO utilisateurDAO) {
		this.utilisateurDAO = utilisateurDAO;
	}

	
	public static UtilisateurManager getInstance() {
		if (instance == null) {
<<<<<<< HEAD
			instance = new UtilisateurManager(UtilisateurDAO.getInstance());
=======
			instance = new UtilisateurManager(DAOFactory.getUtilisateurDAO());
>>>>>>> branch 'main' of https://github.com/NathanCorroyer/ProjetEncheres.git
		}
		return instance;
	}
	
<<<<<<< HEAD
	public Utilisateur login(String email, String password) {
		return utilisateurDAO.login(email,password);
		
		// TO DO METHODE UTILISATEURDAO POUR LOGIN
	}
=======

	public void deleteByMail(String email) throws SQLException {
		utilisateurDAO.deleteByMail(email);
	}
	
	public void update(Utilisateur u) throws SQLException {
		utilisateurDAO.update(u);
	}
	
	public void register (Utilisateur u) throws SQLException {
		utilisateurDAO.register(u);
	}
	
	public void login(Utilisateur u) {
		utilisateurDAO.login(u);
	}
}
>>>>>>> branch 'main' of https://github.com/NathanCorroyer/ProjetEncheres.git
