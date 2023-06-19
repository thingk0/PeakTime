package org.peaktime.controller;

import org.junit.jupiter.api.Test;
import org.peaktime.entity.SdtMenu;
import org.peaktime.repository.SdtMenuRepository;
import org.peaktime.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;


@SpringBootTest
class MenuControllerTest {

    @Autowired
    MenuService menuService;

    @Autowired
    SdtMenuRepository sdtMenuRepository;

    @Test // 임시용 데이터 설정.
    void sdtMenuCreateTest() throws Exception {
        String[] menuList = new String[]{
                "수육국밥",
                "매콤닭갈비덮밥",
                "떡김새&김가루밥",
                "돈까스카레덮밥",
                "함박스테이크덮밥"};

        for (int i = 0; i < menuList.length; i++) {
            SdtMenu sdtMenu = SdtMenu.builder()
                    .date(LocalDate.now().minusDays(1).plusDays(i))
                    .menu(menuList[i])
                    .build();

//            menuService.saveSdtMenu(sdtMenu);
        }


    }

}