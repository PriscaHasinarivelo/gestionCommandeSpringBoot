package com.projet.tickets.tefyproject.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.projet.tickets.tefyproject.model.Article;
import com.projet.tickets.tefyproject.service.impl.ArticleService;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class ArticleController {

	private ArticleService articleService;
	public static String UPLOAD_DIRECTORY = System
			.getProperty("user.home") + "/react-app/caissier-project/caissier-app/src/uploads";

	public ArticleService getArticleService() {
		return articleService;
	}

	@Autowired
	public void setArticleService(ArticleService articleService) {
		this.articleService = articleService;
	}

	public ArticleController(ArticleService articleService) {
		super();
		this.articleService = articleService;
	}

	@GetMapping("/articles/getAllArticles")
	public List<Article> getAllArticles() throws Exception {
		return articleService.findAllArticle();
	}

	@GetMapping("/articles/getArticlesById/{id}")
	public Article getArticlesById(@PathVariable("id") int id) throws Exception {
		return articleService.findById(id);
	}

	@PostMapping("/articles/createArticle")
	public List<Article> createArticle(@RequestBody Article article) throws Exception {
		return articleService.createArticle(article);
	}

	@PutMapping("/articles/updateArticle/{id}")
	public Article updateArticle(@RequestBody Article article, @PathVariable("id") int id) throws Exception {
		return articleService.updateArticle(article, id);
	}

	@DeleteMapping("/article/deleteArticle/{id}")
	public void deleteArticle(@PathVariable("id") int id) throws Exception {
		articleService.deleteArticle(id);
	}

	@GetMapping("/rechercheParDateParArticle/{date_debut}/{date_fin}/{ref_article}")
	public List<Article> rechercheParDateParArticle(LocalDate date_debut, LocalDate date_fin, String ref_article) {
		return articleService.rechercheParDateParArticle(date_debut, date_fin, ref_article);
	}

	@GetMapping("/listeArticleParCategorie/")
	public List<Article> listeArticleParCategorie() {
		return articleService.listeArticleParCategorie();
	}

	@CrossOrigin("http://localhost:5173")
	@PostMapping(path = "/article/uploadImage/{id_article}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public String uploadImage(@PathVariable("id_article") int id_article, @RequestParam("image") MultipartFile file)
			throws Exception {
		StringBuilder fileNames = new StringBuilder();
		Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, file.getOriginalFilename());
		fileNames.append(file.getOriginalFilename());
		Files.write(fileNameAndPath, file.getBytes());

		Article article = getArticlesById(id_article);
		article.setUrl_photos("src/uploads/" + file.getOriginalFilename());
		articleService.updateArticle(article, id_article);
		return UPLOAD_DIRECTORY + "_" + file.getOriginalFilename();
	}

}
