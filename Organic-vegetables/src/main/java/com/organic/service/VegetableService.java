package com.organic.service;

import java.util.List;

import com.organic.exception.VegetableException;
import com.organic.model.Vegetable;

public interface VegetableService {
	
	public Vegetable addVegetable(Vegetable vegetable) throws VegetableException;

	public Vegetable updateVegetable(Vegetable vegetable) throws VegetableException;

	public Vegetable removeVegetable(Integer vegId) throws VegetableException;

	public List<Vegetable> viewAllVegetables() throws VegetableException;
	
	public List<Vegetable> viewVegetableList(String type) throws VegetableException;

	public List<Vegetable> getVegetableByName(String name) throws VegetableException;
}
