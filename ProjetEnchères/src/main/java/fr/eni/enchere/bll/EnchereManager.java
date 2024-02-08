package fr.eni.enchere.bll;

import java.sql.SQLException;
import java.util.List;

import fr.eni.enchere.bo.Enchere;
import fr.eni.enchere.dal.DAOFactory;
import fr.eni.enchere.dal.EnchereDAO;

public class EnchereManager {
	private static EnchereManager instance = null;
	private EnchereDAO enchereDAO;
	
	
	
	public EnchereManager(EnchereDAO enchereDAO) {
		this.enchereDAO = enchereDAO;
	}


	public static EnchereManager getInstance() {
		if(instance == null) {
			instance = new EnchereManager(DAOFactory.getEnchereDAO()) ;
		}
		return instance;
	}
	
	public int ajouter(Enchere e) throws SQLException {
		return enchereDAO.ajouter(e);
	}
	
	public Enchere selectByNoEnchere(int no_enchere) throws SQLException {
		return enchereDAO.SelectByNoEnchere(no_enchere);
	}
	
	public List<Enchere> selectByNoArticle(int no_article){
		return enchereDAO.SelectByNoArticle(no_article);
	}
	
	public List<Enchere> selectByNoUtilisateur(int no_utilisateur){
		return enchereDAO.SelectByNoUtilisateur(no_utilisateur);
	}
	
	public Enchere selectLatestEnchereFromArticle(int no_article) {
		return enchereDAO.SelectLatestEnchereFromArticle(no_article);
		}
	
	public List<Enchere> selectByNoArticleTriDecroissant( int no_article ){
		return enchereDAO.selectByNoArticleTriDecroissant(no_article);
	}
}
