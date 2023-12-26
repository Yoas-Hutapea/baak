package com.tugasbesar.baak.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tugasbesar.baak.model.Kaos;
import com.tugasbesar.baak.service.KaosService;

@Controller
@RequestMapping("/kaos")
public class KaosController {
	@Autowired
    KaosService kaosService;
    
    @GetMapping("/list")
    public String list(Model model) {
        List<Kaos> kaosList = kaosService.listAllKaos();
        model.addAttribute("ruanganList", kaosList);
        return "user/create-kaos";
    }

    @PostMapping("/")
    @ResponseBody
    public void add(@RequestBody Kaos kaos) {
        kaosService.saveKaos(kaos);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> update(@RequestBody Kaos kaos, @PathVariable Long id) {
        try {
            kaos.setId(id);
            kaosService.saveKaos(kaos);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public void delete(@PathVariable Long id) {
        kaosService.deleteKaos(id);
    }

    @GetMapping("/search/{namaKaos}")
    @ResponseBody
    public List<Kaos> search(@PathVariable String namaKaos) {
        return kaosService.searchKaos(namaKaos);
    }

}
