package fr.eni.enchere.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import fr.eni.enchere.bll.ArticleManager;
import fr.eni.enchere.bll.CategorieManager;
import fr.eni.enchere.bll.UtilisateurManager;
import fr.eni.enchere.bo.Article;
import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.bo.Utilisateur;

/**
 * Servlet implementation class ServletCreerEnchere
 */
@MultipartConfig 
@WebServlet("/creer_enchere")
public class ServletCreerEnchere extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utilisateur connectedUser = (Utilisateur) request.getSession().getAttribute("userConnected");
		CategorieManager cm = CategorieManager.getInstance();
		List<Categorie> listeCategorie = cm.selectAll(); 
        request.setAttribute("listeCategorie" , listeCategorie);
		request.setAttribute("user", connectedUser);
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/creerEnchere.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// Upload de l'image 
			Part filePart = request.getPart("photoArticle"); //Récupération du fichier 
			String nomFichier = filePart.getSubmittedFileName(); //Récupération du nom du fichier 
			//String directoryPath = "C:\\Users\\Sushiett\\git\\ProjetEncheres\\ProjetEnchères\\src\\main\\webapp\\images\\imagesArticles\\";
			String directoryPath = "C:\\Users\\mlecam2023\\git\\ProjetEncheres\\ProjetEnchères\\src\\main\\webapp\\images\\imagesArticles\\";
			String directoryAbsolute = directoryPath + nomFichier ;
			String cheminAbsoluImage = "/ProjetEnchères/images/imagesArticles/" + nomFichier ; //chemin absolu = nom du dossier récupérant les images + nom image 
			
			InputStream is = filePart.getInputStream(); // InputStream sert à lire les infos, un flux 
			FileOutputStream os = new FileOutputStream(directoryAbsolute); // OutputStream sert à écrire les infos
			
			int bytesLus = -1 ;   //Déclare une variable pour stocker le nombre d'octets lus à chaque itération
			byte[] buffer = new byte[8192]; // Déclare un tampon de 4096 octets pour stocker les données du flux
			while (( bytesLus = is.read( buffer )) != -1 ) { //lit les données du flux d'entrée, et les écrit dans le flux de sortie
				os.write( buffer, 0 , bytesLus );
			}
			
			is.close(); //fermeture des flux
			os.close();
			
			//Fin de récupération image 
		 	
			ArticleManager a = ArticleManager.getInstance();
		 	Utilisateur user = (Utilisateur) request.getSession().getAttribute("userConnected");
		 	int numeroVendeur = user.getNoUtilisateur();
			String nom = request.getParameter("nom_article");
	        String description = request.getParameter("description");
	        int categorie = Integer.parseInt(request.getParameter("categorie"));
	        int prixInitial = Integer.parseInt(request.getParameter("prix_initial"));
	        LocalDateTime dateDébut = LocalDateTime.parse(request.getParameter("date_debut_encheres"));
	        LocalDateTime dateFin = LocalDateTime.parse(request.getParameter("date_fin_encheres"));
	        
	       // String modalitesRetrait = request.getParameter("modalitesRetrait");

	        
	        Article art = new Article (nom,description, dateDébut, dateFin, prixInitial, categorie, numeroVendeur, cheminAbsoluImage ); //cheminAbsoluImage
	        Integer key = null;
	        try {
				key = a.ajouter(art);
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
	        
	        
	        
	    
	        
	        Article article = null;
			try {
				article = a.selectArticleById(key);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
	        
	        request.setAttribute("article", article);

	        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/confirmationArticle.jsp");

	        dispatcher.forward(request, response);
	        
	        
	}

}
