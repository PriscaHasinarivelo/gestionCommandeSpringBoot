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

import com.projet.tickets.tefyproject.model.TableRestaurant;
import com.projet.tickets.tefyproject.service.impl.TableService;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class TableController {

	private TableService tableService;

	public TableService getTableService() {
		return tableService;
	}

	@Autowired
	public void setTableService(TableService tableService) {
		this.tableService = tableService;
	}

	public TableController(TableService tableService) {
		super();
		this.tableService = tableService;
	}

	@GetMapping("/tables/getAllTableRestaurant")
	public List<TableRestaurant> getAllTableRestaurant() throws Exception {
		return tableService.findAllTable();
	}

	@GetMapping("/table/getAllTableRestaurant/{id}")
	public TableRestaurant getTableById(@PathVariable("id") int id) throws Exception {
		return tableService.findById(id);
	}

	@PostMapping("/table/createTable")
	public List<TableRestaurant> createTable(@RequestBody TableRestaurant Table) throws Exception {
		return tableService.createTable(Table);
	}

	@PutMapping("/table/updatTable/{id}")
	public TableRestaurant updatTable(@RequestBody TableRestaurant Table, @PathVariable("id") int id) throws Exception {
		return tableService.updateTable(Table, id);
	}

	@DeleteMapping("/table/deleteTable/{id}")
	public void deleteTable(@PathVariable("id") int id) throws Exception {
		tableService.deleteTable(id);
	}
}
