package com.tugasbesar.baak.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tugasbesar.baak.model.IzinBermalam;
import com.tugasbesar.baak.model.User;
import com.tugasbesar.baak.repository.IzinBermalamRepository;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class IzinBermalamService {
    @Autowired
    private IzinBermalamRepository izinBermalamRepository;

    public List<IzinBermalam> listAllIzinBermalam() {
        return izinBermalamRepository.findAll();
    }

    public List<IzinBermalam> listAllIzinBermalamByUserId(Long userId) {
        return izinBermalamRepository.findAllByUserId(userId);
    }

    public void saveIzinBermalam(IzinBermalam izinBermalam) {
        if (izinBermalam.getId() == null) {
            izinBermalam.setStatus("pending");
        }
        izinBermalamRepository.save(izinBermalam);
    }

    public IzinBermalam getIzinBermalam(Long id) {
        return izinBermalamRepository.findById(id).get();
    }

    public void deleteIzinBermalam(Long id) {
        izinBermalamRepository.deleteById(id);
    }

    public void cancelIzinBermalam(Long id) {
        IzinBermalam izinBermalam = izinBermalamRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No IzinBermalam found with id: " + id));
        izinBermalam.setStatus("cancel");
        izinBermalamRepository.save(izinBermalam);
    }
    
    public void approveIzinBermalam(Long id) {
        IzinBermalam izinBermalam = izinBermalamRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No IzinBermalam found with id: " + id));
        izinBermalam.setStatus("approved");
        izinBermalamRepository.save(izinBermalam);
    }

    public void declineIzinBermalam(Long id) {
        IzinBermalam izinBermalam = izinBermalamRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No IzinBermalam found with id: " + id));
        izinBermalam.setStatus("declined");
        izinBermalamRepository.save(izinBermalam);
    }

    public String getUserNameFromIzinBermalam(Long izinBermalamId) {
        IzinBermalam izinBermalam = izinBermalamRepository.findById(izinBermalamId).orElseThrow(() -> new NoSuchElementException("No IzinBermalam found with id: " + izinBermalamId));
        User user = izinBermalam.getUser();	
        return user.getNamalengkap(); // Ganti dengan metode getter yang sesuai di kelas User Anda
    }
}
