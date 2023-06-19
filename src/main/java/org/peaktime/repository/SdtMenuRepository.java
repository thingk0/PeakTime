package org.peaktime.repository;

import org.peaktime.entity.SdtMenu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface SdtMenuRepository extends JpaRepository<SdtMenu, Integer> {

    Optional<SdtMenu> findSdtMenuByDate(LocalDate date);
    Optional<SdtMenu> findByDate(LocalDate dateTime);
    boolean existsByDate(LocalDate datetime);
    List<SdtMenu> findAllByWeekEquals(Integer week);
}
