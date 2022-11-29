package org.peaktime.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.peaktime.dto.member.MemberCreateDto;
import org.peaktime.entity.Member;
import org.peaktime.repository.MemberRepository;
import org.peaktime.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class MemberControllerTest {

    @Autowired
    MemberService memberService;

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

}