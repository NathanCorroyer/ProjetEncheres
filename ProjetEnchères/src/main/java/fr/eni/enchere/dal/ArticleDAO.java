package fr.eni.enchere.dal;

import java.util.List;

import fr.eni.enchere.bo.Article;
import fr.eni.enchere.bo.Utilisateur;

public interface ArticleDAO {
	List<Article> selectAll();			//Lister 
	List<Article> selectByCategorie(); 	//Filtrer résultats par catégorie
	List<Article> selectByName(); 		//Surement un name like ? pour filtre de recherche par nom
	List<Article> selectArticlesFromUser(Utilisateur u); //Pour afficher tous les articles d'un même utilisateur
	void ajouter(Article a);
	void supprimer(Article a);
	void update(Article a);
	void deleteSingleArticleFromUser(Utilisateur u, Article a);
	void deleteAllArticlesFromUser(Utilisateur u);
}

