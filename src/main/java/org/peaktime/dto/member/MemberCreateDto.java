package org.peaktime.dto.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.peaktime.entity.Member;

@Data
@Builder
@AllArgsConstructor
public class MemberCreateDto {
    private String name;
    private String email;
    private String password;
}
