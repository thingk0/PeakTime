package org.peaktime.controller;

import lombok.RequiredArgsConstructor;
import org.peaktime.constant.Cafeteria;
import org.peaktime.dto.board.BoardCreateDto;
import org.peaktime.service.BoardService;
import org.peaktime.service.MemberService;
import org.peaktime.service.MenuService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/boards")
public class BoardController {

    private final BoardService boardService;
    private final MenuService menuService;
    private final MemberService memberService;


    @PostMapping(value = "/student")
    public String createBoard(@ModelAttribute BoardCreateDto boardCreateDto) {
        menuService.updateTodaySdtMenuScore(boardCreateDto.getScore());
        boardCreateDto.setCafeteria(Cafeteria.STUDENT); // 현재 학생 식당만 운영을 가정.
        boardService.saveBoardWithTodayMenu(boardCreateDto);

        return "redirect:/boards/student";
    }


    @GetMapping(value = "/student")
    public String getSdtBoards(Model model, Principal principal) {

        if (principal != null) {
            String email = principal.getName();
            String username = memberService.findUsernameByEmail(email);
            model.addAttribute("username", username);
            model.addAttribute("Top_bar_nickname_notation", "no");
        }

        // 게시물 리스트
        model.addAttribute("boardList", boardService.getTodayBoards(Cafeteria.STUDENT));
        // 별점
        model.addAttribute("todayScore", menuService.getTodaySdtMenuScore());
        // 오늘의 메뉴
        model.addAttribute("todayMenu", menuService.getTodaySdtMenuName());

        return "boards/student_board";
    }

}
