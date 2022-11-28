package org.peaktime.repository;

import org.peaktime.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Integer> {

    Optional<Member> findByName(String name);
    Optional<Member> findByEmail(String email);

    /* 유효성 검사 - 중복 체크
     * 중복 : true
     * 중복이 아닌 경우 : false
     */
    boolean existsByName(String name);
    boolean existsByEmail(String email);
}
