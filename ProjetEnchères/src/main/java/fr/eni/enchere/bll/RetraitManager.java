package fr.eni.enchere.bll;

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
}
