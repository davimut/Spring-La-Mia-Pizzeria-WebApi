package it.davimut.pizzeria.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.davimut.pizzeria.model.PizzaModel;
import it.davimut.pizzeria.repository.PizzaRepository;

@Service
public class PizzaServiceImpl implements PizzaService {

    @Autowired
	private PizzaRepository PizzaRepo;
	
	@Override
	public Optional<PizzaModel> findById(Integer id){
		return PizzaRepo.findById(id);
	}

	@Override
	public PizzaModel save(PizzaModel pizza) {
	
		return PizzaRepo.save(pizza);
	}

	@Override
	public PizzaModel update(Integer id, PizzaModel inputPizza) {
		
		Optional<PizzaModel> pizzaCercata = PizzaRepo.findById(id);
		
		if(pizzaCercata.isEmpty()) { 
			throw new IllegalArgumentException("La pizza cercata con id" +id+ "non esiste");
		} 
		
		PizzaModel pizza = pizzaCercata.get();
		
		pizza.setDescrizione(inputPizza.getDescrizione());
		pizza.setFotoUrl(inputPizza.getFotoUrl());
		pizza.setIngredienti(inputPizza.getIngredienti());
		pizza.setNome(inputPizza.getNome());
		pizza.setOfferte(inputPizza.getOfferte());
		pizza.setPrice(inputPizza.getPrice());
		
		return PizzaRepo.save(pizza);
	}

	@Override
	public void delete(Integer id) {
	
		PizzaRepo.deleteById(id);
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
