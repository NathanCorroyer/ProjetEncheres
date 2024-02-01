package fr.eni.enchere.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.enchere.bll.UtilisateurManager;
import fr.eni.enchere.bo.Article;
import fr.eni.enchere.bo.Utilisateur;

public class ArticleDAOJdbcImpl implements ArticleDAO{
	private static final String SQL_SELECT_ALL = "SELECT no_article,nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,prix_vente,no_vendeur,no_categorie, no_acheteur FROM ARTICLES_VENDUS";
	
	private static final String SQL_SELECT_BY_CATEGORIE = "SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_vendeur, no_categorie"
												+ "FROM ARTICLES_VENDUS WHERE no_categorie = ?";
	
	private static final String SQL_SELECT_NAME = "SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_vendeur, no_categorie"
												+ "FROM ARTICLES_VENDUS WHERE nom_article LIKE ?";
	
	private static final String SQL_SELECT_FROM_USER = "SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_vendeur, no_categorie"
													+  "FROM ARTICLES_VENDUS WHERE no_vendeur = ?";
	
	private static final String SQL_AJOUTER = "INSERT INTO ARTICLES_VENDUS(nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_vendeur, no_categorie)"
											+ "VALUES (?,?,?,?,?,?,?,?)";
	private static final String SQL_AJOUTER_AVEC_ACHETEUR = "INSERT INTO ARTICLES_VENDUS(nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_vendeur, no_categorie,no_acheteur)"
			+ "VALUES (?,?,?,?,?,?,?,?,?)";
	
	private static final String SQL_SUPPRIMER = "DELETE FROM ARTICLES_VENDUS WHERE no_Article = ?";
	
	private static final String SQL_UPDATE = "UPDATE ARTICLES_VENDUS SET nom_article=? , description = ?, date_debut_encheres = ?, date_fin_encheres = ?, prix_initial = ?, prix_vente = ?"
											+ ", no_vendeur=?, no_categorie=?";
	private static final String SQL_DELETE_SINGLE_ARTICLE_FROM_USER = "DELETE FROM ARTICLES_VENDUS WHERE no_vendeur=? and no_article=?";
	
	private static final String SQL_DELETE_ALL_ARTICLES_FROM_USER = "DELETE FROM ARTICLES_VENDUS WHERE no_vendeur=?";
	

	private Article ArticleBuilder(ResultSet rs) {
		Article a = null;
		try {
			a=new Article();
			a.setNoArticle(rs.getInt("no_article"));
			a.setNom_Article(rs.getString("nom_article"));
			a.setDescription(rs.getString("description"));
			a.setDate_debut_encheres(rs.getDate("date_debut_encheres").toLocalDate());
			a.setDate_fin_encheres(rs.getDate("date_fin_encheres").toLocalDate());
			a.setPrix_initial(rs.getInt("prix_initial"));
			a.setPrix_vente(rs.getInt("prix_vente"));
			a.setCategorie(rs.getInt("no_categorie"));
			UtilisateurManager um = UtilisateurManager.getInstance();
			Utilisateur vendeur = um.selectUserByNumero(rs.getInt("no_vendeur"));
			a.setNoVendeur(rs.getInt("no_vendeur"));
			a.setVendeur(vendeur);
			Utilisateur acheteur;
			Integer no_acheteur = rs.getInt("no_acheteur");
			if(no_acheteur != null) {
				acheteur = um.selectUserByNumero(rs.getInt("no_acheteur"));
				a.setAcheteur(acheteur);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;
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
		
		return listeArticles;
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
	public void ajouter(Article a) throws SQLException {
		try(Connection cnx = ConnectionProvider.getConnection(); PreparedStatement pstmt = cnx.prepareStatement(SQL_AJOUTER)){
			pstmt.setString(1, a.getNom_Article());
			pstmt.setString(2, a.getDescription());
			pstmt.setDate(3, Date.valueOf(a.getDate_debut_encheres()));
			pstmt.setDate(4, Date.valueOf(a.getDate_fin_encheres()));
			pstmt.setInt(5, a.getPrix_initial());
			pstmt.setInt(6, a.getPrix_vente());
			pstmt.setInt(7, a.getNoVendeur());
			pstmt.setInt(8, a.getCategorie());
			pstmt.executeUpdate();
			
		}
		
	}

	
	@Override
	public void ajouterAvecAcheteur(Article a) throws SQLException {
		try(Connection cnx = ConnectionProvider.getConnection(); PreparedStatement pstmt = cnx.prepareStatement(SQL_AJOUTER_AVEC_ACHETEUR)){
			pstmt.setString(1, a.getNom_Article());
			pstmt.setString(2, a.getDescription());
			pstmt.setDate(3, Date.valueOf(a.getDate_debut_encheres()));
			pstmt.setDate(4, Date.valueOf(a.getDate_fin_encheres()));
			pstmt.setInt(5, a.getPrix_initial());
			pstmt.setInt(6, a.getPrix_vente());
			pstmt.setInt(7, a.getNoVendeur());
			pstmt.setInt(8, a.getCategorie());
			pstmt.setInt(9, a.getNo_acheteur());
			
			pstmt.executeUpdate();
		}		
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
