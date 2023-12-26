package com.tugasbesar.baak.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tugasbesar.baak.model.Ruangan;
import com.tugasbesar.baak.model.User;
import com.tugasbesar.baak.repository.RuanganRepository;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class RuanganService {
    @Autowired
    private RuanganRepository ruanganRepository;

    public List<Ruangan> listAllRuangan() {
        return ruanganRepository.findAll();
    }

    public List<Ruangan> listAllRuanganByUserId(Long userId) {
        return ruanganRepository.findAllByUserId(userId);
    }

    public void saveRuangan(Ruangan ruangan) {
        if (ruangan.getId() == null) {
            ruangan.setStatus("pending");
        }
        ruanganRepository.save(ruangan);
    }

    public Ruangan getRuangan(Long id) {
        return ruanganRepository.findById(id).get();
    }

    public void deleteRuangan(Long id) {
        ruanganRepository.deleteById(id);
    }
    
    public void cancelRuangan(Long id) {
        Ruangan ruangan = ruanganRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No Ruangan found with id: " + id));
        ruangan.setStatus("canceled");
        ruanganRepository.save(ruangan);
    }

    public void approveRuangan(Long id) {
        Ruangan ruangan = ruanganRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No Ruangan found with id: " + id));
        ruangan.setStatus("booked");
        ruanganRepository.save(ruangan);
    }

    public void declineRuangan(Long id) {
        Ruangan ruangan = ruanganRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No Ruangan found with id: " + id));
        ruangan.setStatus("declined");
        ruanganRepository.save(ruangan);
    }

    public String getUserNameFromRuangan(Long ruanganId) {
        Ruangan ruangan = ruanganRepository.findById(ruanganId).orElseThrow(() -> new NoSuchElementException("No Ruangan found with id: " + ruanganId));
        User user = ruangan.getUser();	
        return user.getNamalengkap(); // Ganti dengan metode getter yang sesuai di kelas User Anda
    }
}
