package fr.eni.enchere.dal;

import java.sql.SQLException;
import java.util.List;

import fr.eni.enchere.bo.Article;
import fr.eni.enchere.bo.Categorie;

public interface DAOCategories {
	List<Categorie> selectAll();
	
    void ajouter(Categorie c) throws SQLException;
    void supprimer(Categorie c);
    void update(Categorie c);
}
