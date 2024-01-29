package fr.eni.enchere.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import fr.eni.enchere.bo.Utilisateur;

public class UtilisateurDAOJdbcImpl {
	private final String SQL_UPDATE = "UPDATE UTILISATEURS SET pseudo=?, nom=?, prenom=?,email=?,telephone=?,rue=?,code_postal=?,ville=?,mot_de_passe=?,credit=?,administrateur=? WHERE noUtilisateur=?";
	
	
	
	
	public void update(Utilisateur u) throws SQLException {
		try(Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement pstmt = cnx.prepareStatement(SQL_UPDATE);
			pstmt.setString(1,u.getPseudo());
			pstmt.setString(2,u.getNom());
			pstmt.setString(3, u.getPrenom());
			pstmt.setString(4, u.getEmail());
			pstmt.setString(5, u.getTelephone());
			pstmt.setString(6, u.getRue());
			pstmt.setString(7, u.getCode_postal());
			pstmt.setString(8, u.getVille());
			pstmt.setString(9, u.getPassword());
			pstmt.setInt(10,u.getCredit());
			pstmt.setBoolean(11, u.isAdministrateur());
			pstmt.setInt(12, u.getNoUtilisateur());
		}
	}
}
