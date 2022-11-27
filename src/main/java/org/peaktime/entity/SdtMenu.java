package org.peaktime.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.peaktime.entity.base.BaseEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "student_menu")
public class SdtMenu extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_menu_id")
    private Integer id;

    @Column
    private LocalDateTime dateTime;
    @Column
    private String menu;
    @Column
    private Double score;

    @Builder
    public SdtMenu(LocalDateTime dateTime, String menu) {
        this.dateTime = dateTime;
        this.menu = menu;
        this.score = 0.0;
    }


}
