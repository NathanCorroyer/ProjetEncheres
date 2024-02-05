package fr.eni.enchere.tests;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.enchere.bo.Article;
import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.dal.ArticleDAO;
import fr.eni.enchere.dal.ArticleDAOJdbcImpl;
import fr.eni.enchere.dal.DAOFactory;
import fr.eni.enchere.dal.UtilisateurDAO;


@WebServlet("/TestCreationArticle")
public class TestCreationArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Article a = new Article("Ordinateur", "Un ordinateur", LocalDate.of(2023, 12, 5), LocalDate.of(2024,01,25),50,150,9,2);
		Article art = new Article("Clavier", "Un clavier", LocalDate.of(2023, 10, 2), LocalDate.of(2024,01,15),70,180,9,2);
		Utilisateur acheteur = new Utilisateur("JDER","Derulo","Jason","jder@gmail.com","0222222222" , "KOUKOU", "05205", "Konoha", "glacevanillefraise",0,false);
		ArticleDAO aDAO = DAOFactory.getArticleDAO();
		UtilisateurDAO uDAO = DAOFactory.getUtilisateurDAO();
	try {
			uDAO.register(acheteur);
			aDAO.ajouter(a);
			
		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Article> listeArticles = aDAO.selectAll();
		for(Article article : listeArticles) {
			System.out.println(article.toString());
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
