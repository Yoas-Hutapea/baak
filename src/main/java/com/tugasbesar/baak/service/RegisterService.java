package com.tugasbesar.baak.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tugasbesar.baak.model.User;
import com.tugasbesar.baak.repository.RegisterRepository;

@Service
public class RegisterService {

	@Autowired
	private RegisterRepository repo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public User register(String username, String password, String roles, String noktp, String nim, String namalengkap,
			String nohp) {
		User user = repo.findByUsername(username);
		if (user == null) {
			user = new User();
			user.setUsername(username);
			user.setPassword(passwordEncoder.encode(password));
			user.setRoles("mahasiswa");
			user.setNoktp(noktp);
			user.setNim(nim);
			user.setNamalengkap(namalengkap);
			user.setNohp(nohp);
			repo.save(user);
		}
		return user;
	}

}
