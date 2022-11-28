package org.peaktime.service;

import lombok.RequiredArgsConstructor;
import org.peaktime.constant.Cafeteria;
import org.peaktime.dto.BoardCreateDto;
import org.peaktime.entity.Board;
import org.peaktime.repository.BoardRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    /**
     * 보드 저장
     */
    public void saveBoard(BoardCreateDto boardCreateDto) {
        boardRepository.save(boardCreateDto.toEntity());
    }

    /**
     * 특정 보드 전체조회
     */
    public List<Board> getBoards(Cafeteria cafeteria) {
        return boardRepository.findByCafeteria(cafeteria);
    }


}
