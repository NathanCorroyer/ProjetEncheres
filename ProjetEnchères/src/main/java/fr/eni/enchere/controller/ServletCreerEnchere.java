package fr.eni.enchere.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.enchere.bo.Utilisateur;

/**
 * Servlet implementation class ServletCreerEnchere
 */
@WebServlet("/creer_enchere")
public class ServletCreerEnchere extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 	ArticleManager a;
		 	Utilisateur user = (Utilisateur) request.getSession().getAttribute("userConnected");
		 	int numeroVendeur = user.getNoUtilisateur();
			String nom = request.getParameter("nom_article");
	        String description = request.getParameter("description");
	        String categorie = request.getParameter("no_categorie");
	        int prixInitial = Integer.parseInt(request.getParameter("prix_initial"));
	        String dateDébut = request.getParameter("date_debut_encheres");
	        String dateFin = request.getParameter("date_fin_encheres");
	        String modalitesRetrait = request.getParameter("modalitesRetrait");

	        
	        response.sendRedirect("confirmation.jsp");
	}

}
