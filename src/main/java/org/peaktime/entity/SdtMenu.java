package org.peaktime.entity;

import lombok.*;
import org.peaktime.dto.menu.MenuCreateDto;
import org.peaktime.dto.menu.MenuUpdateDto;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.temporal.WeekFields;

@Entity
@Getter
@Builder
@AllArgsConstructor
@Table(name = "student_menu")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SdtMenu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_menu_id")
    private Integer id;

    private Double score;

    private String menu;

    private LocalDate date;

    private Integer week;


    public static SdtMenu createSdtMenu(MenuCreateDto createDto) {
        return SdtMenu.builder()
                .score(0.0)
                .menu(createDto.getMenu())
                .date(createDto.getDate())
                .week(createDto.getDate().get(WeekFields.ISO.weekOfYear()))
                .build();
    }

    public int updateMenu(MenuUpdateDto menuUpdateDto) {
        this.menu = menuUpdateDto.getMenu();
        this.date = menuUpdateDto.getDate();
        this.week = menuUpdateDto.getDate().get(WeekFields.ISO.weekOfYear());
        return this.getId();
    }

    public void updateScore(Double point) {
        this.score = (this.score + point) / 2.0;
    }

}
