package fr.eni.enchere.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.enchere.bll.ArticleManager;
import fr.eni.enchere.bll.CategorieManager;
import fr.eni.enchere.bo.Article;
import fr.eni.enchere.bo.Categorie;

/**
 * Servlet implementation class ServletRecuperationListeEncheres
 */
@WebServlet("/ServletRecuperationListeEncheres")
public class ServletRecuperationListeEncheres extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(request.getAttribute("succes_creation"));
		System.out.println(request.getAttribute("succesSuppression"));
		System.out.println(request.getAttribute("erreurSuppression"));
		if(request.getAttribute("succes_creation") != null) {
			request.setAttribute("succes_creation", request.getAttribute("succes_creation"));
		}
		if(request.getAttribute("succesSuppression") != null) {
			request.setAttribute("succesSuppression", request.getAttribute("succesSuppression"));
		}
		if(request.getAttribute("erreurSuppression") != null) {
			request.setAttribute("erreurSuppression", request.getAttribute("erreurSuppression"));
		}
		
		System.out.println("JE PASSE DANS LA SERVLET");
		List<Article> listeArticles = new ArrayList<>();
		ArticleManager am = ArticleManager.getInstance();
		listeArticles = am.selectAll();
		CategorieManager cm = CategorieManager.getInstance();
		List<Categorie> listeCategorie = cm.selectAll(); 
        request.setAttribute("listeCategorie" , listeCategorie);

		request.setAttribute("listeArticles", listeArticles);
		RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
		rd.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
