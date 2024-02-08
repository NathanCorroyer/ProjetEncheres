package fr.eni.enchere.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.enchere.bll.UtilisateurManager;
import fr.eni.enchere.bo.MD5;
import fr.eni.enchere.bo.Utilisateur;

/**
 * Servlet implementation class ServletModifierMotDePasse
 */
@WebServlet("/modifierMotDePasse")
public class ServletModifierMotDePasse extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(request.getContextPath()+"/WEB-INF/jsp/updatePassword.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String newPassword = request.getParameter("password");
		Integer no_utilisateur = ((Utilisateur)request.getSession().getAttribute("userConnected")).getNoUtilisateur();
		
		if(no_utilisateur != null && newPassword != null) {
			UtilisateurManager um = UtilisateurManager.getInstance();
			um.updatePassword(MD5.getMd5(newPassword), no_utilisateur);
		}
		
		request.setAttribute("succesModifMdp", "Modification du mot de passe réussie");
		RequestDispatcher rd = request.getRequestDispatcher("/login");
		rd.forward(request, response);
	}

}
