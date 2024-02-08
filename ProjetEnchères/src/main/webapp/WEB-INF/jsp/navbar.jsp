	<link rel="stylesheet" href="styles/navbar.css">
 	<%@page import="fr.eni.enchere.bo.Utilisateur"%>
 	<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://kit.fontawesome.com/2a64b3031f.js" crossorigin="anonymous"></script>

	<%Utilisateur connectedUser = (Utilisateur) session.getAttribute("userConnected"); %>
 <c:choose>
	 <c:when test="${userConnected ne null}">
	    <nav class="navbar">
	    
	    <h1 id="title"><a href="<%=request.getContextPath()%>/index.jsp"><img src="<%=request.getContextPath()%>/images/logo.png"></a></h1>
	      <div class="navbar-links">

	          <ul>
	          <li><a href="<%=request.getContextPath()%>/index.jsp"><i class="fa-solid fa-store"></i> Enchères </a></li>
	          <li><a href="<%=request.getContextPath()%>/creer_enchere"><i class="fa-solid fa-euro-sign"></i> Vendre</a></li>
	          <li><a href="<%=request.getContextPath()%>/mon_profil"><i class="fa-solid fa-user"></i> Mon Profil</a></li>
	          <li><a href="<%=request.getContextPath()%>/servletdeconnexion"><i class="fa-solid fa-right-from-bracket"></i> Deconnexion</a></li>
	              
	              <!--  TO DO PAGE ACCUEIL DECONNECTED -->
	          </ul>
	      </div>
	    </nav>
	 </c:when>
 

   

	 <c:otherwise>
	 <nav class="navbar">
	 <h1 id="title"><a href="<%=request.getContextPath()%>/index.jsp"><img src="<%=request.getContextPath()%>/images/logo.png"></a></h1>
	        <div class="navbar-links">
	            <ul>
	                <li><a href="<%=request.getContextPath()%>/register"><i class="fa-solid fa-address-card"></i> Créer Un Compte </a></li>
	                 <li><a href="<%=request.getContextPath()%>/login"><i class="fa-solid fa-user"></i> Se Connecter </a></li>
	            </ul>
	        </div>
	    </nav>
	 </c:otherwise>
</c:choose>

    
