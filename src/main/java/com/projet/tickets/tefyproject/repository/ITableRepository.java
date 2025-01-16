package com.projet.tickets.tefyproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projet.tickets.tefyproject.model.TableRestaurant;

public interface ITableRepository extends JpaRepository<TableRestaurant, Integer> {

}
