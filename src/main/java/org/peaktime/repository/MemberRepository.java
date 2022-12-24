package org.peaktime.repository;

import org.peaktime.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Integer> {

    Optional<Member> findByName(String name);
    Optional<Member> findByEmail(String email);
    boolean existsByEmail(String email);
}
