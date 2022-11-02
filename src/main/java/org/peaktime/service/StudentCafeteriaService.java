package org.peaktime.service;

import lombok.RequiredArgsConstructor;
import org.peaktime.entity.StudentMenu;
import org.peaktime.repository.StudentMenuRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class StudentCafeteriaService {

    private final StudentMenuRepository studentMenuRepository;

    @Transactional
    public List<StudentMenu> studentMenuList() {
        return studentMenuRepository.findAll();
    }

}
