package org.peaktime.controller.cafeteria;

import lombok.RequiredArgsConstructor;
import org.peaktime.entity.StudentMenu;
import org.peaktime.repository.StudentMenuRepository;
import org.peaktime.service.StudentCafeteriaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class StudentCafeteriaController {

    private final StudentCafeteriaService studentCafeteriaService;

    @GetMapping(value = "/cafeteria-student")
    public String getCafeteriaStudent(Model model) {
        List<StudentMenu> studentMenus = studentCafeteriaService.studentMenuList();

        return "cafeteria_student";
    }

}
