package com.tugasbesar.baak.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tugasbesar.baak.model.Kaos;

@Repository
public interface KaosRepository extends JpaRepository<Kaos, Long> {

	List<Kaos> findByKodeKaosContaining(String kodeKaos);
}
