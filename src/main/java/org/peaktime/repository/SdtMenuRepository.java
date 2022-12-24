package org.peaktime.repository;

import org.peaktime.entity.SdtMenu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface SdtMenuRepository extends JpaRepository<SdtMenu, Integer> {

    Optional<SdtMenu> findSdtMenuByDateTime(LocalDate date);
    Optional<SdtMenu> findByDateTime(LocalDate dateTime);
    boolean existsByDateTime(LocalDate datetime);
    List<SdtMenu> findAllByWeekEquals(Integer week);
}
