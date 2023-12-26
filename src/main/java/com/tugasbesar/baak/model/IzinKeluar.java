package com.tugasbesar.baak.model;

import java.util.Date;
import jakarta.persistence.*;

@Entity
@Table(name="izin_keluar")
public class IzinKeluar {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="id_mahasiswa", nullable=false)
	private User user;
	private Date rencana_berangkat;
	private Date rencana_kembali;
	private String keperluan_ik;
	private String status;
	
	public IzinKeluar() {
		super();
	}

	public IzinKeluar(Long id, User user, Date rencana_berangkat, Date rencana_kembali, String keperluan_ik,
			String status) {
		super();
		this.id = id;
		this.user = user;
		this.rencana_berangkat = rencana_berangkat;
		this.rencana_kembali = rencana_kembali;
		this.keperluan_ik = keperluan_ik;
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

	public Date getRencana_berangkat() {
		return rencana_berangkat;
	}

	public void setRencana_berangkat(Date rencana_berangkat) {
		this.rencana_berangkat = rencana_berangkat;
	}

	public Date getRencana_kembali() {
		return rencana_kembali;
	}

	public void setRencana_kembali(Date rencana_kembali) {
		this.rencana_kembali = rencana_kembali;
	}

	public String getKeperluan_ik() {
		return keperluan_ik;
	}

	public void setKeperluan_ik(String keperluan_ik) {
		this.keperluan_ik = keperluan_ik;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "IzinKeluar [id=" + id + ", user=" + user + ", rencana_berangkat=" + rencana_berangkat
				+ ", rencana_kembali=" + rencana_kembali + ", keperluan_ik=" + keperluan_ik + ", status=" + status
				+ "]";
	}

}
