package org.peaktime.controller;

import lombok.RequiredArgsConstructor;
import org.peaktime.dto.MenuCreateDto;
import org.peaktime.entity.SdtMenu;
import org.peaktime.service.MenuService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/menu")
public class MenuController {

    private final MenuService menuService;

    /**
     *  학생식당 메뉴 추가.
     */
    @PostMapping(value = "/student/create")
    public String createSdtMenu(@RequestParam("date")String date,
                                @RequestParam("menu")String menu,
                                Model model) {

        MenuCreateDto menuCreateDto = MenuCreateDto.builder()
                .date(LocalDate.parse(date))
                .menu(menu)
                .build();

        menuService.saveSdtMenu(menuCreateDto);

        return "redirect:/";
    }

}

