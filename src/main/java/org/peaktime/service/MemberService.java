package org.peaktime.service;

import lombok.RequiredArgsConstructor;
import org.peaktime.constant.Role;
import org.peaktime.dto.member.MemberCreateDto;
import org.peaktime.entity.Member;
import org.peaktime.repository.MemberRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;


    @Transactional
    public Integer createMember(MemberCreateDto memberCreateDto) {
        Member member = Member.createMember(memberCreateDto, passwordEncoder);
        validateEmailDoesNotExist(member.getEmail());
        return memberRepository.save(member).getId();
    }


    @Transactional
    public void updateMemberRole(Integer memberId, Role role) {
        // 권한 업데이트
        memberRepository.findById(memberId).orElseThrow(
                () -> new EntityNotFoundException("해당 ID를 통해서 아이디를 찾을 수 없습니다.")
        ).updateRole(role);
    }


    public String findUsernameByEmail(String email) {
        return findMemberByEmail(email).getName();
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = findMemberByEmail(email);
        return User.builder()
                .username(member.getEmail())
                .password(member.getPassword())
                .roles(member.getRole().getValue())
                .build();
    }

    public Member findMemberByEmail(String email) {
        return memberRepository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException(email));
    }


    public void validateEmailDoesNotExist(String email) {
        if (memberRepository.existsByEmail(email)) {
            throw new IllegalStateException("이미 존재하는 이메일입니다.");
        }
    }
}
