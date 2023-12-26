package com.tugasbesar.baak.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.tugasbesar.baak.service.IzinKeluarService;
import com.tugasbesar.baak.config.CustomUserDetails;
import com.tugasbesar.baak.model.IzinKeluar;
import com.tugasbesar.baak.model.User;

@Controller
@RequestMapping("/izinkeluar")
public class IzinKeluarController {
    @Autowired
    IzinKeluarService izinKeluarService;

    @GetMapping("/request")
    public String list(Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {
        User user = userDetails.getUser();
        List<IzinKeluar> izinKeluarList = izinKeluarService.listAllIzinKeluarByUserId(user.getId());
        model.addAttribute("izinKeluarList", izinKeluarList);
        return "user/request-ik";
    }
    
    @GetMapping("/create-request-ik")
    public String createRequest(Model model) {
        model.addAttribute("izinKeluar", new IzinKeluar());
        return "user/create-request-ik";
    }
    
    @PostMapping("/create-izinkeluar")
    public ResponseEntity<?> add(@RequestBody IzinKeluar izinKeluar, @AuthenticationPrincipal CustomUserDetails userDetails) {
        User user = userDetails.getUser();
        izinKeluar.setUser(user);
        izinKeluar.setStatus("pending");
        izinKeluarService.saveIzinKeluar(izinKeluar);
        return ResponseEntity.ok("Success");
    }
    
    @PutMapping("/edit/{id}")
    public ResponseEntity<?> update(@RequestBody IzinKeluar izinKeluar, @PathVariable Long id, @AuthenticationPrincipal CustomUserDetails userDetails) {
        try {
            User user = userDetails.getUser();
            izinKeluar.setUser(user);
            izinKeluar.setId(id);
            izinKeluarService.saveIzinKeluar(izinKeluar);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/edit-request-ik/{id}")
    public String editRequest(@PathVariable Long id, Model model) {
        IzinKeluar izinKeluar = izinKeluarService.getIzinKeluar(id);
        model.addAttribute("izinKeluar", izinKeluar);
        return "user/edit-request-ik";
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        izinKeluarService.deleteIzinKeluar(id);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @PutMapping("/cancel/{id}")
    public ResponseEntity<?> cancel(@PathVariable Long id) {
        try {
            izinKeluarService.cancelIzinKeluar(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/approve/{id}")
    public ResponseEntity<?> approve(@PathVariable Long id) {
        try {
            izinKeluarService.approveIzinKeluar(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/decline/{id}")
    public ResponseEntity<?> decline(@PathVariable Long id) {
        try {
            izinKeluarService.declineIzinKeluar(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
