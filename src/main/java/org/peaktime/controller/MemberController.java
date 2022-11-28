package org.peaktime.controller;

import lombok.RequiredArgsConstructor;
import org.peaktime.dto.member.MemberCreateDto;
import org.peaktime.dto.member.MemberFormDto;
import org.peaktime.service.MemberService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/members")
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    // 회원가입 페이지 반환.
    @GetMapping(value = "/new")
    public String memberForm(Model model) {
        model.addAttribute("memberFormDto", new MemberFormDto());
        return "member/memberForm";
    }

    // 회원가입
    @PostMapping(value = "/new")
    public String createMember(@Valid MemberFormDto memberFormDto,
                                BindingResult bindingResult,
                                Model model) {

        // 만약 error 가 발생하면 다시 템플릿 반환.
        if (bindingResult.hasErrors()) {
            /* 회원가입 실패 시 입력 데이터 값 유지 */
            model.addAttribute("memberFormDto", memberFormDto);
            return "member/memberForm";
        }

        try {
            MemberCreateDto memberCreateDto = MemberCreateDto.builder()
                    .email(memberFormDto.getEmail())
                    .password(passwordEncoder.encode(memberFormDto.getPassword()))
                    .name(memberFormDto.getName())
                    .build();

            // 회원가입
            memberService.signUp(memberCreateDto);

        } catch (IllegalStateException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "member/memberForm";
        }

        return "redirect:/cafeteria/student";
    }


    // 로그인
    @GetMapping(value = "/login")
    public String loginMember() {
        return "member/memberLoginForm";
    }


    @GetMapping(value = "/login/error")
    public String loginError(Model model) {
        model.addAttribute("loginErrorMsg", "이메일 또는 비밀번호를 확인해주세요");
        return "member/memberLoginForm";
    }

}
