package fr.eni.enchere.controller;

import java.io.IOException;
import java.sql.SQLException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import fr.eni.enchere.bo.Article;
import fr.eni.enchere.bo.Enchere;
import fr.eni.enchere.bo.Retrait;
import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.bll.ArticleManager;
import fr.eni.enchere.bll.BLLException;
import fr.eni.enchere.bll.EnchereManager;
import fr.eni.enchere.bll.RetraitManager;
import fr.eni.enchere.bll.UtilisateurManager;


@WebServlet("/ServletDetailsEnchere")
public class ServletDetailsEnchere extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int no_article = Integer.parseInt(request.getParameter("no_article"));
		Article article = null;
		Retrait retrait = null;
		UtilisateurManager um = UtilisateurManager.getInstance(); 
		Utilisateur vendeur = null;
		 
		try {
		vendeur = um.selectUserByPseudo(request.getParameter("nomVendeur"));
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (BLLException e) {
			e.printStackTrace();
		}
		
		ArticleManager articleManager = ArticleManager.getInstance();
		RetraitManager retraitManager = RetraitManager.getInstance();		
		Integer enchereProposee = null ;	
		 try {
			  article = articleManager.selectArticleById(no_article);
			  retrait = retraitManager.selectByArticle(article);
			  enchereProposee = article.getPrix_initial() + 1 ;
			 } catch (SQLException e) {
			   e.printStackTrace();
			            
			 }
		request.setAttribute("prixInitialEnchere", enchereProposee);
		request.setAttribute("Vendeur",vendeur);				
		request.setAttribute("article", article);
		request.setAttribute("retrait", retrait);
		request.getRequestDispatcher("/WEB-INF/jsp/details_enchere.jsp").forward(request, response);
		}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}