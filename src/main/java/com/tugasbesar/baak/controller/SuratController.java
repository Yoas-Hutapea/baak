package com.tugasbesar.baak.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.tugasbesar.baak.service.SuratService;
import com.tugasbesar.baak.config.CustomUserDetails;
import com.tugasbesar.baak.model.Surat;
import com.tugasbesar.baak.model.User;

@Controller
@RequestMapping("/surat")
public class SuratController {
    @Autowired
    SuratService suratService;

    @GetMapping("/request")
    public String list(Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {
        User user = userDetails.getUser();
        List<Surat> suratList = suratService.listAllSuratByUserId(user.getId());
        model.addAttribute("suratList", suratList);
        return "user/request-surat";
    }
    
    @GetMapping("/create-request-surat")
    public String createRequest(Model model) {
        model.addAttribute("surat", new Surat());
        return "user/create-request-surat";
    }
    
    @PostMapping("/create-surat")
    public ResponseEntity<?> add(@RequestBody Surat surat, @AuthenticationPrincipal CustomUserDetails userDetails) {
        User user = userDetails.getUser();
        surat.setUser(user);
        surat.setStatus("pending");
        suratService.saveSurat(surat);
        return ResponseEntity.ok("Success");  // mengirim respons HTTP 200 OK dengan pesan "Success"
    }

    
    @PutMapping("/edit/{id}")
    public ResponseEntity<?> update(@RequestBody Surat surat, @PathVariable Long id) {
        try {
            surat.setId(id);
            suratService.saveSurat(surat);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/edit-request-surat")
    public String editRequest(Model model) {
        model.addAttribute("surat", new Surat());
        return "user/edit-request-surat";
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        suratService.deleteSurat(id);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @PutMapping("/cancel/{id}")
    public ResponseEntity<?> cancel(@PathVariable Long id) {
        try {
            suratService.cancelSurat(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/approve/{id}")
    public ResponseEntity<?> approve(@PathVariable Long id) {
        try {
            suratService.approveSurat(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/decline/{id}")
    public ResponseEntity<?> decline(@PathVariable Long id) {
        try {
            suratService.declineSurat(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
