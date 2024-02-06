package fr.eni.enchere.bo;

import java.time.LocalDateTime;

public class Enchere {
	private int no_enchere;
	private LocalDateTime date_enchere;
	private int montant_enchere;
	private Article article;
	private Utilisateur utilisateur;
	
	
	
	
	public int getNo_enchere() {
		return no_enchere;
	}
	public void setNo_enchere(int no_enchere) {
		this.no_enchere = no_enchere;
	}

	public LocalDateTime getDate_enchere() {
		return date_enchere;
		
	}
	public void setDate_enchere(LocalDateTime date_enchere) {
		this.date_enchere = date_enchere;
	}
	public int getMontant_enchere() {
		return montant_enchere;
	}
	public void setMontant_enchere(int montant_enchere) {
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
	public Enchere(LocalDateTime date_enchere, int montant_enchere, Article article, Utilisateur utilisateur) {
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
	public Enchere(int no_enchere, LocalDateTime date_enchere, int montant_enchere, Article article,
			Utilisateur utilisateur) {
		super();
		this.no_enchere = no_enchere;
		this.date_enchere = date_enchere;
		this.montant_enchere = montant_enchere;
		this.article = article;
		this.utilisateur = utilisateur;
	}
	
	
}
