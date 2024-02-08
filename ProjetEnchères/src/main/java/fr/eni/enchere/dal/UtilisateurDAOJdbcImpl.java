package fr.eni.enchere.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.enchere.bo.MD5;
import fr.eni.enchere.bo.Utilisateur;

public class UtilisateurDAOJdbcImpl implements UtilisateurDAO {

	private static final String SQL_REGISTER = "INSERT INTO UTILISATEURS (pseudo,nom,prenom,email,telephone,rue,code_postal,ville,mot_de_passe) "
			+ "									VALUES (?,?,?,?,?,?,?,?,?)";
	
	private static final String SQL_DELETE_BY_EMAIL = "DELETE FROM Utilisateurs WHERE email like ?";
	
	private static final String SQL_UPDATE = "UPDATE UTILISATEURS SET pseudo=?, nom=?, prenom=?,email=?,telephone=?,rue=?,code_postal=?,ville=?,mot_de_passe=?,credit=?,administrateur=? "
			+ "							WHERE no_Utilisateur=?";
	
	private static final String SQL_FIND_ALL = "SELECT * FROM Utilisateurs";
	
	private static final String SQL_DELETE_ALL = "DELETE from Utilisateurs" ;
	
	private static final String SQL_LOGIN= "SELECT no_Utilisateur,pseudo,nom,prenom,email,telephone,rue,code_postal,ville,mot_de_passe,credit,administrateur,actif FROM UTILISATEURS WHERE email = ? and mot_de_passe = ?";

	private static final String SELECT_BY_PSEUDO = "SELECT * FROM Utilisateurs WHERE pseudo = ?";
	private static final String SELECT_BY_NUMERO = "SELECT * FROM Utilisateurs WHERE no_utilisateur = ?";
	private static final String SELECT_BY_MAIL = "SELECT * FROM Utilisateurs WHERE email = ?";

	private static final String SQL_DISABLE_BY_MAIL = "UPDATE UTILISATEURS SET actif = 0 WHERE email = ?";

	private static final String SQL_ENABLE_BY_MAIL = "UPDATE UTILISATEURS SET actif = 1 WHERE email = ?";

	private static final String SQL_UPDATE_PASSWORD = "UPDATE UTILISATEURS SET mot_de_passe = ? WHERE no_utilisateur =?";

	
	
	
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
			pstmt.setString(9 , MD5.getMd5(utilisateur.getPassword()));
			
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
	
	@Override
	public void updatePassword(String newPassword, int no_utilisateur) {
		try(Connection con = ConnectionProvider.getConnection();PreparedStatement pstmt = con.prepareStatement(SQL_UPDATE_PASSWORD)){
			pstmt.setString(1,newPassword);
			pstmt.setInt(2,no_utilisateur);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public List<Utilisateur> findAll(){
		
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
				u.setPassword(rs.getString("mot_de_passe"));
				u.setActif(rs.getBoolean("actif")); 
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
				 	utilisateur.setNoUtilisateur(rs.getInt("no_Utilisateur"));
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
					utilisateur.setActif(rs.getBoolean("actif"));
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return utilisateur;
		 
	 }
	 
	 public Utilisateur selectByPseudo(String pseudo) {
	        Utilisateur utilisateur = null;

	        try (Connection connection = ConnectionProvider.getConnection();
	             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_PSEUDO)) {

	            preparedStatement.setString(1, pseudo);

	            try (ResultSet rs = preparedStatement.executeQuery()) {
	                if (rs.next()) {
	                    utilisateur = mapResultSetToUtilisateur(rs);
	                }
	            }

	        } catch (SQLException e) {
	        	e.printStackTrace();
	        }
	            

			return utilisateur;
	    }
	 
	 
	 public Utilisateur selectByNumero(int numero) {
	        Utilisateur utilisateur = null;

	        try (Connection connection = ConnectionProvider.getConnection();
	             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_NUMERO)) {

	            preparedStatement.setInt(1, numero);

	            try (ResultSet rs = preparedStatement.executeQuery()) {
	                if (rs.next()) {
	                    utilisateur = mapResultSetToUtilisateur(rs);
	                }
	            }

	        } catch (SQLException e) {
	        	e.printStackTrace();
	        }
	            

			return utilisateur;
	    }
	 
	 public Utilisateur selectByMail(String mail ) {
	        Utilisateur utilisateur = null;

	        try (Connection connection = ConnectionProvider.getConnection();
	             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_MAIL)) {

	            preparedStatement.setString(1, mail );

	            try (ResultSet rs = preparedStatement.executeQuery()) {
	                if (rs.next()) {
	                    utilisateur = mapResultSetToUtilisateur(rs);
	                }
	            }

	        } catch (SQLException e) {
	        	e.printStackTrace();
	        }
	            

			return utilisateur;
	    }

	 public Utilisateur mapResultSetToUtilisateur(ResultSet rs) throws SQLException {
	        Utilisateur utilisateur = new Utilisateur();
	        
	        
	        utilisateur.setNoUtilisateur(rs.getInt("no_Utilisateur"));
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
			utilisateur.setActif(rs.getBoolean("actif"));

	        return utilisateur;
	    }
	 
	 @Override
	 public boolean disableByMail(String email) {
		boolean disabled = false;
		Integer rs = null;
		  try (Connection connection = ConnectionProvider.getConnection();
		       PreparedStatement preparedStatement = connection.prepareStatement(SQL_DISABLE_BY_MAIL)) {
			  	preparedStatement.setString(1, email);
			  	rs = preparedStatement.executeUpdate();
			  	if(rs != null) {
			  		disabled = true;
			  	}
		  } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return disabled;
	}
	
	 @Override
	 public boolean enableByMail(String email) {
		boolean enabled = false;
		Integer rs = null;
		  try (Connection connection = ConnectionProvider.getConnection();
		       PreparedStatement preparedStatement = connection.prepareStatement(SQL_ENABLE_BY_MAIL)) {
			  	preparedStatement.setString(1, email);
			  	preparedStatement.executeUpdate();
			  	enabled = true;
		  } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return enabled;
	}	 
	 


}
	


