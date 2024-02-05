<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Profil Vendeur</title>
    <link rel="stylesheet" href="styles/styleprofile.css">
    <link rel="icon" href="https://capecia-formations.fr/wp-content/uploads/2019/09/LogoENIcertification-print.png" type="image/x-icon"> 
    
</head>

<body>
     <%@ include file = "navbar.jsp" %>
     <section class="main">
    <div class="informations"><h2>Profil Vendeur</h2>
    <p>Pseudo : ${userAffiche.pseudo}</p>
    <p>Nom : ${userAffiche.nom}</p>
    <p>Prénom : ${userAffiche.prenom}</p>
    <p>Email : ${userAffiche.email}</p> 
    <p>Téléphone : ${userAffiche.telephone}</p>
    <p>Rue : ${userAffiche.rue}</p>
    <p>Ville : ${userAffiche.ville}</p>
    <p> Code postal : ${userAffiche.code_postal}</p>
    <h3>Crédit : ${userAffiche.credit}</h3> 

</div>
    </section>
    

</body>
</html>
