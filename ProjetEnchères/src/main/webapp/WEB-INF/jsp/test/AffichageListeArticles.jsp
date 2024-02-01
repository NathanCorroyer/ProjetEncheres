<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="fr.eni.enchere.bo.Article" %>
<%@page import="fr.eni.enchere.bo.Utilisateur" %>
<%@page import="java.util.List" %>
<%@page import="javax.servlet.RequestDispatcher" %>
<link rel="stylesheet" href="styles/style.css">
<c:if test="${empty redirected}">
	
	<% 
	session.setAttribute("callerPage","/WEB-INF/jsp/test/AffichageListeArticles.jsp");
 	RequestDispatcher dispatcher = request.getRequestDispatcher("/ServletTestAffichage");
	dispatcher.forward(request,response); 
	
	
	%>
</c:if>
	<% List<Article> listeArticles = (List<Article>) request.getAttribute("listeArticles");%>
	<c:forEach var="a" items="${listeArticles}">
		<section class="annonces">
        <ul>
            <li>
                <img src="https://i.imgur.com/sGTINHD.jpg" alt="Image de l'annonce">
                <div class="annonce-details">
                    <h4>${a.getNom_Article()}</h4>
                    <p>Prix : ${a.getPrix_initial()} points</p>
                    <p>Fin de l'enchère : ${a.getDate_fin_encheres() }</p>
                    <p>Vendeur : ${a.getVendeur().getPseudo()}</p>
                </div>
            </li>

        </ul>
    </section>
	</c:forEach>
