<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page import ="java.time.format.DateTimeFormatter"%>
<%@ page import="java.time.LocalDateTime" %>
<html>
<head>
    <link rel="stylesheet" href="styles/styledetailsarticle.css">
	<%! 
    String formatLocalDateTime(LocalDateTime localDateTime, String pattern) {
        return localDateTime.format(java.time.format.DateTimeFormatter.ofPattern(pattern));
    }
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	%>
    <c:set var="localDateTime" value="${article.getDate_fin_encheres()}" />
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
                <p>Fin de l'enchère : <%= formatLocalDateTime((LocalDateTime) pageContext.getAttribute("localDateTime"), "EEEE, dd MMMM yyyy, HH 'h' mm") %></p>
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
