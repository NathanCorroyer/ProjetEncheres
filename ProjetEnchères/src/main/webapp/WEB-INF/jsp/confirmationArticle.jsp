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
        <th>Rue</th>
        <th>Code postal</th>
        <th>Ville</th>
    </tr>
    <tr>
        <td>${a.nom_article}</td>
        <td>${a.description}</td>
        <td>${a.no_categorie}</td>
        <td>${a.prix_initial}</td>
        <td>${a.date_debut_encheres}</td>
        <td>${a.date_fin_encheres}</td>
        <td>${a.rue}</td>
        <td>${a.code_postal}</td>
        <td>${a.ville}</td>
    </tr>
    </table>
    </section>
</body>
</html>
