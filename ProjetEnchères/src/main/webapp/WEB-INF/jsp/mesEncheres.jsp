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
<html>
<head>
<meta charset="UTF-8">
<title>Mes enchères</title>

</head>
<body>
	<%@ include file = "navbar.jsp" %>
	
		<form action="${pageContext.request.contextPath}/mes_encheres" method = "POST">
			<div class="filtre-enchere">
		      	<label for="radioVentes" >Ventes</label>
		      	<input type="radio" name="tri" value="ventes" id="radioVentes" checked><br>
		      	<label for="radioAchats" >Achats</label>
		      	<input type="radio" name="tri" value="achats" id="radioAchats"><br>
			</div>
			<input type="submit" value="Filtrer">
		</form>
		
		<c:choose>
			<c:when test="${tri eq 'achats' }">
			<h2>Les enchères en cours sur lesquelles j'ai fait une offre</h2>
			</c:when>
			<c:otherwise>
			<h2>Mes ventes en cours</h2>
			</c:otherwise>
		</c:choose>
		<c:if test = "${listeArticles eq null}">
			<%
			String contextPath = request.getContextPath();
			request.getRequestDispatcher("/mes_encheres").forward(request,response); 
			%>
		</c:if>
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

		                  		<a href="${pageContext.request.contextPath}/login?lienEnchere=${debut_lien}${fin_lien }&connexionNecessaire=${connexionNecessaire}&dateDebutEnchere=${a.getDate_debut_encheres()}">
		                  		
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
</body>
</html>