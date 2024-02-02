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
	void ajouter(Article a) throws SQLException;
	void supprimer(Article a);
	void update(Article a);
	void deleteSingleArticleFromUser(Utilisateur u, Article a);
	void deleteAllArticlesFromUser(Utilisateur u);
	void ajouterAvecAcheteur(Article a) throws SQLException;
	
}

