package com.tugasbesar.baak.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tugasbesar.baak.model.IzinKeluar;
import com.tugasbesar.baak.model.User;
import com.tugasbesar.baak.repository.IzinKeluarRepository;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class IzinKeluarService {
    @Autowired
    private IzinKeluarRepository izinKeluarRepository;

    public List<IzinKeluar> listAllIzinKeluar() {
        return izinKeluarRepository.findAll();
    }

    public List<IzinKeluar> listAllIzinKeluarByUserId(Long userId) {
        return izinKeluarRepository.findAllByUserId(userId);
    }

    public void saveIzinKeluar(IzinKeluar izinKeluar) {
        if (izinKeluar.getId() == null) {
            izinKeluar.setStatus("pending");
        }
        izinKeluarRepository.save(izinKeluar);
    }

    public IzinKeluar getIzinKeluar(Long id) {
        return izinKeluarRepository.findById(id).get();
    }

    public void deleteIzinKeluar(Long id) {
        izinKeluarRepository.deleteById(id);
    }

    public void cancelIzinKeluar(Long id) {
        IzinKeluar izinKeluar = izinKeluarRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No IzinKeluar found with id: " + id));
        izinKeluar.setStatus("cancel");
        izinKeluarRepository.save(izinKeluar);
    }

    public void approveIzinKeluar(Long id) {
        IzinKeluar izinKeluar = izinKeluarRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No IzinKeluar found with id: " + id));
        izinKeluar.setStatus("approved");
        izinKeluarRepository.save(izinKeluar);
    }

    public void declineIzinKeluar(Long id) {
        IzinKeluar izinKeluar = izinKeluarRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No IzinKeluar found with id: " + id));
        izinKeluar.setStatus("declined");
        izinKeluarRepository.save(izinKeluar);
    }

    public String getUserNameFromIzinKeluar(Long izinKeluarId) {
        IzinKeluar izinKeluar = izinKeluarRepository.findById(izinKeluarId).orElseThrow(() -> new NoSuchElementException("No IzinKeluar found with id: " + izinKeluarId));
        User user = izinKeluar.getUser();	
        return user.getNamalengkap(); // Ganti dengan metode getter yang sesuai di kelas User Anda
    }
}
