package org.peaktime.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.peaktime.dto.member.MemberCreateDto;
import org.peaktime.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    @Transactional
    @DisplayName("회원가입 테스트")
    void signUp() {
        // given
        MemberCreateDto memberCreateDto = MemberCreateDto.builder()
                .email("test@test.com")
                .password("1234")
                .name("test")
                .build();

        Member member = memberCreateDto.createMember();

        // when
        memberRepository.save(member);
        Member findByNameMember = memberRepository.findByName("test").orElseThrow(EntityNotFoundException::new);

        // then
        assertThat(findByNameMember.getName()).isEqualTo(member.getName());
        assertThat(findByNameMember.getEmail()).isEqualTo(member.getEmail());
        assertThat(findByNameMember.getPassword()).isEqualTo(member.getPassword());
    }
}