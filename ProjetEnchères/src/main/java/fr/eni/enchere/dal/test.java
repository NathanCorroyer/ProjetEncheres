package fr.eni.enchere.dal;

import java.sql.SQLException;

import fr.eni.enchere.bo.Utilisateur;

public class test {
	public static void main(String[] args) {
		Utilisateur Kevin = new Utilisateur("sasukeke", "narutoxique", "sakura", "darksasuke@exemple.com","0222222222" , "KOUKOU", "05205", "Konoha", "glacevanillefraise");
		UtilisateurDAO userDAO = DAOFactory.getUtilisateurDAO();
		try {
			userDAO.register(Kevin);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
