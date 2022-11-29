package org.peaktime.controller;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.peaktime.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BoardController.class)
class BoardControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private BoardService boardService;

    @Test
    void saveBoardTest() throws Exception {
        // given
        // when
        // then
    }

}