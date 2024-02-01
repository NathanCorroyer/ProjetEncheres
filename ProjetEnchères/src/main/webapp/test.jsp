<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="fr.eni.enchere.bo.Article" %>
<%@page import="java.util.List" %>
<%@page import="javax.servlet.RequestDispatcher" %>
<c:if test="${requestScope.listeArticles eq null}">
	
	<% 
	   // Récupérer l'URL relative à l'application
    String requestURI = request.getRequestURI();
    
    // Récupérer le chemin du contexte de l'application
    String contextPath = request.getContextPath();

    // Récupérer la portion de l'URL après l'URL de base du projet
    String relativeURL = requestURI.substring(contextPath.length());
	RequestDispatcher dispatcher = request.getRequestDispatcher("/ServletRecuperationListeEncheres");
	request.setAttribute("referer", relativeURL);
	dispatcher.forward(request,response); 
	
	%>
</c:if>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<% List<Article> listeArticles = (List<Article>)request.getAttribute("listeArticles");%>
	<c:forEach var="a" items="${listeArticles}">
		<p>${a.toString()}</p>
	</c:forEach>
</body>
</html>