package org.peaktime.dto.board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.peaktime.constant.Cafeteria;
import org.peaktime.entity.Board;
import org.peaktime.entity.SdtMenu;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardCreateDto {

    private Cafeteria cafeteria;
    private String content;
    private Double score;

    public Board toEntity(SdtMenu sdtMenu) {
        return Board.builder()
                .cafeteria(this.cafeteria)
                .content(this.content)
                .score(this.score)
                .sdtMenu(sdtMenu)
                .build();
    }
}
