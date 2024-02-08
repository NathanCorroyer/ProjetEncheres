<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Catégories</title>
<script src="${pageContext.request.contextPath}/javascript/hideParagraph.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/style.css">
</head>
<body>
	<%@ include file ="/WEB-INF/jsp/navbar.jsp" %>
	
	
	<c:if test="${not empty requestScope.succesSuppression}">
    	<p style="color : green" id ="succesSuppression">${requestScope.succesSuppression}</p>
    	<script>fadeOut("succesSuppression")</script>
    </c:if>
	<c:if test="${not empty requestScope.echecSuppression}">
    	<p style="color : green" id ="echecSuppression">${requestScope.echecSuppression}</p>
    	<script>fadeOut("echecSuppression")</script>
    </c:if>
    
    <c:if test="${not empty requestScope.succesModification}">
    	<p style="color : green" id ="succesModification">${requestScope.succesModification}</p>
    	<script>fadeOut("succesModification")</script>
    </c:if>
	<c:if test="${not empty requestScope.succesAjout}">
    	<p style="color : green" id ="succesAjout">${requestScope.succesAjout}</p>
    	<script>fadeOut("succesAjout")</script>
    </c:if>
    <c:if test="${not empty requestScope.echecAjout}">
    	<p style="color : green" id ="echecAjout">${requestScope.echecAjout}</p>
    	<script>fadeOut("echecAjout")</script>
    </c:if>
    
    <c:if test="${listeCategories eq null}">
		<%
		String contextPath = request.getContextPath();
		request.getRequestDispatcher("/ServletAffichageListeCategories").forward(request,response); 
		%>
	</c:if>
	<c:if test="${listeCategories ne null}">
		<table border="1px solid white" style="background-color:white;">
				<tr>
					<th >Numéro</th>
					<th>Libellé</th>
					<th colspan="2">Actions</th>
				</tr>
				<c:forEach items="${listeCategories}" var="c">
				
						<tr>
							<td style=""text-align:center">${c.no_categorie}</td>
							<td style=""text-align:center">${c.libelle}</td>
							
							<td><button><a href="${pageContext.request.contextPath}/modifierCategorie?numero=${c.no_categorie}&libelle=${c.libelle}"> Modifier</a></td>
							<td><button><a href="${pageContext.request.contextPath}/supprimerCategorie?numero=${c.no_categorie}"> Supprimer </a></button></td>
						</tr>
					
				</c:forEach>
		</table>
		<button><a href="${pageContext.request.contextPath}/ajouterCategorie">Ajouter une catégorie</a></button>
	</c:if>
</body>
</html>