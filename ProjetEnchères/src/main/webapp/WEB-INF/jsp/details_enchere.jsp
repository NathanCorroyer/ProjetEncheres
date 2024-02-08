<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page import ="java.time.format.DateTimeFormatter"%>
<%@ page import="java.time.LocalDateTime" %>
<html>
<head>
    <link rel="stylesheet" href="styles/styledetailsarticle.css">
	<%! 
    String formatLocalDateTime(LocalDateTime localDateTime, String pattern) {
        return localDateTime.format(java.time.format.DateTimeFormatter.ofPattern(pattern));
    }
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	%>
    <c:set var="localDateTime" value="${article.getDate_fin_encheres()}" />
    <c:set var="localDateTimeDebut" value="${article.getDate_debut_encheres()}" />
    <title>Détails de l'enchère</title>
    <style>
        .image-container {
            border: 1px solid  #fd4040; 
            padding: 10px; 
            display: inline-block; 
            margin-bottom: 8%;
        }
        
        .description {
        	border: 1px dashed grey;
        	margin-bottom: 7%;
        }
        .prix_initial {
        	margin-top: 10%;
        }
        .retrait {
        	margin-top: 10%
        }
        
        .encherir-bouton {
    		background-color: #4CAF50; 
    		border: none; 
   			 color: white; 
    		padding: 10px 20px; 
   			text-align: center; 
    		text-decoration: none; 
   			 display: inline-block; 
   			 font-size: 16px; 
   			 margin: 4px 2px; 
   			 cursor: pointer; 
    		border-radius: 8px; 
    		margin-top: 4%;
		}
		button {
    background-color: #fd4040;
    color: white;
    padding: 10px 15px;
    border: none;
    border-radius: 4px;
    cursor: pointer;
	}
button, input[type="button"] {
    display: inline-block;
    margin-right: 100px;
    width: 100%;
    margin-top:50px;
}

button[type="button"] {
 border: 1px solid #fd4040;
    background-color: rgb(40,36,36);
	margin-top: 10px;
	color: #fd4040;
}

button[type="submit"] {
    width: 100%;
    
}

		
		}
    </style>
</head>
<body>
    <%@ include file="navbar.jsp" %>

    <section class="main">
        <h2>Détails de l'enchère</h2>

        <c:if test="${not empty article}">
        	<form action="${pageContext.request.contextPath}/ServletAugmentationEnchere" method="post">
            <div class="annonce-details">
            	
            		
   					 
   					<c:if test="${not empty requestScope.echec_paiement}">
    				<p style="color : red">${requestScope.echec_paiement}</p>
   					</c:if>
    				
            	                    	
                <h4>${article.getNom_Article()}</h4>
                <div class="image-container">
                <img src="${article.getImagePath()}" width="300" height="300" alt="TestImage">                
                </div>         
                <div class="description">               
                <p>Description : </p>
                
                <p>${article.getDescription()}</p>
                <input type="hidden" name="noArticle" value="${article.getNoArticle()}">
                
                </div>
                <p>Catégorie : ${article.getCategorieComplete().getLibelle()}</p>
                <p>Début de l'enchère : <%= formatLocalDateTime((LocalDateTime) pageContext.getAttribute("localDateTimeDebut"), "EEEE, dd MMMM yyyy, HH 'h' mm") %></p>
                <input type="hidden" name="debutEnchere" value="<%= formatLocalDateTime((LocalDateTime) pageContext.getAttribute("localDateTimeDebut"), "EEEE, dd MMMM yyyy, HH 'h' mm") %>">
                <p>Fin de l'enchère : <%= formatLocalDateTime((LocalDateTime) pageContext.getAttribute("localDateTime"), "EEEE, dd MMMM yyyy, HH 'h' mm") %></p>
                <input type="hidden" name="finEnchere" value="<%= formatLocalDateTime((LocalDateTime) pageContext.getAttribute("localDateTime"), "EEEE, dd MMMM yyyy, HH 'h' mm") %>">
                <p>Prix actuel :<b> ${article.getPrix_initial()} points </b></p>
                <input type="hidden" name="prixInitial" value="${article.getPrix_initial()}">
                <hr>
                <div class="retrait">
                <h4>Retrait : </h4>
                <p> Adresse : ${retrait.rue}, ${retrait.code_postal}, ${retrait.ville}  </p>
                <p> Pseudo du vendeur : ${Vendeur.getPseudo()} </p>
                <input type="hidden" name="pseudoVendeur" value="${Vendeur.getPseudo()}">

	            </div>
                <c:if test="${userConnected.actif eq false}">
	                <hr>
	                <label for="prix_initial">Ma proposition :</label>
	                <br>           
	        		<input type="number" name="enchereProposee" id="enchere" required min="${prixInitialEnchere}" value="${prixInitialEnchere}"> 
	        		<br>        
	                <button class="submit">Enchérir</button>

                </c:if>
                <br>
                
                <c:if test="${Vendeur.getPseudo() eq userConnected.getPseudo()}">
                <p>Liste des enchéreurs actuels de l'objet :</p>
                <ol>
                	<c:forEach var ="e" items="${listeEncheresDESC}">
                		<li>Pseudo : ${e.getUtilisateur().getPseudo()}
                		<br>
                		Enchère proposée : ${e.getMontant_enchere()}
                		</li>
                		<br>
                	</c:forEach>
                </ol>
                </c:if>
                
                
                
                <button type="button" onclick="annuler()">Retour à l'accueil</button>
                
            </div>
            </form>
 	</c:if>
			
       
        <c:if test="${empty article}">
            <p>Les détails de l'enchère ne sont pas disponibles.</p>
        </c:if>
         <script>
        function annuler() {
            window.location.href = '<%=request.getContextPath()%>/index.jsp ';
        }
        
        </script>
    </section>
</body>
</html>
