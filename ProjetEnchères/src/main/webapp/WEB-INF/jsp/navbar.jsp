	<link rel="stylesheet" href="styles/navbar.css">
 	<%@page import="fr.eni.enchere.bo.Utilisateur"%>
 	<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<%Utilisateur connectedUser = (Utilisateur) session.getAttribute("userConnected"); %>
 <c:choose>
	 <c:when test="${userConnected ne null}">
	    <nav class="navbar">
	    
	    <h1 id="title"><a href="<%=request.getContextPath()%>/index.jsp">ENI Encheres</a></h1>
	      <div class="navbar-links">

	          <ul>
	          <li><a href="#">Enchères </a></li>
	          <li><a href="#">Vendre</a></li>
	          <li><a href="<%=request.getContextPath()%>/mon_profil">Mon Profil</a></li>
	          <li><a href="<%=request.getContextPath()%>/servletdeconnexion">Deconnexion</a></li>
	              
	              <!--  TO DO PAGE ACCUEIL DECONNECTED -->
	          </ul>
	      </div>
	    </nav>
	 </c:when>
 

   

	 <c:otherwise>
	 <nav class="navbar">
	 <h1 id="title"><a href="<%=request.getContextPath()%>/index.jsp">ENI Encheres</a></h1>
	        <div class="navbar-links">
	            <ul>
	                <li><a href="<%=request.getContextPath()%>/register"> Créer Un Compte </a></li>
	                 <li><a href="<%=request.getContextPath()%>/login"> Se Connecter </a></li>
	            </ul>
	        </div>
	    </nav>
	 </c:otherwise>
</c:choose>

    
