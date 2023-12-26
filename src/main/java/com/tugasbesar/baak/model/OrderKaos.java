package com.tugasbesar.baak.model;

import jakarta.persistence.*;


@Entity
@Table(name="OrderKaos")
public class OrderKaos {
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name="id_mahasiswa", nullable=false)
	private User user;

	@ManyToOne
	@JoinColumn(name="id_kaos", nullable=false)
	private Kaos kaos;

	private String bukti_pembayaran;
	private String status;
	
	public OrderKaos() {
		super();
	}

	public OrderKaos(Long id, User user, Kaos kaos, String bukti_pembayaran, String status) {
		super();
		this.id = id;
		this.user = user;
		this.kaos = kaos;
		this.bukti_pembayaran = bukti_pembayaran;
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

	public Kaos getKaos() {
		return kaos;
	}

	public void setKaos(Kaos kaos) {
		this.kaos = kaos;
	}

	public String getBukti_pembayaran() {
		return bukti_pembayaran;
	}

	public void setBukti_pembayaran(String bukti_pembayaran) {
		this.bukti_pembayaran = bukti_pembayaran;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "OrderKaos [id=" + id + ", user=" + user + ", kaos=" + kaos + ", bukti_pembayaran="
				+ bukti_pembayaran + ", status=" + status + "]";
	}
	
}
