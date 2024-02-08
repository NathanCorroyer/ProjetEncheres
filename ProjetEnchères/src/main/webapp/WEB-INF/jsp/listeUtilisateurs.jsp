<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP affichant liste utilisateurs</title>
<script src="${pageContext.request.contextPath}/javascript/hideParagraph.js"></script>
</head>
<body>
	<%@ include file = "/WEB-INF/jsp/navbar.jsp" %>
	<c:if test="${not empty requestScope.succesSuppression}">
    	<p style="color : green" id ="succesSuppression">${requestScope.succesSuppression}</p>
    	<script>fadeOut("succesSuppression")</script>
    </c:if>
    <c:if test="${not empty requestScope.erreurSuppression}">
    	<p style="color : red" id ="erreurSuppression">${requestScope.erreurSuppression}</p>
    	<script>fadeOut("erreurSuppression")</script>
    </c:if>
	<c:if test="${not empty requestScope.succesActivation}">
    	<p style="color : green" id ="succesActivation">${requestScope.succesActivation}</p>
    	<script>fadeOut("succesActivation")</script>
    </c:if>
    <c:if test="${not empty requestScope.erreurActivation}">
    	<p style="color : red" id ="erreurActivation">${requestScope.erreurActivation}</p>
    	<script>fadeOut("erreurActivation")</script>
    </c:if>
	<c:if test="${listeUtilisateurs eq null}">
		<%
		String contextPath = request.getContextPath();
		request.getRequestDispatcher("/ServletAffichageListeUtilisateurs").forward(request,response); 
		%>
	</c:if>
	<c:if test="${listeUtilisateurs ne null}">
		<table border="1">
				<tr>
					<th>Pseudo</th>
					<th>Nom</th>
					<th>Prenom</th>
					<th>Email</th>
					<th>Téléphone</th>
					<th>Rue</th>
					<th>Code Postal</th>
					<th>Ville</th>
					<th>Compte activé?</th>
					<th colspan="2"> Actions </th>
				</tr>
				<c:forEach items="${listeUtilisateurs}" var="u">
					<c:if test="${u.administrateur eq false}">
						<tr>
							<td>${u.pseudo}</td>
							<td>${u.nom}</td>
							<td>${u.prenom}</td>
							<td>${u.email}</td>
							<td>${u.telephone}</td>
							<td>${u.rue}</td>
							<td>${u.code_postal}</td>
							<td>${u.ville}</td>
							<td>${u.actif eq true ? 'activé' : 'désactivé' }
							<td><button><a href="${pageContext.request.contextPath}/desactiverUser?email=${u.email}"> ${u.actif eq true ? 'Désactiver' : 'Activer' }</a></td>
							<td><button><a href="${pageContext.request.contextPath}/supprimerUser?email=${u.email}"> Supprimer </a></button></td>
						</tr>
					</c:if>
				</c:forEach>
		</table>
	</c:if>
</body>
</html>