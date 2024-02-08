package fr.eni.enchere.dal;

import java.sql.SQLException;
import java.util.List;

import fr.eni.enchere.bo.Article;
import fr.eni.enchere.bo.Categorie;

public interface DAOCategories {
	List<Categorie> selectAll();
	 Categorie selectByNoCategorie(int no_categorie);
    boolean ajouter(String libelle) throws SQLException;
    boolean supprimer(Integer c) throws SQLException;
    void update(Categorie c);
    
}
