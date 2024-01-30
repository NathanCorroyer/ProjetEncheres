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
				<td>
					<tr>${u.pseudo}</tr>
					<tr>${u.nom}</tr>
					<tr>${u.prenom}</tr>
					<tr>${u.email}</tr>
					<tr>${u.telephone}</tr>
					<tr>${u.rue}</tr>
					<tr>${u.code_postal}</tr>
					<tr>${u.ville}</tr>
				</td>
			</c:forEach>
	</table>
</body>
</html>