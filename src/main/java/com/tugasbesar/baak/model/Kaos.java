package com.tugasbesar.baak.model;

import jakarta.persistence.*;

@Entity
@Table(name = "kaos")
public class Kaos {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long Id; 
	private String kodeKaos;
	private String ukuran;
	private String harga;
	private Integer stok; 
	
	public Kaos() {
		super();
	}

	public Kaos(Long id, String kodeKaos, String ukuran, String harga, Integer stok) {
		super();
		Id = id;
		this.kodeKaos = kodeKaos;
		this.ukuran = ukuran;
		this.harga = harga;
		this.stok = stok;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getKodeKaos() {
		return kodeKaos;
	}

	public void setKodeKaos(String kodeKaos) {
		this.kodeKaos = kodeKaos;
	}

	public String getUkuran() {
		return ukuran;
	}

	public void setUkuran(String ukuran) {
		this.ukuran = ukuran;
	}

	public String getHarga() {
		return harga;
	}

	public void setHarga(String harga) {
		this.harga = harga;
	}

	public Integer getStok() {
		return stok;
	}

	public void setStok(Integer stok) {
		this.stok = stok;
	}

	@Override
	public String toString() {
		return "Kaos [Id=" + Id + ", kodeKaos=" + kodeKaos + ", ukuran=" + ukuran + ", harga=" + harga + "]";
	}

	
	
}
