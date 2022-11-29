package org.peaktime.service;

import lombok.RequiredArgsConstructor;
import org.peaktime.dto.member.MemberCreateDto;
import org.peaktime.entity.Member;
import org.peaktime.repository.MemberRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;


@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;

    /**
     * 회원가입 로직
     */
    public Integer signUp(MemberCreateDto memberCreateDto) {
        Member member = memberCreateDto.createMember();

        // 회원 중복 조회!
        if (memberRepository.existsByEmail(member.getEmail())) {
            throw new IllegalStateException("이미 존재하는 이메일입니다.");
        }

        Member savedMember = memberRepository.save(member);
        return savedMember.getId();
    }

    /**
     * 이메일을 통해서 회원조회하고 닉네임 반환.
     */
    public String usernameFindByEmail(String email) {
        Member member = memberRepository.findByEmail(email).orElseThrow(
                () -> new EntityNotFoundException("해당 이메일을 사용한 계정을 찾을 수 없습니다."));
        return member.getName();
    }

    /**
     * 이메일을 통해서 기존에 계정이 있는지를 확인하여 만약 중복될 경우 에러 발생.
     */
//    private void validateDuplicateMember(Member member) {
//        memberRepository.findByEmail(member.getEmail()).orElseThrow(
//                () -> new IllegalStateException("이미 가입된 회원입니다."));
//
//        if (memberRepository.existsByEmail(member.getEmail())) {
//            throw new IllegalStateException("이미 존재하는 이메일입니다.");
//        }
//    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Member member = memberRepository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException(email));

        return User.builder()
                .username(member.getEmail())
                .password(member.getPassword())
                .roles(member.getRole().toString())
                .build();
    }
}
