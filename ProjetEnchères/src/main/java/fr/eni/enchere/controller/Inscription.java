package fr.eni.enchere.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Inscription")
public class Inscription extends HttpServlet {
    private static final long serialVersionUID = 1L;


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/register.jsp");
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

        	
            String nom = request.getParameter("nom");
            String prenom = request.getParameter("prenom");
            String email = request.getParameter("email");
            String motDePasse = request.getParameter("motDePasse");
            String telephone = request.getParameter("telephone");
            String rue = request.getParameter("rue");
            String ville = request.getParameter("ville");
            String codePostal = request.getParameter("codePostal");

            if (nom == null || nom.trim().isEmpty() || email == null || email.trim().isEmpty() /* ... */) {

                response.sendRedirect("erreur.jsp"); 
                return;
            }

   


            response.sendRedirect("inscriptionReussie.jsp");
        } catch (Exception e) {
   
            log("Erreur lors du traitement de la requête POST", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur interne du serveur");
        }
    }
    }
