package fr.eni.enchere.bo;

import java.time.LocalDate;

public class Article {
	 private boolean vendu;
	 private String nom_Article;
	 private String description;
	 private LocalDate date_debut_encheres;
	 private LocalDate date_fin_encheres;
	 private float prix_initial;
	 private float prix_vente;
	 private Categorie categorie;
	 private Utilisateur vendeur;
	 private Utilisateur acheteur;
	 
}	
