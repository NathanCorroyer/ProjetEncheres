<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Créer une Enchère</title>
</head>
<body>
<%@ include file = "navbar.jsp" %>

<section class="main">
    <h2>Créer une Enchère</h2>
    
    <form class="enchere-form" action="${pageContext.request.contextPath}/creer_enchere" method="post">
        <label for="nom_article">Nom de l'article :</label>
        <input type="text" name="nom_article" required>

        <label for="description">Description :</label>
        <textarea name="description" required></textarea>

        <label for="no_categorie">Catégorie :</label>
        <input type="number" name="no_categorie" required>

        <label for="prix_initial">Prix de départ (en points) :</label>
        <input type="number" name="prix_initial" required>

        <label for="date_debut_encheres">Date et heure d'ouverture :</label>
        <input type="date" name="date_debut_encheres" required>

        <label for="date_fin_encheres">Date et heure de fermeture :</label>
        <input type="date" name="date_fin_encheres" required>
        
        
<!--
        <label for="modalitesRetrait">Modalités de retrait :</label>
        <textarea name="modalitesRetrait"></textarea>
		 Modalité de retrait à modifier --> 
		 
        <input type="submit" value="Créer Enchère">
    </form>
    
</section>
</body>
</html>
