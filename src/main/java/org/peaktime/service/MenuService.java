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


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MenuService {

    private final SdtMenuRepository sdtMenuRepository;


    public List<SdtMenu> getAllSdtMenusForCurrentWeek(LocalDate today) {
        return sdtMenuRepository.findAllByWeekEquals(today.get(WeekFields.ISO.weekOfYear()));
    }

    public String getTodaySdtMenuName() {
        return sdtMenuRepository.findSdtMenuByDate(LocalDate.now())
                .map(SdtMenu::getMenu)
                .orElseGet(() -> "");
    }


    public Double getTodaySdtMenuScore() {
        return sdtMenuRepository.findByDate(LocalDate.now())
                .map(SdtMenu::getScore)
                .orElseGet(() -> 0.0);
    }


    @Transactional
    public void updateTodaySdtMenuScore(Double score) {

        SdtMenu sdtMenu = sdtMenuRepository.findByDate(LocalDate.now())
                .orElseThrow(() -> new EntityNotFoundException("해당 날짜의 메뉴가 존재하지 않습니다."));

        // 스코어 누적 갱신.
        sdtMenu.updateScore(score);
    }


    public void createSdtMenu(MenuCreateDto menuCreateDto) {
        SdtMenu sdtMenu = SdtMenu.createSdtMenu(menuCreateDto);
        validateNoMenuExistsOnDate(sdtMenu.getDate());
        sdtMenuRepository.save(sdtMenu);
    }


    public Integer updateSdtMenuByDate(MenuUpdateDto menuUpdateDto) {
        SdtMenu sdtMenu = sdtMenuRepository.findByDate(menuUpdateDto.getDate())
                .orElseThrow(() -> new EntityNotFoundException("해당 날짜에 메뉴가 존재하지 않습니다."));

        // 메뉴가 존재한다면 수정.
        return sdtMenu.updateMenu(menuUpdateDto);
    }

    public SdtMenu getSdtMenuByDate(LocalDate date) {
        return sdtMenuRepository.findByDate(date).orElseThrow(
                () -> new EntityNotFoundException("해당 날짜에 메뉴가 존재하지 않습니다."));
    }

    public void deleteSdtMenuByDate(LocalDate date) {
        sdtMenuRepository.delete(getSdtMenuByDate(date));
    }

    public void validateNoMenuExistsOnDate(LocalDate date) throws IllegalStateException {
        if (sdtMenuRepository.existsByDate(date))
            throw new IllegalStateException("해당 날짜에는 이미 메뉴가 있습니다.");
    }



}
