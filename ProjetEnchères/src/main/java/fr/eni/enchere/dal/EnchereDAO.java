package fr.eni.enchere.dal;

import java.sql.SQLException;
import java.util.List;

import fr.eni.enchere.bo.Enchere;

public interface EnchereDAO {
	Integer ajouter(Enchere e) throws SQLException; 
	Enchere SelectByNoEnchere(int no_enchere) throws SQLException;
	List<Enchere> SelectByNoArticle(int no_article);
	List<Enchere> SelectByNoUtilisateur(int no_utilisateur);
	Enchere SelectLatestEnchereFromArticle(int no_article); 
	List<Enchere> selectByNoArticleTriDecroissant( int no_article );
}
