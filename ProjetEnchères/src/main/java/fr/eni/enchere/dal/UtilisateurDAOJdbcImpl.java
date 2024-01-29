package fr.eni.enchere.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.enchere.bo.Utilisateur;

public class UtilisateurDAOJdbcImpl implements UtilisateurDAO {

	private static final String SQL_REGISTER = "INSERT INTO UTILISATEURS (pseudo,nom,prenom,email,telephone,rue,code_postal,ville,mot_de_passe,credit,administrateur) "
			+ "									VALUES (?,?,?,?,?,?,?,?,?,?,?)";
	
	private static final String SQL_DELETE_BY_EMAIL = "DELETE FROM Utilisateurs WHERE email like ?";
	
	private final String SQL_UPDATE = "UPDATE UTILISATEURS SET pseudo=?, nom=?, prenom=?,email=?,telephone=?,rue=?,code_postal=?,ville=?,mot_de_passe=?,credit=?,administrateur=? "
			+ "							WHERE noUtilisateur=?";
	
	private final String SQL_FIND_ALL = "SELECT * FROM Utilisateurs";
	
	private final String SQL_DELETE_ALL = "DELETE from Utilisateurs" ;
	private final String SQL_LOGIN= "SELECT noUtilisateur,pseudo,nom,prenom,email,telephone,rue,code_postal,ville,mot_de_passe,credit,administrateur FROM UTILISATEURS WHERE email = ? and password = ?";

	
	public void register ( Utilisateur utilisateur ) throws SQLException {
		
		try ( Connection con = ConnectionProvider.getConnection() ; PreparedStatement pstmt = con.prepareStatement(SQL_REGISTER) ){
			
			pstmt.setString(1 , utilisateur.getPseudo());
			pstmt.setString(2 , utilisateur.getNom());
			pstmt.setString(3 , utilisateur.getPrenom());
			pstmt.setString(4 , utilisateur.getEmail());
			pstmt.setString(5 , utilisateur.getTelephone());
			pstmt.setString(6 , utilisateur.getRue());
			pstmt.setString(7 , utilisateur.getCode_postal());
			pstmt.setString(8 , utilisateur.getVille());
			pstmt.setString(9 , utilisateur.getPassword());
			pstmt.setInt(10,utilisateur.getCredit());
			pstmt.setInt(11, 0);
			
			pstmt.executeUpdate();
			
		} catch ( SQLException e ) {
			e.printStackTrace();
		} 
	}
	
	public void deleteByMail( String email ) throws SQLException {
		
		try ( Connection con = ConnectionProvider.getConnection() ; PreparedStatement ptsmt = con.prepareStatement(SQL_DELETE_BY_EMAIL)){
			
			ptsmt.setString(1 , email);
			ptsmt.executeUpdate();
		
		} catch ( SQLException e ) {
			e.printStackTrace();
		}
	}
	
	
	public void update(Utilisateur u) throws SQLException {
		try(Connection con = ConnectionProvider.getConnection();PreparedStatement pstmt = con.prepareStatement(SQL_UPDATE)){
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
			pstmt.executeUpdate();
			pstmt.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	
	public List<Utilisateur> findAll() throws SQLException{
		
		List<Utilisateur> listeUtilisateurs = new ArrayList();
		
		try( Connection con = ConnectionProvider.getConnection() ; PreparedStatement pstmt = con.prepareStatement(SQL_FIND_ALL)){
			
			ResultSet rs = pstmt.executeQuery() ;
			
			while ( rs.next() ) {
				Utilisateur u = new Utilisateur();
				u.setPseudo(rs.getString("pseudo"));
				u.setNom(rs.getString("nom"));
				u.setPrenom(rs.getString("prenom"));
				u.setEmail(rs.getString("email"));
				u.setTelephone(rs.getString("telephone"));
				u.setRue(rs.getString("rue"));
				u.setCode_postal(rs.getString("code_postal"));
				u.setVille(rs.getString("ville"));
				u.setPassword(rs.getString("password"));
				 
				listeUtilisateurs.add(u);
			}
			
		} catch ( SQLException e) {
			e.printStackTrace();
		}
		
		return listeUtilisateurs ;
	}
		
	 public void deleteAll() {
	        try ( Connection con = ConnectionProvider.getConnection() ; PreparedStatement stmt = con.prepareStatement(SQL_DELETE_ALL)){
	            stmt.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	 }
	 
	 public Utilisateur login(String email, String mdp){
		 Utilisateur utilisateur=null;
		 try(Connection cnx = ConnectionProvider.getConnection() ; PreparedStatement pstmt = cnx.prepareStatement(SQL_LOGIN)){
			 pstmt.setString(1, email);
			 pstmt.setString(2, mdp);
			 ResultSet rs = pstmt.executeQuery();
			 if(rs.next()) {
				 	utilisateur= new Utilisateur();
				 	utilisateur.setNoUtilisateur(rs.getInt("noUtilisateur"));
				 	utilisateur.setPseudo(rs.getString("pseudo"));
					utilisateur.setNom(rs.getString("nom"));
					utilisateur.setPrenom(rs.getString("prenom"));
					utilisateur.setEmail(rs.getString("email"));
					utilisateur.setCode_postal(rs.getString("code_postal"));
					utilisateur.setRue(rs.getString("rue"));;
					utilisateur.setVille(rs.getString("ville"));
					utilisateur.setPassword(rs.getString("mot_de_passe"));
					utilisateur.setCredit(rs.getInt("credit"));
					utilisateur.setAdministrateur(rs.getBoolean("administrateur"));
					utilisateur.setTelephone(rs.getString("telephone"));
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return utilisateur;
		 
	 }

	

}
