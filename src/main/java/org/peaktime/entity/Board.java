package org.peaktime.entity;

import lombok.*;
import org.peaktime.constant.Cafeteria;
import org.peaktime.dto.board.BoardCreateDto;
import org.peaktime.entity.base.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Builder
@Table(name = "board")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board extends BaseEntity {

    @Id
    @Column(name = "board_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private Cafeteria cafeteria;

    @Column(nullable = false, length = 100)
    private String content;

    private Double score;

    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "student_menu_id")
    private SdtMenu sdtMenu;

    public static Board createBoard(SdtMenu sdtMenu, BoardCreateDto createDto) {
        return Board.builder()
                .cafeteria(createDto.getCafeteria())
                .content(createDto.getContent())
                .score(sdtMenu.getScore())
                .date(LocalDate.now())
                .sdtMenu(sdtMenu)
                .build();
    }
}
