package com.projet.tickets.tefyproject.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.projet.tickets.tefyproject.model.Article;

public interface IArticleRepository extends JpaRepository<Article, Integer> {

	@Query(value = "FROM article WHERE date BETWEEN ?1 and ?2 and reference = ?3", nativeQuery = true)
	public List<Article> rechercheParDateParArticle(LocalDate date_debut, LocalDate date_fin, String ref_article);

	@Query(value = "FROM article GROUP BY id_categorie_article", nativeQuery = true)
	public List<Article> listeArticleParCategorie();

}
