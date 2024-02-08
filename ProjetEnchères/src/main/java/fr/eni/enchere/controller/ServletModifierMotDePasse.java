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
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/demandeMail.jsp");
		rd.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String newPassword = request.getParameter("newPassword");
		String mail = request.getParameter("mail");
		UtilisateurManager um = UtilisateurManager.getInstance();
		Utilisateur user = null;;
		try {
			user = um.selectUserByMail(mail);
		} catch (SQLException | BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(user == null ) {
			request.setAttribute("erreurMail", "Il n'y a pas de compte associé à ce mail");
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
			rd.forward(request, response);
		}else {
			if(mail == null) {
				
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/demandeMail.jsp");
				rd.forward(request, response);
			}else {
				
				if(newPassword != null) {
					System.out.println("J'update le password ");
					um = UtilisateurManager.getInstance();
					um.updatePassword(MD5.getMd5(newPassword), mail);
					request.setAttribute("succesModifMdp", "Modification du mot de passe réussie");
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
					rd.forward(request, response);
				}else {
					request.setAttribute("mail", mail);
					//System.out.println("Je passe dans l'update password avec l'attribut mail = " + mail);
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/updatePassword.jsp");
					rd.forward(request, response);
				}
			}
		}
		
	}
}

