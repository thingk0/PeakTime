package org.peaktime.controller;

import lombok.RequiredArgsConstructor;
import org.peaktime.constant.Cafeteria;
import org.peaktime.dto.board.BoardCreateDto;
import org.peaktime.entity.Board;
import org.peaktime.entity.SdtMenu;
import org.peaktime.service.BoardService;
import org.peaktime.service.MemberService;
import org.peaktime.service.MenuService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/boards")
public class BoardController {

    private final BoardService boardService;
    private final MenuService menuService;
    private final MemberService memberService;

    /**
     * 학생식당 게시글 작성
     */
    @PostMapping(value = "/student/create")
    public String createBoard(@RequestParam("score") Double score,
                              @RequestParam("content") String content,
                              Model model) {


        // 오늘 날짜의 메뉴 스코어를 갱신.
        menuService.updateSdtMenuScore(score);

        // DTO 생성.
        BoardCreateDto createDto = BoardCreateDto.builder()
                .cafeteria(Cafeteria.STUDENT)
                .content(content)
                .score(score)
                .build();

        // 보드 저장.
        boardService.saveBoard(createDto);

        return "redirect:/boards/student";
    }

    /**
     * 학생식당 게시판 조회하기.
     */
    @GetMapping(value = "/student")
    public String getSdtBoards(Model model,
                               Principal principal) {

        // 혹시 로그인한 객체가 있을 경우, 상단 닉네임 표기 X
        if (principal != null) {
            String email = principal.getName();
            String username = memberService.usernameFindByEmail(email);
            model.addAttribute("username", username);
            model.addAttribute("Top_bar_nickname_notation", "no");
        }


        // 게시물 리스트
        List<Board> boardList = boardService.getBoardsInToday(Cafeteria.STUDENT);
        model.addAttribute("boardList", boardList);

        // 별점
        Double todaySdtMenuScoreScore = menuService.todaySdtMenuScore();
        model.addAttribute("todayScore", todaySdtMenuScoreScore);

        // 오늘의 메뉴
        String todayMenu = menuService.todaySdtMenu();
        model.addAttribute("todayMenu", todayMenu);

        return "boards/student_board";
    }

}
