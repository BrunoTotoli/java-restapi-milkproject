package com.bruno.springmyproject.service;


import com.bruno.springmyproject.entity.MonthlyMilk;
import com.bruno.springmyproject.exception.MonthlyMilkNotFoundException;
import com.bruno.springmyproject.repository.MonthlyMilkRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
@Log4j2
public class MonthlyMilkService {

    private MonthlyMilkRepository monthlyMilkRepository;

    public MonthlyMilk findById(Long id) {
        Optional<MonthlyMilk> optionalMonthlyMilk = monthlyMilkRepository.findById(id);
        return optionalMonthlyMilk.orElseThrow(() -> new MonthlyMilkNotFoundException("This id not found"));
    }

    public List<MonthlyMilk> findAll() {
        return monthlyMilkRepository.findAll();
    }

    public MonthlyMilk findMonthlyMilkByMonthAndYear(int month, int year) {
        return monthlyMilkRepository.findMonthlyMilkByMilkMonthAndMilkYear(month, year);
    }

    public MonthlyMilk savePriceMonthMilkAndSum(int month, int year, Double priceMonthMilk) {
        MonthlyMilk monthlyMilk = monthlyMilkRepository.findMonthlyMilkByMilkMonthAndMilkYear(month, year);
        if (monthlyMilk.getMilkMonthPrice() == null) {
            monthlyMilk.setMilkMonthPrice(priceMonthMilk);
            monthlyMilk.setAllMilkQuantityInMonthPriceValue(monthlyMilk.getMilkMonthPrice() * monthlyMilk.getAllMilkQuantityInMonth());
            return monthlyMilkRepository.save(monthlyMilk);
        }
        return monthlyMilk;
    }

    public MonthlyMilk updatePriceMonthMilkAndSum(int month, int year, Double priceMonthMilk) {
        MonthlyMilk monthlyMilk = monthlyMilkRepository.findMonthlyMilkByMilkMonthAndMilkYear(month, year);
        if (monthlyMilk.getMilkMonthPrice() != null) {
            monthlyMilk.setMilkMonthPrice(priceMonthMilk);
            monthlyMilk.setAllMilkQuantityInMonthPriceValue(monthlyMilk.getMilkMonthPrice() * monthlyMilk.getAllMilkQuantityInMonth());
            return monthlyMilkRepository.save(monthlyMilk);
        }
        throw new IllegalStateException("MilkMonthPrice is null");
    }
}
