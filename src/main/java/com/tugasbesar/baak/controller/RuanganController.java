package com.tugasbesar.baak.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.tugasbesar.baak.service.RuanganService;
import com.tugasbesar.baak.config.CustomUserDetails;
import com.tugasbesar.baak.model.Ruangan;
import com.tugasbesar.baak.model.User;

@Controller
@RequestMapping("/ruangan")
public class RuanganController {
    @Autowired
    RuanganService ruanganService;

    @GetMapping("/request")
    public String list(Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {
        User user = userDetails.getUser();
        List<Ruangan> ruanganList = ruanganService.listAllRuanganByUserId(user.getId());
        model.addAttribute("ruanganList", ruanganList);
        return "user/request-ruangan";
    }
    
    @GetMapping("/create-request-ruangan")
    public String createRequest(Model model) {
        model.addAttribute("ruangan", new Ruangan());
        return "user/create-request-ruangan";
    }
    
    @PostMapping("/create-ruangan")
    public ResponseEntity<?> add(@RequestBody Ruangan ruangan, @AuthenticationPrincipal CustomUserDetails userDetails) {
        User user = userDetails.getUser();
        ruangan.setUser(user);
        ruangan.setStatus("pending");
        ruanganService.saveRuangan(ruangan);

        return ResponseEntity.ok("Success");  // mengirim respons HTTP 200 OK dengan pesan "Success"
    }
    
    @PutMapping("/edit/{id}")
    public ResponseEntity<?> update(@RequestBody Ruangan ruangan, @PathVariable Long id) {
        try {
            ruangan.setId(id);
            ruanganService.saveRuangan(ruangan);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/edit-request-ruangan")
    public String editRequest(Model model) {
        model.addAttribute("ruangan", new Ruangan());
        return "user/edit-request-ruangan";
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        ruanganService.deleteRuangan(id);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }
    
    @PutMapping("/cancel/{id}")
    public ResponseEntity<?> cancel(@PathVariable Long id) {
        try {
            ruanganService.cancelRuangan(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/approve/{id}")
    public ResponseEntity<?> approve(@PathVariable Long id) {
        try {
            ruanganService.approveRuangan(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/decline/{id}")
    public ResponseEntity<?> decline(@PathVariable Long id) {
        try {
            ruanganService.declineRuangan(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
