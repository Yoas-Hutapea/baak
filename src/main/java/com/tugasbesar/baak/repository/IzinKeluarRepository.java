package com.tugasbesar.baak.repository;

import com.tugasbesar.baak.model.IzinKeluar;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IzinKeluarRepository extends JpaRepository<IzinKeluar, Long> {
	List<IzinKeluar> findAllByUserId(Long userId);
}
