package org.peaktime.service;

import lombok.RequiredArgsConstructor;
import org.peaktime.entity.SdtMenu;
import org.peaktime.repository.SdtMenuRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class SdtCafeteriaService {

    private final SdtMenuRepository sdtMenuRepository;

    @Transactional
    public List<SdtMenu> getSdtMenus() {
        return sdtMenuRepository.findAll();
    }
}
