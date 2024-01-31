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
import fr.eni.enchere.dal.ArticleDAO;
import fr.eni.enchere.dal.ArticleDAOJdbcImpl;
import fr.eni.enchere.dal.DAOFactory;


@WebServlet("/TestCreationArticle")
public class TestCreationArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		Article a = new Article("Poudre de cacao", "Tu sais lire", LocalDate.of(2023, 12, 5), LocalDate.of(2024,01,25),50,150,9,2);
//		Article art = new Article("Poudre de caca", "Tu sais pas lire?", LocalDate.of(2023, 10, 2), LocalDate.of(2024,01,15),70,180,9,2);
		ArticleDAO aDAO = DAOFactory.getArticleDAO();
//	try {
//			aDAO.ajouter(art);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		List<Article> listeArticles = aDAO.selectAll();
		for(Article a : listeArticles) {
			System.out.println(a.toString());
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
