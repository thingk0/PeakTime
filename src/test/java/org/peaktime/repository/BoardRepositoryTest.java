package org.peaktime.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.peaktime.constant.Cafeteria;
import org.peaktime.dto.BoardCreateDto;
import org.peaktime.entity.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class BoardRepositoryTest {

    @Autowired
    BoardRepository boardRepository;

    @Test
    @Transactional
    @DisplayName("보드 생성 및  조회 테스트")
    void findByCafeteriaTest() throws Exception {

        // given
        for (int i = 1; i <= 10; i++) {
            BoardCreateDto createDto = BoardCreateDto.builder()
                    .cafeteria(Cafeteria.STUDENT)
                    .content("오늘 " + i + "점만큼 너무 맛있습니다!")
                    .build();

            boardRepository.save(createDto.toEntity());
        }

        // when
        List<Board> byCafeteria = boardRepository.findByCafeteria(Cafeteria.STUDENT);

        // then
        assertThat(byCafeteria.size()).isEqualTo(10);
        assertThat(byCafeteria.get(5).getContent()).isEqualTo("오늘 6점만큼 너무 맛있습니다!");
    }

    @Test
    @Transactional
    @DisplayName("보드 생성 테스트")
    void boardSaveTest() throws Exception {
        // given
        Board board = Board.builder()
                .cafeteria(Cafeteria.STUDENT)
                .content("테스트")
                .build();

        boardRepository.save(board);

        // when
        List<Board> byCafeteria = boardRepository.findByCafeteria(Cafeteria.STUDENT);

        // then
        assertThat(byCafeteria.size()).isEqualTo(1);
    }

}