package fr.eni.enchere.tests;

import java.io.IOException;
import java.sql.SQLException;
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
@WebServlet("/ServletRecuperationListeEncheresTestTri")
public class ServletRecuperationListeEncheresTestTri extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getAttribute("succes_creation") != null) {
			request.setAttribute("succes_creation", request.getAttribute("succes_creation"));
		}
		if(request.getAttribute("succesSuppression") != null) {
			request.setAttribute("succesSuppression", request.getAttribute("succesSuppression"));
		}
		if(request.getAttribute("erreurSuppression") != null) {
			request.setAttribute("erreurSuppression", request.getAttribute("erreurSuppression"));
		}
		
		CategorieManager cm = CategorieManager.getInstance();
		List<Categorie> listeCategorie = cm.selectAll(); 
        request.setAttribute("listeCategorie" , listeCategorie);
		
        
    	List<Article> listeArticles = new ArrayList<>();
    	ArticleManager am = ArticleManager.getInstance();
    	
        String categorie = request.getParameter("categorie");
        Integer no_categorie = null;
        if(categorie != null) {
        	if(categorie.equals("all")) {
        		no_categorie = -1;
        	}else {
        		no_categorie = Integer.parseInt(request.getParameter("categorie"));        		
        	}
        }
        
        
        System.out.println(no_categorie);
        
        
        String nomTri = request.getParameter("search");
        System.out.println("nomtri : " + nomTri);
        if(categorie == null || categorie.equals("all")) {
        	if(nomTri == null || nomTri.trim().equals("")) {
        		System.out.println("Je passe dans le selectAll");
        		listeArticles = am.selectAll();
        	}else {
        		try {
        			System.out.println("Je passe au selectByName");
					listeArticles = am.selectByName(nomTri.trim());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        }else {
        	if(nomTri == null || nomTri.trim().equals("")) {
	        	try {
	        		System.out.println("Je passe dans le selectArticleByCategorie");
					listeArticles = am.selectArticleByCategorie(Integer.parseInt(request.getParameter("categorie")));
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		}else {
        		try {
        			System.out.println("Je passe dans le selectArticleByCategorieAndByName");
					listeArticles = am.selectArticleByCategorieAndByName(Integer.parseInt(request.getParameter("categorie")),nomTri.trim());
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        }
        
        request.setAttribute("categorie", no_categorie);
		request.setAttribute("listeArticles", listeArticles);
		RequestDispatcher rd = request.getRequestDispatcher("/indextest.jsp");
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
