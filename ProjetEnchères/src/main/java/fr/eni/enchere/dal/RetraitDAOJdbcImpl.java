package fr.eni.enchere.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.enchere.bll.ArticleManager;
import fr.eni.enchere.bo.Article;
import fr.eni.enchere.bo.Retrait;

public class RetraitDAOJdbcImpl implements RetraitDAO {
	
	private static final String SQL_CREATE = "INSERT INTO RETRAITS (no_article, rue,code_postal,ville) VALUES (?,?,?,?)";
	private static final String SQL_UPDATE = "UPDATE UTILISATEURS SET rue=?,code_postal=?,ville=? WHERE no_article=?";
	private static final String SQL_DELETE_ALL = "DELETE FROM Retraits";
	private static final String SQL_FIND_ALL = "SELECT * FROM Retraits";
	private static final String SELECT_BY_ARTICLE = "SELECT * FROM Retraits WHERE no_article = ?";
	@Override
	public void create(Retrait retrait) throws SQLException {
		try ( Connection con = ConnectionProvider.getConnection() ; PreparedStatement pstmt = con.prepareStatement(SQL_CREATE) ){
			pstmt.setInt(1 , retrait.getArticle().getNoArticle());
			pstmt.setString(2, retrait.getRue());
			pstmt.setString(3 , retrait.getCode_postal());
			pstmt.setString(4 , retrait.getVille());
			
			pstmt.executeUpdate();
		} catch ( SQLException e ) {
			e.printStackTrace();
		} 
	}

	@Override
	public void update(Retrait retrait) throws SQLException {
		try(Connection con = ConnectionProvider.getConnection();PreparedStatement pstmt = con.prepareStatement(SQL_UPDATE)){
			pstmt.setString(1, retrait.getRue());
			pstmt.setString(2 , retrait.getCode_postal());
			pstmt.setString(3 , retrait.getVille());
			pstmt.setInt(4 , retrait.getArticle().getNoArticle());
			pstmt.executeUpdate();
			//pstmt.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}


	@Override
	public void deleteAll() throws SQLException {
		try ( Connection con = ConnectionProvider.getConnection() ; PreparedStatement stmt = con.prepareStatement(SQL_DELETE_ALL)){
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

	@Override
	public List<Retrait> findAll() throws SQLException {
List<Retrait> listeRetrait = new ArrayList();
		
		try( Connection con = ConnectionProvider.getConnection() ; PreparedStatement pstmt = con.prepareStatement(SQL_FIND_ALL)){
			
			ResultSet rs = pstmt.executeQuery() ;
			
			while ( rs.next() ) {
				Article a = null;
				ArticleManager am = ArticleManager.getInstance();
				a = am.selectArticleById(rs.getInt("no_article"));
				Retrait r = new Retrait();
				r.setRue(rs.getString("rue"));
				r.setCode_postal(rs.getString("code_postal"));
				r.setVille(rs.getString("ville"));
				r.setArticle(a);
				 
				listeRetrait.add(r);
			}
			
		} catch ( SQLException e) {
			e.printStackTrace();
		}
		
		return listeRetrait ;
	}

	@Override
	public Retrait selectByArticle(Article article) throws SQLException {
		Retrait retrait = null;
		
		try( Connection con = ConnectionProvider.getConnection() ; PreparedStatement pstmt = con.prepareStatement(SELECT_BY_ARTICLE)){
			
			pstmt.setInt(1, article.getNoArticle());
			
			try(ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					
					
					retrait = new Retrait();
					
					retrait.setRue(rs.getString("rue"));
					retrait.setCode_postal(rs.getString("code_postal"));
					retrait.setVille(rs.getString("ville"));
					retrait.setArticle(article);
				}
			} catch (SQLException e) {
	        	e.printStackTrace();
	        }
		return retrait;
		}

	}
	
}
