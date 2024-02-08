package fr.eni.enchere.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.enchere.bo.Article;
import fr.eni.enchere.bo.Categorie;

public class DAOCategoriesJdbcImpl implements DAOCategories {
	private static final String SQL_SELECT_ALL = "SELECT no_categorie, libelle FROM CATEGORIES";
	private static final String SQL_SELECT_BY_ID = "SELECT no_categorie, libelle FROM CATEGORIES WHERE no_categorie = ?";
	private static final String SQL_UPDATE = "UPDATE CATEGORIES set libelle = ? WHERE no_categorie = ?";
	private static final String SQL_DELETE = "DELETE FROM CATEGORIES WHERE no_categorie = ?";
	private static final String SQL_INSERT = "INSERT INTO CATEGORIES (libelle) values (?)";
	

	@Override
	public List<Categorie> selectAll() {
		List<Categorie>listeCategories=new ArrayList<>();
		try(Connection cnx = ConnectionProvider.getConnection(); PreparedStatement pstmt = cnx.prepareStatement(SQL_SELECT_ALL)){
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Categorie c = CategorieBuilder(rs);
				listeCategories.add(c);
				
			}
			

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listeCategories;
	}
	
	public Categorie selectByNoCategorie(int no_categorie) {
		Categorie cat = null;
		try(Connection cnx = ConnectionProvider.getConnection(); PreparedStatement pstmt = cnx.prepareStatement(SQL_SELECT_BY_ID)){
			pstmt.setInt(1, no_categorie);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				 cat = CategorieBuilder(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cat;
	}

		private Categorie CategorieBuilder(ResultSet rs) {
		Categorie c = null;
		try {	
			c=new Categorie();
			c.setNo_categorie(rs.getInt("no_categorie"));
			c.setLibelle(rs.getString("libelle"));
		
	} catch (SQLException e) {
			e.printStackTrace();
	}
		return c;

		
	}

	@Override
	public boolean ajouter(String libelle) throws SQLException {
		boolean ajout = false;
		try(Connection cnx = ConnectionProvider.getConnection(); PreparedStatement pstmt = cnx.prepareStatement(SQL_INSERT)){
			pstmt.setString(1, libelle);
			int rs = pstmt.executeUpdate();
			if(rs == 1) {
				ajout = true;
			}
		}
		return ajout;
	}
	

	@Override
	public boolean supprimer(Integer no_categorie) throws SQLException {
		boolean isDeleted = false;
		try(Connection cnx = ConnectionProvider.getConnection(); PreparedStatement pstmt = cnx.prepareStatement(SQL_DELETE)){
			pstmt.setInt(1, no_categorie);
			int rs = pstmt.executeUpdate();
			if(rs == 1) {
				isDeleted = true;
			}
		}
		return isDeleted;
	}

	@Override
	public void update(Categorie c) {
		try(Connection cnx = ConnectionProvider.getConnection(); PreparedStatement pstmt = cnx.prepareStatement(SQL_UPDATE)){
			pstmt.setString(1, c.getLibelle());
			pstmt.setInt(2, c.getNo_categorie());
			pstmt.executeUpdate();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
