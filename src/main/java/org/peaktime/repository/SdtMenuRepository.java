package org.peaktime.repository;

import org.peaktime.entity.SdtMenu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SdtMenuRepository extends JpaRepository<SdtMenu, Integer> {

    List<SdtMenu> findAll();
}
