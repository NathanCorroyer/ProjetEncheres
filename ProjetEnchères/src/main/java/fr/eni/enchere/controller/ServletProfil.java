package fr.eni.enchere.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.enchere.bo.Utilisateur;

@WebServlet("/ServletProfil")
public class ServletProfil extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Utilisateur connectedUser = (Utilisateur) request.getSession().getAttribute("userConnected");


        request.setAttribute("user", connectedUser);

        request.getRequestDispatcher("/WEB-INF/jsp/mon_profil.jsp").forward(request, response);
    }
}
