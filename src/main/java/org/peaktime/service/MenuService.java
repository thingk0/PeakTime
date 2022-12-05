package org.peaktime.service;

import lombok.RequiredArgsConstructor;
import org.peaktime.dto.MenuCreateDto;
import org.peaktime.entity.SdtMenu;
import org.peaktime.repository.SdtMenuRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
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

    @Transactional
    public List<SdtMenu> getAllSdtMenus(LocalDate today) {
        int thisWeek = today.get(WeekFields.ISO.weekOfYear());

        // 이번 주차와 같은 메뉴들만 찾아서 반환.
        return sdtMenuRepository.findAllByWeekEquals(thisWeek);
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
    public void updateSdtMenuScore(LocalDate today, Double score) {

        // 해당하는 날짜에 메뉴가 존재하지 않을 경우.
        if (!sdtMenuRepository.existsByDateTime(today)) {
            throw new EntityNotFoundException("해당 날짜의 메뉴가 존재하지 않습니다.");
        }

        // 날짜를 통해서 메뉴 가져오기.
        SdtMenu menu = sdtMenuRepository.findByDateTime(today);

        // 스코어 누적 갱신.
        menu.updateScore(score);
    }

    /**
     * 메뉴 저장.
     */
    @Transactional
    public void saveSdtMenu(MenuCreateDto menuCreateDto) {

        // Dto -> Entity
        SdtMenu sdtMenu = menuCreateDto.toEntity();

        // 해당하는 날짜에 메뉴가 이미 존재할 경우,
        // 1일 1메뉴 -> Unique
        if (sdtMenuRepository.existsByDateTime(sdtMenu.getDateTime())) {
            throw new IllegalStateException("해당 날짜에는 이미 메뉴가 있습니다.");
        }

        // 리포지터리에 메뉴 저장.
        sdtMenuRepository.save(sdtMenu);
    }

}
