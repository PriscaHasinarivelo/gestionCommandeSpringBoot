package com.projet.tickets.tefyproject.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projet.tickets.tefyproject.model.CategorieArticle;
import com.projet.tickets.tefyproject.repository.ICategorieArticleRepository;
import com.projet.tickets.tefyproject.service.intf.ICategorieArticleService;

@Service
public class CategorieArticleService implements ICategorieArticleService {

	@Autowired
	private ICategorieArticleRepository categorieArticleRepository;

	@Override
	public List<CategorieArticle> findAllCategorieArticle() throws Exception {
		return categorieArticleRepository.findAll();
	}

	@Override
	public CategorieArticle findById(int id) throws Exception {
		return categorieArticleRepository.findById(id).get();
	}

	@Override
	public List<CategorieArticle> createCategorieArticle(CategorieArticle categorieArticle) throws Exception {
		categorieArticleRepository.save(categorieArticle);
		return findAllCategorieArticle();
	}

	@Override
	public CategorieArticle updateCategorieArticle(CategorieArticle categorieArticle, int id) throws Exception {
		CategorieArticle categorieArticleUpdated = findById(id);
		categorieArticleUpdated.setLibelle(categorieArticle.getLibelle());
		categorieArticleUpdated.setReference(categorieArticle.getReference());
		categorieArticleRepository.save(categorieArticleUpdated);
		return categorieArticleUpdated;
	}

	@Override
	public void deleteCategorieArticle(int id_categorie_article) throws Exception {
		categorieArticleRepository.delete(findById(id_categorie_article));
	}

}
