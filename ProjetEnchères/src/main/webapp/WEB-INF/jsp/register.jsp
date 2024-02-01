<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ENI-Encheres / Inscription</title>
   <link rel="stylesheet" href="styles/styleregister.css">
   <link rel="icon" href="https://capecia-formations.fr/wp-content/uploads/2019/09/LogoENIcertification-print.png" type="image/x-icon">
    <script>
    function validerFormulaire() {
      var motDePasse = document.getElementById('motDePasse').value;
      var confirmation = document.getElementById('confirmation').value;

      if (motDePasse !== confirmation) {
        alert("Les mots de passe ne correspondent pas.");
        return false; 
      }

      window.location.href = '<%=request.getContextPath()%>/login';
      return true;
    }
  </script>
</head>
<body>

    <%@ include file = "navbar.jsp" %>
    
    <section class="main">
    <h2>Création de compte</h2>
    
    
    <form class="inscription-form" action="${pageContext.request.contextPath}/register" method="post" onsubmit="return validerFormulaire()">
        
              <label for="pseudo">Pseudo :</label>
  	<input type="text" id="pseudo" name="pseudo" pattern="[a-zA-Z0-9]+"
	 	title="Le pseudo doit contenir uniquement des caractères alphanumériques " required>
        
          <label for="email">Email :</label>
        <input type="email" id="email" name="email" required>
        
          <label for="motDePasse">Mot de passe :</label>
        <input type="password" id="motDePasse" name="motDePasse" required>
        
        <label for="confirmation">Confirmation du mot de passe : </label>
        <input type="password" id="confirmation" name="confirmation" required>
        
        <label for="nom">Nom :</label>
        <input type="text" id="nom" name="nom" required>
        
        
        <label for="prenom">Prénom :</label>
        <input type="text" id="prenom" name="prenom" required>
     
        
        <label for="telephone">Téléphone :</label>
        <input type="text" id="telephone" name="telephone" pattern="[0-9]{10}" title="Entrez un numéro de téléphone valide à 10 chiffres" maxlength="10" required>

        
         <label for="ville">Ville :</label>
        <input type="text" id="ville" name="ville" required>

        <label for="rue">Rue :</label>
        <input type="text" id="rue" name="rue" required>
        
          <label for="codePostal">Code postal :</label>
        <input type="text" id="codePostal" name="codePostal" pattern="[0-9]{5}" title="Entrez un code postal valide à 5 chiffres" maxlength="5" required>


        <button type="submit">Créer</button>
        <button type="button" onclick="annuler()">Annuler</button>
    </form>

    <script>
        function annuler() {
            window.location.href = '<%=request.getContextPath()%>/login';
        }
    </script>
    
    </section>
</body>
</html>
