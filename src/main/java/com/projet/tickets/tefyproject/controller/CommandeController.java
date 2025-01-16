package com.projet.tickets.tefyproject.controller;

import java.time.LocalDate;
import java.util.ArrayList;
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

import com.projet.tickets.tefyproject.model.Commande;
import com.projet.tickets.tefyproject.service.impl.CommandeService;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class CommandeController {
	
	private CommandeService commandeService;

	public CommandeService getCommandeService() {
		return commandeService;
	}

	@Autowired
	public void setCommandeService(CommandeService commandeService) {
		this.commandeService = commandeService;
	}

	public CommandeController(CommandeService commandeService) {
		super();
		this.commandeService = commandeService;
	}
	
	@GetMapping("/commandes/getAllCommandes")
	public List<Commande> getAllCommandes() throws Exception {
		return commandeService.findAllCommande();
	}

	@GetMapping("/commande/getCommandeById/{id}")
	public Commande getCommandeById(@PathVariable("id") int id) throws Exception {
		return commandeService.findById(id);
	}

	@PostMapping("/commande/createCommande")
	public List<Commande> createCommande(@RequestBody Commande commande) throws Exception {
		return commandeService.createCommande(commande);
	}

	@PutMapping("/commande/updateCommande/{id}")
	public Commande updateCommande(@RequestBody Commande commande, @PathVariable("id") int id) throws Exception {
		return commandeService.updateCommande(commande, id);
	}

	@DeleteMapping("/commande/deleteCommande/{id}")
	public void deleteCommande(@PathVariable("id") int id) throws Exception {
		commandeService.deleteCommande(id);
	}



	@GetMapping("/voirCommandePayeParTable/table/{id_table}")
	public List<Commande> voirCommandePayeParTable(@PathVariable("id_table") int idTable) {
		return commandeService.voirCommandePayeParTable(idTable);
	}

	@GetMapping("/voirCommandeNonPayeParTable/table/{id_table}")
	public List<Commande> voirCommandeNonPayeParTable(@PathVariable("id_table") int idTable) {
		return commandeService.voirCommandeNonPayeParTable(idTable);
	}

	@GetMapping("/voirCommandeNonPaye/")
	public List<Commande> voirCommandeNonPaye() {
		return commandeService.voirCommandeNonPaye();
	}

	@GetMapping("/voirCommandePayeOuEnAttenteParTable/table/{id_table}")
	public List<Commande> voirCommandePayeOuEnAttenteParTable(@PathVariable("id_table") int idTable) {
		return commandeService.voirCommandePayeOuEnAttenteParTable(idTable);
	}

	@GetMapping("/voirCommandeValiderParTable/table/{id_table}")
	public List<Commande> voirCommandeValiderParTable(@PathVariable("id_table") int idTable) {
		return commandeService.voirCommandeValiderParTable(idTable);
	}

	@GetMapping("/voirCommandeNonValiderParTable/table/{id_table}")
	public List<Commande> voirCommandeNonValiderParTable(@PathVariable("id_table") int idTable) {
		return commandeService.voirCommandeNonValiderParTable(idTable);
	}

	@GetMapping("/voirCommandeEnAttenteParTable/table/{id_table}")
	public List<Commande> voirCommandeEnAttenteParTable(@PathVariable("id_table") int idTable) {
		return commandeService.voirCommandeEnAttenteParTable(idTable);
	}

	@PutMapping("/validercommande/{ref_commande}")
	public void validerCommande(@PathVariable("ref_commande") String ref_commande) {
		commandeService.validerCommande(ref_commande);
	}

	@PutMapping("/payecommande/{ref_commande}")
	public void payerCommande(@PathVariable("ref_commande") String ref_commande) {
		commandeService.payerCommande(ref_commande);
	}

	@GetMapping("/rechercheCommandeParDate/{date_debut}/{date_fin}")
	public List<Commande> rechercheCommandeParDate(@PathVariable("date_debut") LocalDate date_debut,
			@PathVariable("date_fin") LocalDate date_fin) {
		return commandeService.rechercheParDate(date_debut, date_fin);
	}

	@CrossOrigin("http://localhost:5173")
	@GetMapping("/rechercheCommandeParDateParArticle/{date_debut}/{date_fin}")
	public List<Commande> rechercheCommandeParDateParArticle(@PathVariable("date_debut") LocalDate date_debut,
			@PathVariable("date_fin") LocalDate date_fin) {

		List<Commande> listeCommandeArticleAFiltrer = commandeService.rechercheCommandeParDateParArticle(date_debut, date_fin);
		List<Commande> listeCommandeArticleFiltrer = new ArrayList<>();
		if (!listeCommandeArticleAFiltrer.isEmpty()) {
			for (Commande commande : listeCommandeArticleAFiltrer) {
				if (isCommandeArticleExisteDejaDansLaFiltre(commande, listeCommandeArticleFiltrer)
						&& listeCommandeArticleFiltrer.size() != 1) {
					int nombreactuel = listeCommandeArticleFiltrer.get(getIndexTableauCommandeArticle(commande, listeCommandeArticleFiltrer)).getNombre();
					listeCommandeArticleFiltrer.get(getIndexTableauCommandeArticle(commande, listeCommandeArticleFiltrer))
							.setNombre(nombreactuel + commande.getNombre());
				} else {
					listeCommandeArticleFiltrer.add(commande);
				}
			}
		}
		return listeCommandeArticleFiltrer;
	}

	private boolean isCommandeArticleExisteDejaDansLaFiltre(Commande commande, List<Commande> listeCommandeFiltrer) {
		for (Commande commande2 : listeCommandeFiltrer) {
			if (commande2.getArticle().getId() == commande.getArticle().getId()) {
				return true;
			}
		}
		return false;
	}

	private int getIndexTableauCommandeArticle(Commande commande, List<Commande> listeCommandeFiltrer) {
		int i = 0;
		for (Commande commande2 : listeCommandeFiltrer) {
			if (commande2.getArticle().getId() == commande.getArticle().getId()) {
				return i;
			}
			i++;
		}
		return i;
	}


	@PutMapping("/annulerCommande/{ref_commande}")
	public List<Commande> annulerCommande(String ref_commande) {
		return commandeService.annulerCommande(ref_commande);
	}
}
