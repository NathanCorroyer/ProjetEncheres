<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="fr.eni.enchere.bo.Article" %>
<%@page import="fr.eni.enchere.bo.Utilisateur" %>
<%@page import="java.util.List" %>
<%@page import="javax.servlet.RequestDispatcher" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ENI-Enchères</title>
   <link rel="icon" href="https://capecia-formations.fr/wp-content/uploads/2019/09/LogoENIcertification-print.png" type="image/x-icon">

    <link rel="stylesheet" href="styles/style.css">
	

    
</head>
<body>
    
    <!--  Navbar -->
    <%@ include file = "WEB-INF/jsp/navbar.jsp" %>
<section class="main">
    <a href="${pageContext.request.contextPath}/ServletAffichageListeUtilisateurs">Liste Utilisateurs</a>
    
    <c:if test="${not empty requestScope.succesSuppression}">
    	<p style="color : green">${requestScope.succesSuppression}</p>
    </c:if>
    <c:if test="${not empty requestScope.erreurSuppression}">
    	<p style="color : red">${requestScope.erreurSuppression}</p>
    </c:if>
    <c:if test="${not empty requestScope.succes_creation}">
    	<p style="color : green">${requestScope.succes_creation}</p>
    </c:if>
    <h2>Liste des enchères</h2>
    <!-- Barre de recherche déplacée en dessous de "Filtres :" -->
    <form class="search-form" action="#" method="get">
        <input type="text" name="search" placeholder="Le nom de l'article contient">
    </form>
    
    <h4>Catégorie :</h4>
    <select class="category-dropdown">
        <option value="categorie1">Toutes</option>
        <option value="categorie2">Informatique</option>
        <option value="categorie3">Ameublement</option>
        <option value="categorie4">Vêtement</option>
        <option value="categorie5">Sports et Loisirs</option>
      </select>
  	
  	
	<% List<Article> listeArticles = (List<Article>) request.getAttribute("listeArticles");%>
	<c:if test="${listeArticles eq null}">
		<%response.sendRedirect(request.getContextPath()+"/ServletRecuperationListeEncheres"); %>
	</c:if>
	<c:if test="${listeArticles ne null}">
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
                    <p>Numéro d'article : ${a.getNoArticle()}
                </div>
            </li>

        </ul>
    </section>
	</c:forEach>
	</c:if>
    </section>
</body>
</html>