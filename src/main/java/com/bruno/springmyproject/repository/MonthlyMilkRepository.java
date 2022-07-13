package com.bruno.springmyproject.repository;

import com.bruno.springmyproject.entity.MonthlyMilk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonthlyMilkRepository extends JpaRepository<MonthlyMilk, Long> {

    MonthlyMilk findMonthlyMilkByMilkMonthAndMilkYear(Integer milkMonth, Integer milkYear);

}
