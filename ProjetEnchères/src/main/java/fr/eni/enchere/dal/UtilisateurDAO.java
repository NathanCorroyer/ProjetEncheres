package fr.eni.enchere.dal;

import java.sql.SQLException;
import java.util.List;

import fr.eni.enchere.bo.Utilisateur;

public interface UtilisateurDAO {
	void register(Utilisateur utilisateur) throws SQLException;
	
	void update(Utilisateur utilisateur) throws SQLException;
	
	void deleteByMail(String email ) throws SQLException;
	
	void deleteAll() throws SQLException;
	
	Utilisateur login(String email, String password) throws SQLException;
	
	List<Utilisateur> findAll() throws SQLException;

	Utilisateur selectByPseudo(String pseudo) throws SQLException;
	
}
