package com.tugasbesar.baak.repository;

import com.tugasbesar.baak.model.Surat;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface SuratRepository extends JpaRepository<Surat, Long> {
	List<Surat> findAllByUserId(Long userId);
}
