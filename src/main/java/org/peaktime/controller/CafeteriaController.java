package org.peaktime.controller;

import lombok.RequiredArgsConstructor;
import org.peaktime.dto.BoardFormDto;
import org.peaktime.entity.SdtMenu;
import org.peaktime.service.MemberService;
import org.peaktime.service.MenuService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/cafeteria")
public class CafeteriaController {

    private final MenuService menuService;
    private final MemberService memberService;

    @GetMapping(value = "/student")
    public String cafeteriaStudent(Model model, Principal principal) {

        // 혹시 로그인한 객체가 있을 경우,
        if (principal != null) {
            String email = principal.getName();
            String username = memberService.usernameFindByEmail(email);
            model.addAttribute("username", username);
        }

        // 학생식당 메뉴 리스트.
        List<SdtMenu> sdtMenus = menuService.getAllSdtMenus(LocalDate.now());

        model.addAttribute("menu", sdtMenus);
        model.addAttribute("today", LocalDate.now());

        return "cafeteria/student";
    }

    @GetMapping(value = "/dormitory")
    public String cafeteriaDormitory(Model model, Principal principal) {

        // 혹시 로그인한 객체가 있을 경우,
        if (principal != null) {
            String email = principal.getName();
            String username = memberService.usernameFindByEmail(email);
            model.addAttribute("username", username);
        }

        return "cafeteria/dormitory";
    }

    @GetMapping(value = "/staff")
    public String cafeteriaStaff(Model model, Principal principal) {

        // 혹시 로그인한 객체가 있을 경우,
        if (principal != null) {
            String email = principal.getName();
            String username = memberService.usernameFindByEmail(email);
            model.addAttribute("username", username);
        }

        return "cafeteria/staff";
    }
}
