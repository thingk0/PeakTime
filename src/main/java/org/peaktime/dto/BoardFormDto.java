package org.peaktime.dto;

import lombok.*;
import javax.validation.constraints.*;

@Getter
@Setter
public class BoardFormDto {

    @NotBlank(message = "최소 10자 이상 100자 미만으로 리뷰를 남겨주세요.")
    @Min(value = 10) @Max(value = 100)
    private String content;

}
