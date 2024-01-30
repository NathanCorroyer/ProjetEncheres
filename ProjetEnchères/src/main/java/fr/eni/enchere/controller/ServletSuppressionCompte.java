package fr.eni.enchere.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.enchere.bll.UtilisateurManager;

/**
 * Servlet implementation class ServletSuppressionCompte
 */
@WebServlet("/ServletSuppressionCompte")
public class ServletSuppressionCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UtilisateurManager um = UtilisateurManager.getInstance();
		String mail = request.getParameter("mail");
		
		if ( mail != null && !mail.isEmpty() ) {
			try {
				request.getSession().invalidate();
				um.deleteByMail(mail);
				request.setAttribute("succesSuppression", "L'utilisateur a été supprimé avec succès.");
			} catch (SQLException e) {
				e.printStackTrace();
			}		
		} else {
			request.setAttribute("erreurSuppression", "L'adresse e-mail n'a pas été fournie correctement.");
		}
		
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/index.jsp");
		rd.forward(request, response);
	}
}