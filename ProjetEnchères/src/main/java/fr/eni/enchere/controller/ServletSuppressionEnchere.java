package fr.eni.enchere.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.enchere.bll.ArticleManager;
import fr.eni.enchere.bll.EnchereManager;
import fr.eni.enchere.bll.RetraitManager;
import fr.eni.enchere.bo.Article;
import fr.eni.enchere.bo.Utilisateur;

/**
 * Servlet implementation class ServletSuppressionEnchere
 */
@WebServlet("/ServletSuppressionEnchere")
public class ServletSuppressionEnchere extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int noArticle =  Integer.parseInt(request.getParameter("noArticle"));
		int noUtilisateur = Integer.parseInt(request.getParameter("noUser"));
		ArticleManager am = ArticleManager.getInstance();
		RetraitManager rm = RetraitManager.getInstance();
		rm.deleteByNoArticle(noArticle);
		am.deleteSingleArticleFromUser(noUtilisateur, noArticle);
		
		request.setAttribute("suppression_enchere_reussie", "Enchère Supprimée avec succès.");
		RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
		rd.forward(request, response);
		
		
	}

}
