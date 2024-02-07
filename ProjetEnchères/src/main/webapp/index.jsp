<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page import="fr.eni.enchere.bo.Article" %>
<%@page import="fr.eni.enchere.bo.Utilisateur" %>
<%@page import="java.util.List" %>
<%@page import="javax.servlet.RequestDispatcher" %>
<%@page import="fr.eni.enchere.bo.Categorie" %>
<%@page import ="java.time.format.DateTimeFormatter"%>
<%@ page import="java.time.LocalDateTime" %>

<%! 
    String formatLocalDateTime(LocalDateTime localDateTime, String pattern) {
        return localDateTime.format(java.time.format.DateTimeFormatter.ofPattern(pattern));
    }
%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ENI-Enchères</title>
   <link rel="icon" href="https://capecia-formations.fr/wp-content/uploads/2019/09/LogoENIcertification-print.png" type="image/x-icon">

    <link rel="stylesheet" href="styles/style.css">
	<script src="javascript/hideParagraph.js"></script>

    
</head>
<body>
    
    <!--  Navbar -->
    <%@ include file = "WEB-INF/jsp/navbar.jsp" %>
<section class="main">

   <!-- <a href="${pageContext.request.contextPath}/ServletAffichageListeUtilisateurs">Liste Utilisateurs</a> --> 
    
    <!-- Tests d'existence des messages, appel d'une fonction js qui les fait disparaitre progressivement en 3sec -->
    <c:if test="${not empty requestScope.succesSuppression}">
    	<% request.setAttribute("succesSuppression", "Votre compte a bien été supprimé"); %>
    	<p style="color : green" id ="succesSuppression">${requestScope.succesSuppression}</p>
    	<script>fadeOut("succesSuppression")</script>
    </c:if>
    <c:if test="${not empty requestScope.erreurSuppression}">
   		 <% request.setAttribute("erreurSuppression", "Erreur lors de la suppression de votre compte"); %>
    	<p style="color : red" id ="erreurSuppression">${requestScope.erreurSuppression}</p>
    	<script>fadeOut("erreurSuppression")</script>
    </c:if>
    <c:if test="${not empty requestScope.succes_creation}">
    	<% request.setAttribute("succes_creation", "Votre compte a bien été créé"); %>
    	<p style="color : green" id="succes_creation">${requestScope.succes_creation}</p>
    	<script>fadeOut("succes_creation")</script>
    </c:if>
    <h2>Liste des enchères</h2>
    <!-- Barre de recherche déplacée en dessous de "Filtres :" -->
    
       
    <h4>Catégorie :</h4>
    
    <!-- Recherche des listes de catégories et d'articles, si l'une d'elles est nulle on forward vers la servlet pour les récupérer -->
    <%  List<Categorie> listeCategorie = (List<Categorie>) request.getAttribute("listeCategorie");
    	List<Article> listeArticles = (List<Article>) request.getAttribute("listeArticles");
    	/* Déclaration du formatter qui nous servira à afficher la date correctement dans la liste */
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    %>   
	<c:if test="${listeCategorie eq null or listeArticles eq null}">
		<%
		String contextPath = request.getContextPath();
		request.getRequestDispatcher("/ServletRecuperationListeEncheres").forward(request,response); 
		%>
	</c:if>
	
	<!-- Affichage de la liste des catégories et choix des filtres de la liste d'articles -->
	<c:if test="${listeCategorie ne null}">
		<form action="${pageContext.request.contextPath}/ServletRecuperationListeEncheres" method="POST">
	    	<select class="category-dropdown search-form" name="categorie">
	    		<%-- On fait en sorte ici de présélectionner la catégorie déjà sélectionnée précédemment par l'utilisateur
	    			Grace aux ternaires : si aucun choix alors on présélectionne "toutes", sinon celle voulue
	    		 --%>
		   		<option value="all" ${empty categorie || categorie eq -1 ? 'selected' :''}>Toutes</option>
		       		<c:forEach var="c" items="${listeCategorie}">      
		       			 <option value="${c.no_categorie}" ${c.no_categorie eq categorie ? 'selected' : ''}>${c.libelle}</option>
		         	</c:forEach>
	      </select>
	      	<label for="radioAll" >Toutes les enchères</label>
	      	<input type="radio" name="etat_enchere" value="all" id="radioAll">
	      	<label for="radioEnCours" >Enchères en cours</label>
	      	<input type="radio" name="etat_enchere" value="enCours" id="radioEnCours">
	      	<label for="radioFinies" >Enchères terminées</label>
	      	<input type="radio" name="etat_enchere" value="finies" id="radioFinies">
	      	
	       	<input type="text" name="search" placeholder="Le nom de l'article contient">	
     		<input type="submit" value="Filtrer">
     	</form> 
     </c:if>
      
  
  	
	
	<!--<img src="${a.imagePath}" alt="Image de l'annonce">  -->
	<!-- De même que pour la liste de catégories, on va afficher ici tous les éléments de la liste d'articles passée en paramètres
		 On peut très bien avoir la liste complète comme la liste filtrée
	 -->
	<c:if test="${listeArticles ne null}">
		<section class="annonces">
		<%-- On parcourt la liste avec un forEach --%>
		<ul>
			<c:forEach var="a" items="${listeArticles}">
				<c:set var="localDateTime" value="${a.getDate_fin_encheres()}" />
	        	
	        	<%-- Pour chaque article présent dans la liste, une balise li est créée et on y utilise les données qui nous intéressent en utilisant les getters de la classe Article  --%>
	           	 <li>	
	           	 	
		              <div class="annonce-details">
		                  <h4>${a.getNom_Article()}</h4>
		                  
		                  <div class = "annonce-img">
		                  	<img src="${a.getImagePath()}" alt="TestImage">
		                  </div>
		                  <div class = "annonce-infos">
			                  <p>Prix : ${a.getPrix_initial()} points</p>
			                  <%-- Première utilisation du formatage de date, avec le DateTimeFormatter défini plus haut --%>
			                  <p>Fin de l'enchère : <%= formatLocalDateTime((LocalDateTime) pageContext.getAttribute("localDateTime"), "EEEE, dd MMMM yyyy, HH 'h' mm") %></p>
			                  <%-- Lien vers la servlet de récup des données du vendeur, qui nous permettra d'afficher ses informations --%>
			                  <p>Vendeur : <a href="${pageContext.request.contextPath}/ServletAffichantProfilVendeur?userPseudo=${a.getUtilisateur().getPseudo()}"> ${a.getUtilisateur().getPseudo()} </a></p>
			                  <p>Catégorie : ${a.getCategorieComplete().getLibelle()}</p>
			                  <p>État de la vente : ${a.vendu eq true ? 'Enchère terminée.' : 'Enchère en cours.' } </p>
			                  <br>
			                  <br>
			                  
			                  <%-- Lien vers toutes les informations de l'article--%>
			                 <a href="${pageContext.request.contextPath}/ServletDetailsEnchere?no_article=${a.getNoArticle()}&nomVendeur=${a.getUtilisateur().getPseudo()}">Détails de l'article</a>
			              </div>
		               </div>
		               
		         </li>
	
			</c:forEach>
        </ul>

    </section>
	</c:if>
    </section>
</body>
</html>