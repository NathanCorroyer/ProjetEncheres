package fr.eni.enchere.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.enchere.bll.UtilisateurManager;
import fr.eni.enchere.bo.Utilisateur;

/**
 * Servlet implementation class ServletAffichageListeUtilisateurs
 */
@WebServlet("/ServletAffichageListeUtilisateurs")
public class ServletAffichageListeUtilisateurs extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*if ( request.getSession().getAttribute("userConnected") != null ) {*/
			List<Utilisateur> listeUtilisateurs ; 
				try {
					listeUtilisateurs = UtilisateurManager.getInstance().findAll();
					request.setAttribute("listeUtilisateurs", listeUtilisateurs );
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/listeUtilisateurs.jsp");
					rd.forward(request, response);
				} catch (SQLException e) {
					e.printStackTrace();
				}
	//}
	}

}
