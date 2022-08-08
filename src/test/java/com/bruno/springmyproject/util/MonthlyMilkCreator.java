package com.bruno.springmyproject.util;

import com.bruno.springmyproject.entity.MonthlyMilk;

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

    public static MonthlyMilk createValidUpdateWithMonthPriceMonthlyMilk() {
        return MonthlyMilk.builder()
                .id(1L)
                .milkMonthPrice(2.80D)
                .milkMonth(12)
                .milkYear(2022)
                .milkList(null)
                .allMilkQuantityInMonth(120D)
                .allMilkQuantityInMonthPriceValue(336D)
                .build();
    }
}

