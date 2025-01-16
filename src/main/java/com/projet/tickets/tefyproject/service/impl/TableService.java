package com.projet.tickets.tefyproject.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projet.tickets.tefyproject.model.TableRestaurant;
import com.projet.tickets.tefyproject.repository.ITableRepository;
import com.projet.tickets.tefyproject.service.intf.ITableService;

@Service
public class TableService implements ITableService {

	@Autowired
	public ITableRepository tableRepository;

	public TableService() {
		super();
	}

	@Override
	public List<TableRestaurant> findAllTable() throws Exception {
		return tableRepository.findAll();
	}

	@Override
	public TableRestaurant findById(int id) throws Exception {
		return tableRepository.findById(id).get();
	}

	@Override
	public List<TableRestaurant> createTable(TableRestaurant table) throws Exception {
		tableRepository.save(table);
		return tableRepository.findAll();
	}

	@Override
	public TableRestaurant updateTable(TableRestaurant table, int id) throws Exception {
		tableRepository.save(table);
		return findById(id);
	}

	@Override
	public void deleteTable(int idTable) throws Exception {
		tableRepository.delete(findById(idTable));
	}

}
