package com.bruno.springmyproject.service;


import com.bruno.springmyproject.entity.Milk;
import com.bruno.springmyproject.entity.MonthlyMilk;
import com.bruno.springmyproject.repository.MilkRepository;
import com.bruno.springmyproject.repository.MonthlyMilkRepository;
import com.bruno.springmyproject.util.DateUtil;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
@Log4j2
public class MonthlyMilkService {

    private MilkRepository milkRepository;
    private MonthlyMilkRepository monthlyMilkRepository;

    public MonthlyMilk findById(Long id) {
        Optional<MonthlyMilk> optionalMonthlyMilk = monthlyMilkRepository.findById(id);
        return optionalMonthlyMilk.orElseThrow();
    }

    public List<Milk> findMilkListByMonthAndYear(int month, int year) {
        LocalDate localDateStart = LocalDate.of(year, month, 1);
        LocalDate localDateEnd = LocalDate.of(year, month, 31);
        return milkRepository.findByDateBetween(localDateStart, localDateEnd);
    }

    public List<Milk> findMilkListByDayYearAndMonth(String date) {
        try {
            LocalDate localDate = DateUtil.convertStringToLocalDate(date);
            return milkRepository.findByDate(localDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }


}
