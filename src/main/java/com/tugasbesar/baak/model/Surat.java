package com.tugasbesar.baak.model;

import jakarta.persistence.*;

@Entity
@Table(name="surat")
public class Surat {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="id_mahasiswa", nullable=false)
	private User user;
	private String jenis_surat;
	private String deskripsi;
	private String status;
	
	public Surat() {
		super();
	}

	public Surat(Long id, User user, String jenis_surat, String deskripsi, String status) {
		super();
		this.id = id;
		this.user = user;
		this.jenis_surat = jenis_surat;
		this.deskripsi = deskripsi;
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

	public String getJenis_surat() {
		return jenis_surat;
	}

	public void setJenis_surat(String jenis_surat) {
		this.jenis_surat = jenis_surat;
	}

	public String getDeskripsi() {
		return deskripsi;
	}

	public void setDeskripsi(String deskripsi) {
		this.deskripsi = deskripsi;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Surat [id=" + id + ", user=" + user + ", jenis_surat=" + jenis_surat + ", deskripsi=" + deskripsi
				+ ", status=" + status + "]";
	}
	
	
}
