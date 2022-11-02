package org.peaktime.controller;

import lombok.RequiredArgsConstructor;
import org.peaktime.repository.StudentMenuRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class CafeteriaController {


    @GetMapping(value = "/cafeteria-dormitory")
    public String getCafeteriaDormitory() {
        return "cafeteria_dormitory";
    }

    @GetMapping(value = "/cafeteria-staff")
    public String getCafeteriaStaff() {
        return "cafeteria_staff";
    }
}
