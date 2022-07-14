package com.bruno.springmyproject.service;


import com.bruno.springmyproject.entity.Milk;
import com.bruno.springmyproject.entity.MonthlyMilk;
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
        return optionalMonthlyMilk.orElseThrow();
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
            List<Milk> milkList = monthlyMilk.getMilkList();
            double quantitySum = milkList.stream()
                    .mapToDouble(Milk::getQuantity)
                    .sum();
            monthlyMilk.setAllMilkQuantityInMonth(quantitySum);
            monthlyMilk.setMilkMonthPrice(priceMonthMilk);
            monthlyMilkRepository.save(monthlyMilk);
            return monthlyMilk;
        }
        return monthlyMilk;


    }
}
