package com.tugasbesar.baak.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tugasbesar.baak.repository.SuratRepository;
import com.tugasbesar.baak.model.Surat;
import com.tugasbesar.baak.model.User;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class SuratService {
    @Autowired
    private SuratRepository suratRepository;

    public List<Surat> listAllSurat() {
        return suratRepository.findAll();
    }

    public List<Surat> listAllSuratByUserId(Long userId) {
        return suratRepository.findAllByUserId(userId);
    }

    public void saveSurat(Surat surat) {
        if (surat.getId() == null) {
            surat.setStatus("pending");
        }
        suratRepository.save(surat);
    }

    public Surat getSurat(Long id) {
        return suratRepository.findById(id).get();
    }

    public void deleteSurat(Long id) {
        suratRepository.deleteById(id);
    }

    public void cancelSurat(Long id) {
        Surat surat = suratRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No Surat found with id: " + id));
        surat.setStatus("cancel");
        suratRepository.save(surat);
    }
    
    public void approveSurat(Long id) {
        Surat surat = suratRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No Surat found with id: " + id));
        surat.setStatus("approved");
        suratRepository.save(surat);
    }

    public void declineSurat(Long id) {
        Surat surat = suratRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No Surat found with id: " + id));
        surat.setStatus("declined");
        suratRepository.save(surat);
    }

    public String getUserNameFromSurat(Long suratId) {
        Surat surat = suratRepository.findById(suratId).orElseThrow(() -> new NoSuchElementException("No Surat found with id: " + suratId));
        User user = surat.getUser();	
        return user.getNamalengkap(); // Ganti dengan metode getter yang sesuai di kelas User Anda
    }
}
