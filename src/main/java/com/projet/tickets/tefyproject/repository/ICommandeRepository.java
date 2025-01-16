package com.projet.tickets.tefyproject.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.projet.tickets.tefyproject.model.Commande;

public interface ICommandeRepository extends JpaRepository<Commande, Integer> {

	@Query(value = "SELECT * FROM Commande WHERE STATUS = 'PAYE' and id_table_restaurant = ?1", nativeQuery = true)
	public List<Commande> voirCommandePayeParTable(int idTable);

	// à reconfirmer par le serveur
	@Query(value = "SELECT * FROM Commande WHERE STATUS = 'VALIDE' and id_table_restaurant = ?1", nativeQuery = true)
	public List<Commande> voirCommandeNonPayeParTable(int idTable);

	@Query(value = "SELECT * FROM Commande WHERE STATUS = 'VALIDE 'and id_table_restaurant = ?1", nativeQuery = true)
	public List<Commande> voirCommandeValiderParTable(int idTable);

	// à reconfirmer par le serveur
	@Query(value = "SELECT * FROM Commande WHERE STATUS = 'EN_ATTENTE' and id_table_restaurant = ?1", nativeQuery = true)
	public List<Commande> voirCommandeNonValiderParTable(int idTable);

	@Query(value = "SELECT * FROM Commande WHERE STATUS IN ('EN_ATTENTE','VALIDE') and id_table_restaurant = ?1", nativeQuery = true)
	public List<Commande> voirCommandePayeOuEnAttenteParTable(int idTable);

	// public void validerCommande(Commande commande);

	@Query(value = "SELECT * FROM Commande WHERE  STATUS = 'PAYE' and date BETWEEN ?1 and ?2", nativeQuery = true)
	public List<Commande> rechercheParDate(LocalDate date_debut, LocalDate date_fin);

	@Query(value = "SELECT ID_ARTICLE, STATUS, DATE,  SUM(nombre) as total_nombre FROM Commande WHERE  STATUS = 'PAYE' and date BETWEEN ?1 and ?2 GROUP BY ID_ARTICLE", nativeQuery = true)
	public List<Commande> rechercheCommandeParDateParArticle(LocalDate date_debut, LocalDate date_fin);

	@Query(value = "SELECT * FROM Commande WHERE reference = ?1", nativeQuery = true)
	public List<Commande> voirCommandeParReference(String ref_commande);

	@Query(value = "SELECT * FROM Commande WHERE STATUS = 'EN_ATTENTE' and id_table_restaurant = ?1", nativeQuery = true)
	public List<Commande> voirCommandeEnAttenteParTable(int idTable);

	@Query(value = "SELECT DATE,ID_ARTICLE,ID_COMMANDE,ID_TABLE_RESTAURANT, NOMBRE, REFERENCE, STATUS FROM Commande WHERE STATUS IN ('EN_ATTENTE','VALIDE', 'ANNULER') GROUP BY REFERENCE, ID_ARTICLE", nativeQuery = true)
	public List<Commande> voirCommandeNonPaye();
	
	@Query(value = "SELECT * FROM Commande WHERE STATUS = 'PAYE'", nativeQuery = true)
	public List<Commande> rechercheCommandeParDateParArticle();
	
}
