package org.peaktime.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.peaktime.repository.SdtMenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;


@SpringBootTest
class MenuServiceTest {

    @Autowired
    SdtMenuRepository sdtMenuRepository;

    @Test
    @DisplayName("주차별 요일 구하기.")
    void getMenuDay() throws Exception {
        // given
        LocalDate date = LocalDate.now(); // ex) 목요일
        List<LocalDate> dateList = new ArrayList<>();

//        for (int i = 0; i < 10; i++) {
//            LocalDate localDate = date.plusDays(i);
//            System.out.println("localDate = " + localDate);
//            DayOfWeek dayOfWeek = localDate.getDayOfWeek();
//            System.out.println("dayOfWeek = " + dayOfWeek);
//            int value = dayOfWeek.getValue();
//            System.out.println("value = " + value);
//        }

        int weekValue = date.get(WeekFields.ISO.weekOfYear());
        System.out.println("weekValue = " + weekValue);


        // when
        // then
    }

}