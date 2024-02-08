package fr.eni.enchere.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.enchere.bll.CategorieManager;

/**
 * Servlet implementation class ServletSupprimerCategorie
 */
@WebServlet("/supprimerCategorie")
public class ServletSupprimerCategorie extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer no_categorie = Integer.parseInt(request.getParameter("numero"));
		boolean isDeleted = false;
		if(no_categorie != null) {
			CategorieManager cm = CategorieManager.getInstance();
			try {
				isDeleted = cm.supprimer(no_categorie);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(isDeleted == true) {
			request.setAttribute("succesSuppression", "La suppression de la catégorie est un succès !");			
		}else {
			request.setAttribute("echecSuppression", "La suppression a échoué, cette catégorie a sûrement des articles qui lui sont associés !");
		}
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/listeCategories.jsp");
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
