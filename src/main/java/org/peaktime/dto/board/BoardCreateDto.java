package org.peaktime.dto.board;

import lombok.*;
import org.peaktime.constant.Cafeteria;

import javax.annotation.PostConstruct;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardCreateDto {
    private Cafeteria cafeteria;
    private String content;
    private Double score;



}
