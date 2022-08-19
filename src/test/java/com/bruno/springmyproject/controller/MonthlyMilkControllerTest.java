package com.bruno.springmyproject.controller;

import com.bruno.springmyproject.entity.MonthlyMilk;
import com.bruno.springmyproject.service.MonthlyMilkService;
import com.bruno.springmyproject.util.MonthlyMilkCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@DisplayName("MonthlyMilk tests")
class MonthlyMilkControllerTest {

    @Mock
    private MonthlyMilkService monthlyMilkService;

    @InjectMocks
    private MonthlyMilkController monthlyMilkController;

    @BeforeEach
    void setUp() {
        Mockito.when(monthlyMilkService.findById(ArgumentMatchers.anyLong()))
                .thenReturn(MonthlyMilkCreator.createValidMonthlyMilk());
        Mockito.when(monthlyMilkService.findAll())
                .thenReturn(List.of(MonthlyMilkCreator.createValidMonthlyMilk()));
        Mockito.when(monthlyMilkService.findMonthlyMilkByMonthAndYear(ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt()))
                .thenReturn(MonthlyMilkCreator.createValidMonthlyMilk());
        Mockito.when(monthlyMilkService.savePriceMonthMilkAndSum(ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt(), ArgumentMatchers.anyDouble()))
                .thenReturn(MonthlyMilkCreator.createValidSaveMonthlyMilk());
        Mockito.when(monthlyMilkService.updatePriceMonthMilkAndSum(ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt(), ArgumentMatchers.anyDouble()))
                .thenReturn(MonthlyMilkCreator.createValidUpdatedMonthlyMilk());


    }

    @Test
    @DisplayName("findById returns monthly milk when successful")
    void findById_ReturnsMonthlyMilk_WhenSuccessful() {
        MonthlyMilk monthlyMilk = MonthlyMilkCreator.createValidMonthlyMilk();
        MonthlyMilk body = monthlyMilkController.findById(1L).getBody();

        Assertions.assertThat(body)
                .isNotNull()
                .isEqualTo(monthlyMilk);

        Assertions.assertThat(body.getId())
                .isNotNull()
                .isEqualTo(monthlyMilk.getId());
    }

    @Test
    @DisplayName("findAll returns list of monthly milk when successful")
    void findAll_ReturnsMonthlyMilk_WhenSuccessful() {
        MonthlyMilk monthlyMilk = MonthlyMilkCreator.createValidMonthlyMilk();
        List<MonthlyMilk> body = monthlyMilkController.findAll().getBody();

        Assertions.assertThat(body)
                .isNotNull()
                .isNotEmpty()
                .containsOnly(monthlyMilk)
                .hasSize(1);
    }

    @Test
    @DisplayName("findByYearAndMonth returns monthly milk when successful")
    void findByYearAndMonth_ReturnsMonthlyMilk_WhenSuccessful() {
        MonthlyMilk monthlyMilk = MonthlyMilkCreator.createValidMonthlyMilk();
        MonthlyMilk body = monthlyMilkController.findByYearAndMonth(12, 2022).getBody();

        Assertions.assertThat(body)
                .isNotNull()
                .isEqualTo(monthlyMilk);

        Assertions.assertThat(body.getId())
                .isNotNull()
                .isEqualTo(monthlyMilk.getId());
    }

    @Test
    @DisplayName("saveMonthMilkPrice add monthMilkPrice returns monthly milk when successful")
    void saveMonthMilkPrice_ReturnsMonthlyMilk_WhenSuccessful() {
        MonthlyMilk monthlyMilk = MonthlyMilkCreator.createValidSaveMonthlyMilk();
        MonthlyMilk body = monthlyMilkController.saveMonthMilkPrice(12, 2022, 2.43D).getBody();

        Assertions.assertThat(body)
                .isNotNull()
                .isEqualTo(monthlyMilk);

        Assertions.assertThat(body.getId())
                .isNotNull()
                .isEqualTo(monthlyMilk.getId());
    }

    @Test
    @DisplayName("updateMonthMilkPrice update monthlyMilkPrice returns monthly milk when successful")
    void updateMonthMilkPrice_ReturnsMonthlyMilk_WhenSuccessful() {
        MonthlyMilk monthlyMilk = MonthlyMilkCreator.createValidUpdatedMonthlyMilk();
        MonthlyMilk body = monthlyMilkController.updateMonthMilkPrice(12, 2022, 2.33D).getBody();

        Assertions.assertThat(body)
                .isNotNull()
                .isEqualTo(monthlyMilk);

        Assertions.assertThat(body.getId())
                .isNotNull()
                .isEqualTo(monthlyMilk.getId());

    }
}