package org.peaktime.service;

import lombok.RequiredArgsConstructor;
import org.peaktime.constant.Role;
import org.peaktime.dto.member.MemberCreateDto;
import org.peaktime.entity.Member;
import org.peaktime.repository.MemberRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;

    /**
     * 회원가입 로직
     */
    @Transactional
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
    @Transactional(readOnly = true)
    public String usernameFindByEmail(String email) {
        Member member = memberRepository.findByEmail(email).orElseThrow(
                () -> new EntityNotFoundException("해당 이메일을 사용한 계정을 찾을 수 없습니다."));
        return member.getName();
    }

    /**
     * 권한 설정
     */
    @Transactional
    public void updateRole(Integer memberId, Role role) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new EntityNotFoundException("해당 ID를 통해서 아이디를 찾을 수 없습니다.")
        );

        // 권한 업데이트
        member.updateRole(role);
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Member member = memberRepository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException(email));

        return User.builder()
                .username(member.getEmail())
                .password(member.getPassword())
                .roles(member.getRole().getValue())
                .build();
    }

}
