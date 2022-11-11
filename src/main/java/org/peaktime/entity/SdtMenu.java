package org.peaktime.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "student_menu")
public class SdtMenu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_menu_id")
    private Integer id;

    private LocalDateTime dateTime;
    private String menu;

    @Builder
    public SdtMenu(LocalDateTime dateTime, String menu) {
        this.dateTime = dateTime;
        this.menu = menu;
    }


}
