package com.bruno.springmyproject.config;

import com.bruno.springmyproject.entity.Milk;
import com.bruno.springmyproject.entity.MonthlyMilk;
import com.bruno.springmyproject.entity.enums.PeriodTime;
import com.bruno.springmyproject.mapper.MilkMapper;
import com.bruno.springmyproject.repository.MilkRepository;
import com.bruno.springmyproject.repository.MonthlyMilkRepository;
import com.bruno.springmyproject.request.MilkPostRequestBody;
import com.bruno.springmyproject.service.MilkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class MilkCommandLineRunner implements CommandLineRunner {

    @Autowired
    MilkService milkService;

    @Autowired
    MonthlyMilkRepository monthlyMilkRepository;


    @Override
    public void run(String... args) throws Exception {
        insertAllData();
    }

    private void insertAllData() {
        List<MilkPostRequestBody> milksToBeSave = createFullDaysMonthWithRandomQuantities(7, 2022, null);
        List<MilkPostRequestBody> milksToBeSave2 = createFullDaysMonthWithRandomQuantities(8, 2022, null);
        for (int i = 0; i < milksToBeSave.size(); i++) {
            milkService.save(milksToBeSave.get(i));
        }
        for (int i = 0; i < milksToBeSave2.size(); i++) {
            milkService.save(milksToBeSave2.get(i));
        }
    }


    private List<MilkPostRequestBody> createFullDaysMonthWithRandomQuantities(int month, int year, MonthlyMilk monthlyMilk) {
        List<MilkPostRequestBody> milkList = new ArrayList<>();
        for (int i = 1; i <= 31; i++) {
            MilkPostRequestBody buildMilk =
                    MilkPostRequestBody.builder()
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
