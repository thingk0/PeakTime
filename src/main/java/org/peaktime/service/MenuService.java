package org.peaktime.service;

import lombok.RequiredArgsConstructor;
import org.peaktime.dto.menu.MenuCreateDto;
import org.peaktime.dto.menu.MenuUpdateDto;
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

    @Transactional(readOnly = true)
    public SdtMenu getTodaySdtMenu() {
        return sdtMenuRepository.findSdtMenuByDateTime(LocalDate.now()).orElseThrow(
                () -> new EntityNotFoundException("찾을 수 없습니다.")
        );
    }


    /**
     * 오늘 메뉴 스코어 가져오기.
     */
    @Transactional
    public Double todaySdtMenuScore() {

        // 해당하는 날짜에 메뉴가 존재하지 않을 경우.
//        if (!sdtMenuRepository.existsByDateTime(LocalDate.now())) {
//            throw new EntityNotFoundException("해당 날짜의 메뉴가 존재하지 않습니다.");
//        }
//
//        try {
//            boolean isExistsTodaySdtMenu = sdtMenuRepository.existsByDateTime(LocalDate.now());
//        } catch (EntityNotFoundException e) {
//
//        }

        Optional<SdtMenu> byDateTime = sdtMenuRepository.findByDateTime(LocalDate.now());
        if (byDateTime.isEmpty()) {
            return 0.0;
        }

//        if (sdtMenu.getScore() == null) {
//            return 0.0;
//        }

        return byDateTime.get().getScore();
    }


    /**
     * 메뉴 스코어 갱신 로직.
     */
    @Transactional
    public void updateSdtMenuScore(Double score) {

        // 해당하는 날짜에 메뉴가 존재하지 않을 경우.
        if (!sdtMenuRepository.existsByDateTime(LocalDate.now())) {
            throw new EntityNotFoundException("해당 날짜의 메뉴가 존재하지 않습니다.");
        }

        // 날짜를 통해서 메뉴 가져오기.
        SdtMenu sdtMenu = sdtMenuRepository.findByDateTime(LocalDate.now()).orElseThrow(
                () -> new EntityNotFoundException("해당 날짜의 메뉴가 없습니다.")
        );

        // 스코어 누적 갱신.
        sdtMenu.updateScore(score);
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

    /**
     * 메뉴 수정 로직.
     */
    @Transactional
    public void updateSdtMenu(MenuUpdateDto menuUpdateDto) {

        // 먼저, 해당하는 날짜의 메뉴가 있는지 조회.
        SdtMenu sdtMenu = sdtMenuRepository.findByDateTime(menuUpdateDto.getDate()).orElseThrow(
                // 메뉴가 없다면, 예외처리
                () -> new EntityNotFoundException("해당하는 날짜에 메뉴가 없습니다.")
        );

        // 메뉴가 존재한다면 수정.
        sdtMenu.updateMenu(menuUpdateDto);
    }

    /**
     * 오늘 메뉴 존재하는지?
     */
    @Transactional
    public boolean isExistTodayMenu() {
        return sdtMenuRepository.existsByDateTime(LocalDate.now());
    }

    /**
     * 날짜로 해당 메뉴 삭제하기
     */
    @Transactional
    public void deleteSdtMenu(LocalDate date) {
        Optional<SdtMenu> byDateTime = sdtMenuRepository.findByDateTime(date);

        if (byDateTime.isEmpty()) {
            throw new EntityNotFoundException("해당 날짜에 메뉴가 존재하지 않습니다.");
        }

        byDateTime.ifPresent(sdtMenuRepository::delete);
    }

    /**
     * 메뉴 추가 전 특정 날짜의 메뉴가 이미 존재하는지 조사.
     */
    @Transactional(readOnly = true)
    public boolean findSdtMenuByDateTime(LocalDate date) {
        return sdtMenuRepository.existsByDateTime(date);
    }
}
