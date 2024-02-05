package fr.eni.enchere.bo;


import java.time.LocalDateTime;

public class Article {
	 private boolean vendu;
	 private Integer no_article;
	 private String nom_Article;
	 private String description;
	 private LocalDateTime date_debut_encheres;
	 private LocalDateTime date_fin_encheres;
	 private int prix_initial;
	 private int prix_vente;
	 private int categorie;
	 private int no_utilisateur;
	 private Utilisateur utilisateur;
	 private String imagePath ;
	 private Categorie categorieComplete;

	 
	 


	
	public String getImagePath() {
		return imagePath;
	}


	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}


	public Utilisateur getUtilisateur() {
		return utilisateur;
	}


	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}


	public int getNo_utilisateur() {
		return no_utilisateur;
	}


	public void setNo_utilisateur(int no_utilisateur) {
		this.no_utilisateur = no_utilisateur;
	}


	public Article() {
		super();
	}
	
	
	public Article(String nom_Article, String description, LocalDateTime date_debut_encheres,
			LocalDateTime date_fin_encheres, int prix_initial, int categorie, int no_utilisateur, String imagePath) {
		super();
		this.nom_Article = nom_Article;
		this.description = description;
		this.date_debut_encheres = date_debut_encheres;
		this.date_fin_encheres = date_fin_encheres;
		this.prix_initial = prix_initial;
		this.categorie = categorie;
		this.no_utilisateur = no_utilisateur;
		this.imagePath = imagePath;
	}


	public Article(String nom_Article, String description, LocalDateTime date_debut_encheres, LocalDateTime date_fin_encheres,
			int prix_initial, int prix_vente, int no_utilisateur,int categorie) {
		super();
		this.nom_Article = nom_Article;
		this.description = description;
		this.date_debut_encheres = date_debut_encheres;
		this.date_fin_encheres = date_fin_encheres;
		this.prix_initial = prix_initial;
		this.prix_vente = prix_vente;
		this.categorie = categorie;
		this.no_utilisateur = no_utilisateur;	
	}
	
	public Article(String nom_Article, String description, LocalDateTime date_debut_encheres, LocalDateTime date_fin_encheres,
			int prix_initial, int categorie, int no_utilisateur) {
		super();
		this.nom_Article = nom_Article;
		this.description = description;
		this.date_debut_encheres = date_debut_encheres;
		this.date_fin_encheres = date_fin_encheres;
		this.prix_initial = prix_initial;
		this.categorie = categorie;
		this.no_utilisateur = no_utilisateur;
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
	public LocalDateTime getDate_debut_encheres() {
		return date_debut_encheres;
	}
	public void setDate_debut_encheres(LocalDateTime date_debut_encheres) {
		this.date_debut_encheres = date_debut_encheres;
	}
	public LocalDateTime getDate_fin_encheres() {
		return date_fin_encheres;
	}
	public void setDate_fin_encheres(LocalDateTime date_fin_encheres) {
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

//	 
	@Override
	public String toString() {
		StringBuilder chaine = new StringBuilder();
		chaine.append(String.format("%d || %s || %s || %s || %s || %d || %d || %d || %d || Vendeur : %s %n %s", no_article, nom_Article, description, date_debut_encheres.toString(), 
																			date_fin_encheres.toString(), prix_initial, prix_vente, no_utilisateur, categorie, utilisateur.getPseudo() ));
		
		return chaine.toString();
	}


	public Categorie getCategorieComplete() {
		return categorieComplete;
	}


	public void setCategorieComplete(Categorie cat) {
		this.categorieComplete = cat;
	}
	 
}	
