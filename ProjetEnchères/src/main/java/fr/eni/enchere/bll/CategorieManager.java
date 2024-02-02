package fr.eni.enchere.bll;

import java.util.List;

import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.dal.DAOCategories;
import fr.eni.enchere.dal.DAOFactory;

public class CategorieManager {
	private static CategorieManager instance=null;
	private DAOCategories CategorieDAO;
	
	public CategorieManager(DAOCategories categorieDAO) {
		CategorieDAO = categorieDAO;
	}
	
	public static CategorieManager getInstance() {
		if (instance == null) {
			instance = new CategorieManager(DAOFactory.getCategorieDAO());

		}
		return instance;
	}
	
	public List<Categorie> selectAll(){
		return CategorieDAO.selectAll();
	}
	

}
