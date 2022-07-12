package com.bruno.springmyproject.repository;

import com.bruno.springmyproject.entity.Milk;
import com.bruno.springmyproject.entity.enums.PeriodTime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;


public interface MilkRepository extends JpaRepository<Milk, Long> {

    List<Milk> findByDate(LocalDate dateTime);

    List<Milk> findByPeriodTime(PeriodTime periodTime);

}
