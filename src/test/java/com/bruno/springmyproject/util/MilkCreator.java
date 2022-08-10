package com.bruno.springmyproject.util;

import com.bruno.springmyproject.entity.Milk;
import com.bruno.springmyproject.entity.MonthlyMilk;
import com.bruno.springmyproject.entity.enums.PeriodTime;
import com.bruno.springmyproject.request.MilkPostRequestBody;

import java.time.LocalDate;

public class MilkCreator {

    public static Milk createValidMilk() {
        return Milk.builder()
                .monthlyMilk(null)
                .date(LocalDate.now())
                .periodTime(PeriodTime.MANHA)
                .quantity(120D)
                .id(1L)
                .build();
    }

    public static Milk createValidMilkWithMonthlyMilk() {
        MonthlyMilk monthlyMilk = new MonthlyMilk();
        monthlyMilk.setMilkMonth(12);
        monthlyMilk.setMilkYear(2022);
        monthlyMilk.setAllMilkQuantityInMonth(260D);

        return Milk.builder()
                .monthlyMilk(monthlyMilk)
                .date(LocalDate.of(2022,12,10))
                .periodTime(PeriodTime.MANHA)
                .quantity(120D)
                .id(1L)
                .build();
    }

    public static MilkPostRequestBody createValidMilkPostRequestBody() {
        return MilkPostRequestBody.builder()
                .date(LocalDate.now())
                .periodTime(PeriodTime.MANHA)
                .quantity(120D)
                .build();
    }


}
