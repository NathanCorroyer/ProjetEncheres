package fr.eni.enchere.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.enchere.bll.UtilisateurManager;
<<<<<<< HEAD
import fr.eni.enchere.bo.Utilisateur;
=======
>>>>>>> branch 'main' of https://github.com/NathanCorroyer/ProjetEncheres.git

/**
 * Servlet implementation class ServletRegisterUtilisateur
 */
@WebServlet("/register")
public class ServletRegisterUtilisateur extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/register.jsp");
			rd.forward(request, response);
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
<<<<<<< HEAD
		Utilisateur um = new UtilisateurManager.getInstance();
		
=======
		UtilisateurManager um = UtilisateurManager.getInstance();
<<<<<<< HEAD
		Request.getParameter
>>>>>>> branch 'main' of https://github.com/NathanCorroyer/ProjetEncheres.git
=======
>>>>>>> branch 'main' of https://github.com/NathanCorroyer/ProjetEncheres.git
	}

}
