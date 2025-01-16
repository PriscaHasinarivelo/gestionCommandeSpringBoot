package com.projet.tickets.tefyproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projet.tickets.tefyproject.model.CategorieArticle;
import com.projet.tickets.tefyproject.service.impl.CategorieArticleService;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class CategorieArticleController {

	private CategorieArticleService categorieArticleService;

	public CategorieArticleService getCategorieArticleService() {
		return categorieArticleService;
	}

	@Autowired
	public void setCategorieArticleService(CategorieArticleService categorieArticleService) {
		this.categorieArticleService = categorieArticleService;
	}

	@GetMapping("/categoriearticles/getAllCategorieArticles")
	public List<CategorieArticle> getAllCategorieArticles() throws Exception {
		return categorieArticleService.findAllCategorieArticle();
	}

	@GetMapping("/categoriearticles/getCategorieArticlesById/{id}")
	public CategorieArticle getCategorieArticlesById(@PathVariable("id") int id) throws Exception {
		return categorieArticleService.findById(id);
	}

	@PostMapping("/categoriearticles/createCategorieArticle")
	public List<CategorieArticle> createCategorieArticle(@RequestBody CategorieArticle CategorieArticle)
			throws Exception {
		return categorieArticleService.createCategorieArticle(CategorieArticle);
	}

	@PutMapping("/categoriearticles/updateCategorieArticle/{id}")
	public CategorieArticle updateCategorieArticle(@RequestBody CategorieArticle CategorieArticle,
			@PathVariable("id") int id) throws Exception {
		return categorieArticleService.updateCategorieArticle(CategorieArticle, id);
	}

	@DeleteMapping("/categoriearticle/deleteCategorieArticle/{id}")
	public void deleteCategorieArticle(@PathVariable("id") int id) throws Exception {
		categorieArticleService.deleteCategorieArticle(id);
	}

}
