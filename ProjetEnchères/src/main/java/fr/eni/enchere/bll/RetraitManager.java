package fr.eni.enchere.bll;

import java.sql.SQLException;

import fr.eni.enchere.bo.Retrait;
import fr.eni.enchere.dal.DAOFactory;
import fr.eni.enchere.dal.RetraitDAO;

public class RetraitManager {

		private static RetraitManager instance=null;
		private RetraitDAO RetraitDAO;
		
		public RetraitManager(RetraitDAO retraitDAO) {
			RetraitDAO = retraitDAO;
		}
		
		public static RetraitManager getInstance() {
			if (instance == null) {
				instance = new RetraitManager(DAOFactory.getRetraitDAO());
			}
			return instance;
		}
		
		public void create(Retrait retrait) throws SQLException {
			RetraitDAO.create(retrait);
		}
}