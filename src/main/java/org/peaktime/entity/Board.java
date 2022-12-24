package org.peaktime.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.peaktime.constant.Cafeteria;
import org.peaktime.entity.base.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "board")
public class Board extends BaseEntity {

    @Id
    @Column(name = "board_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private Cafeteria cafeteria;

    @Column(nullable = false, length = 100)
    private String content;

    private Double score;

    @Column(name = "board_date")
    private LocalDate dateTime;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "student_menu_id")
    private SdtMenu sdtMenu;

    @Builder
    public Board(Cafeteria cafeteria, String content, Double score, SdtMenu sdtMenu) {
        this.cafeteria = cafeteria;
        this.content = content;
        this.dateTime = LocalDate.now();
        this.score = score;
        this.sdtMenu = sdtMenu;
    }
}
