package com.organic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.organic.exception.VegetableException;
import com.organic.model.Vegetable;
import com.organic.repository.VegetableRepository;





@Service
public class VegetableServiceImpl implements VegetableService {
	
	
	@Autowired
	private VegetableRepository vr;

	@Override
	public Vegetable addVegetable(Vegetable vegetable) throws VegetableException {

		if(vegetable!=null) {
			Vegetable vegetable1=vr.save(vegetable);
			return vegetable1;
		}
		else {
			throw new VegetableException("Input might be incorrect");
		}
		
	}

	@Override
	public Vegetable updateVegetable(Vegetable veg) throws VegetableException {
		Optional<Vegetable> vegetable=vr.findById(veg.getVegId());
		
		if(vegetable.isPresent()) {
			Vegetable update=vegetable.get();
			System.out.println(update);
			if(veg.getName() != null ) update.setName(veg.getName());
			if(veg.getPrice() != null) update.setPrice(veg.getPrice());
			if(veg.getQuantity() != null ) update.setQuantity(veg.getQuantity());
			vr.save(update);
			return update;
		}else{
			throw new VegetableException("Not Valid Type");
		}
		

	}

	@Override
	public Vegetable removeVegetable(Integer vegId) throws VegetableException {
		// TODO Auto-generated method stub
		Optional<Vegetable> vegetable=vr.findById(vegId);
		if(vegetable.isPresent()) {
			Vegetable veg=vegetable.get();
			vr.delete(veg);
			return veg;
		}
		else throw new VegetableException("Not get Vegetable with this "+vegId);
	}

	@Override
	public List<Vegetable> viewAllVegetables() throws VegetableException {
		
		List<Vegetable> listOfVegetables=vr.findAll();
		if(listOfVegetables.isEmpty()) throw new VegetableException("There is no Vegetable.");
		
		return listOfVegetables;
		
	}
	
	@Override
	public List<Vegetable> viewVegetableList(String type) throws VegetableException {
		
		List<Vegetable> list=vr.findByType(type);
		if(list ==null) throw new VegetableException("Not get vegetable list");
		return list;
	}

	@Override
	public List<Vegetable> getVegetableByName(String name) throws VegetableException {
		
		List<Vegetable> list=vr.findByName(name);
		if(list ==null) throw new VegetableException("Not get vegetable by name");
		return list;
	}

	

}
