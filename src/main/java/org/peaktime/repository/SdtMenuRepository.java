package org.peaktime.repository;

import org.peaktime.entity.MenuDateTime;
import org.peaktime.entity.SdtMenu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface SdtMenuRepository extends JpaRepository<SdtMenu, Integer> {

    List<SdtMenu> findAll();
    Optional<SdtMenu> findByMenuAndDateTime(String menu, LocalDate dateTime);
    SdtMenu findByDateTime(LocalDate dateTime);
    boolean existsByDateTime(LocalDate datetime);
    List<MenuDateTime> findAllByDateTimeEquals(LocalDate localDate);
    List<SdtMenu> findAllByWeekEquals(Integer week);
}
