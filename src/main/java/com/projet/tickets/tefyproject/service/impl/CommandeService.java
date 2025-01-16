package com.projet.tickets.tefyproject.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projet.tickets.tefyproject.model.Commande;
import com.projet.tickets.tefyproject.model.Commande.StatusCommandeEnum;
import com.projet.tickets.tefyproject.model.TableRestaurant;
import com.projet.tickets.tefyproject.repository.ICommandeRepository;
import com.projet.tickets.tefyproject.repository.ITableRepository;
import com.projet.tickets.tefyproject.service.intf.ICommandeService;

@Service
public class CommandeService implements ICommandeService {

	@Autowired
	public ICommandeRepository commandeRepository;

	@Autowired
	public ITableRepository tableRepository;

	public CommandeService() {
		super();
	}

	@Override
	public List<Commande> findAllCommande() throws Exception {
		return commandeRepository.findAll();
	}

	@Override
	public Commande findById(int id) throws Exception {
		return commandeRepository.findById(id).get();
	}

	@Override
	public List<Commande> createCommande(Commande commande) throws Exception {
		TableRestaurant tableExistant = tableRepository.findById(commande.getTable_restaurant().getId()).get();
		commande.setDate(LocalDate.now());
		commande.setTable_restaurant(tableExistant);
		commandeRepository.save(commande);
		return commandeRepository.findAll();
	}

	@Override
	public Commande updateCommande(Commande commande, int id) throws Exception {
		Commande updated = findById(id);
		updated.setArticle(commande.getArticle());
		updated.setDate(commande.getDate());
		updated.setReference(commande.getReference());
		updated.setTable_restaurant(commande.getTable_restaurant());
		updated.setStatus(commande.getStatus());
		commandeRepository.save(updated);
		return findById(id);
	}

	@Override
	public void deleteCommande(int idCommande) throws Exception {
		commandeRepository.delete(findById(idCommande));
	}

	@Override
	public void validerCommande(String ref_commande) {
		try {
			List<Commande> listCommande = voirCommandeParReference(ref_commande);

			for (Commande commandeAValider : listCommande) {
				commandeAValider.setStatus(StatusCommandeEnum.VALIDE);
				updateCommande(commandeAValider, commandeAValider.getId());
			}

		} catch (Exception e) {
		}
	}

	@Override
	public void payerCommande(String ref_commande) {
		try {
			List<Commande> listCommande = voirCommandeParReference(ref_commande);

			for (Commande commandeAValider : listCommande) {
				commandeAValider.setStatus(StatusCommandeEnum.PAYE);
				updateCommande(commandeAValider, commandeAValider.getId());
			}

		} catch (Exception e) {
		}
	}


	// Liste des commandes des articles passer par date
	@Override
	public List<Commande> rechercheParDate(LocalDate date_debut, LocalDate date_fin) {
		// Create query
		return commandeRepository.rechercheParDate(date_debut, date_fin);
	}


	@Override
	public List<Commande> voirCommandeParReference(String ref_commande) {
		return commandeRepository.voirCommandeParReference(ref_commande);
	}

	@Override
	public List<Commande> voirCommandePayeParTable(int idTable) {
		return commandeRepository.voirCommandePayeParTable(idTable);
	}

	@Override
	public List<Commande> voirCommandeNonPayeParTable(int idTable) {
		return commandeRepository.voirCommandeNonPayeParTable(idTable);
	}

	@Override
	public List<Commande> voirCommandeValiderParTable(int idTable) {
		return commandeRepository.voirCommandeValiderParTable(idTable);
	}

	@Override
	public List<Commande> voirCommandeNonValiderParTable(int idTable) {
		return commandeRepository.voirCommandeNonValiderParTable(idTable);
	}

	@Override
	public List<Commande> annulerCommande(String ref_commande) {
		try {
			List<Commande> listCommande = voirCommandeParReference(ref_commande);

			for (Commande commandeAValider : listCommande) {
				commandeAValider.setStatus(StatusCommandeEnum.ANNULER);
				updateCommande(commandeAValider, commandeAValider.getId());
			}

		} catch (Exception e) {
		}

		return voirCommandeParReference(ref_commande);
	}

	@Override
	public List<Commande> voirCommandeEnAttenteParTable(int idTable) {
		return commandeRepository.voirCommandeEnAttenteParTable(idTable);
	}

	@Override
	public List<Commande> voirCommandePayeOuEnAttenteParTable(int idTable) {
		return commandeRepository.voirCommandePayeOuEnAttenteParTable(idTable);
	}

	@Override
	public List<Commande> voirCommandeNonPaye() {
		return commandeRepository.voirCommandeNonPaye();
	}

	@Override
	public List<Commande> rechercheCommandeParDateParArticle(LocalDate date_debut, LocalDate date_fin) {
		return commandeRepository.rechercheCommandeParDateParArticle();
	}

}
