package fr.eni.enchere.bo;

public class Categorie {
	//On veut l'identifiant et le nom de la catégorie
	private int no_categorie;
	private String libelle;
	
	
	public int getNo_categorie() {
		return no_categorie;
	}
	public void setNo_categorie(int no_categorie) {
		this.no_categorie = no_categorie;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
	public Categorie(int no_categorie, String libelle) {
		super();
		this.no_categorie = no_categorie;
		this.libelle = libelle;
	}
	public Categorie() {
		super();
	}
	
	
	
}
