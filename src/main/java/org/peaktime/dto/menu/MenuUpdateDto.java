package org.peaktime.dto.menu;

import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuUpdateDto {
    private LocalDate date;
    private String menu;
}
