package com.tugasbesar.baak.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.tugasbesar.baak.model.*;

import com.tugasbesar.baak.service.IzinBermalamService;
import com.tugasbesar.baak.service.IzinKeluarService;
import com.tugasbesar.baak.service.RuanganService;
import com.tugasbesar.baak.service.SuratService;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    IzinBermalamService izinBermalamService;
    @Autowired
    IzinKeluarService izinKeluarService;
    @Autowired
    RuanganService ruanganService;
    @Autowired
    SuratService suratService;

    @GetMapping("/dashboard")
    public String dashboard() {
        return "admin/dashboard";
    }

    @GetMapping("/user-ib")
    public String user_ib(Model model) {
        List<IzinBermalam> izinBermalamList = izinBermalamService.listAllIzinBermalam();
        model.addAttribute("izinBermalamList", izinBermalamList);
        return "admin/user-ib";
    }

    @GetMapping("/user-ik")
    public String user_ik(Model model) {
        List<IzinKeluar> izinKeluarList = izinKeluarService.listAllIzinKeluar();
        model.addAttribute("izinKeluarList", izinKeluarList);
        return "admin/user-ik";
    }

    @GetMapping("/user-ruangan")
    public String user_ruangan(Model model) {
        List<Ruangan> ruanganList = ruanganService.listAllRuangan();
        model.addAttribute("ruanganList", ruanganList);
        return "admin/user-ruangan";
    }

    @GetMapping("/user-surat")
    public String user_surat(Model model) {
        List<Surat> suratList = suratService.listAllSurat();
        model.addAttribute("suratList", suratList);
        return "admin/user-surat";
    }


    // Tambahkan metode lainnya sesuai kebutuhan
}
