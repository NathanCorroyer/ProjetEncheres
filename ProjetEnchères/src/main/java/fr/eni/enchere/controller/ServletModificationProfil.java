package fr.eni.enchere.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.enchere.bll.BLLException;
import fr.eni.enchere.bll.UtilisateurManager;
import fr.eni.enchere.bo.MD5;
import fr.eni.enchere.bo.Utilisateur;

/**
 * Servlet implementation class ServletModificationProfil
 */
@WebServlet("/ServletModificationProfil")
public class ServletModificationProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Utilisateur connectedUser = (Utilisateur) request.getSession().getAttribute("userConnected");
		request.setAttribute("user", connectedUser);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/modifier_profil.jsp");
		rd.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utilisateur connectedUser = (Utilisateur) request.getSession().getAttribute("userConnected");
		Integer noUtilisateur = connectedUser.getNoUtilisateur();
		String ancienMotDePasse = MD5.getMd5(request.getParameter("motDePasseActuel"));
		int creditactuel = connectedUser.getCredit();
		
		
		if ( ancienMotDePasse.equals(connectedUser.getPassword())) {
			try {
				Utilisateur user = new Utilisateur( request.getParameter("pseudo"),
						request.getParameter("nom"),
						request.getParameter("prenom"),
						request.getParameter("email"),
						request.getParameter("telephone"),
						request.getParameter("rue"), 
						request.getParameter("codePostal"),
						request.getParameter("ville"),
						MD5.getMd5(request.getParameter("motDePasse")));
						user.setNoUtilisateur(noUtilisateur);
						user.setCredit(creditactuel);
				UtilisateurManager.getInstance().update(user);
			    request.getSession().setAttribute( "userConnected" , user );
				request.setAttribute("succesModif", "Toutes les modifications ont été prises en compte.");
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/mon_profil.jsp");
				rd.forward(request, response);	
			
		} catch (SQLException | BLLException e) {
			e.printStackTrace();
		}
		} else {		
			Integer idUser = connectedUser.getNoUtilisateur();	
			UtilisateurManager um = UtilisateurManager.getInstance();
			Utilisateur userAffiche = null;
			
				try {
					userAffiche= um.selectUserByNumero(idUser);
					
				} catch (SQLException | BLLException e) {
					e.printStackTrace();
				}
			request.setAttribute("userConnected", userAffiche );
			request.setAttribute("erreurModif", "L'ancien mot de passe que vous avez entré ne correspond pas.");
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/mon_profil.jsp");
			rd.forward(request, response);
		}
		
		//response.sendRedirect("mon_profil");
																		
		
	}

}
