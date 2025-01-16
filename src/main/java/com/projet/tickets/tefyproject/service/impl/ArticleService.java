package com.projet.tickets.tefyproject.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projet.tickets.tefyproject.model.Article;
import com.projet.tickets.tefyproject.model.CategorieArticle;
import com.projet.tickets.tefyproject.model.HistoriquePrixArticle;
import com.projet.tickets.tefyproject.repository.IArticleRepository;
import com.projet.tickets.tefyproject.repository.ICategorieArticleRepository;
import com.projet.tickets.tefyproject.service.intf.IArticleService;

@Service
public class ArticleService implements IArticleService {

	@Autowired
	public IArticleRepository articleRepository;

//	@Autowired
//	public IHistoriquePrixRepository historiquePrixRepository;

	@Autowired
	public ICategorieArticleRepository categorieArticleRepository;

	public ArticleService() {
		super();
	}


	@Override
	public List<Article> findAllArticle() throws Exception {
		return articleRepository.findAll();
	}

	@Override
	public Article findById(int id) throws Exception {
		return articleRepository.findById(id).get();
	}

	@Override
	public List<Article> createArticle(Article article) throws Exception {
		articleRepository.save(article);
		HistoriquePrixArticle historique = new HistoriquePrixArticle();

		historique.setPrix(article.getPrix());
		historique.setDate(LocalDate.now());
		historique.setArticle(article);
		// historiquePrixRepository.save(historique);

		return findAllArticle();
	}

	@Override
	public Article updateArticle(Article article, int id) throws Exception {
		Article articleExistant = findById(id);
		// HistoriquePrixArticle historique = new HistoriquePrixArticle();
		CategorieArticle categorieExistant = categorieArticleRepository
				.findById(article.getCategorie_article().getId_categorie_article()).get();
		
		articleExistant.setId(id);
		articleExistant.setLibelle(article.getLibelle());
		articleExistant.setDate(article.getDate());
		articleExistant.setDisponible(article.isDisponible());
		articleExistant.setReference(article.getReference());
		articleExistant.setUrl_photos(article.getUrl_photos());
		articleExistant.setCategorie_article(categorieExistant);

		// Ecriture dans l'historique
		if (article.getPrix() != articleExistant.getPrix()) {
			articleExistant.setPrix(article.getPrix());
//			historique.setPrix(articleExistant.getPrix());
//			historique.setDate(LocalDate.now());
//			historique.setArticle(articleExistant);
			// historiquePrixRepository.save(historique);
		}

		articleRepository.save(articleExistant);

		
		return findById(id);
	}

	@Override
	public void deleteArticle(int idArticle) throws Exception {
		articleRepository.delete(findById(idArticle));
	}

	@Override
	public void changerDisponibilite(Article article, boolean disponibilite) {
		try {
			article.setDisponible(disponibilite);
			updateArticle(article, article.getId());
		} catch (Exception e) {
		}
	}

	// Liste des articles par dates
	@Override
	public List<Article> rechercheParDateParArticle(LocalDate date_debut, LocalDate date_fin, String ref_article) {
		return articleRepository.rechercheParDateParArticle(date_debut, date_fin, ref_article);
	}

	@Override
	public List<Article> listeArticleParCategorie() {
		return articleRepository.listeArticleParCategorie();
	}

}
