<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
    <%@page import ="java.time.format.DateTimeFormatter"%>
<%@ page import="java.time.LocalDateTime" %>
<%! 
    String formatLocalDateTime(LocalDateTime localDateTime, String pattern) {
        return localDateTime.format(java.time.format.DateTimeFormatter.ofPattern(pattern));
    }
%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <link rel="stylesheet" href="styles/confirmationarticle.css">
<title>Confirmation de création d'article</title>
</head>
<body>
<%@ include file = "navbar.jsp" %>
<section class="main">

    <% 
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    %>   
<h2><i class="fa-solid fa-circle-check"></i> Votre enchère a été ajouté aves succès !</h2>

<div class="tableau">
<h3>Voici votre récapitulatif :</h3>
<table border="1">
	<c:set var="date-fin" value="${article.date_fin_encheres}" />
	<c:set var="date-debut" value="${article.date_debut_encheres}" />
    <tr>
        <th>Article</th>
        <th>Description</th>
        <th>Catégorie</th>
        <th>Mise à prix</th>
        <th>Début de l'enchère</th>
        <th>Fin de l'enchère</th>
    </tr>
    <tr>
        <td>${article.nom_Article}</td>
        <td>${article.description}</td>
        <td>${article.categorie}</td>
        <td>${article.prix_initial}</td>
        <td><%= formatLocalDateTime((LocalDateTime) pageContext.getAttribute("date-debut"), "EEEE, dd MMMM yyyy, HH 'h' mm") %></td>
        <td><%= formatLocalDateTime((LocalDateTime) pageContext.getAttribute("date-fin"), "EEEE, dd MMMM yyyy, HH 'h' mm") %></td>
      
    </tr>
    </table>
    
</div>
<div id="accueil">
<button type="button" onclick="Acceuil()">Revenir à l'accueil</button>
     <script>
        function Acceuil() {
            window.location.href = '<%=request.getContextPath()%>/index.jsp';
        }
    </script>
 </div>
</section>
</body>
</html>
