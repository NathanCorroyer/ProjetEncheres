package fr.eni.enchere.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.enchere.bll.CategorieManager;
import fr.eni.enchere.bo.Categorie;

/**
 * Servlet implementation class ServletModifierCategorie
 */
@WebServlet("/modifierCategorie")
public class ServletModifierCategorie extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("libelleModifie") != null) {
			CategorieManager cm = CategorieManager.getInstance();
			Categorie cat = new Categorie(Integer.parseInt(request.getParameter("no_categorie")),request.getParameter("libelleModifie"));
			cm.update(cat);
			request.setAttribute("succesModification", "La catégorie a été modifiée avec succès");
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/listeCategories.jsp");
			rd.forward(request, response);
			
		}else {
			request.setAttribute("no_categorie", request.getParameter("numero"));
			request.setAttribute("libelle", request.getParameter("libelle"));
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/modifierCategorie.jsp");
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
