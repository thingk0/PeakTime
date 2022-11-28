package org.peaktime.repository;

import org.peaktime.constant.Cafeteria;
import org.peaktime.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Integer> {

    List<Board> findByCafeteria(Cafeteria cafeteria);
}
