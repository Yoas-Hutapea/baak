package com.tugasbesar.baak.model;

import jakarta.persistence.*;

@Entity
@Table(name="user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String roles;
    private String noktp;
    private String nim;
    private String namalengkap;
    private String nohp;
    
    public User() 
    {
    	
    }
    
	public User(Long id, String username, String password, String roles, String noktp, String nim, String namalengkap,
			String nohp) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.roles = roles;
		this.noktp = noktp;
		this.nim = nim;
		this.namalengkap = namalengkap;
		this.nohp = nohp;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	public String getNoktp() {
		return noktp;
	}
	public void setNoktp(String noktp) {
		this.noktp = noktp;
	}
	public String getNim() {
		return nim;
	}
	public void setNim(String nim) {
		this.nim = nim;
	}
	public String getNamalengkap() {
		return namalengkap;
	}
	public void setNamalengkap(String namalengkap) {
		this.namalengkap = namalengkap;
	}
	public String getNohp() {
		return nohp;
	}
	public void setNohp(String nohp) {
		this.nohp = nohp;
	}
    
}
