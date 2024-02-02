<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Confirmation de création d'article</title>
</head>
<body>
<%@ include file = "navbar.jsp" %>
     <section class="main">
<table border="1">
    <tr>
        <th>Article:</th>
        <th>Description</th>
        <th>Catégorie</th>
        <th>Mise à prix:</th>
        <th>Début de l'enchère</th>
        <th>Fin de l'enchère</th>
    </tr>
    <tr>
        <td>${article.nom_Article}</td>
        <td>${article.description}</td>
        <td>${article.categorie}</td>
        <td>${article.prix_initial}</td>
        <td>${article.date_debut_encheres}</td>
        <td>${article.date_fin_encheres}</td>
      
    </tr>
    </table>
    </section>
</body>
</html>
