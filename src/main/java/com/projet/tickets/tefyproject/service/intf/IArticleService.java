package com.projet.tickets.tefyproject.service.intf;

import java.time.LocalDate;
import java.util.List;

import com.projet.tickets.tefyproject.model.Article;

public interface IArticleService {

	public List<Article> findAllArticle() throws Exception;

	public Article findById(int id) throws Exception;

	public List<Article> createArticle(Article article) throws Exception;

	public Article updateArticle(Article article, int id) throws Exception;

	public void deleteArticle(int idArticle) throws Exception;

	public void changerDisponibilite(Article article, boolean disponible);

	public List<Article> rechercheParDateParArticle(LocalDate date_debut, LocalDate date_fin, String ref_article);

	public List<Article> listeArticleParCategorie();
}
