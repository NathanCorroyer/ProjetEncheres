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

	public void deleteByMail(String email) throws SQLException {
		utilisateurDAO.deleteByMail(email);
	}
	
	public void update(Utilisateur u) throws SQLException {
		utilisateurDAO.update(u);
	}
	
	public void register (Utilisateur u) throws SQLException {
		utilisateurDAO.register(u);
	}
	
	public Utilisateur login(String email, String mdp) {
		Utilisateur user = null;
		try {
			user = utilisateurDAO.login(email, mdp);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public List<Utilisateur> findAll() throws SQLException{
		return utilisateurDAO.findAll();
	}
	
	public Utilisateur selectUserByPseudo(String pseudo) throws BusinessException {
       
		Utilisateur user = null;

        try {
            // Ici, vous pourriez ajouter des vérifications supplémentaires si nécessaire
            // par exemple, vérifier si le pseudo est non null ou non vide.

            user= utilisateurDAO.selectByPseudo(pseudo);

            if (user == null) {
                // Gérer le cas où l'utilisateur n'est pas trouvé
                throw new BusinessException("Utilisateur avec le pseudo " + pseudo + " non trouvé.");
            }
        } catch (Exception e) {
            // Gérer les exceptions et les erreurs de la base de données
            throw new BusinessException("Erreur lors de la récupération de l'utilisateur par pseudo", e);
        }

        return user;
    }
}

