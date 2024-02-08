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
import fr.eni.enchere.bo.Categorie;

/**
 * Servlet implementation class ServletAjouterCategorie
 */
@WebServlet("/ajouterCategorie")
public class ServletAjouterCategorie extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String libelle = request.getParameter("newLibelle");
		if(libelle != null) {
			boolean ajout = false;
		
			CategorieManager cm = CategorieManager.getInstance();
			try {
				ajout = cm.ajouter(libelle);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(ajout == true) {
				request.setAttribute("succesAjout", "Catégorie ajoutée !");				
			}else {
				request.setAttribute("echecAjout", "L'ajout de catégorie a échoué!");
			}
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/listeCategories.jsp");
			rd.forward(request, response);
			
		}else {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/ajouterCategorie.jsp");
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
