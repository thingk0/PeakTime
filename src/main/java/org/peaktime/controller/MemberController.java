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

        if (bindingResult.hasErrors()) {
            model.addAttribute("memberFormDto", memberFormDto);
            model.addAttribute("errorMessage", "회원 가입에 실패했습니다. 다시 시도해주세요.");
            return "member/memberForm";
        }

        try {
            // 회원가입
            memberService.createMember(MemberCreateDto.builder()
                    .name(memberFormDto.getName())
                    .email(memberFormDto.getEmail())
                    .password((memberFormDto.getPassword()))
                    .build());

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
