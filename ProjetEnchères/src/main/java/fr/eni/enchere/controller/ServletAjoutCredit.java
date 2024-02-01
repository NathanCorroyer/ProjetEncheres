package fr.eni.enchere.controller;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.enchere.bll.UtilisateurManager;
import fr.eni.enchere.bo.Utilisateur; 

@WebServlet("/ServletAjoutCredit")
public class ServletAjoutCredit extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	
    	Utilisateur connectedUser = (Utilisateur) request.getSession().getAttribute("userConnected");
    	String paramRecup = request.getParameter("nombreCredit");
    	Integer noUtilisateur = connectedUser.getNoUtilisateur();
    	int creditsAjoutes = Integer.parseInt(paramRecup);
    	int anciensCredits = connectedUser.getCredit();
    	int nouveauxCredits = anciensCredits + creditsAjoutes ;
   
    	connectedUser.setCredit(connectedUser.getCredit() + creditsAjoutes );
    	
    	try {
    	
    	UtilisateurManager.getInstance().update(connectedUser);
    	request.setAttribute( "user" , connectedUser );
    	RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/mon_profil.jsp");
    	
			rd.forward(request, response);
		} catch (ServletException | IOException | SQLException e) {
			e.printStackTrace();
		}
    	
    	
}
}