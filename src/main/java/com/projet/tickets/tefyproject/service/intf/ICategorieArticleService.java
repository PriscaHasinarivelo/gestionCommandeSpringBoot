package com.projet.tickets.tefyproject.service.intf;

import java.util.List;

import com.projet.tickets.tefyproject.model.CategorieArticle;

public interface ICategorieArticleService {
	public List<CategorieArticle> findAllCategorieArticle() throws Exception;

	public CategorieArticle findById(int id) throws Exception;

	public List<CategorieArticle> createCategorieArticle(CategorieArticle categorieArticle) throws Exception;

	public CategorieArticle updateCategorieArticle(CategorieArticle categorieArticle, int id) throws Exception;

	public void deleteCategorieArticle(int id_categorie_article) throws Exception;

}
