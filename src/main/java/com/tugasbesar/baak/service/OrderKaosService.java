package com.tugasbesar.baak.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tugasbesar.baak.model.OrderKaos;
import com.tugasbesar.baak.repository.OrderKaosRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class OrderKaosService {
	@Autowired
	private OrderKaosRepository orderKaosRepository;

	public List<OrderKaos> listAllOrderKaos() {
		return orderKaosRepository.findAll();
	}

	public void saveOrderKaos(OrderKaos orderKaos) {
		orderKaosRepository.save(orderKaos);
	}

	public OrderKaos getOrderKaos(long id) {
		return orderKaosRepository.findById(id).get();
	}

	public void deleteOrderKaos(long id) {
		orderKaosRepository.deleteById(id);
	}

	public List<OrderKaos> searchOrderKaos(String namalengkap) {
		return orderKaosRepository.findByUser_NamalengkapContaining(namalengkap);
	}

}
