package org.peaktime.service;

import lombok.RequiredArgsConstructor;
import org.peaktime.constant.Cafeteria;
import org.peaktime.dto.board.BoardCreateDto;
import org.peaktime.entity.Board;
import org.peaktime.repository.BoardRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    /**
     * 보드 저장
     */
    public void saveBoard(BoardCreateDto boardCreateDto) {
        // Entity 로 변환.
        Board board = boardCreateDto.toEntity();

        // Entity 저장.
        boardRepository.save(board);
    }

    /**
     * 특정 보드 전체조회 & 날짜별로
     */
    public List<Board> getBoardsInToday(Cafeteria cafeteria) {
        // Cafeteria ENUM 을 통해서 특정 보드를 찾은 후 반환.
//        return boardRepository.findByCafeteria(cafeteria);

        List<Board> boardList = boardRepository.findByCafeteriaAndDateTime(cafeteria, LocalDate.now());
        if (boardList.isEmpty()) {
            return new ArrayList<>();
        }

        // 식당 종류와 오늘 날짜를 기준으로 조회.
        return boardRepository.findByCafeteriaAndDateTime(cafeteria, LocalDate.now());
    }


}
