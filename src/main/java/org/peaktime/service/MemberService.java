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


@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;

    /**
     * 회원가입 로직
     */
    public void signUp(MemberCreateDto memberCreateDto) {
        Member member = memberCreateDto.createMember();
        validateDuplicateMember(member);
        memberRepository.save(member);
    }

    /**
     * 이메일을 통해서 기존에 계정이 있는지를 확인하여 만약 중복될 경우 에러 발생.
     */
    private void validateDuplicateMember(Member member) {
        Member findMember = memberRepository.findByEmail(member.getEmail());
        if (findMember != null) {
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(email);

        if (member == null) {
            throw new UsernameNotFoundException(email);
        }

        return User.builder()
                .username(member.getEmail())
                .password(member.getPassword())
                .roles(member.getRole().toString())
                .build();
    }
}
