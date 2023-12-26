package com.tugasbesar.baak.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tugasbesar.baak.model.OrderKaos;
import com.tugasbesar.baak.service.OrderKaosService;

@RestController
@RequestMapping("/orderkaos")
public class OrderKaosController {
	
	@Autowired
	OrderKaosService orderKaosService;

	@GetMapping("")

	public List<OrderKaos> list() {
		return orderKaosService.listAllOrderKaos();
	}

	@PostMapping("/")
	public void add(@RequestBody OrderKaos orderKaos) {
		orderKaosService.saveOrderKaos(orderKaos);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody OrderKaos orderKaos, @PathVariable Long id) {
		try {
			orderKaos.setId(id);
			orderKaosService.saveOrderKaos(orderKaos);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		orderKaosService.deleteOrderKaos(id);
	}

	@GetMapping("/search/{namalengkap}")
	public List<OrderKaos> search(@PathVariable String namalengkap) {
		return orderKaosService.searchOrderKaos(namalengkap);
	}
	
}