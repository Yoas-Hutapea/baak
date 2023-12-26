package com.tugasbesar.baak.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tugasbesar.baak.model.OrderKaos;

@Repository
public interface OrderKaosRepository extends JpaRepository<OrderKaos, Long>{

	List<OrderKaos> findByUser_NamalengkapContaining(String namalengkap);

}
