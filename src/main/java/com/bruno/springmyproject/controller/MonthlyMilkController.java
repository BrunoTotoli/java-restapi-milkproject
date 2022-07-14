package com.bruno.springmyproject.controller;


import com.bruno.springmyproject.entity.Milk;
import com.bruno.springmyproject.entity.MonthlyMilk;
import com.bruno.springmyproject.service.MonthlyMilkService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@Log4j2
@RequestMapping("v1/monthly")
public class MonthlyMilkController {

    private MonthlyMilkService monthlyMilkService;

    @GetMapping("/{month}/{year}")
    public ResponseEntity<List<Milk>> findByYearAndMonth(@PathVariable Integer month, @PathVariable Integer year) {
        return ResponseEntity.ok().body(monthlyMilkService.findMilkListByMonthAndYear(month, year));
    }

    @GetMapping("/string/{date}")
    public ResponseEntity<List<Milk>> findByDayYearAndMonth(@PathVariable String date) {
        return ResponseEntity.ok().body(monthlyMilkService.findMilkListByDayYearAndMonth(date));
    }

    @GetMapping("list/{id}")
    public ResponseEntity<MonthlyMilk> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(monthlyMilkService.findById(id));
    }

}
