package fr.eni.enchere.controller;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse; 

@WebServlet("/ServletAjoutCredit")
public class ServletAjoutCredit extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException { 
    
        String creditStr = request.getParameter("nombreCredit");

       
        if (creditStr != null && creditStr.matches("\\d+")) {
            int nombreCredit = Integer.parseInt(creditStr);


            String url = "jdbc:sqlserver://localhost:1433;databaseName=ENCHERE_BDD";
            String utilisateurBDD = "votre_utilisateur";
            String motDePasseBDD = "votre_mot_de_passe";

            try (Connection connexion = DriverManager.getConnection(url, utilisateurBDD, motDePasseBDD)) {
               
                String requete = "UPDATE utilisateurs SET credit = credit + ? WHERE id_utilisateur = ?";
                try (PreparedStatement preparedStatement = connexion.prepareStatement(requete)) {
                   
                    int idUtilisateur = 1; 

                    preparedStatement.setInt(1, nombreCredit);
                    preparedStatement.setInt(2, idUtilisateur);

                  
                    preparedStatement.executeUpdate();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                
            }

          
            response.sendRedirect(request.getContextPath() + "/profile.jsp");
        } else {
            
            response.sendRedirect(request.getContextPath() + "/profile.jsp?erreur=credit_invalid");
        }
    }
}
