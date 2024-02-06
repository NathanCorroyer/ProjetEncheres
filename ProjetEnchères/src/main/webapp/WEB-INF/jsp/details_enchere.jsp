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
    <c:set var="localDateTimeDebut" value="${article.getDate_debut_encheres()}" />
    <title>Détails de l'enchère</title>
    <style>
        .image-container {
            border: 2px solid black; 
            padding: 10px; 
            display: inline-block; 
            margin-bottom: 8%;
        }
        
        .description {
        	border: 1px dashed grey;
        	margin-bottom: 7%;
        }
        .prix_initial {
        	margin-top: 10%;
        }
        .retrait {
        	margin-top: 10%
        }
        
        .encherir-bouton {
    		background-color: #4CAF50; 
    		border: none; 
   			 color: white; 
    		padding: 10px 20px; 
   			text-align: center; 
    		text-decoration: none; 
   			 display: inline-block; 
   			 font-size: 16px; 
   			 margin: 4px 2px; 
   			 cursor: pointer; 
    		border-radius: 8px; 
}
    </style>
</head>
<body>
    <%@ include file="navbar.jsp" %>

    <section class="main">
        <h2>Détails de l'enchère</h2>

        <c:if test="${not empty article}">
            <div class="annonce-details">                    	
                <h4>${article.getNom_Article()}</h4>
                <div class="image-container">
                <img src="${article.getImagePath()}" width="300" height="300" alt="TestImage">                
                </div>         
                <div class="description">               
                <p>Description : </p>
                <p>${article.getDescription()}</p>
                </div>
                <p>Catégorie : ${article.getCategorieComplete().getLibelle()}</p>
                <p> Début de l'enchère : <%= formatLocalDateTime((LocalDateTime) pageContext.getAttribute("localDateTimeDebut"), "EEEE, dd MMMM yyyy, HH 'h' mm") %></p>
                <p>Fin de l'enchère : <%= formatLocalDateTime((LocalDateTime) pageContext.getAttribute("localDateTime"), "EEEE, dd MMMM yyyy, HH 'h' mm") %></p>
                <p>Prix initial : ${article.getPrix_initial()} points</p>
                <hr>
                <h4>Retrait : </h4>
                <p> Adresse : ${Vendeur.getRue()}, ${Vendeur.getCode_postal()}, ${Vendeur.getVille()}  </p>
                <p> Pseudo du vendeur : ${Vendeur.getPseudo()} </p>
                <hr>
                <div class="retrait">
                <label for="prix_initial">Ma proposition :</label>
                </div>
                <br>           
        <input type="number" name="prix_initial" required> 
        <br>        
                <button class="encherir-bouton">Enchérir</button>
            </div>
        </c:if>

       
        <c:if test="${empty article}">
            <p>Les détails de l'enchère ne sont pas disponibles.</p>
        </c:if>
    </section>
</body>
</html>
