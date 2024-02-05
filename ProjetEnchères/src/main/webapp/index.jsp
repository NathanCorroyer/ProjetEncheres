<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page import="fr.eni.enchere.bo.Article" %>
<%@page import="fr.eni.enchere.bo.Utilisateur" %>
<%@page import="java.util.List" %>
<%@page import="javax.servlet.RequestDispatcher" %>
<%@page import="fr.eni.enchere.bo.Categorie" %>
<%@page import ="java.time.format.DateTimeFormatter"%>
<%@ page import="java.time.LocalDateTime" %>

<%! 
    String formatLocalDateTime(LocalDateTime localDateTime, String pattern) {
        return localDateTime.format(java.time.format.DateTimeFormatter.ofPattern(pattern));
    }
%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ENI-Enchères</title>
   <link rel="icon" href="https://capecia-formations.fr/wp-content/uploads/2019/09/LogoENIcertification-print.png" type="image/x-icon">

    <link rel="stylesheet" href="styles/style.css">
	<script src="javascript/hideParagraph.js"></script>

    
</head>
<body>
    
    <!--  Navbar -->
    <%@ include file = "WEB-INF/jsp/navbar.jsp" %>
<section class="main">
    <a href="${pageContext.request.contextPath}/ServletAffichageListeUtilisateurs">Liste Utilisateurs</a>
    
    <c:if test="${not empty requestScope.succesSuppression}">
    	<% request.setAttribute("succesSuppression", "Votre compte a bien été supprimé"); %>
    	<p style="color : green" id ="succesSuppression">${requestScope.succesSuppression}</p>
    	<script>fadeOut("succesSuppression")</script>
    </c:if>
    <c:if test="${not empty requestScope.erreurSuppression}">
   		 <% request.setAttribute("erreurSuppression", "Erreur lors de la suppression de votre compte"); %>
    	<p style="color : red" id ="erreurSuppression">${requestScope.erreurSuppression}</p>
    	<script>fadeOut("erreurSuppression")</script>
    </c:if>
    <c:if test="${not empty requestScope.succes_creation}">
    	<% request.setAttribute("succes_creation", "Votre compte a bien été créé"); %>
    	<p style="color : green" id="succes_creation">${requestScope.succes_creation}</p>
    	<script>fadeOut("succes_creation")</script>
    </c:if>
    <h2>Liste des enchères</h2>
    <!-- Barre de recherche déplacée en dessous de "Filtres :" -->
        
    <h4>Catégorie :</h4>
    <% List<Categorie> listeCategorie = (List<Categorie>) request.getAttribute("listeCategorie");%>
    <% List<Article> listeArticles = (List<Article>) request.getAttribute("listeArticles");
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    %>   
	<c:if test="${listeCategorie eq null or listeArticles eq null}">
		<%
		String contextPath = request.getContextPath();
		request.getRequestDispatcher("/ServletRecuperationListeEncheres").forward(request,response); 
		%>
	</c:if>
	<c:if test="${listeCategorie ne null}">
		<form action="${pageContext.request.contextPath}/ServletRecuperationListeEncheres" method="POST">
	    	<select class="category-dropdown search-form" name="categorie">
		   		<option value="all" ${empty categorie || categorie eq -1 ? 'selected' :''}>Toutes</option>
		       		<c:forEach var="c" items="${listeCategorie}">      
		       			 <option value="${c.no_categorie}" ${c.no_categorie eq categorie ? 'selected' : ''}>${c.libelle}</option>
		         	</c:forEach>
	      </select>
	       	<input type="text" name="search" placeholder="Le nom de l'article contient">	
     		<input type="submit" value="Filtrer">
     	</form> 
     </c:if>
      
  	
  	
	
	<!--<img src="${a.imagePath}" alt="Image de l'annonce">  -->
	<c:if test="${listeArticles ne null}">
		<section class="annonces">
		<c:forEach var="a" items="${listeArticles}">
			<c:set var="localDateTime" value="${a.getDate_fin_encheres()}" />
        	<ul>
           	 <li>
	             
	              <div class="annonce-details">
	                  <h4>${a.getNom_Article()}</h4>
	                  <p>Prix : ${a.getPrix_initial()} points</p>
	                  <p>Fin de l'enchère : <%= formatLocalDateTime((LocalDateTime) pageContext.getAttribute("localDateTime"), "EEEE, dd MMMM yyyy, HH 'h' mm") %></p>
	                  <p>Vendeur : <a href="${pageContext.request.contextPath}/ServletAffichantProfilVendeur?userPseudo=${a.getUtilisateur().getPseudo()}"> ${a.getUtilisateur().getPseudo()} </a></p>
	                  <p>Numéro d'article : ${a.getNoArticle()}
	               </div>
	         </li>

        	</ul>
	</c:forEach>
    </section>
	</c:if>
    </section>
</body>
</html>