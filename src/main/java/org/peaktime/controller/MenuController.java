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

    /**
     * 학생식당 메뉴 추가.
     */
    @PostMapping(value = "/student/create")
    public String createSdtMenu(@RequestParam("date") String date,
                                @RequestParam("menu") String menu,
                                Model model) {

        MenuCreateDto menuCreateDto = MenuCreateDto.builder()
                .date(LocalDate.parse(date))
                .menu(menu)
                .build();

        if (menuService.findSdtMenuByDateTime(menuCreateDto.getDate())) {
            model.addAttribute("alreadyExists", "해당 메뉴가 이미 존재합니다.");
            return "redirect:/";
        }

        menuService.saveSdtMenu(menuCreateDto);
        return "redirect:/";
    }

    /**
     * 학생식당 메뉴 수정.
     */
    @PostMapping(value = "/student/modify")
    public String updateSdtMenu(@RequestParam("date") String date,
                                @RequestParam("menu") String menu,
                                Model model) {

        MenuUpdateDto menuUpdateDto = MenuUpdateDto.builder()
                .date(LocalDate.parse(date))
                .menu(menu)
                .build();

        menuService.updateSdtMenu(menuUpdateDto);

        return "redirect:/";
    }

    /**
     * 학생식당 메뉴 삭제.
     */
    @PostMapping(value = "/student/delete")
    public String deleteSdtMenu(@RequestParam("date") String date) {
        menuService.deleteSdtMenu(LocalDate.parse(date));

        return "redirect:/";
    }
}

