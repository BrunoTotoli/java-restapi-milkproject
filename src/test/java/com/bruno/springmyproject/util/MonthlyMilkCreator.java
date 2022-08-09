package com.bruno.springmyproject.util;

import com.bruno.springmyproject.entity.Milk;
import com.bruno.springmyproject.entity.MonthlyMilk;
import com.bruno.springmyproject.entity.enums.PeriodTime;

import java.time.LocalDate;
import java.util.Arrays;

public class MonthlyMilkCreator {

    public static MonthlyMilk createValidMonthlyMilk() {
        return MonthlyMilk.builder()
                .id(1L)
                .milkMonthPrice(4.20D)
                .milkMonth(12)
                .milkYear(2022)
                .milkList(null)
                .allMilkQuantityInMonth(null)
                .allMilkQuantityInMonthPriceValue(null)
                .build();
    }

    public static MonthlyMilk createInvalidMonthlyMilk() {
        return MonthlyMilk.builder()
                .id(1L)
                .milkMonthPrice(null)
                .milkMonth(12)
                .milkYear(2022)
                .milkList(null)
                .allMilkQuantityInMonth(null)
                .allMilkQuantityInMonthPriceValue(null)
                .build();
    }

    public static MonthlyMilk createValidWithMonthPriceMonthlyMilk() {
        return MonthlyMilk.builder()
                .id(1L)
                .milkMonthPrice(2.40D)
                .milkMonth(12)
                .milkYear(2022)
                .milkList(null)
                .allMilkQuantityInMonth(140D)
                .allMilkQuantityInMonthPriceValue(288D)
                .build();
    }


    public static MonthlyMilk createValidFindMonthlyMilkByMilkMonthAndMilkYear() {
        return MonthlyMilk.builder()
                .id(1L)
                .milkMonthPrice(null)
                .milkMonth(12)
                .milkYear(2022)
                .milkList(Arrays.asList(
                        new Milk(null, 100D, PeriodTime.MANHA, LocalDate.now(), null),
                        new Milk(null, 100D, PeriodTime.MANHA, LocalDate.now(), null),
                        new Milk(null, 100D, PeriodTime.MANHA, LocalDate.now(), null),
                        new Milk(null, 100D, PeriodTime.MANHA, LocalDate.now(), null),
                        new Milk(null, 100D, PeriodTime.MANHA, LocalDate.now(), null)))
                .allMilkQuantityInMonth(500D)
                .allMilkQuantityInMonthPriceValue(null)
                .build();
    }

    public static MonthlyMilk createValidSaveMonthlyMilk() {
        return MonthlyMilk.builder()
                .id(1L)
                .milkMonthPrice(2.20D)
                .milkMonth(12)
                .milkYear(2022)
                .milkList(null)
                .allMilkQuantityInMonth(500D)
                .allMilkQuantityInMonthPriceValue(1100D)
                .build();
    }

    public static MonthlyMilk createValidUpdatedMonthlyMilk() {
        return MonthlyMilk.builder()
                .id(1L)
                .milkMonthPrice(2.80D)
                .milkMonth(12)
                .milkYear(2022)
                .milkList(null)
                .allMilkQuantityInMonth(500D)
                .allMilkQuantityInMonthPriceValue(1400D)
                .build();
    }
}


