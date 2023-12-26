package com.tugasbesar.baak.model;

import jakarta.persistence.*;

@Entity
@Table(name="ruangan")
public class Ruangan {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="id_mahasiswa", nullable=false)
	private User user;
	private String nama;
	private String status;
	
	public Ruangan() {
		super();
	}

	public Ruangan(Long id, User user, String nama, String status) {
		super();
		this.id = id;
		this.user = user;
		this.nama = nama;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Ruangan [id=" + id + ", user=" + user + ", nama=" + nama + ", status=" + status + "]";
	}
	
}
