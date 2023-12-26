package com.tugasbesar.baak.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.tugasbesar.baak.service.IzinBermalamService;
import com.tugasbesar.baak.config.CustomUserDetails;
import com.tugasbesar.baak.model.IzinBermalam;
import com.tugasbesar.baak.model.User;

@Controller
@RequestMapping("/izinbermalam")
public class IzinBermalamController {
    @Autowired
    IzinBermalamService izinBermalamService;

    @GetMapping("/request")
    public String list(Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {
        User user = userDetails.getUser();
        List<IzinBermalam> izinBermalamList = izinBermalamService.listAllIzinBermalamByUserId(user.getId());
        model.addAttribute("izinBermalamList", izinBermalamList);
        return "user/request-ib";
    }
    
    @GetMapping("/create-request-ib")
    public String createRequest(Model model) {
        model.addAttribute("izinBermalam", new IzinBermalam());
        return "user/create-request-ib";
    }
    
    @PostMapping("/create-izinbermalam")
    public ResponseEntity<?> add(@RequestBody IzinBermalam izinBermalam, @AuthenticationPrincipal CustomUserDetails userDetails) {
        User user = userDetails.getUser();
        izinBermalam.setUser(user);
        izinBermalam.setStatus("pending");
        izinBermalamService.saveIzinBermalam(izinBermalam);
        return ResponseEntity.ok("Success");
    }
    
    @PutMapping("/edit/{id}")
    public ResponseEntity<?> update(@RequestBody IzinBermalam izinBermalam, @PathVariable Long id, @AuthenticationPrincipal CustomUserDetails userDetails) {
        try {
            User user = userDetails.getUser();
            izinBermalam.setUser(user);
            izinBermalam.setId(id);
            izinBermalamService.saveIzinBermalam(izinBermalam);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/edit-request-ib/{id}")
    public String editRequest(@PathVariable Long id, Model model) {
        IzinBermalam izinBermalam = izinBermalamService.getIzinBermalam(id);
        model.addAttribute("izinBermalam", izinBermalam);
        return "user/edit-request-ib";
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        izinBermalamService.deleteIzinBermalam(id);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @PutMapping("/approve/{id}")
    public ResponseEntity<?> approve(@PathVariable Long id) {
        try {
            izinBermalamService.approveIzinBermalam(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/decline/{id}")
    public ResponseEntity<?> decline(@PathVariable Long id) {
        try {
            izinBermalamService.declineIzinBermalam(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/cancel/{id}")
    public ResponseEntity<?> cancel(@PathVariable Long id) {
        try {
            izinBermalamService.cancelIzinBermalam(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
