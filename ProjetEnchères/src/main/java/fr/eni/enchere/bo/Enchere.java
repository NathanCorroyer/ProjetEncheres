package fr.eni.enchere.bo;

public class Enchere {
	private String date_enchere;
	private String montant_enchere;
	private Article article;
	private Utilisateur utilisateur;
	
	
	public String getDate_enchere() {
		return date_enchere;
		
	}
	public void setDate_enchere(String date_enchere) {
		this.date_enchere = date_enchere;
	}
	public String getMontant_enchere() {
		return montant_enchere;
	}
	public void setMontant_enchere(String montant_enchere) {
		this.montant_enchere = montant_enchere;
	}
	public Article getArticle() {
		return article;
	}
	public void setArticle(Article article) {
		this.article = article;
	}
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	public Enchere(String date_enchere, String montant_enchere, Article article, Utilisateur utilisateur) {
		this.date_enchere = date_enchere;
		this.montant_enchere = montant_enchere;
		this.article = article;
		this.utilisateur = utilisateur;
	}
	
	
	
	@Override
	public String toString() {
		return String.format("enchère || date enchère : %s, montant enchère %s, article %s, utilisateur %s", this.date_enchere, this.montant_enchere, this.article.getNom_Article(), this.utilisateur.getPseudo() );
	}
	
	public Enchere() {}
	
}
