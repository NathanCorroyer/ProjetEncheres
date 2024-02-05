<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="styles/stylecreerenchere.css"> 
    <link rel="icon" href="https://capecia-formations.fr/wp-content/uploads/2019/09/LogoENIcertification-print.png" type="image/x-icon">
    <title>Créer une Enchère</title>
</head>
<body>
<%@ include file = "navbar.jsp" %>

<section class="main">
    <h2>Nouvelle vente</h2>
    
    <form class="enchere-form" action="${pageContext.request.contextPath}/creer_enchere" method="post" enctype="multipart/form-data">
        <label for="nom_article">Article:</label>
        <input type="text" name="nom_article" required>

        <label for="description">Description :</label>
        <textarea name="description" required></textarea>

        <label for="no_categorie">Catégorie :</label>
        <input type="number" name="no_categorie" required>
        
        <label for="file">Photo de l'article :</label>
        <input type="file" name="photoArticle" accept="image/*">

        <label for="prix_initial">Mise à prix :</label>
        <input type="number" name="prix_initial" required>

        <label for="date_debut_encheres">Début de l'enchère :</label>
        <input type="date" name="date_debut_encheres" required>

        <label for="date_fin_encheres">Fin de l'enchère :</label>
        <input type="date" name="date_fin_encheres" required>
        
        <div class="retrait">
	        <h2>Retrait</h2>
	       	 <label for="rue">Rue :</label>
       		 <input type="text" id="rue" name="rue"  value="${user.rue}" required>
       		 
       
	        <label for="codePostal">Code postal :</label>
	        <input type="text" id="codePostal" name="codePostal" pattern="[0-9]{5}" title="Entrez un code postal valide à 5 chiffres" maxlength="5" required>
<br>
	        
	        <label for="ville">Ville :</label>
	        <input type="text" id="ville" name="ville"  value="${user.ville}" required><br>
        </div>
<!--
        <label for="modalitesRetrait">Modalités de retrait :</label>
        <textarea name="modalitesRetrait"></textarea>
		 Modalité de retrait à modifier --> 
		 
        <button type="submit">Enregister</button>
        <button type="button" onclick="annuler()">Annuler</button>
    </form>
    <script>
        function annuler() {
            window.location.href = '<%=request.getContextPath()%>/index.jsp';
        }
    </script>
</section>
</body>
</html>
