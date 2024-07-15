package com.projet.tickets.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Description;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projet.tickets.model.User;
import com.projet.tickets.services.impl.UserServices;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@Description("Gestion des Utilisateurs.")
@Api(tags = "Crud utilisateur API")
@RequestMapping("/api")
public class UserController {

	private Logger log = LoggerFactory.getLogger(UserController.class);

	private UserServices userServices;

	private static final String GESTION_DES_UTILISATEURS = "Gestion des utilisateurs";
	private static final String LISTE_DES_UTILISATEURS = "Liste de tous les utilisateurs";
	private static final String PROPRIETE_UTILISATEUR = "Propriété d'un utilisateur";
	private static final String IDENTIFIANT_INVALID = "Identifiant invalide.";
	private static final String UTILISATEUR_NON_TROUVE = "Utilisateur inéxistant!";
	private static final String CREATION_UTILISATEUR = "Création d'un utilisateur";
	private static final String MODIFICATION_UTILISATEUR = "Modification d'un utilisateur";
	private static final String UN_25_USER = "La valeur de l'username doit contenir 1 à 25 caractères.";
	private static final String UN_50_MAIL = "L'email doit contenir 1 à 50 caractères.";
	private static final String CONTROL_MAIL = "Valeur du mail erronée, un mail doit contenir un . et un @ .";

	public UserController(UserServices userServices) {
		super();
		this.userServices = userServices;
	}

	@Operation(description = GESTION_DES_UTILISATEURS, summary = LISTE_DES_UTILISATEURS)
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = LISTE_DES_UTILISATEURS, content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = User.class)) }) })
	@GetMapping("/users")
	public List<User> getAllUser() {
		return userServices.findAllUser();
	}

	@Operation(description = GESTION_DES_UTILISATEURS, summary = PROPRIETE_UTILISATEUR)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = PROPRIETE_UTILISATEUR, content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = User.class)) }),
			@ApiResponse(responseCode = "406", description = IDENTIFIANT_INVALID, content = @Content),
			@ApiResponse(responseCode = "404", description = UTILISATEUR_NON_TROUVE, content = @Content) })
	@GetMapping("/users/{id}")
	public User getUserById(@PathVariable("id") int id) throws Exception {
		return userServices.findById(id);
	}

	@Operation(description = GESTION_DES_UTILISATEURS, summary = CREATION_UTILISATEUR)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = CREATION_UTILISATEUR, content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = User.class)) }),
			@ApiResponse(responseCode = "406", description = "<ul><li>" + IDENTIFIANT_INVALID + "</li><li>" + UN_25_USER
					+ "</li><li>" + UN_50_MAIL + "</li><li>" + CONTROL_MAIL + "</li>" + "</ul>", content = @Content) })
	@PostMapping("/users")
	public List<User> createUser(@RequestBody User user) throws Exception {
		userServices.createUser(user);
		log.info("Utilisateur créer : ", user);
		return getAllUser();
	}

	@Operation(description = GESTION_DES_UTILISATEURS, summary = MODIFICATION_UTILISATEUR)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = MODIFICATION_UTILISATEUR, content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = User.class)) }),
			@ApiResponse(responseCode = "404", description = UTILISATEUR_NON_TROUVE, content = @Content),
			@ApiResponse(responseCode = "406", description = "<ul><li>" + IDENTIFIANT_INVALID + "</li><li>" + UN_25_USER
					+ "</li><li>" + UN_50_MAIL + "</li><li>" + CONTROL_MAIL + "</li>" + "</ul>", content = @Content) })

	@PutMapping("/users/{id}")
	public User updateUser(@RequestBody User user, @PathVariable("id") int id) throws Exception {
		userServices.updateUser(user, id);
		return userServices.findById(id);
	}

}
