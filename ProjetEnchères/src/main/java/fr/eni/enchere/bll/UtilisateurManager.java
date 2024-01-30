package fr.eni.enchere.bll;


import fr.eni.enchere.bo.Utilisateur;


import java.sql.SQLException;

import fr.eni.enchere.dal.DAOFactory;
import fr.eni.enchere.dal.UtilisateurDAO;

public class UtilisateurManager {
	private static UtilisateurManager instance = null;
	private UtilisateurDAO utilisateurDAO;
	
	private UtilisateurManager(UtilisateurDAO utilisateurDAO) {
		this.utilisateurDAO = utilisateurDAO;
	}

	
	public static UtilisateurManager getInstance() {
		if (instance == null) {
			instance = new UtilisateurManager(DAOFactory.getUtilisateurDAO());

		}
		return instance;
	}
	


	public void deleteByMail(String email) throws SQLException {
		utilisateurDAO.deleteByMail(email);
	}
	
	public void update(Utilisateur u) throws SQLException {
		utilisateurDAO.update(u);
	}
	
	public void register (Utilisateur u) throws SQLException {
		utilisateurDAO.register(u);
	}
	
	public Utilisateur login(String email, String mdp) {
		Utilisateur user = null;
		try {
			user = utilisateurDAO.login(email, mdp);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
}

