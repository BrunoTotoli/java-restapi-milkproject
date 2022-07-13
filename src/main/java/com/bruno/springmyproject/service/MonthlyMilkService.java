package com.bruno.springmyproject.service;

import com.bruno.springmyproject.entity.Milk;
import com.bruno.springmyproject.entity.MonthlyMilk;
import com.bruno.springmyproject.repository.MilkRepository;
import com.bruno.springmyproject.repository.MonthlyMilkRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
@Log4j2
public class MonthlyMilkService {

    private MilkRepository milkRepository;

    private MonthlyMilkRepository monthlyMilkRepository;

    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public List<Milk> getMilkListByYearAndMonth(int month, int year) {
        MonthlyMilk monthlyMilk = new MonthlyMilk();
        monthlyMilk.setMilkMonth(month);
        monthlyMilk.setMilkYear(year);

        if (monthlyMilkRepository.findMonthlyMilkByMilkMonthAndMilkYear(month, year) != null) {
            monthlyMilk.setId(monthlyMilkRepository.findMonthlyMilkByMilkMonthAndMilkYear(month, year).getId());
        }
        MonthlyMilk savedMonthlyMilk = monthlyMilkRepository.save(monthlyMilk);

        List<Milk> milkListMonthYear = new ArrayList<>();

        for (int i = 1; i <= 31; i++) {
            try {
                Date parse = sdf.parse(i + "/" + month + "/" + year);
                LocalDate localDateTime = parse.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                log.info(localDateTime);
                List<Milk> byDate = milkRepository.findByDate(localDateTime);
                if (!byDate.isEmpty()) {
                    if (i == 31) {
                        for (int y = 0; i < byDate.size(); y++) {
                            if (byDate.get(y).getDate().getMonth().getValue() == month) {
                                filterList(savedMonthlyMilk, milkListMonthYear, byDate);
                            }
                        }
                    }
                    filterList(savedMonthlyMilk, milkListMonthYear, byDate);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        milkRepository.saveAll(milkListMonthYear);
        return milkListMonthYear;

    }

    public MonthlyMilk findById(Long id) {
        Optional<MonthlyMilk> optionalMonthlyMilk = monthlyMilkRepository.findById(id);
        return optionalMonthlyMilk.orElseThrow();
    }


    private void filterList(MonthlyMilk savedMonthlyMilk, List<Milk> milkListMonthYear, List<Milk> byDate) {
        List<Milk> filterList = byDate.stream().filter(p -> p.getMonthlyMilk() == null).toList();
        filterList.forEach(p -> p.setMonthlyMilk(savedMonthlyMilk));
        if (filterList.isEmpty()) {
            milkListMonthYear.addAll(byDate);
        } else {
            milkListMonthYear.addAll(filterList);
        }
    }

}
