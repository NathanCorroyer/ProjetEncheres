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
<div id="banner">
<h2>Le site n°1 des ventes aux enchères en France</h2>
<img src="<%=request.getContextPath()%>/images/banner.png">
</div>  
<section class="main">

  
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
    <c:if test="${not empty requestScope.reussite_enchere}">
    	<p id="reussite_enchere" style="color : green">${requestScope.reussite_enchere}</p>
    	<script>fadeOut("reussite_enchere")</script>
    </c:if>
    <c:if test="${not empty requestScope.echec_enchere}">
    <p id="echec_enchere" style="color : red">${requestScope.echec_enchere}</p>
    <script>fadeOut("echec_enchere")</script>
    </c:if>
    <c:if test="${not empty requestScope.echec_enchere_meme_personne}">
    	<p id="echec_enchere_meme_personne" style="color : red">${requestScope.echec_enchere_meme_personne}</p>
    	<script>fadeOut("echec_enchere_meme_personne")</script>
    </c:if>
    <c:if test="${not empty requestScope.suppression_enchere_reussie}">
    	<p id="suppression_enchere_reussie" style="color : red">${requestScope.suppression_enchere_reussie}</p>
    	<script>fadeOut("suppression_enchere_reussie")</script>
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
	<div class="categorie">
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
	      <div class="filtre-enchere">
	      	<label for="radioAll" >Toutes les enchères</label>
	      	<input type="radio" name="tri_etat_enchere" value="all" id="radioAll"><br>
	      	<label for="radioEnCours" >Enchères à venir</label>
	      	<input type="radio" name="tri_etat_enchere" value="aVenir" id="radioAVenir"><br>
	      	<label for="radioEnCours" >Enchères en cours</label>
	      	<input type="radio" name="tri_etat_enchere" value="enCours" id="radioEnCours"><br>
	      	<label for="radioFinies" >Enchères terminées</label>
	      	<input type="radio" name="tri_etat_enchere" value="finies" id="radioFinies"><br>
		</div>
		<div class="limite">      	
	      	<c:if test="${userConnected ne null}">
	      		<p>Voulez-vous limiter les résultats à vos ventes?</p>	
	      		 <label for="radioOui" >Oui</label>
	      		<input type="radio" name="tri_encheres_user" value="tri" id="radioOui"><br>
	      		<label for="radioNon" >Non</label>
	      		<input type="radio" name="tri_encheres_user" value="pas_tri" id="radioNon"><br>
	      	</c:if>
	    </div>
	    <div class="filtrer">  	
	       	<input type="text" name="search" placeholder="Le nom de l'article contient">	
     		<input type="submit" value="Filtrer">
     	</div>
     	</form> 
     </c:if>
    </div>  
  
  	
	
	<!--<img src="${a.imagePath}" alt="Image de l'annonce">  -->
	<!-- De même que pour la liste de catégories, on va afficher ici tous les éléments de la liste d'articles passée en paramètres
		 On peut très bien avoir la liste complète comme la liste filtrée
	 -->
	<c:if test="${listeArticles ne null}">
		<section class="annonces">
		<%-- On parcourt la liste avec un forEach --%>
		<ul>
			<c:forEach var="a" items="${listeArticles}">
				
				<c:set var="maintenant" value="${LocalDateTime.now()}"/>
				<c:set var="localDateTime" value="${a.getDate_fin_encheres()}" />
	        	<c:set var="localDebutTime" value="${a.getDate_debut_encheres()}" />
	        	<c:set var="debut_lien" value="/ServletDetailsEnchere?no_article=${a.getNoArticle()}"/>
	        	<c:set var="fin_lien" value="&nomVendeur=${a.getUtilisateur().getPseudo()}"/>
	        	       	
	        	
	        	<c:set var="connexionNecessaire" value="Vous devez être connecté pour accéder à ce contenu"/>
	        	<%-- Pour chaque article présent dans la liste, une balise li est créée et on y utilise les données qui nous intéressent en utilisant les getters de la classe Article  --%>
	           	 <li>	
	           	 	
		              <div class="annonce-details">
		                  <h4 style="color: red">${a.getNom_Article()}</h4>
		                  
		                  <div class = "annonce-img">
		                  <c:choose>
		                  	<c:when test="${userConnected ne null}">
		                 		 <a href="${pageContext.request.contextPath}/ServletDetailsEnchere?no_article=${a.getNoArticle()}&nomVendeur=${a.getUtilisateur().getPseudo()}&dateDebutEnchere=${a.getDate_debut_encheres()}">
		                	  </c:when>
		                 	 <c:otherwise>

		                  		<a href="${pageContext.request.contextPath}/login?lienEnchere=${debut_lien}${fin_lien }&connexionNecessaire=${connexionNecessaire}">
		                  		
		                	 </c:otherwise>
		                  </c:choose>
		                  	<img src="${a.getImagePath()}" alt="TestImage">
		                  	</a>
		                  </div>  
		                  <br>
			                  <hr>
		                  <div class = "annonce-infos">
			               
			                  <%-- Lien vers la servlet de récup des données du vendeur, qui nous permettra d'afficher ses informations --%>
			                  
			                   <p><span style="color: red">Catégorie</span> :  ${a.getCategorieComplete().getLibelle()}</p>
			                  <p><span style="color: red">Vendeur</span> : <a href="${pageContext.request.contextPath}/ServletAffichantProfilVendeur?userPseudo=${a.getUtilisateur().getPseudo()}"> ${a.getUtilisateur().getPseudo()} </a></p>
                
			                   <c:choose>
			                  	<c:when test="${a.vendu eq true}">
			                  		<p style="color: red">Enchère terminée.</p>
			                  	</c:when>
			                  	<c:when test="${a.vendu eq false and localDebutTime.compareTo(maintenant) < 0}">
			                  		<p style="color: red">Enchère en cours.</p>
			                  	</c:when>
			                  	<c:otherwise>
			                  		<p><span style="color: red">L'enchère commencera le </span>: </span> <%= formatLocalDateTime((LocalDateTime) pageContext.getAttribute("localDebutTime"), "EEEE, dd MMMM yyyy, HH 'h' mm") %></p>
			                  	</c:otherwise>
			                  </c:choose>
			                 
			                   <%-- Première utilisation du formatage de date, avec le DateTimeFormatter défini plus haut --%>
			                  <p><span style="color: red">Fin de l'enchère </span>: <%= formatLocalDateTime((LocalDateTime) pageContext.getAttribute("localDateTime"), "EEEE, dd MMMM yyyy, HH 'h' mm") %></p>
			                   <p><span style="color: red">Prix </span>:<b>${a.getPrix_initial()} points </b></p>
			                  
			                   <br>
			                  <br>
			                  <%-- Lien vers toutes les informations de l'article--%>
			               
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