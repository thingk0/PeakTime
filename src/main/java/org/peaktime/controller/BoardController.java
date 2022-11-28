package org.peaktime.controller;

import lombok.RequiredArgsConstructor;
import org.peaktime.dto.BoardFormDto;
import org.peaktime.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/boards")
public class BoardController {

    private final BoardService boardService;

    @PostMapping(value = "/create")
    public String createBoard(@Valid BoardFormDto boardFormDto,
                              BindingResult bindingResult,
                              HttpServletRequest request,
                              Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute(boardFormDto);
            return "redirect:" + request.getRequestURI();
        }

        return "redirect:" + request.getRequestURI();
    }

}
