package it.davimut.pizzeria.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PostExchange;

import it.davimut.pizzeria.model.PizzaModel;
import it.davimut.pizzeria.service.PizzaService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@CrossOrigin
@RequestMapping("/api/pizze")

public class PizzaRestController {
	@Autowired
	private PizzaService pizzaService;

	@GetMapping("{id}")
	public ResponseEntity get(@PathVariable("id") Integer idPizza) {

		Optional<PizzaModel> pizza = pizzaService.findById(idPizza);

		if (pizza.isPresent()) {
			return new ResponseEntity<>(pizza.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Pizza con id" + idPizza + "non trovata", HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping
	public ResponseEntity store(@Valid @RequestBody PizzaModel pizza) {
		try {
			PizzaModel PizzaSalvata = pizzaService.save(pizza);
			return ResponseEntity.ok(PizzaSalvata);
		} catch (Exception e) {
			return new ResponseEntity<>("Errore nel salvataggio di pizza", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("{id}")
	public ResponseEntity update(@PathVariable("id") Integer idPizza, @Valid @RequestBody PizzaModel pizza) {
		try {
			PizzaModel pizzaUpdate = pizzaService.update(idPizza, pizza);
			return ResponseEntity.ok(pizzaUpdate);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>("Errore nel salvataggio di pizza", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("{id}")
	public ResponseEntity<String> delete(@PathVariable("id") Integer id) {
		
	try {
		pizzaService.delete(id);
		return ResponseEntity.ok("cancellazione effettuata");
	} catch (Exception e) {
		return new ResponseEntity<>("Errore nel salvataggio di pizza", HttpStatus.INTERNAL_SERVER_ERROR);
	}	
		
	}
}
	
	
	
