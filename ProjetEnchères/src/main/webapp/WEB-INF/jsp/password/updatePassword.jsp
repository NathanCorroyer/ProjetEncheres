<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modification de votre mot de passe</title>
</head>

<body>
	<%@ include file = "../navbar.jsp" %>
	<h2>Modification de votre mot de passe.</h2>
 
	<form id="formulaireModif" action="${pageContext.request.contextPath}/modifierMotDePasse" method = "POST">
		<label for="newPassword">Nouveau mot de passe : </label>
		<input type = "password" name="newPassword" id="newPassword">
		<label for="confirmationNewPassword">Confirmer le mot de passe : </label>
		<input type = "password" name="confirmationNewPassword" id="confirmationNewPassword">
		<button type="submit" value="Valider"></button>
	</form>
		

</body>
</html>