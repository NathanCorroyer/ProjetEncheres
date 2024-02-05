package fr.eni.enchere.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;

import fr.eni.enchere.bll.BLLException;
import fr.eni.enchere.bll.UtilisateurManager;
import fr.eni.enchere.bo.Article;
import fr.eni.enchere.bo.Utilisateur;

public class ArticleDAOJdbcImpl implements ArticleDAO{
	private static final String SQL_SELECT_ALL = "SELECT no_article,nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,prix_vente,no_utilisateur,no_categorie FROM ARTICLES_VENDUS";
	
	private static final String SQL_SELECT_BY_CATEGORIE = "SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie FROM ARTICLES_VENDUS WHERE no_categorie = ?";
	
	private static final String SQL_SELECT_BY_NAME = "SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie FROM ARTICLES_VENDUS WHERE nom_article LIKE ?";
	
	private static final String SQL_SELECT_FROM_USER = "SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie"
													+  "FROM ARTICLES_VENDUS WHERE no_utilisateur = ?";
	
	private static final String SQL_AJOUTER = "INSERT INTO ARTICLES_VENDUS(nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, no_utilisateur, no_categorie)"
											+ "VALUES (?,?,?,?,?,?,?)";
	
	private static final String SQL_SUPPRIMER = "DELETE FROM ARTICLES_VENDUS WHERE no_Article = ?";
	
	private static final String SQL_UPDATE = "UPDATE ARTICLES_VENDUS SET nom_article=? , description = ?, date_debut_encheres = ?, date_fin_encheres = ?, prix_initial = ?, prix_vente = ?"
											+ ", no_utilisateur=?, no_categorie=?";
	private static final String SQL_DELETE_SINGLE_ARTICLE_FROM_USER = "DELETE FROM ARTICLES_VENDUS WHERE no_utilisateur=? and no_article=?";
	
	private static final String SQL_DELETE_ALL_ARTICLES_FROM_USER = "DELETE FROM ARTICLES_VENDUS WHERE no_utilisateur=?";
	
	private static final String SQL_SELECT_ARTICLE_BY_ID = "SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie FROM ARTICLES_VENDUS WHERE no_article = ?";
	
	private static final String SQL_SELECT_ARTICLE_BY_CATEGORIE_AND_NAME = "SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie FROM ARTICLES_VENDUS WHERE no_categorie = ? AND nom_article like ?";
	
	private Article ArticleBuilder(ResultSet rs) {
		Article a = null;
		try {
			a=new Article();
			
			a.setNoArticle(rs.getInt("no_article"));
			a.setNom_Article(rs.getString("nom_article"));
			a.setDescription(rs.getString("description"));
			a.setDate_debut_encheres((rs.getTimestamp("date_debut_encheres").toLocalDateTime()));
			a.setDate_fin_encheres((rs.getTimestamp("date_fin_encheres").toLocalDateTime()));
			a.setPrix_initial(rs.getInt("prix_initial"));
			a.setCategorie(rs.getInt("no_categorie"));
			UtilisateurManager um = UtilisateurManager.getInstance();
			Utilisateur utilisateur = um.selectUserByNumero(rs.getInt("no_utilisateur"));
			a.setNo_utilisateur(rs.getInt("no_utilisateur"));
			a.setUtilisateur(utilisateur);

			
		} catch (SQLException | BLLException e) {
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
	public Article selectArticleById(int no_article) throws SQLException {
		Article a = null;
		try(Connection cnx = ConnectionProvider.getConnection(); PreparedStatement pstmt = cnx.prepareStatement(SQL_SELECT_ARTICLE_BY_ID)){
			pstmt.setInt(1, no_article);
			ResultSet rs = pstmt.executeQuery();
			
	
	
		if(rs.next() ) {
			a = ArticleBuilder(rs);
		}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return a;
		
	}

	@Override
	public Integer ajouter(Article a) throws SQLException {
		Integer key = null;
		
		try(Connection cnx = ConnectionProvider.getConnection(); PreparedStatement pstmt = cnx.prepareStatement(SQL_AJOUTER, Statement.RETURN_GENERATED_KEYS)){
			pstmt.setString(1, a.getNom_Article());
			pstmt.setString(2, a.getDescription());
			pstmt.setTimestamp(3, Timestamp.valueOf(a.getDate_debut_encheres()));
			pstmt.setTimestamp(4, Timestamp.valueOf(a.getDate_fin_encheres()));
			pstmt.setInt(5, a.getPrix_initial());
			pstmt.setInt(6, a.getNo_utilisateur());
			pstmt.setInt(7, a.getCategorie());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				key = rs.getInt(1);
			}
			
		}
		return key;
		
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

	@Override
	public List<Article> selectArticleByCategorie(int no_categorie) throws SQLException {
		List<Article> listeArticles = new ArrayList<>();
		try(Connection cnx = ConnectionProvider.getConnection(); PreparedStatement pstmt = cnx.prepareStatement(SQL_SELECT_BY_CATEGORIE)){
			pstmt.setInt(1, no_categorie);
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
	public List<Article> selectByName(String nomTri) throws SQLException {
		List<Article> listeArticles = new ArrayList<>();
		try(Connection cnx = ConnectionProvider.getConnection(); PreparedStatement pstmt = cnx.prepareStatement(SQL_SELECT_BY_NAME)){
			pstmt.setString(1, nomTri);
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
	public List<Article> selectArticleByCategorieAndByName(int no_categorie, String nomTri) throws SQLException {
		List<Article> listeArticles = new ArrayList<>();
		try(Connection cnx = ConnectionProvider.getConnection(); PreparedStatement pstmt = cnx.prepareStatement(SQL_SELECT_ARTICLE_BY_CATEGORIE_AND_NAME)){
			pstmt.setInt(1, no_categorie);
			pstmt.setString(2, nomTri);
			
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
	
	
	
}
