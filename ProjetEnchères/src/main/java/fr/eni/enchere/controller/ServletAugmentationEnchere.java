package fr.eni.enchere.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.enchere.bll.ArticleManager;
import fr.eni.enchere.bll.BLLException;
import fr.eni.enchere.bll.EnchereManager;
import fr.eni.enchere.bll.UtilisateurManager;
import fr.eni.enchere.bo.Article;
import fr.eni.enchere.bo.Enchere;
import fr.eni.enchere.bo.Utilisateur;

/**
 * Servlet implementation class ServletAugmentationEnchere
 */
@WebServlet("/ServletAugmentationEnchere")
public class ServletAugmentationEnchere extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Récupération des paramètres sur l'acheteur, le connecté
		Utilisateur connectedUser = (Utilisateur) request.getSession().getAttribute("userConnected");
		Integer noUtilisateur = connectedUser.getNoUtilisateur();
		//Récupération des paramètres sur le vendeur
		String pseudoVendeur = request.getParameter("pseudoVendeur");
		//Récupération et formatage des dates de l'enchère et du moment actuel
		String momentPresentString = LocalDateTime.now().toString();
		String finEnchereString = request.getParameter("finEnchere"); 
		LocalDateTime momentPresent = LocalDateTime.parse( momentPresentString );
		DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy, HH 'h' mm", Locale.FRENCH);
		LocalDateTime finEnchere = LocalDateTime.parse( finEnchereString, formatter1 );
		DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS");
		String momentPresentFormate = momentPresent.format(formatter2);
		String finEnchereFormate = finEnchere.format(formatter2);
		//Récupération des paramètres sur l'article ( prix de base et numéro )
		int enchereProposee = Integer.parseInt(request.getParameter("enchereProposee"));
		int noArticleEnVente = Integer.parseInt(request.getParameter("noArticle"));
		//Déclaration des instance de manager et d'objets dont j'aurais besoin 
		UtilisateurManager um = UtilisateurManager.getInstance();
		Utilisateur vendeur = null ;
		ArticleManager am = ArticleManager.getInstance();
		Article articleActuel = null;
		Integer prixInitialArticle = null ;
		Integer totalCreditUser = null ;
		Article articleEnVente = null ;
		
		try {
			vendeur = um.selectUserByPseudo(pseudoVendeur);
			articleActuel = am.selectArticleById(noArticleEnVente);
			prixInitialArticle = articleActuel.getPrix_initial();
			
			System.out.println(prixInitialArticle);
			
		} catch (SQLException | BLLException e) {
			e.printStackTrace();
		}
		
		
		if ( prixInitialArticle < enchereProposee && momentPresent.isBefore(finEnchere))
		{
			try {
				Enchere nouvelleEnchere = new Enchere( finEnchere ,
						  enchereProposee ,
						  articleActuel,
						  connectedUser );
							
				EnchereManager em = EnchereManager.getInstance();
				Integer keyEnchere = em.ajouter(nouvelleEnchere);
				
				totalCreditUser = um.selectUserByNumero(noUtilisateur).getCredit();
				
				
				if ( totalCreditUser > enchereProposee ) {
					
					System.out.println(totalCreditUser);
					System.out.println(enchereProposee);
					
					Integer creditRestant = totalCreditUser - enchereProposee; 
					
					System.out.println( creditRestant );
					
					connectedUser.setCredit(creditRestant);
					um.update(connectedUser);
					am.selectArticleById(noArticleEnVente).setPrix_initial(enchereProposee);
					am.updateEnchere(enchereProposee, noArticleEnVente);
					
					//request.setAttribute("article", articleActuel);
					//request.setAttribute("Vendeur", vendeur );
					//request.setAttribute("prixInitalEnchere", enchereProposee );
					request.setAttribute("reussite_enchere", "Vous avez réussi à enchérir.");
					request.getRequestDispatcher("/index.jsp").forward(request, response);
					
				} else {
					
					System.out.println("Vous n'avez pas assez de crédit.");
					request.setAttribute("article", articleActuel);
					request.setAttribute("Vendeur", vendeur );
					request.setAttribute("echec_paiement", "Vous n'avez pas assez de crédit.");
					request.getRequestDispatcher("/WEB-INF/jsp/details_enchere.jsp").forward(request, response);
				}
				
			} catch (SQLException | BLLException e) {
				e.printStackTrace();
			}
		} else {
			
			//request.setAttribute("article", articleActuel);
			//request.setAttribute("Vendeur", vendeur );
			request.setAttribute("echec_enchere", "Vous n'avez pas réussi à enchérir.");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
		
		
	}
	

	

}
