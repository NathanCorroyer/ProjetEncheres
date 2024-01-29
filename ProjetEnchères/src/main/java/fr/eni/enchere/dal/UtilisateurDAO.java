package fr.eni.enchere.dal;

import fr.eni.enchere.bo.Utilisateur;

public interface UtilisateurDAO {
	void insert(Utilisateur utilisateur);
	
	void update(Utilisateur utilisateur);
	
	void delete(Utilisateur utilisateur);
	
	
}
