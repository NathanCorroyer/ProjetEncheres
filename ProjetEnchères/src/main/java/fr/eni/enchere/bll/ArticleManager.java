package fr.eni.enchere.bll;

import java.sql.SQLException;
import java.util.List;

import fr.eni.enchere.bo.Article;
import fr.eni.enchere.dal.ArticleDAO;
import fr.eni.enchere.dal.DAOFactory;

public class ArticleManager {
 private static ArticleManager instance=null;
 private ArticleDAO articleDAO;
 
 public ArticleManager(ArticleDAO articleDAO2) {
	this.articleDAO = articleDAO2;
}

public static ArticleManager getInstance() {
		if (instance == null) {
			instance = new ArticleManager(DAOFactory.getArticleDAO());

		}
		return instance;
	}

public List<Article> selectAll(){
	return articleDAO.selectAll();
}


public void ajouter(Article a) throws SQLException {
	articleDAO.ajouter(a);
}
}
