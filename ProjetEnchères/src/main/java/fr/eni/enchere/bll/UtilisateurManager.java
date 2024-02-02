package fr.eni.enchere.bll;


import fr.eni.enchere.bo.Utilisateur;


import java.sql.SQLException;
import java.util.List;

import fr.eni.enchere.dal.DAOFactory;
import fr.eni.enchere.dal.UtilisateurDAO;

public class UtilisateurManager {
	private static UtilisateurManager instance = null;
	private UtilisateurDAO utilisateurDAO;
	
	private UtilisateurManager(UtilisateurDAO utilisateurDAO) {
		this.utilisateurDAO = utilisateurDAO;
	}

	
	public static UtilisateurManager getInstance() {
		if (instance == null) {
			instance = new UtilisateurManager(DAOFactory.getUtilisateurDAO());
		}
		return instance;
	}

	
	public void deleteByMail(String email) throws BLLException, SQLException {
		utilisateurDAO.deleteByMail(email);
	}
	
	
	public void update(Utilisateur u) throws SQLException, BLLException{
		try {
			validerUser(u);
			utilisateurDAO.update(u);
		} catch (BLLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public void register (Utilisateur u) throws SQLException , BLLException {
		try {
			validerUser(u);
			utilisateurDAO.register(u);
		} catch (BLLException e) {
			e.printStackTrace();
		}
	}
	
	
	public Utilisateur login(String email, String mdp) throws BLLException {
		Utilisateur user = null;
		try {
			user = utilisateurDAO.login(email, mdp);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	
	public List<Utilisateur> findAll() throws SQLException , BLLException {
		return utilisateurDAO.findAll();
	}
	
	
	public Utilisateur selectUserByPseudo(String pseudo) throws SQLException , BLLException {
		Utilisateur user = null;
        try {
			user = utilisateurDAO.selectByPseudo(pseudo);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return user;
    }
	
	
	public Utilisateur selectUserByNumero(int numero) throws SQLException , BLLException {
		Utilisateur user = null;
        try {
			user = utilisateurDAO.selectByNumero(numero);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return user;
    }
	
	
	public void validerUser( Utilisateur u ) throws BLLException {
		boolean ok = true ;
		StringBuffer sb = new StringBuffer();
		
		if ( u == null ) {
			throw new BLLException("Utilisateur null");
		}
		
		if ( u.getPseudo() == null || u.getPseudo().trim().length() == 0 ) {
			sb.append("Le pseudo ne doit pas être null." );
			ok = false;
		}
		
		if ( u.getNom() == null || u.getNom().trim().length() == 0 ) {
			sb.append("Le nom ne doit pas être null." );
			ok = false;
		}
		
		if ( u.getPrenom() == null || u.getPrenom().trim().length() == 0 ) {
			sb.append("Le prenom ne doit pas être null." );
			ok = false;
		}
		
		if ( u.getEmail() == null || u.getEmail().trim().length() == 0 ) {
			sb.append("Le mail ne doit pas être null." );
			ok = false;
		}
		
		if ( u.getTelephone() == null || u.getTelephone().trim().length() == 0 ) {
			sb.append("Le téléphone ne doit pas être null." );
			ok = false;
		}
		
		if ( u.getRue() == null || u.getRue().trim().length() == 0 ) {
			sb.append("La rue ne doit pas être null." );
			ok = false;
		}
		
		if ( u.getCode_postal() == null || u.getCode_postal().trim().length() == 0 ) {
			sb.append("Le code postal ne doit pas être null." );
			ok = false;
		}
		
		if ( u.getVille() == null || u.getVille().trim().length() == 0 ) {
			sb.append("La ville ne doit pas être null." );
			ok = false;
		}
		
		if ( u.getPassword() == null || u.getPassword().trim().length() == 0 ) {
			sb.append("Le mot de passe ne doit pas être null." );
			ok = false;
		}
		
		if ( u.getNoUtilisateur() == null || u.getNoUtilisateur() == 0 ) {
			sb.append("Le pseudo ne doit pas être null." );
			ok = false;
			
		if ( !ok ) {
			throw new BLLException(sb.toString());
		}
		}
	}
}

