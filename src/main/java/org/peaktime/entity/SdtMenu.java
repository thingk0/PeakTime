package org.peaktime.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.peaktime.entity.base.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "student_menu")
public class SdtMenu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_menu_id")
    private Integer id;
    @Column(name = "student_menu_created")
    private LocalDate dateTime;
    @Column(name = "student_menu_name")
    private String menu;
    @Column(name = "student_menu_score")
    private Double score;

    @Builder
    public SdtMenu(LocalDate dateTime, String menu) {
        this.dateTime = dateTime;
        this.menu = menu;
    }

    public void updateScore(Double point) {

        // 최초 갱신 시, 점수가 없기 때문에 point 로 초기화.
        if (score == null) {
            score = point;
        }

        score = (score + point) / 2.0;
    }

}
