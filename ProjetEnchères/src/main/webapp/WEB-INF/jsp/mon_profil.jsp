<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/styleprofil.css">
<head>
    <meta charset="UTF-8">
    <title>ENI-Enchères</title>
</head>
<body>
    <h1>ENI-Enchères</h1>
    <h2>Mon profil</h2>
    <p>Pseudo : ${user.pseudo}</p>
    <p>Nom : ${user.nom}</p>
    <p>Prénom : ${user.prenom}</p>
    <p>Email : ${user.email}</p>
    <p>Téléphone : ${user.telephone}</p>
    <p>Rue : ${user.rue}</p>
    <p>Code postal : ${user.codePostal}</p>
    <p>Ville : ${user.ville}</p>
    <a href="${pageContext.request.contextPath}/ServletSuppressionCompte" onclick="return confirm('Êtes-vous sûr de vouloir supprimer votre compte ?')">Supprimer mon compte</a>
</body>
</html>
