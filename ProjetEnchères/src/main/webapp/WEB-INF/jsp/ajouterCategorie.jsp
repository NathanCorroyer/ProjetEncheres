<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ajout de catégorie</title>
</head>
<body>
	<%@ include file = "/WEB-INF/jsp/navbar.jsp" %>
	
	<form action="${pageContext.request.contextPath}/ajouterCategorie" method="POST">
		<label for="no_categorie">Libellé de la categorie : </label>
		<input type = "text" name = "newLibelle"/>
		<button type="submit">Ajouter</button>
		<button> <a href="${pageContext.request.contextPath}/ServletAffichageListeCategories">Annuler</a></button>
	</form>
</body>
</html>