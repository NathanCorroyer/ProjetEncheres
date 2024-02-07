package fr.eni.enchere.dal;

import java.sql.SQLException;
import java.util.List;

import fr.eni.enchere.bo.Article;
import fr.eni.enchere.bo.Utilisateur;

public interface ArticleDAO {
	
	List<Article> selectAll();			//Lister
	List<Article> selectAllEnCours();			//Lister
	List<Article> selectAllFinies();
	List<Article> selectArticleByCategorie(int no_categorie) throws SQLException; 	//Filtrer résultats par catégorie
	List<Article> selectArticlesFromUser(Utilisateur u); //Pour afficher tous les articles d'un même utilisateur
	Article selectArticleById(int no_article) throws SQLException; 
	Integer ajouter(Article a) throws SQLException;
	void supprimer(Article a);
	void update(Article a);
	void updateEnchere( int prix , int no_article );
	void deleteSingleArticleFromUser(Utilisateur u, Article a);
	void deleteAllArticlesFromUser(Utilisateur u);
	List<Article> selectByName(String nomTri) throws SQLException;
	List<Article> selectArticleByCategorieAndByName(int no_categorie, String nomTri) throws SQLException;
	List<Article> selectEnchereFinieByCategorieAndByName(int no_categorie, String nomTri) throws SQLException;
	List<Article> selectEnchereFinieByCategorie(int no_categorie);
	List<Article> selectEnchereFinieByName(String keyword);
	List<Article> selectEnchereEnCoursByName(String keyword);
	List<Article> selectEnchereEnCoursByCategorie(int no_categorie);
	List<Article> selectEnchereEnCoursByCategorieAndByName(int no_categorie, String nomTri) throws SQLException;

	
}

