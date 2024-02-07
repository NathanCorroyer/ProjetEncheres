package fr.eni.enchere.dal;


import java.sql.SQLException;
import java.util.List;

import fr.eni.enchere.bo.Article;
import fr.eni.enchere.bo.Retrait;

public interface RetraitDAO {

	void create (Retrait retrait) throws SQLException;
	
	void update (Retrait retrait) throws SQLException;
	
	void deleteAll() throws SQLException;
	
	List<Retrait> findAll() throws SQLException;

	Retrait selectByArticle(Article article) throws SQLException;
	
}
