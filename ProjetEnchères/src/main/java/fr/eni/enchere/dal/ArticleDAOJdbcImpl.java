package fr.eni.enchere.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.enchere.bo.Article;
import fr.eni.enchere.bo.Utilisateur;

public class ArticleDAOJdbcImpl implements ArticleDAO{
	private static final String SQL_SELECT_ALL = "SELECT a.no_article, a.nom_article, a.description, a.date_debut_encheres, a.date_fin_encheres, a.prix_initial, a.prix_vente, a.no_utilisateur, a.no_categorie"
												+ ","
												+ "FROM ARTICLES_VENDUS a INNER JOIN UTILISATEURS u on a. ";
	
	private static final String SQL_SELECT_BY_CATEGORIE = "SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie"
												+ "FROM ARTICLES_VENDUS WHERE no_categorie = ?";
	
	private static final String SQL_SELECT_NAME = "SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie"
												+ "FROM ARTICLES_VENDUS WHERE nom_article LIKE ?";
	
	private static final String SQL_SELECT_FROM_USER = "SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie"
													+  "FROM ARTICLES_VENDUS WHERE no_utilisateur = ?";
	
	private static final String SQL_AJOUTER = "INSERT INTO ARTICLES_VENDUS(nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie)"
											+ "VALUES (?,?,?,?,?,?,?,?)";
	
	private static final String SQL_SUPPRIMER = "DELETE FROM ARTICLES_VENDUS WHERE no_Article = ?";
	
	private static final String SQL_UPDATE = "UPDATE ARTICLES_VENDUS SET nom_article=? , description = ?, date_debut_encheres = ?, date_fin_encheres = ?, prix_initial = ?, prix_vente = ?"
											+ ", no_utilisateur=?, no_categorie=?";
	private static final String SQL_DELETE_SINGLE_ARTICLE_FROM_USER = "DELETE FROM ARTICLES_VENDUS WHERE no_utilisateur=? and no_article=?";
	
	private static final String SQL_DELETE_ALL_ARTICLES_FROM_USER = "DELETE FROM ARTICLES_VENDUS WHERE no_utilisateur=?";
	

	private Article ArticleBuilder(ResultSet rs) {
		Article a = new Article();
		a.set
		return null;
	}

	@Override
	public List<Article> selectAll() {
		List<Article> listeArticles = new ArrayList<>();
		try(Connection cnx = ConnectionProvider.getConnection(); PreparedStatement pstmt = cnx.prepareStatement(SQL_SELECT_ALL)){
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Article a = ArticleBuilder(rs);
				listeArticles.add(a);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
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
