
 	<%@page import="fr.eni.enchere.bo.Utilisateur"%>
<% if( session.getAttribute("ConnectedUser") != null){ %>
 		<!--Navbar-->
 
    <nav class="navbar">
      <div class="navbar-links">
      <%Utilisateur connectedUser = (Utilisateur) session.getAttribute("ConnectedUser"); %>
          <ul>
              <li><a href="#">Deconnexion</a></li>
              
              <!--  TO DO PAGE ACCUEIL DECONNECTED -->
          </ul>
      </div>
    </nav>
 
 	<% } else {  %>

   
<!-- Deconnected page -->

 <!--Navbar-->
 
 <nav class="navbar">

        <div class="navbar-links">
            <ul>
                <li><a href="<%=request.getContextPath()%>/register"> Créer Un Compte </a></li>
                 <li><a href="<%=request.getContextPath()%>/login"> Se Connecter </a></li>
            </ul>
        </div>
    </nav>
    <% } %>
    
