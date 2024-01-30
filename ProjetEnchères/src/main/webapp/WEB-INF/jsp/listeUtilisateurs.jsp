<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP affichant liste utilisateurs</title>
</head>
<body>
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
			</tr>
			<c:forEach items="${listeUtilisateurs}" var="u">
				<li>${u.pseudo}</li>
				<li>${u.nom}</li>
				<li>${u.prenom}</li>
				<li>${u.email}</li>
				<li>${u.telephone}</li>
				<li>${u.rue}</li>
				<li>${u.code_postal}</li>
				<li>${u.ville}</li>
			</c:forEach>
	</table>
</body>
</html>