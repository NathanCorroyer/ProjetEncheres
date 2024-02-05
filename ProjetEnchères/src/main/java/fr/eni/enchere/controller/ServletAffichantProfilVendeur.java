package fr.eni.enchere.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.enchere.bll.BLLException;
import fr.eni.enchere.bll.UtilisateurManager;
import fr.eni.enchere.bo.Utilisateur;

/**
 * Servlet implementation class ServletAffichantProfilVendeur
 */
@WebServlet("/ServletAffichantProfilVendeur")
public class ServletAffichantProfilVendeur extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String pseudoUser = (String) request.getParameter("userPseudo");
		UtilisateurManager um = UtilisateurManager.getInstance();
		Utilisateur userConnected = (Utilisateur) session.getAttribute("userConnected");
		Utilisateur userAffiche = null ;
		
		
		
		if ( userConnected != null ) {
			String pseudoUserConnected = userConnected.getPseudo();
			if ( pseudoUserConnected.equals(pseudoUser) ) {
				request.setAttribute("userConnected", userConnected );
				request.getRequestDispatcher("/WEB-INF/jsp/mon_profil.jsp").forward(request, response);	
			}	else {
					try {
						userAffiche = um.selectUserByPseudo(pseudoUser);
					} catch (SQLException | BLLException e) {
						e.printStackTrace();
				}
				request.setAttribute("userAffiche", userAffiche);
				request.getRequestDispatcher("/WEB-INF/jsp/AffichageUtilisateurVendeur.jsp").forward(request, response);
			}
			
		} else {
			
			try {
				userAffiche = um.selectUserByPseudo(pseudoUser);
			} catch (SQLException | BLLException e) {
				e.printStackTrace();
			}
			request.setAttribute("userAffiche", userAffiche);
			request.getRequestDispatcher("/WEB-INF/jsp/AffichageUtilisateurVendeur.jsp").forward(request, response);
		
		}
	
}
}


