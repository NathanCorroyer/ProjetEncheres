package fr.eni.enchere.dal;

public class DAOFactory {
	
	public static UtilisateurDAO getUtilisateurDAO() {
		int push = 0 ;
		return new UtilisateurDAOJdbcImpl();
		
	}
}
