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
	          <c:if test="${userConnected.administrateur eq true }">
	          	<li><a href="<%=request.getContextPath()%>/ServletAffichageListeUtilisateurs"><i class="fa-solid fa-user-group"></i></i> Utilisateurs </a></li>
		        <li><a href="<%=request.getContextPath()%>/ServletAffichageListeCategories"><i class="fa-solid fa-list"></i></i> Cat�gories </a></li>
	          </c:if>
	          <c:if test="${userConnected.actif eq true}">
		          <li><a href="<%=request.getContextPath()%>/mes_encheres"><i class="fa-solid fa-store"></i>Mes ench�res en cours</a></li>
		          <li><a href="<%=request.getContextPath()%>/creer_enchere"><i class="fa-solid fa-euro-sign"></i> Vendre</a></li>
		      </c:if> 
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
	                <li><a href="<%=request.getContextPath()%>/register"><i class="fa-solid fa-address-card"></i> Cr�er Un Compte </a></li>
	                 <li><a href="<%=request.getContextPath()%>/login"><i class="fa-solid fa-user"></i> Se Connecter </a></li>
	            </ul>
	        </div>
	    </nav>
	 </c:otherwise>
</c:choose>

    
