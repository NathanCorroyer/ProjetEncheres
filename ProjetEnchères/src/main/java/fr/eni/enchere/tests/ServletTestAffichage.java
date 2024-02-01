package fr.eni.enchere.tests;

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
import fr.eni.enchere.bo.Article;

/**
 * Servlet implementation class ServletTestAffichage
 */
@WebServlet("/ServletTestAffichage")
public class ServletTestAffichage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("redirected") == null) {
			System.out.println("JE PASSE DANS LA SERVLET");
			List<Article> listeArticles = new ArrayList<>();
			ArticleManager am = ArticleManager.getInstance();
			System.out.println("Attribut referer : " +  request.getAttribute("referer").toString());
			listeArticles = am.selectAll();
			request.setAttribute("listeArticles", listeArticles);
			String referer = (String) request.getAttribute("referer");
			String redirected = "true";
			request.setAttribute("redirected", redirected);
		System.out.println(referer);
		RequestDispatcher rd = request.getRequestDispatcher(referer);
		rd.forward(request, response);

			
		}else {
			
			System.out.println("Je passe dans le ELSE");
			String requestURI = request.getRequestURI();
		    
			// Récupérer le chemin du contexte de l'application
			String contextPath = request.getContextPath();

			// Récupérer la portion de l'URL après l'URL de base du projet
			String relativeURL = requestURI.substring(contextPath.length());
			request.setAttribute("redirected", true);
			RequestDispatcher rd = request.getRequestDispatcher(relativeURL);
			rd.forward(request, response);
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
