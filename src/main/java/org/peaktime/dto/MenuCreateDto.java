package org.peaktime.dto;

import lombok.Builder;
import lombok.Getter;
import org.peaktime.entity.SdtMenu;

import java.time.LocalDate;

@Getter
@Builder
public class MenuCreateDto {

    private LocalDate date;
    private String menu;

    public SdtMenu toEntity() {
        return SdtMenu.builder()
                .dateTime(date)
                .menu(menu)
                .build();
    }
}
