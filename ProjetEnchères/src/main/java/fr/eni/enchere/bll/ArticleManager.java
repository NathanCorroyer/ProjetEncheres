package fr.eni.enchere.bll;

import java.sql.SQLException;
import java.util.List;

import fr.eni.enchere.bo.Article;
import fr.eni.enchere.dal.ArticleDAO;
import fr.eni.enchere.dal.DAOFactory;

public class ArticleManager {
 private static ArticleManager instance=null;
 private ArticleDAO articleDAO;
 
 public ArticleManager(ArticleDAO articleDAO2) {
	this.articleDAO = articleDAO2;
}

public static ArticleManager getInstance() {
		if (instance == null) {
			instance = new ArticleManager(DAOFactory.getArticleDAO());

		}
		return instance;
	}

public void updateEnchere( int prixEnchere , int no_article ) {
	articleDAO.updateEnchere( prixEnchere , no_article );
}

public List<Article> selectAll(){
	return articleDAO.selectAll();
}
public List<Article> selectAllEnCours(){
	return articleDAO.selectAllEnCours();
}

public List<Article> selectAllFinies(){
	return articleDAO.selectAllFinies();
}

public Integer ajouter(Article a) throws SQLException {
	return articleDAO.ajouter(a);
}

public Article selectArticleById(int no_article) throws SQLException {
	return articleDAO.selectArticleById(no_article); 
}

public List<Article> selectArticleByCategorie(int no_categorie) throws SQLException {
	return articleDAO.selectArticleByCategorie(no_categorie);
}

public List<Article> selectByName(String nomTri) throws SQLException {
	return articleDAO.selectByName(nomTri);
}

public List<Article> selectArticleByCategorieAndByName(int no_categorie, String nomTri) throws SQLException{
	return articleDAO.selectArticleByCategorieAndByName(no_categorie,nomTri);
}

public List<Article> selectEnchereFinieByCategorie(int no_categorie){
	return articleDAO.selectEnchereFinieByCategorie(no_categorie);
}

public List<Article> selectEnchereFinieByName(String keyword){
	return articleDAO.selectEnchereFinieByName(keyword);
}

public List<Article> selectEnchereFinieByCategorieAndByName(int no_categorie, String keyword) throws SQLException{
	return articleDAO.selectEnchereFinieByCategorieAndByName(no_categorie, keyword);
}


public List<Article> selectEnchereEnCoursByCategorie(int no_categorie){
	return articleDAO.selectEnchereEnCoursByCategorie(no_categorie);
}

public List<Article> selectEnchereEnCoursByName(String keyword){
	return articleDAO.selectEnchereEnCoursByName(keyword);
}

public List<Article> selectEnchereEnCoursByCategorieAndByName(int no_categorie, String keyword) throws SQLException{
	return articleDAO.selectEnchereEnCoursByCategorieAndByName(no_categorie,keyword);
}
}


