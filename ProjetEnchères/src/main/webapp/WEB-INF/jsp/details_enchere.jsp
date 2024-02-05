<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="styles/styledetailsarticle.css">

    
    <title>Détails de l'enchère</title>
</head>
<body>
    <%@ include file="navbar.jsp" %>

    <section class="main">
        <h2>Détails de l'enchère</h2>

        <c:if test="${not empty article}">
            <div class="annonce-details">
                <h4>${article.getNom_Article()}</h4>
                <p>Description : ${article.getDescription()}</p>
                <p>Catégorie : ${article.getCategorie()}</p>
                <p> Début de l'enchère : ${article.getDate_debut_encheres()}</p>
                <p>Fin de l'enchère : ${article.getDate_fin_encheres()}</p>
                <p>Prix initial : ${article.getPrix_initial()} points</p>
                <h4>Retrait : </h4>
                <p> Adresse : ${Vendeur.getRue()}, ${Vendeur.getCode_postal()}, ${Vendeur.getVille()}  </p>
                <p> Pseudo du vendeur : ${Vendeur.getPseudo()} </p>
                
                
                
            </div>
        </c:if>

       
        <c:if test="${empty article}">
            <p>Les détails de l'enchère ne sont pas disponibles.</p>
        </c:if>
    </section>
</body>
</html>
