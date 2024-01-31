<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modification de votre profil</title>
</head>
<body>
	
	<h1>ENI-Enchères</h1>
   	<h2>Modification du profil</h2>
   	
   	<script>
    document.getElementById("formulaireModif").addEventListener("submit", function(event) {
        var password = document.getElementById("motDePasse").value;
        var confirmPassword = document.getElementById("confirmation").value;

        if (password !== confirmPassword) {
            alert("Les mots de passe ne correspondent pas !");
            event.preventDefault(); // Empêche l'envoi du formulaire
        }
    });
	</script>
    
    <form id="formulaireModif" class="modification-form" action="${pageContext.request.contextPath}/ServletModificationProfil" method="POST">
        <label for="pseudo">Pseudo :</label>
        <input type="text" id="pseudo" name="pseudo" value="${user.pseudo}"  required><br>

 		<label for="nom">Nom :</label>
        <input type="text" id="nom" name="nom" value="${user.nom}" required><br>
        
        <label for="prenom">Prénom :</label>
        <input type="text" id="prenom" name="prenom" value="${user.prenom}" required><br>
        
        <label for="email">Email :</label>
        <input type="email" id="email" name="email" value="${user.email}"  required><br>

        <label for="telephone">Téléphone :</label>
        <input type="tel" id="telephone" name="telephone"  value="${user.telephone}" required><br>
        
        <label for="rue">Rue :</label>
        <input type="text" id="rue" name="rue"  value="${user.rue}" required><br>

        <label for="codePostal">Code postal :</label>
        <input type="text" id="codePostal" name="codePostal"  value="${user.code_postal}" required><br>
        
        <label for="ville">Ville :</label>
        <input type="text" id="ville" name="ville"  value="${user.ville}" required><br>
        
        <label for="motDePasseActuel">Mot de passe actuel :</label>
		<input type="password" id="motDePasseActuel" name="motDePasseActuel" required><br>
		
        <label for="NouveauMotDePasse">Nouveau mot de passe :</label>
        <input type="password" id="motDePasse" name="motDePasse" required><br>

        <label for="confirmation">Confirmation du nouveau mot de passe :</label>
        <input type="password" id="confirmation" name="confirmation" required><br>
        
        <button type="submit">S'inscrire</button>
    </form>
    
</body>
</html>