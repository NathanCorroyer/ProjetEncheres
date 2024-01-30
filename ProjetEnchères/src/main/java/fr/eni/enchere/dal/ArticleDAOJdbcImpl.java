package fr.eni.enchere.dal;

import java.util.List;

import fr.eni.enchere.bo.Article;
import fr.eni.enchere.bo.Utilisateur;

public class ArticleDAOJdbcImpl implements ArticleDAO{

	@Override
	public List<Article> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Article> selectByCategorie() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Article> selectByName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Article> selectArticlesFromUser(Utilisateur u) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void ajouter(Article a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void supprimer(Article a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Article a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteSingleArticleFromUser(Utilisateur u, Article a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllArticlesFromUser(Utilisateur u) {
		// TODO Auto-generated method stub
		
	}
	
}
