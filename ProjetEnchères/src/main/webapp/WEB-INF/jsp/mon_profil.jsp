<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mon Profil</title>
    <link rel="stylesheet" href="styles/styleprofile.css">
    <link rel="icon" href="https://capecia-formations.fr/wp-content/uploads/2019/09/LogoENIcertification-print.png" type="image/x-icon">
    
</head>

<body>
     <%@ include file = "navbar.jsp" %>
     <section class="main">
    <div class="informations"><h2>Mon profil</h2>
    <p>Pseudo : ${userConnected.pseudo}</p>
    <p>Nom : ${userConnected.nom}</p>
    <p>Prénom : ${userConnected.prenom}</p>
    <p>Email : ${userConnected.email}</p> 
    <p>Téléphone : ${userConnected.telephone}</p>
    <p>Rue : ${userConnected.rue}</p>
    <p>Ville : ${userConnected.ville}</p>
    <p> Code postal : ${userConnected.code_postal}</p>
    <h3>Crédit : ${userConnected.credit}</h3> 
    <form action="${pageContext.request.contextPath}/ServletAjoutCredit" method="post"> 
    <label for="nombreCredit">Nombre de crédits :</label>
    <input type="number" id="nombreCredit" name="nombreCredit" required>
    <button type="submit">Ajouter du crédit</button>
</form>

    <a class ="supprimer-compte" href="${pageContext.request.contextPath}/ServletSuppressionCompte" onclick="return confirm('Êtes-vous sûr de vouloir supprimer votre compte ?')">Supprimer mon compte</a>
    <a class="modifier-profil" href="${pageContext.request.contextPath}/ServletModificationProfil">Modifier mon profil </a>
</div>
    </section>
    
    	<c:if test="${not empty requestScope.succesModif}">
    	<p style="color : green">${requestScope.succesModif}</p>
    </c:if>
    <c:if test="${not empty requestScope.erreurModif}">
    	<p style="color : red">${requestScope.erreurModif}</p>
    </c:if>
    
</body>
</html>
