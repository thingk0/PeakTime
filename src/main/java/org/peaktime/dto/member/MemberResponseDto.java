package org.peaktime.dto.member;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.peaktime.entity.Member;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberResponseDto {

    private String email;
    private String password;
    private String name;

    public MemberResponseDto(Member member) {
        this.email = member.getEmail();
        this.password = member.getPassword();
        this.name = member.getName();
    }
}
