package com.tugasbesar.baak.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tugasbesar.baak.model.User;

@Repository
public interface RegisterRepository extends JpaRepository<User, Long>{
	User findByUsername(String username);
}
