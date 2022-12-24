package org.peaktime.service;

import lombok.RequiredArgsConstructor;
import org.peaktime.constant.Cafeteria;
import org.peaktime.dto.board.BoardCreateDto;
import org.peaktime.entity.Board;
import org.peaktime.entity.SdtMenu;
import org.peaktime.repository.BoardRepository;
import org.peaktime.repository.SdtMenuRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final SdtMenuRepository sdtMenuRepository;

    /**
     * 보드 저장
     */
    public void saveBoard(BoardCreateDto boardCreateDto) {
        // 오늘 학식 메뉴 가져오기.
        Optional<SdtMenu> todayMenu = sdtMenuRepository.findByDateTime(LocalDate.now());

        if (todayMenu.isPresent()) {
            // Entity 로 변환.
            Board board = boardCreateDto.toEntity(todayMenu.get());
            // Entity 저장.
            boardRepository.save(board);
        } else {
            throw new IllegalStateException("게시글 저장 실패");
        }
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
