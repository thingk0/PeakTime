package org.peaktime.entity;

import lombok.*;
import org.peaktime.constant.Role;
import org.peaktime.dto.member.MemberCreateDto;
import org.peaktime.entity.base.BaseTimeEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Getter
@Entity
@Builder
@Table(name = "member")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity {

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String email;

    @Column(name = "username")
    private String name;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    public static Member createMember(MemberCreateDto createDto, PasswordEncoder passwordEncoder) {
        return Member.builder()
                .email(createDto.getEmail())
                .password(passwordEncoder.encode(createDto.getPassword()))
                .name(createDto.getName())
                .role(Role.USER)
                .build();
    }

    public void updateRole(Role role) {
        this.role = role;
    }
}
