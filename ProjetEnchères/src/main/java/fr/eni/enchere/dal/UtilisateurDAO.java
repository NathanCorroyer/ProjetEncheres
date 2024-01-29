package fr.eni.enchere.dal;

import java.sql.SQLException;

import fr.eni.enchere.bo.Utilisateur;

public interface UtilisateurDAO {
	void register(Utilisateur utilisateur) throws SQLException;
	
	void update(Utilisateur utilisateur) throws SQLException;
	
	void deleteByMail(String email ) throws SQLException;
	
	
}
