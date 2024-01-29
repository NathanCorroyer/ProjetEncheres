<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ENI-Encheres / Inscription</title>
    <link rel="stylesheet" href="styleinscription.css">
</head>
<body>
    <h1>ENI-Enchères</h1>
    <h2>Mon profil</h2>
    
    <form class="inscription-form" action="#" method="post">
        <label for="pseudo">Pseudo :</label>
        <input type="text" id="pseudo" name="pseudo" required>

        <label for="prenom">Prénom :</label>
        <input type="text" id="prenom" name="prenom" required>

        <label for="telephone">Téléphone :</label>
        <input type="tel" id="telephone" name="telephone" required>

        <label for="codePostal">Code postal :</label>
        <input type="text" id="codePostal" name="codePostal" required>

        <label for="motDePasse">Mot de passe :</label>
        <input type="password" id="motDePasse" name="motDePasse" required>

        <label for="nom">Nom :</label>
        <input type="text" id="nom" name="nom" required>

        <label for="email">Email :</label>
        <input type="email" id="email" name="email" required>

        <label for="rue">Rue :</label>
        <input type="text" id="rue" name="rue" required>

        <label for="ville">Ville :</label>
        <input type="text" id="ville" name="ville" required>

        <label for="confirmation">Confirmation :</label>
        <input type="password" id="confirmation" name="confirmation" required>

        <button type="submit">S'inscrire</button>
    </form>



</body>
</html>