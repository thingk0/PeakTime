package org.peaktime.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.peaktime.constant.Role;
import org.peaktime.dto.member.MemberCreateDto;
import org.peaktime.entity.Member;
import org.peaktime.repository.MemberRepository;
import org.peaktime.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class MemberControllerTest {

    @Autowired
    MemberService memberService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("회원가입 테스트")
    void singUpTest() throws Exception {
        // given
        MemberCreateDto memberCreateDto = MemberCreateDto.builder()
                .email("test001@test.com")
                .password("123123123")
                .name("테스트1")
                .build();

        // when
        Integer savedMemberId = memberService.signUp(memberCreateDto);
        Member findMember = memberRepository.findById(savedMemberId).orElseThrow(
                () -> new EntityNotFoundException("해당 ID 를 통해 계정을 찾을 수 없습니다.")
        );

        // then
        assertThat(findMember.getEmail()).isEqualTo(memberCreateDto.getEmail());
    }

    @Test
    @DisplayName("어드민 계정 생성 테스트")
    void adminCreateTest() throws Exception {
        // given
        MemberCreateDto memberCreateDto = MemberCreateDto.builder()
                .email("sdt@manager.com")
                .password(passwordEncoder.encode("123123123"))
                .name("학관식당_매니저")
                .build();

        // when
        Integer memberId = memberService.signUp(memberCreateDto);
        memberService.updateRole(memberId, Role.STUDENT_MANAGER);

        Optional<Member> byId = memberRepository.findById(memberId);

        if (byId.isEmpty()) {
            throw new EntityNotFoundException("존재하지 않는 계정입니다.");
        }

        // then
        assertThat(byId.get().getRole()).isEqualTo(Role.STUDENT_MANAGER);
    }

}