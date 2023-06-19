package org.peaktime.controller;

import lombok.RequiredArgsConstructor;
import org.peaktime.dto.menu.MenuCreateDto;
import org.peaktime.dto.menu.MenuUpdateDto;
import org.peaktime.service.MenuService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/menu")
public class MenuController {

    private final MenuService menuService;


    @PostMapping(value = "/student/create")
    public String createSdtMenu(@ModelAttribute MenuCreateDto menuCreateDto, Model model) {

        try {
            menuService.validateNoMenuExistsOnDate(menuCreateDto.getDate());
        } catch (IllegalStateException e) {
            model.addAttribute("alreadyExists", "해당 메뉴가 이미 존재합니다.");
            return "redirect:/";
        }

        menuService.createSdtMenu(menuCreateDto);
        return "redirect:/";
    }


    @PostMapping(value = "/student/modify")
    public String updateSdtMenu(@ModelAttribute MenuUpdateDto menuUpdateDto, Model model) {
        menuService.updateSdtMenuByDate(menuUpdateDto);
        return "redirect:/";
    }


    @PostMapping(value = "/student/delete")
    public String deleteSdtMenu(@ModelAttribute("date") String date) {
        menuService.deleteSdtMenuByDate(LocalDate.parse(date));
        return "redirect:/";
    }
}

