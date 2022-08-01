package com.bruno.springmyproject.config;

import com.bruno.springmyproject.entity.Milk;
import com.bruno.springmyproject.entity.MonthlyMilk;
import com.bruno.springmyproject.entity.enums.PeriodTime;
import com.bruno.springmyproject.repository.MilkRepository;
import com.bruno.springmyproject.repository.MonthlyMilkRepository;
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

    @Autowired
    MonthlyMilkRepository monthlyMilkRepository;


    @Override
    public void run(String... args) throws Exception {
//        insertAllData();
    }

    private void insertAllData() {
        MonthlyMilk monthlyMilk7 = new MonthlyMilk(null, 7, 2022, 2D, null, null, null);
        MonthlyMilk monthlyMilk8 = new MonthlyMilk(null, 8, 2022, 3D, null, null, null);
        monthlyMilkRepository.save(monthlyMilk7);
        monthlyMilkRepository.save(monthlyMilk8);
        List<Milk> milksToBeSave = createFullDaysMonthWithRandomQuantities(7, 2022, monthlyMilk7);
        List<Milk> milksToBeSave2 = createFullDaysMonthWithRandomQuantities(8, 2022, monthlyMilk8);
        milkRepository.saveAll(milksToBeSave);
        milkRepository.saveAll(milksToBeSave2);
    }


    private List<Milk> createFullDaysMonthWithRandomQuantities(int month, int year, MonthlyMilk monthlyMilk) {
        List<Milk> milkList = new ArrayList<>();
        for (int i = 1; i <= 31; i++) {
            Milk buildMilk =
                    Milk.builder()
                            .quantity(getRandomNumber(80, 140)
                                    .doubleValue())
                            .periodTime(PeriodTime.MANHA)
                            .date(LocalDate.of(year, month, i))
                            .monthlyMilk(monthlyMilk)
                            .build();
            milkList.add(buildMilk);
        }
        return milkList;
    }

    private Integer getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
