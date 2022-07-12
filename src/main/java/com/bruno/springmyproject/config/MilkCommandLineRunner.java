package com.bruno.springmyproject.config;

import com.bruno.springmyproject.entity.Milk;
import com.bruno.springmyproject.entity.enums.PeriodTime;
import com.bruno.springmyproject.repository.MilkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class MilkCommandLineRunner implements CommandLineRunner {

    @Autowired
    MilkRepository milkRepository;


    @Override
    public void run(String... args) throws Exception {
        List<Milk> milksToBeSave = createFullDaysMonthWithRandomQuantities(7, 2022);
        milkRepository.saveAll(milksToBeSave);
    }

    private List<Milk> createFullDaysMonthWithRandomQuantities(int month, int year) {
        List<Milk> milkList = new ArrayList<>();
        for (int i = 1; i <= 31; i++) {
            Milk buildMilk =
                    Milk.builder()
                            .quantity(getRandomNumber(80, 140)
                                    .doubleValue())
                            .periodTime(PeriodTime.MANHA)
                            .date(LocalDate.of(year, month, i))
                            .build();
            milkList.add(buildMilk);
        }
        return milkList;
    }

    private Integer getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
