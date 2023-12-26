package com.tugasbesar.baak.service;

import java.util.List;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tugasbesar.baak.model.Kaos;
import com.tugasbesar.baak.repository.KaosRepository;


@Service
@Transactional
public class KaosService {
	@Autowired
	private KaosRepository kaosRepository;

	public List<Kaos> listAllKaos() {
		return kaosRepository.findAll();
	}

	public void saveKaos(Kaos kaos) {
		kaosRepository.save(kaos);
	}

	public Kaos getKaos(long id) {
		return kaosRepository.findById(id).get();
	}

	public void deleteKaos(long id) {
		kaosRepository.deleteById(id);
	}

	public List<Kaos> searchKaos(String kodeKaos) {
		return kaosRepository.findByKodeKaosContaining(kodeKaos);
	}

}

