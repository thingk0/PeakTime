package org.peaktime.dto;

import lombok.Getter;
import lombok.Setter;
import org.peaktime.entity.SdtMenu;

import java.time.LocalDateTime;


@Getter
@Setter
public class CreateSdtMenuDto {

    private LocalDateTime dateTime;
    private String menu;

    static SdtMenu createSdtMenu(CreateSdtMenuDto createdDto) {
        return SdtMenu.builder()
                .dateTime(createdDto.getDateTime())
                .menu(createdDto.getMenu())
                .build();
    }
}
