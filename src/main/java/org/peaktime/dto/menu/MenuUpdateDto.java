package org.peaktime.dto.menu;

import lombok.Builder;
import lombok.Getter;
import org.peaktime.entity.SdtMenu;

import java.time.LocalDate;

@Getter
@Builder
public class MenuUpdateDto {

    private LocalDate date;
    private String menu;

    @Builder
    public MenuUpdateDto(LocalDate date, String menu) {
        this.date = date;
        this.menu = menu;
    }
}
