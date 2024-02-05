package fr.eni.enchere.dal;

import java.sql.SQLException;
import java.util.List;

import fr.eni.enchere.bo.Article;
import fr.eni.enchere.bo.Utilisateur;

public interface ArticleDAO {
	List<Article> selectAll();			//Lister 
	List<Article> selectByCategorie(); 	//Filtrer résultats par catégorie
	List<Article> selectByName(); 		//Surement un name like ? pour filtre de recherche par nom
	List<Article> selectArticlesFromUser(Utilisateur u); //Pour afficher tous les articles d'un même utilisateur
	Article selectArticleById(int no_article) throws SQLException; 
	public List<Article> selectArticleByCategorie(int no_categorie) throws SQLException;
	Integer ajouter(Article a) throws SQLException;
	void supprimer(Article a);
	void update(Article a);
	void deleteSingleArticleFromUser(Utilisateur u, Article a);
	void deleteAllArticlesFromUser(Utilisateur u);
	List<Article> selectByName(String nomTri) throws SQLException;
	List<Article> selectArticleByCategorieAndByName(int no_categorie, String nomTri) throws SQLException;

	
}

