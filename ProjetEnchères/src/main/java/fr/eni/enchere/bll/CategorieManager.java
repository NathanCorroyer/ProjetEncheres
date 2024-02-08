package fr.eni.enchere.bll;

import java.sql.SQLException;
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
	
	 public Categorie selectByNoCategorie(int no_categorie) {
		return CategorieDAO.selectByNoCategorie(no_categorie);
	 }
	 
	 public void update(Categorie categorie) {
		 CategorieDAO.update(categorie);
	 }
	 
	 public boolean supprimer (Integer no_categorie) throws SQLException {
		 return CategorieDAO.supprimer(no_categorie);
	 }
	 
	 public boolean ajouter(String libelle) throws SQLException {
		 return CategorieDAO.ajouter(libelle);
	 }
}
