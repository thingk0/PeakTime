package org.peaktime.controller;

import lombok.RequiredArgsConstructor;
import org.peaktime.entity.SdtMenu;
import org.peaktime.service.SdtCafeteriaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CafeteriaController {

    private final SdtCafeteriaService studentCafeteriaService;

    @GetMapping(value = "/cafeteria-student")
    public String getAllSdtMenus(Model model) {
        List<SdtMenu> studentMenus = studentCafeteriaService.getSdtMenus();
        model.addAttribute("menu", studentMenus);
        model.addAttribute("today", LocalDateTime.now());
        return "cafeteria_sdt";
    }

    @GetMapping(value = "/cafeteria-dormitory")
    public String gdtAllDmtMenus() {
        return "cafeteria_dmt";
    }

    @GetMapping(value = "/cafeteria-staff")
    public String getAllSffMenus() {
        return "cafeteria_sff";
    }
}
