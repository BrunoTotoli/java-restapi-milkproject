package com.bruno.springmyproject.service;

import com.bruno.springmyproject.entity.Milk;
import com.bruno.springmyproject.entity.MonthlyMilk;
import com.bruno.springmyproject.mapper.MilkMapper;
import com.bruno.springmyproject.repository.MilkRepository;
import com.bruno.springmyproject.repository.MonthlyMilkRepository;
import com.bruno.springmyproject.request.MilkPostRequestBody;
import com.bruno.springmyproject.request.MilkPutRequestBody;
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
public class MilkService {

    private MilkRepository milkRepository;
    private MonthlyMilkRepository monthlyMilkRepository;

    public Milk save(MilkPostRequestBody milkPostRequestBody) {

        Milk milkToBeSaved = MilkMapper.INSTANCE.milkPostRequestBodyToMilk(milkPostRequestBody);
        int month = milkToBeSaved.getDate().getMonth().getValue();
        int year = milkToBeSaved.getDate().getYear();

        MonthlyMilk monthly = monthlyMilkRepository.findMonthlyMilkByMilkMonthAndMilkYear(month, year);

        if (monthly != null) {
            milkToBeSaved.setMonthlyMilk(monthly);
        } else {
            monthly = new MonthlyMilk(null, month, year, null, null, null);
            monthlyMilkRepository.save(monthly);
            milkToBeSaved.setMonthlyMilk(monthly);
        }
        return milkRepository.save(milkToBeSaved);
    }

    public Milk findById(Long id) {
        Optional<Milk> optMilk = milkRepository.findById(id);
        if (optMilk.isPresent())
            return optMilk.get();
        throw new IllegalArgumentException();
    }

    public void replace(MilkPutRequestBody milkPutRequestBody) {
        milkRepository.save(MilkMapper.INSTANCE.milkPutRequestBodyToMilk(milkPutRequestBody));
    }

    public void delete(Long id) {
        milkRepository.deleteById(id);
    }

    public List<Milk> findAll() {
        return milkRepository.findAll();
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
