package org.peaktime.service;

import lombok.RequiredArgsConstructor;
import org.peaktime.constant.Cafeteria;
import org.peaktime.dto.board.BoardCreateDto;
import org.peaktime.entity.Board;
import org.peaktime.entity.SdtMenu;
import org.peaktime.repository.BoardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

    private final MenuService menuService;
    private final BoardRepository boardRepository;

    public void saveBoardWithTodayMenu(BoardCreateDto boardCreateDto) {
        SdtMenu todaySdtMenu = menuService.getSdtMenuByDate(LocalDate.now());
        boardRepository.save(Board.createBoard(todaySdtMenu, boardCreateDto));
    }

    @Transactional(readOnly = true)
    public List<Board> getTodayBoards(Cafeteria cafeteria) {
        // 식당 종류와 오늘 날짜를 기준으로 조회.
        return boardRepository.findByCafeteriaAndDate(cafeteria, LocalDate.now());
    }

}
