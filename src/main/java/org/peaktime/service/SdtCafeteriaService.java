package org.peaktime.service;

import lombok.RequiredArgsConstructor;
import org.peaktime.entity.SdtMenu;
import org.peaktime.repository.SdtMenuRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class SdtCafeteriaService {

    private final SdtMenuRepository sdtMenuRepository;

    @Transactional
    public List<SdtMenu> getSdtMenus() {
        return sdtMenuRepository.findAll();
    }

    @Transactional
    public void saveMenu(SdtMenu sdtMenu) {
        // 만약 이미 메뉴와 날짜가 존재할 경우 저장 실패.
        validateDuplicateMenu(sdtMenu);
        // 리포지터리에 메뉴 저장.
        sdtMenuRepository.save(sdtMenu);
    }

    private void validateDuplicateMenu(SdtMenu sdtMenu) {
        Optional<SdtMenu> findMenu = sdtMenuRepository.findByMenuAndDateTime(sdtMenu.getMenu(), sdtMenu.getDateTime());

        if (findMenu.isPresent()) {
            throw new IllegalStateException("이미 존재하는 메뉴와 날짜입니다.");
        }
    }
}
