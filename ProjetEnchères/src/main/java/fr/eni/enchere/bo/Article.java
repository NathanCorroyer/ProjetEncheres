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
	public float getPrix_initial() {
		return prix_initial;
	}
	public void setPrix_initial(float prix_initial) {
		this.prix_initial = prix_initial;
	}
	public float getPrix_vente() {
		return prix_vente;
	}
	public void setPrix_vente(float prix_vente) {
		this.prix_vente = prix_vente;
	}
	public Categorie getCategorie() {
		return categorie;
	}
	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}
	public Utilisateur getVendeur() {
		return vendeur;
	}
	public void setVendeur(Utilisateur vendeur) {
		this.vendeur = vendeur;
	}
	public Utilisateur getAcheteur() {
		return acheteur;
	}
	public void setAcheteur(Utilisateur acheteur) {
		this.acheteur = acheteur;
	}
	 
	 
}	
