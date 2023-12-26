package com.tugasbesar.baak.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tugasbesar.baak.model.Ruangan;

@Repository
public interface RuanganRepository extends JpaRepository<Ruangan, Long> {

    List<Ruangan> findAllByUserId(Long userId);

}