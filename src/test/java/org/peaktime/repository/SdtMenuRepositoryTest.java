package org.peaktime.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.peaktime.entity.MenuDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;


@SpringBootTest
class SdtMenuRepositoryTest {

    @Autowired
    SdtMenuRepository sdtMenuRepository;

    @Test
    void findAllDateTimeTest() throws Exception {
        // given
        List<MenuDateTime> allBy = sdtMenuRepository.findAllByDateTimeEquals(LocalDate.now());
        // when
        for (MenuDateTime dateTime : allBy) {
            System.out.println("dateTime = " + dateTime.getDateTime());
        }

        // then
//        Assertions.assertThat(allBy.size()).isEqualTo(5);
    }

}