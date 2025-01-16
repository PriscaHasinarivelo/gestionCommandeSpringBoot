package com.projet.tickets.tefyproject.service.intf;

import java.time.LocalDate;
import java.util.List;

import com.projet.tickets.tefyproject.model.Commande;

public interface ICommandeService {
	public List<Commande> findAllCommande() throws Exception;

	public Commande findById(int id) throws Exception;

	public List<Commande> createCommande(Commande commande) throws Exception;

	public Commande updateCommande(Commande commande, int id) throws Exception;

	public void deleteCommande(int idCommande) throws Exception;

	public void validerCommande(String ref_commande);

	public void payerCommande(String ref_commande);

	public List<Commande> rechercheParDate(LocalDate date_debut, LocalDate date_fin);

	public List<Commande> rechercheCommandeParDateParArticle(LocalDate date_debut, LocalDate date_fin);

	public List<Commande> voirCommandeParReference(String ref_commande);

	public List<Commande> voirCommandePayeParTable(int idTable);

	public List<Commande> voirCommandeNonPayeParTable(int idTable);

	public List<Commande> voirCommandeEnAttenteParTable(int idTable);

	public List<Commande> voirCommandePayeOuEnAttenteParTable(int idTable);

	public List<Commande> voirCommandeValiderParTable(int idTable);

	public List<Commande> voirCommandeNonValiderParTable(int idTable);

	public List<Commande> annulerCommande(String ref_commande);

	public List<Commande> voirCommandeNonPaye();
}
