package fr.eni.enchere.bo;

import java.time.LocalDate;

public class Article {
	 private boolean vendu;
	 private Integer no_article;
	 private String nom_Article;
	 private String description;
	 private LocalDate date_debut_encheres;
	 private LocalDate date_fin_encheres;
	 private int prix_initial;
	 private int prix_vente;
	 private int categorie;
	 private int noUtilisateur;
	 //private Utilisateur vendeur;
	 //private Utilisateur acheteur;
	 
	 
	public int getNoUtilisateur() {
		return noUtilisateur;
	}
	public Article() {
		super();
	}
	public Article(String nom_Article, String description, LocalDate date_debut_encheres, LocalDate date_fin_encheres,
			int prix_initial, int prix_vente, int noUtilisateur,int categorie) {
		super();
		this.nom_Article = nom_Article;
		this.description = description;
		this.date_debut_encheres = date_debut_encheres;
		this.date_fin_encheres = date_fin_encheres;
		this.prix_initial = prix_initial;
		this.prix_vente = prix_vente;
		this.noUtilisateur = noUtilisateur;
		this.categorie = categorie;
	}
	public void setNoUtilisateur(int noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
	}
	public Integer getNoArticle() {
		return no_article;
	}
	public void setNoArticle(Integer noArticle) {
		this.no_article = noArticle;
	}
	public boolean isVendu() {
		return vendu;
	}
	public void setVendu(boolean vendu) {
		this.vendu = vendu;
	}
	public String getNom_Article() {
		return nom_Article;
	}
	public void setNom_Article(String nom_Article) {
		this.nom_Article = nom_Article;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDate getDate_debut_encheres() {
		return date_debut_encheres;
	}
	public void setDate_debut_encheres(LocalDate date_debut_encheres) {
		this.date_debut_encheres = date_debut_encheres;
	}
	public LocalDate getDate_fin_encheres() {
		return date_fin_encheres;
	}
	public void setDate_fin_encheres(LocalDate date_fin_encheres) {
		this.date_fin_encheres = date_fin_encheres;
	}
	public int getPrix_initial() {
		return prix_initial;
	}
	public void setPrix_initial(int prix_initial) {
		this.prix_initial = prix_initial;
	}
	public int getPrix_vente() {
		return prix_vente;
	}
	public void setPrix_vente(int prix_vente) {
		this.prix_vente = prix_vente;
	}
	public int getCategorie() {
		return categorie;
	}
	public void setCategorie(int categorie) {
		this.categorie = categorie;
	}
//	public Utilisateur getVendeur() {
//		return vendeur;
//	}
//	public void setVendeur(Utilisateur vendeur) {
//		this.vendeur = vendeur;
//	}
//	public Utilisateur getAcheteur() {
//		return acheteur;
//	}
//	public void setAcheteur(Utilisateur acheteur) {
//		this.acheteur = acheteur;
//	}
//	 
	@Override
	public String toString() {
		return String.format("%d || %s || %s || %s || %s || %d || %d || %d || %d", no_article, nom_Article, description, date_debut_encheres.toString(), 
																			date_fin_encheres.toString(), prix_initial, prix_vente, noUtilisateur, categorie);
	}
	 
}	
