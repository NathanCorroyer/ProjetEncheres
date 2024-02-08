<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Votre mail?</title>
 <link rel="stylesheet" href="styles/styleregister.css">
</head>
<body>
	<%@ include file = "navbar.jsp" %>

	<section class="main">
	<h2>Réinitialisation de mot de passe</h2>
		<form class="inscription-form" action="${pageContext.request.contextPath}/modifierMotDePasse" method = "POST">
		<label for="newPassword">Votre mail : </label>
		<input type = "text" name="mail" id="mail">
		<button type="submit" >Valider</button>
	</form>

	</section>
</body>
</html>