package com.bruno.springmyproject.service;

import com.bruno.springmyproject.entity.Milk;
import com.bruno.springmyproject.entity.MonthlyMilk;
import com.bruno.springmyproject.repository.MilkRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Service
@Log4j2
public class MonthlyMilkService {

    private MilkRepository milkRepository;

    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public List<Milk> getMilkListByDayYearAndMonth(String date) {
        String[] splitDate = date.split(",");
        StringBuilder formatedString = new StringBuilder();


        for (int i = 0; i < splitDate.length; i++) {

            if (i != splitDate.length - 1) {
                formatedString.append(splitDate[i]).append("/");
            } else {
                formatedString.append(splitDate[i]);
            }
        }

        try {
            Date parsedDate = sdf.parse(formatedString.toString());
            LocalDate localDateTime = parsedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            return milkRepository.findByDate(localDateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return Collections.emptyList();

    }

    public List<Milk> getMilkListByYearAndMonth(int month, int year) {

        MonthlyMilk monthlyMilk = new MonthlyMilk();
        monthlyMilk.setMonth(month);
        monthlyMilk.setYear(year);

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
                                milkListMonthYear.addAll(byDate);
                            }
                        }
                    }
                    milkListMonthYear.addAll(byDate);


                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        monthlyMilk.setMilkList(milkListMonthYear);

        return monthlyMilk.getMilkList();

    }

}
