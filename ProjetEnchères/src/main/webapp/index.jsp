<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ENI-Enchères</title>
    <link rel="stylesheet" href="styles/style.css">
    <link rel="stylesheet" href="styles/navbar.css">
</head>
<body>
    
    <h1>ENI-Encheres</h1>
    <!--  Navbar -->
    <%@ include file = "WEB-INF/jsp/navbar.jsp" %>
    <a href="${pageContext.request.contextPath}/ServletAffichageListeUtilisateurs">Coucou</a>
    
    <c:if test="${not empty requestScope.succesSuppression}">
    	<p style="color : green">${requestScope.succesSuppression}</p>
    </c:if>
    <c:if test="${not empty requestScope.erreurSuppression}">
    	<p style="color : red">${requestScope.erreurSuppression}</p>
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
     <section class="annonces">
        <ul>
            <li>
                <img src="https://i.imgur.com/sGTINHD.jpg" alt="Image de l'annonce">
                <div class="annonce-details">
                    <h4>PC Gamer pas terrible</h4>
                    <p>Prix : 210 points</p>
                    <p>Fin de l'enchère : 01/01/2025</p>
                    <p>Vendeur : Sasusuke</p>
                </div>
            </li>

        </ul>
    </section>
</body>
</html>