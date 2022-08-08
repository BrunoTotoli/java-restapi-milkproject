package com.bruno.springmyproject.service;

import com.bruno.springmyproject.entity.MonthlyMilk;
import com.bruno.springmyproject.exception.MonthlyMilkNotFoundException;
import com.bruno.springmyproject.repository.MonthlyMilkRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static com.bruno.springmyproject.util.MonthlyMilkCreator.*;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("MonthlyMilkService Tests")
class MonthlyMilkServiceTest {

    @Mock
    private MonthlyMilkRepository monthlyMilkRepository;
    @InjectMocks
    private MonthlyMilkService monthlyMilkService;

    @BeforeEach
    void setUp() {

        BDDMockito.when(monthlyMilkRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(createValidMonthlyMilk()));
        BDDMockito.when(monthlyMilkRepository.findAll())
                .thenReturn(List.of(createValidMonthlyMilk()));
        BDDMockito.when(monthlyMilkRepository.findMonthlyMilkByMilkMonthAndMilkYear(ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt()))
                .thenReturn(createValidMonthlyMilk());
    }

    @Test
    @DisplayName("FindById returns monthlyMilk when successful")
    void findById_ReturnMonthlyMilk_WhenSuccessful() {
        Long expectedId = createValidMonthlyMilk().getId();
        MonthlyMilk monthlyMilk = monthlyMilkService.findById(1L);

        Assertions.assertThat(monthlyMilk).isNotNull();
        Assertions.assertThat(monthlyMilk.getId())
                .isNotNull()
                .isEqualTo(expectedId);
    }

    @Test
    @DisplayName("FindById throws MonthlyMilkNotFoundException when monthlyMilk not found")
    void findById_ThrowsMonthlyMilkNotFoundException_WhenSuccessful() {
        BDDMockito.when(monthlyMilkRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.empty());

        Assertions.assertThatExceptionOfType(MonthlyMilkNotFoundException.class)
                .isThrownBy(() -> monthlyMilkService.findById(1L));
    }

    @Test
    @DisplayName("FindAll returns list of monthlyMilk when successful")
    void findAll_ReturnsMonthlyMilkList_WhenSuccessful() {
        MonthlyMilk monthlyMilk = createValidMonthlyMilk();
        List<MonthlyMilk> monthlyMilkList = monthlyMilkService.findAll();

        Assertions.assertThat(monthlyMilkList)
                .isNotEmpty()
                .isNotNull()
                .contains(monthlyMilk)
                .hasSize(1);
        Assertions.assertThat(monthlyMilkList.get(0).getId())
                .isEqualTo(monthlyMilk.getId());

    }

    @Test
    @DisplayName("savePriceMonthMilkAndSum returns monthlyMilk with price when successful")
    void savePriceMonthMilkAndSum_ReturnsMonthlyMilkWithPrice_WhenSuccessful() {
        BDDMockito.when(monthlyMilkService.savePriceMonthMilkAndSum(12, 2022, 2.40D))
                .thenReturn(createValidWithMonthPriceMonthlyMilk());

        MonthlyMilk oldMonthlyMilk = createValidMonthlyMilk();
        MonthlyMilk monthlyMilkWithPriceAndAllPrice = monthlyMilkService.savePriceMonthMilkAndSum(12, 2022, 2.40D);

        Assertions.assertThat(monthlyMilkWithPriceAndAllPrice)
                .isNotNull();

        Assertions.assertThat(monthlyMilkWithPriceAndAllPrice.getMilkMonthPrice())
                .isNotNull()
                .isNotEqualTo(oldMonthlyMilk.getMilkMonthPrice())
                .isEqualTo(createValidWithMonthPriceMonthlyMilk().getMilkMonthPrice());

        Assertions.assertThat(monthlyMilkWithPriceAndAllPrice.getAllMilkQuantityInMonthPriceValue())
                .isNotNull()
                .isNotEqualTo(oldMonthlyMilk.getAllMilkQuantityInMonthPriceValue())
                .isEqualTo(createValidWithMonthPriceMonthlyMilk().getAllMilkQuantityInMonthPriceValue());
    }


    @Test
    @DisplayName("updatePriceMonthMilkAndSum returns monthlyMilk with updated price when successful")
    void updatePriceMonthMilkAndSum_ReturnsMonthlyMilkWithPrice_WhenSuccessful() {
        BDDMockito.when(monthlyMilkRepository.findMonthlyMilkByMilkMonthAndMilkYear(ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt()))
                .thenReturn(createValidWithMonthPriceMonthlyMilk());

        BDDMockito.when(monthlyMilkService.updatePriceMonthMilkAndSum(12, 2022, 2.80D))
                .thenReturn(createValidUpdateWithMonthPriceMonthlyMilk());

        MonthlyMilk oldMonthlyMilk = createValidWithMonthPriceMonthlyMilk();
        MonthlyMilk updatedMonthlyMilk = monthlyMilkService.updatePriceMonthMilkAndSum(12, 2022, 2.80D);

        Assertions.assertThat(updatedMonthlyMilk)
                .isNotNull();

        Assertions.assertThat(updatedMonthlyMilk.getMilkMonthPrice())
                .isNotEqualTo(oldMonthlyMilk.getMilkMonthPrice())
                .isEqualTo(2.80D);
    }

    @Test
    @DisplayName("findMonthlyMilkByMonthAndYear returns monthly milk when successful")
    void findMonthlyMilkByMonthAndYear_ReturnsMonthlyMilk_WhenSuccessful() {
        MonthlyMilk monthlyMilk = monthlyMilkService.findMonthlyMilkByMonthAndYear(12, 2022);

        Assertions.assertThat(monthlyMilk).isNotNull();
        Assertions.assertThat(monthlyMilk.getMilkMonth())
                .isNotNull()
                .isEqualTo(12);
        Assertions.assertThat(monthlyMilk.getMilkYear())
                .isNotNull()
                .isEqualTo(2022);
    }

    @Test
    @DisplayName("updatePriceMonthMilkAndSum throws IllegalStateException when milkMonthPrice is null")
    void updatePriceMonthMilkAndSum_throwsIllegalStateException_WhenMilkMonthPriceIsNull() {
        BDDMockito.when(monthlyMilkRepository.findMonthlyMilkByMilkMonthAndMilkYear(ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt()))
                .thenReturn(createInvalidMonthlyMilk());

        Assertions.assertThatExceptionOfType(IllegalStateException.class)
                .isThrownBy(() -> monthlyMilkService.updatePriceMonthMilkAndSum(1, 1, null));
    }

}
