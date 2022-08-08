package com.bruno.springmyproject.util;

import com.bruno.springmyproject.entity.Milk;
import com.bruno.springmyproject.entity.enums.PeriodTime;

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


}
