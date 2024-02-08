<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Votre mail?</title>
</head>
<body>
	<h2>Réinitialisation de mot de passe</h2>
		<form id="formulaireModif" action="${pageContext.request.contextPath}/modifierMotDePasse" method = "POST">
		<label for="newPassword">Votre mail : </label>
		<input type = "text" name="mail" id="mail">
		<button type="submit" >Valider</button>
	</form>
</body>
</html>