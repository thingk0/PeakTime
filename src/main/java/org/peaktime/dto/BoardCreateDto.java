package org.peaktime.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.peaktime.constant.Cafeteria;
import org.peaktime.entity.Board;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardCreateDto {

    private Cafeteria cafeteria;
    private String content;

    private Double score;

    public Board toEntity() {
        return Board.builder()
                .cafeteria(this.cafeteria)
                .content(this.content)
                .score(this.score)
                .build();
    }
}
