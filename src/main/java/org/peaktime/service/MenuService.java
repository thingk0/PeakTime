package org.peaktime.service;

import lombok.RequiredArgsConstructor;
import org.peaktime.entity.SdtMenu;
import org.peaktime.repository.SdtMenuRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class MenuService {

    private final SdtMenuRepository sdtMenuRepository;

    @Transactional
    public List<SdtMenu> findAllSdtMenus() {
        return sdtMenuRepository.findAll();
    }

    /**
     * ID 값으로 메뉴 찾기.
     */
    @Transactional
    public SdtMenu findSdtMenu(Integer menuId) {
        Optional<SdtMenu> byIdSdtMenu = sdtMenuRepository.findById(menuId);

        if (byIdSdtMenu.isEmpty()) {
            throw new EntityNotFoundException("해당 ID의 메뉴는 존재하지 않습니다.");
        }

        return byIdSdtMenu.get();
    }

    /**
     *  메뉴 스코어 갱신 로직.
     */
    @Transactional
    public void updateSdtMenuScore(LocalDate dateTime, Double score) {

        // 해당하는 날짜에 메뉴가 존재하지 않을 경우.
        if (!sdtMenuRepository.existsByDateTime(dateTime)) {
            throw new EntityNotFoundException("해당 날짜의 메뉴가 존재하지 않습니다.");
        }

        SdtMenu menu = sdtMenuRepository.findByDateTime(dateTime);
        // 스코어 누적 갱신.
        menu.updateScore(score);
    }

    @Transactional
    public void saveSdtMenu(SdtMenu sdtMenu) {

        // 해당하는 날짜에 메뉴가 이미 존재할 경우,
        // 1일 1메뉴 -> Unique
        if (sdtMenuRepository.existsByDateTime(sdtMenu.getDateTime())) {
            throw new IllegalStateException("해당 날짜에는 이미 메뉴가 있습니다.");
        }

        // 리포지터리에 메뉴 저장.
        sdtMenuRepository.save(sdtMenu);
    }

}
