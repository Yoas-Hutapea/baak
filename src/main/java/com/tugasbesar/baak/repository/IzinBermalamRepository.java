package com.tugasbesar.baak.repository;

import com.tugasbesar.baak.model.IzinBermalam;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IzinBermalamRepository extends JpaRepository<IzinBermalam, Long> {
	List<IzinBermalam> findAllByUserId(Long userId);
}
