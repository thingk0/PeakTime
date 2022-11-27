package org.peaktime.dto.member;

import lombok.Builder;
import lombok.Getter;
import org.peaktime.entity.Member;

@Getter
public class MemberCreateDto {

    private final String name;
    private final String email;
    private final String password;


    @Builder
    public MemberCreateDto(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Member createMember() {
        return Member.builder()
                .email(email)
                .password(password)
                .name(name)
                .build();
    }
}
