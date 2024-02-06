package fr.eni.enchere.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import fr.eni.enchere.bll.ArticleManager;
import fr.eni.enchere.bll.BLLException;
import fr.eni.enchere.bll.UtilisateurManager;
import fr.eni.enchere.bo.Article;
import fr.eni.enchere.bo.Enchere;
import fr.eni.enchere.bo.Utilisateur;

public class EnchereDAOJdbcImpl implements EnchereDAO {
	private static final String SQL_AJOUTER_ENCHERE = "INSERT INTO ENCHERES (date_enchere, montant_enchere, no_article, no_utilisateur) VALUES (?,?,?,?)";
	private static final String SQL_SELECT_BY_NO_ENCHERE = "SELECT no_enchere, date_enchere, montant_enchere, no_article, no_utilisateur FROM ENCHERES WHERE no_enchere = ?";
	private static final String SQL_SELECT_BY_NO_ARTICLE = "SELECT no_enchere, date_enchere, montant_enchere, no_article, no_utilisateur FROM ENCHERES WHERE no_article = ?";
	private static final String SQL_SELECT_BY_NO_UTILISATEUR = "SELECT no_enchere, date_enchere, montant_enchere, no_article, no_utilisateur FROM ENCHERES WHERE no_utilisateur = ?";
	@Override
	public Integer ajouter(Enchere e) throws SQLException {
		Integer key = null;
		try ( Connection con = ConnectionProvider.getConnection() ; PreparedStatement pstmt = con.prepareStatement(SQL_AJOUTER_ENCHERE, Statement.RETURN_GENERATED_KEYS) ){
			
			pstmt.setTimestamp(1, Timestamp.valueOf(e.getDate_enchere()));
			pstmt.setInt(2 , e.getMontant_enchere());
			pstmt.setInt(3 , e.getArticle().getNoArticle());
			pstmt.setInt(4 , e.getUtilisateur().getNoUtilisateur());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if(rs.next())
				key = rs.getInt(1);
		}
		return key;
	}

	@Override
	public Enchere SelectByNoEnchere(int no_enchere) throws SQLException {
		Enchere e = null;
		try ( Connection con = ConnectionProvider.getConnection() ; PreparedStatement pstmt = con.prepareStatement(SQL_SELECT_BY_NO_ENCHERE)){
			pstmt.setInt(1, no_enchere);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				e = enchereBuilder(rs);
			}
		}
		return e;
	}

	private Enchere enchereBuilder(ResultSet rs) {
		Enchere e = null;
		try {
			int no_enchere = rs.getInt("no_enchere");
			LocalDateTime date_enchere = rs.getTimestamp("date_enchere").toLocalDateTime();
			int montant_enchere = rs.getInt("montant_enchere");
			ArticleManager am = ArticleManager.getInstance();
			Article a = am.selectArticleById(rs.getInt("no_article"));
			UtilisateurManager um = UtilisateurManager.getInstance();
			Utilisateur u = um.selectUserByNumero(rs.getInt("no_utilisateur"));
			e = new Enchere(no_enchere, date_enchere, montant_enchere, a, u);
		} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
		} catch (BLLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
		}
			return e;
			
		
		
	}

	@Override
	public List<Enchere> SelectByNoArticle(int no_article) {
		List<Enchere> listeEncheres = new ArrayList<>();
		try ( Connection con = ConnectionProvider.getConnection() ; PreparedStatement pstmt = con.prepareStatement(SQL_SELECT_BY_NO_ARTICLE)){
			pstmt.setInt(1, no_article);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Enchere e;
				e = enchereBuilder(rs);
				listeEncheres.add(e);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return listeEncheres;
	}


	@Override
	public List<Enchere> SelectByNoUtilisateur(int no_utilisateur) {
		List<Enchere> listeEncheres = new ArrayList<>();
		try ( Connection con = ConnectionProvider.getConnection() ; PreparedStatement pstmt = con.prepareStatement(SQL_SELECT_BY_NO_UTILISATEUR)){
			pstmt.setInt(1, no_utilisateur);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Enchere e;
				e = enchereBuilder(rs);
				listeEncheres.add(e);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return listeEncheres;
	}
	
	public Enchere SelectLatestEnchereFromArticle(int no_article) {
		Enchere best = null;
		List<Enchere> listeEncheres = SelectByNoArticle(no_article);
		int prix = 0;
		int no_best_enchere = 0;
		for(Enchere e : listeEncheres) {
			if(e.getMontant_enchere()>prix) {
				prix = e.getMontant_enchere();
				no_best_enchere = e.getNo_enchere();
			}
		}
		try {
			best = SelectByNoEnchere(no_best_enchere);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return best;
	}
}
