<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modification de catégorie</title>
</head>
<body>
	<%@ include file = "/WEB-INF/jsp/navbar.jsp" %>
	
	<form action="${pageContext.request.contextPath}/modifierCategorie" method="POST">
		<input type="hidden" name="no_categorie" value="${no_categorie}"/>
		<label for="no_categorie">Libellé de la categorie : </label>
		<input type = "text" name = "libelleModifie" value="${libelle}" />
		<button type="submit">Enregister la modification</button>
		<button> <a href="${pageContext.request.contextPath}/ServletAffichageListeCategories">Annuler la modification</a></button>
	</form>
</body>
</html>