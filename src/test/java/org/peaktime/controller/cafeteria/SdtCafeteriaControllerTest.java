package org.peaktime.controller.cafeteria;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.peaktime.entity.SdtMenu;
import org.peaktime.repository.SdtMenuRepository;
import org.peaktime.service.SdtCafeteriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SdtCafeteriaControllerTest {

    @Autowired
    SdtCafeteriaService service;

    @Autowired
    SdtMenuRepository repository;


    @Test
//    @Transactional
    @DisplayName("메뉴 생성 및 조회 테스트")
    void getCafeteriaStudentTest() {

        String[] menus = {"햄야채볶음밥", "스팸김치볶음밥", "참치마요덮밥", "목살스테이크덮밥", "가츠동"};
        // given
        for (int i = 0; i < 5; i++) {
            SdtMenu studentMenu = SdtMenu.builder()
                    .dateTime(LocalDateTime.of(2022, 11, 8 + i, 0, 0, 0))
                    .menu(menus[i])
                    .build();

            repository.save(studentMenu);
        }

        // when
        List<SdtMenu> sdtMenus = service.getSdtMenus();
        for (SdtMenu menu : sdtMenus) {
            System.out.printf("%s   %s\n", menu.getDateTime(), menu.getMenu());
        }

        assertThat(sdtMenus.get(0).getMenu()).isEqualTo(menus[0]);
        assertThat(sdtMenus.get(1).getMenu()).isEqualTo(menus[1]);
        assertThat(sdtMenus.get(2).getMenu()).isEqualTo(menus[2]);
        assertThat(sdtMenus.get(3).getMenu()).isEqualTo(menus[3]);
        assertThat(sdtMenus.get(4).getMenu()).isEqualTo(menus[4]);

    }

}