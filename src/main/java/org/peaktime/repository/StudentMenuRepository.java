package org.peaktime.repository;

import org.peaktime.entity.StudentMenu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentMenuRepository extends JpaRepository<StudentMenu, Integer> {

    List<StudentMenu> findAll();
}
