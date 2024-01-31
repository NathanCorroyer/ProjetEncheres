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
    
    <form class="modification-form" action="${pageContext.request.contextPath}/update" method="POST">
        <label for="pseudo">Pseudo :</label>
        <input type="text" id="pseudo" name="pseudo" value="${userConnected.pseudo}"  required><br>

 		<label for="nom">Nom :</label>
        <input type="text" id="nom" name="nom" value="${userConnected.nom}" required><br>
        
        <label for="prenom">Prénom :</label>
        <input type="text" id="prenom" name="prenom" value="${userConnected.prenom}" required><br>
        
        <label for="email">Email :</label>
        <input type="email" id="email" name="email" value="${userConnected.email}"  required><br>

        <label for="telephone">Téléphone :</label>
        <input type="tel" id="telephone" name="telephone"  value="${userConnected.telephone}" required><br>
        
        <label for="rue">Rue :</label>
        <input type="text" id="rue" name="rue"  value="${userConnected.rue}" required><br>

        <label for="codePostal">Code postal :</label>
        <input type="text" id="codePostal" name="codePostal"  value="${userConnected.code_postal}" required><br>
        
        <label for="ville">Ville :</label>
        <input type="text" id="ville" name="ville"  value="${userConnected.ville}" required><br>
        
        <label for="motDePasseActuel">Mot de passe actuel :</label>
		<input type="password" id="motDePasseActuel" name="motDePasseActuel" required><br>
		<c:choose>
			<c:when test="${not empty param.motDePasseActuel and param.motDePasseActuel ne userConnected.password}">
				<p>Petit filou, ce n'est pas votre mot de passe</p>
			</c:when>
			<c:otherwise>
				<p>Mot de passe valide</p>		
			</c:otherwise>
		</c:choose>
		
        <label for="NouveauMotDePasse">Nouveau mot de passe :</label>
        <input type="password" id="motDePasse" name="motDePasse" required><br>

        <label for="confirmation">Confirmation du nouveau mot de passe :</label>
        <input type="password" id="confirmation" name="confirmation" required><br>
		<c:choose>
			<c:when test="${not empty param.motDePasse and not empty param.confirmation and param.motDePasse ne param.confirmation}">
				<p>Les mots de passe ne correspondent pas</p>
			</c:when>
			<c:otherwise>
				<p>Les mots de passe correspondent</p>
			</c:otherwise>
		</c:choose>
        <button type="submit">S'inscrire</button>
    </form>
    <c:if test="${validation eq true} ">
    	<p>Modification effectuée</p>	
    </c:if>
    <c:if test="${validation eq false }">
    	<c:forEach var="erreur" items="listeErreurs">
    		<p>${erreur}</p>
    	</c:forEach>
    	
    </c:if>
    
</body>
</html>