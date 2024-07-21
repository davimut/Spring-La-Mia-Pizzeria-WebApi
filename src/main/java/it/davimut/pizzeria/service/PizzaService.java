package it.davimut.pizzeria.service;

import java.util.Optional;

import it.davimut.pizzeria.model.PizzaModel;

public interface PizzaService {

	public Optional<PizzaModel> findById(Integer id);

//	Salvo la pizza passata come argomento
//	e restituisco l'elemento appena salvata vantaggio avere l'id
	public PizzaModel save(PizzaModel pizza);

	public PizzaModel update(Integer id, PizzaModel pizza);
	public void delete(Integer id);
}
