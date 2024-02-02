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
		UtilisateurManager um = UtilisateurManager.getInstance();
		Utilisateur newUser = new Utilisateur(
				request.getParameter("pseudo"),
				request.getParameter("nom"),
                request.getParameter("prenom"),
                request.getParameter("email"),
                request.getParameter("telephone"),
                request.getParameter("rue"),
                request.getParameter("codePostal"),
                request.getParameter("ville"),
                request.getParameter("motDePasse")
                
                );
		try {
			
			String email = newUser.getEmail();
			String motDePasse =newUser.getPassword();
			um.register(newUser);
			request.setAttribute("email",email);
			request.setAttribute("motDePasse", motDePasse);
			request.setAttribute("succes_creation", "Votre compte a été créé avec succès.");
			request.getRequestDispatcher("/login").forward(request, response);
			
		} catch (SQLException | BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
