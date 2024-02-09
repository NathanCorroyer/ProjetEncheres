package fr.eni.enchere.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.enchere.bll.ArticleManager;
import fr.eni.enchere.bll.EnchereManager;
import fr.eni.enchere.bo.Article;
import fr.eni.enchere.bo.Enchere;
import fr.eni.enchere.bo.Utilisateur;

/**
 * Servlet implementation class ServletMesEncheres
 */
@WebServlet("/mes_encheres")
public class ServletMesEncheres extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utilisateur userConnected = (Utilisateur) request.getSession().getAttribute("userConnected");
		ArticleManager am = ArticleManager.getInstance();
		EnchereManager em = EnchereManager.getInstance();
		List<Enchere> listeEncheres = em.selectByNoUtilisateur(userConnected.getNoUtilisateur());
		List<Article> listeArticles = new ArrayList<>();
		List<Integer> listeInt = new ArrayList<>();
		
		String tri = request.getParameter("tri");
		
		if(tri == null || tri.equals("ventes")) {
			List<Article> listePlaceholder = trierParNoUtilisateur(am.selectAll(), userConnected.getNoUtilisateur());
			LocalDateTime now = LocalDateTime.now();
			for(Article a : listePlaceholder) {
				if(a.getDate_fin_encheres().isAfter(now)) {
					listeArticles.add(a);
				}
			}
		
		}else {

			for (Enchere enchere : listeEncheres) {
				if(!(listeInt.contains(enchere.getArticle().getNoArticle()))) {
					listeInt .add(enchere.getArticle().getNoArticle());
					try {
						listeArticles .add(am.selectArticleById(enchere.getArticle().getNoArticle()));
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		
		request.setAttribute("listeArticles", listeArticles);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/mesEncheres.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	
	// -------------------------- TRI D'UNE LISTE PAR NO-UTILISATEUR --------------------------------
			protected List<Article> trierParNoUtilisateur (List<Article> listeArticles, int no_utilisateur){
				List<Article> listeResult = new ArrayList<>();
				for(Article a : listeArticles) {
					if(a.getNo_utilisateur() == no_utilisateur) {
						listeResult.add(a);				
						}
				}
				return listeResult;
			}
}
