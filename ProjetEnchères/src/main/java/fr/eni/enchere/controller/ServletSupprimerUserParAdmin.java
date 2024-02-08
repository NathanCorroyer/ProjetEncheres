package fr.eni.enchere.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.enchere.bll.BLLException;
import fr.eni.enchere.bll.UtilisateurManager;
import fr.eni.enchere.bo.Utilisateur;

/**
 * Servlet implementation class ServletSupprimerUserParAdmin
 */
@WebServlet("/supprimerUser")
public class ServletSupprimerUserParAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utilisateur user = 	(Utilisateur) request.getSession().getAttribute("userConnected");
		String email = request.getParameter("email");
		UtilisateurManager um = UtilisateurManager.getInstance();
		Utilisateur userSupprime = null;
		
		if ( user.isAdministrateur()) {
			try {
				um.deleteByMail(email);
			} catch (BLLException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			userSupprime = um.selectUserByMail(email);
		} catch (SQLException | BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(userSupprime == null) {
			request.setAttribute("succesSuppression", "L'utilisateur a été supprimé avec succès.");
		}else {
			request.setAttribute("erreurSuppression", "Problème lors de la suppression du compte.");
		}
			
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/listeUtilisateurs.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
