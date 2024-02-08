<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modification de votre mot de passe</title>
 <link rel="stylesheet" href="styles/styleregister.css">
</head>

<body>
	<%@ include file = "navbar.jsp" %>*
	<section class="main">
	<script>
    document.getElementById("formulaireModif").addEventListener("submit", function(event) {
        var nouveauMotDePasse = document.getElementById("motDePasse").value;
        var confirmationMotDePasse = document.getElementById("confirmation").value;

        if (nouveauMotDePasse !== confirmationMotDePasse) {
            alert("Les nouveaux mots de passe ne correspondent pas !");
            event.preventDefault(); // Empêche l'envoi du formulaire
        }
    });
</script>
	<h2>Modification de votre mot de passe.</h2>
 
	<form class="inscription-form" action="${pageContext.request.contextPath}/modifierMotDePasse" method = "POST">
		<input type="hidden" name="mail" value="${mail}">
		<label for="newPassword">Nouveau mot de passe : </label>
		<input type = "password" name="newPassword" id="newPassword">
		<label for="confirmationNewPassword">Confirmer le mot de passe : </label>
		<input type = "password" name="confirmationNewPassword" id="confirmationNewPassword">
		<button type="submit" >Valider</button>
	</form>
		
	</section>
</body>
</html>