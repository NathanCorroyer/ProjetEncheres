<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <link rel="stylesheet" href="styles/confirmationarticle.css">
<title>Confirmation de création d'article</title>
</head>
<body>
<%@ include file = "navbar.jsp" %>
<section class="main">

<h2><i class="fa-solid fa-circle-check"></i> Votre enchère a été ajouté aves succès !</h2>

<div class="tableau">
<h3>Voici votre récapitulatif :</h3>
<table border="1">

    <tr>
        <th>Article</th>
        <th>Description</th>
        <th>Catégorie</th>
        <th>Mise à prix</th>
        <th>Début de l'enchère</th>
        <th>Fin de l'enchère</th>
    </tr>
    <tr>
        <td>${article.nom_Article}</td>
        <td>${article.description}</td>
        <td>${article.categorie}</td>
        <td>${article.prix_initial}</td>
        <td>${article.date_debut_encheres}</td>
        <td>${article.date_fin_encheres}</td>
      
    </tr>
    </table>
    
</div>
<div id="accueil">
<button type="button" onclick="Acceuil()">Revenir à l'accueil</button>
     <script>
        function Acceuil() {
            window.location.href = '<%=request.getContextPath()%>/index.jsp';
        }
    </script>
 </div>
</section>
</body>
</html>
