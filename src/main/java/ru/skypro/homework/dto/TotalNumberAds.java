package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TotalNumberAds {

    /**
     * Всего объявлений
     */
    private Integer count;

    /**
     * Все найденные объявления
     */
    private List<AdsDto> results;
}
