package fr.eni.enchere.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.enchere.bll.ArticleManager;
import fr.eni.enchere.bll.CategorieManager;
import fr.eni.enchere.bo.Article;
import fr.eni.enchere.bo.Categorie;

/**
 * Servlet implementation class ServletRecuperationListeEncheres
 */
@WebServlet("/ServletRecuperationListeEncheres")
public class ServletRecuperationListeEncheres extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Messages d'erreur ou de succès qui s'affichent en cas de suppression de compte ou création
		if(request.getAttribute("succes_creation") != null) {
			request.setAttribute("succes_creation", request.getAttribute("succes_creation"));
		}
		if(request.getAttribute("succesSuppression") != null) {
			request.setAttribute("succesSuppression", request.getAttribute("succesSuppression"));
		}
		if(request.getAttribute("erreurSuppression") != null) {
			request.setAttribute("erreurSuppression", request.getAttribute("erreurSuppression"));
		}
		
		
		
		
		//Dans tous les cas je veux récupérer ma liste de catégories pour l'afficher sur l'index
		CategorieManager cm = CategorieManager.getInstance();
		List<Categorie> listeCategorie = cm.selectAll(); 
        request.setAttribute("listeCategorie" , listeCategorie);
		
        //Initialisation de la variable qui me permet de récupérer la liste d'articles à afficher
        ArticleManager am = ArticleManager.getInstance();       
        List<Article> listeArticles = new ArrayList<>();
    	List<Article> listeEncheresEnCours = new ArrayList<>();
    	List<Article> listeEncheresFinies = new ArrayList<>();
    	
    	//La catégorie qu'a choisi l'utilisateur
        String categorie = request.getParameter("categorie");
        Integer no_categorie = null;
        //Si elle n'est pas nulle et qu'elle vaut "all", ça correspond à la catégorie "toutes"
        if(categorie != null) {
        	if(categorie.equals("all")) {
        		no_categorie = -1;
        	}else {
        		//Sinon je lui donne la valeur du no_categorie
        		no_categorie = Integer.parseInt(request.getParameter("categorie"));        		
        	}
        }
        
        
        //Je récupère le mot tapé pour filtrer les enchères
        String nomTri = request.getParameter("search");
        
        //Si je n'ai pas reçu de catégorie ou si on ne trie pas par catégorie
        if(categorie == null || no_categorie==-1){
        	//Et qu'en plus je ne trie pas par mot-clé
        	if(nomTri == null || nomTri.trim().equals("")) {
        		//Je récupère tous les articles
        		listeArticles = am.selectAll();
        		listeEncheresEnCours = am.selectAllEnCours();
            	listeEncheresFinies = am.selectAllFinies();
        		
        	}else {
        		//J'ai un mot clé donc je sélectionne par mot-clé
        		try {
					listeArticles = am.selectByName(nomTri.trim());
					listeEncheresEnCours = am.selectEnchereEnCoursByName(nomTri.trim());
					listeEncheresFinies = am.selectEnchereFinieByName(nomTri.trim());
					// Je dois faire le tri des expressions dans les listes en cours et finies
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
        	}
        }else {
        	//Si j'ai une sélection de catégorie mais pas de mot-clé
        	if(nomTri == null || nomTri.trim().equals("")) {	        	
					try {
						//Simple sélection par catégorie
						listeArticles = am.selectArticleByCategorie(Integer.parseInt(request.getParameter("categorie")));
						listeEncheresEnCours = am.selectEnchereEnCoursByCategorie(Integer.parseInt(request.getParameter("categorie")));
						listeEncheresFinies = am.selectEnchereFinieByCategorie(Integer.parseInt(request.getParameter("categorie")));
					} catch (NumberFormatException | SQLException e) {
						//Gestion d'erreur 
						e.printStackTrace();
					}
			
        		}else {
        			//J'ai une sélection de catégorie ET un mot-clé
					try {
						//Je sélectionne seulement les articles de la catégorie et du mot-clé donnés
						listeArticles = am.selectArticleByCategorieAndByName(Integer.parseInt(request.getParameter("categorie")),nomTri.trim());
						listeEncheresEnCours = am.selectEnchereEnCoursByCategorieAndByName(Integer.parseInt(request.getParameter("categorie")),nomTri.trim());
						listeEncheresFinies = am.selectEnchereFinieByCategorieAndByName(Integer.parseInt(request.getParameter("categorie")),nomTri.trim());
					} catch (NumberFormatException | SQLException e) {
						// Gestion d'erreur
						e.printStackTrace();
					}
		
        	}
        }
        
        request.setAttribute("categorie", no_categorie);
        if(request.getParameter("etat_enchere") == null ||  request.getParameter("etat_enchere").equals("all")) {
        	request.setAttribute("listeArticles", listeArticles); 
        }else if(request.getParameter("etat_enchere").equals("enCours")) {
        	request.setAttribute("listeArticles", listeEncheresEnCours);
        }else {
        	request.setAttribute("listeArticles", listeEncheresFinies);
        	    
        }
        
		RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
		rd.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
