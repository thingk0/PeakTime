package org.peaktime.controller.cafeteria;

import lombok.RequiredArgsConstructor;
import org.peaktime.entity.SdtMenu;
import org.peaktime.service.SdtCafeteriaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class SdtCafeteriaController {

    private final SdtCafeteriaService studentCafeteriaService;

    @GetMapping(value = "/cafeteria-student")
    public String getCafeteriaStudent(Model model) {

        List<SdtMenu> studentMenus = studentCafeteriaService.getSdtMenus();
        List<LocalDateTime> dateList = new ArrayList<>();
        List<String> menuList = new ArrayList<>();

        for (SdtMenu sdtMenu : studentMenus) {
            menuList.add(sdtMenu.getMenu());
            dateList.add(sdtMenu.getDateTime());
        }


        model.addAttribute("date", dateList);
        model.addAttribute("menu", menuList);
        model.addAttribute("today", LocalDateTime.now());

        return "cafeteria_sdt";
    }

}
