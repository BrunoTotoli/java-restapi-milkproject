package com.bruno.springmyproject.controller;


import com.bruno.springmyproject.entity.MonthlyMilk;
import com.bruno.springmyproject.service.MonthlyMilkService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@Log4j2
@RequestMapping("v1/monthly")
public class MonthlyMilkController {

    private MonthlyMilkService monthlyMilkService;

    @GetMapping("/{id}")
    public ResponseEntity<MonthlyMilk> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(monthlyMilkService.findById(id));
    }

    @GetMapping("/")
    public ResponseEntity<List<MonthlyMilk>> findAll() {
        return ResponseEntity.ok().body(monthlyMilkService.findAll());
    }

    @GetMapping("/date")
    public ResponseEntity<MonthlyMilk> findByYearAndMonth(@RequestParam Integer month, @RequestParam Integer year) {
        return ResponseEntity.ok().body(monthlyMilkService.findMonthlyMilkByMonthAndYear(month, year));
    }

    @PutMapping("/price")
    public ResponseEntity<MonthlyMilk> saveMonthMilkPrice(@RequestParam Integer month, @RequestParam Integer year, @RequestParam Double price) {
        return ResponseEntity.ok().body(monthlyMilkService.savePriceMonthMilkAndSum(month, year, price));
    }


}
