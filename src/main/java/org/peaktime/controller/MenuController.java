package org.peaktime.controller;

import lombok.RequiredArgsConstructor;
import org.peaktime.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/menu")
public class MenuController {

    private final MenuService menuService;

    /**
     *  학생식당 메뉴 추가.
     */
    @PostMapping(value = "/student/create")
    public String createSdtMenu() {
        return "boards/student_board";
    }
}

