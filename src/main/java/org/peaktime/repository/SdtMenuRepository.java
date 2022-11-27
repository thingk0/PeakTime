package org.peaktime.repository;

import org.peaktime.entity.SdtMenu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface SdtMenuRepository extends JpaRepository<SdtMenu, Integer> {

    List<SdtMenu> findAll();
    Optional<SdtMenu> findByMenuAndDateTime(String menu, LocalDateTime dateTime);
}
