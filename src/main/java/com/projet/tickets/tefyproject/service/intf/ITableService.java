package com.projet.tickets.tefyproject.service.intf;

import java.util.List;

import com.projet.tickets.tefyproject.model.TableRestaurant;

public interface ITableService {

	public List<TableRestaurant> findAllTable() throws Exception;

	public TableRestaurant findById(int id) throws Exception;

	public List<TableRestaurant> createTable(TableRestaurant table) throws Exception;

	public TableRestaurant updateTable(TableRestaurant table, int id) throws Exception;

	public void deleteTable(int idTable) throws Exception;
}
