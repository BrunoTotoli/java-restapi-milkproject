package com.bruno.springmyproject.service;

import com.bruno.springmyproject.entity.Milk;
import com.bruno.springmyproject.mapper.MilkMapper;
import com.bruno.springmyproject.repository.MilkRepository;
import com.bruno.springmyproject.repository.MonthlyMilkRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static com.bruno.springmyproject.util.MilkCreator.createValidMilkPostRequestBody;
import static com.bruno.springmyproject.util.MilkCreator.createValidMilkWithMonthlyMilk;
import static com.bruno.springmyproject.util.MonthlyMilkCreator.createValidMonthlyMilk;
import static com.bruno.springmyproject.util.MonthlyMilkCreator.createValidWithMonthPriceMonthlyMilk;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("MilkService tests")
class MilkServiceTest {

    @Mock
    private MilkRepository milkRepository;
    @Mock
    private MonthlyMilkRepository monthlyMilkRepository;
    @InjectMocks
    private MilkService milkService;


    @BeforeEach
    void setUp() {
        Mockito.when(monthlyMilkRepository.findMonthlyMilkByMilkMonthAndMilkYear(ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt()))
                .thenReturn(createValidMonthlyMilk());
        Mockito.when(monthlyMilkRepository.save(ArgumentMatchers.any()))
                .thenReturn(createValidMonthlyMilk());
        Mockito.when(milkRepository.save(ArgumentMatchers.any()))
                .thenReturn(createValidMilkWithMonthlyMilk());
    }

    @Test
    @DisplayName("save milk and link a monthly milk created returns milk when successful")
    void saveMilk_ReturnsMilkLinkedAMonthlyMilkCreated_WhenSuccessful() {
        Milk unsavedMilk = MilkMapper.INSTANCE
                .milkPostRequestBodyToMilk(createValidMilkPostRequestBody());
        Milk savedMilk = milkService.save(createValidMilkPostRequestBody());

        Assertions.assertThat(savedMilk)
                .isNotNull();
        Assertions.assertThat(savedMilk.getId())
                .isNotNull()
                .isNotEqualTo(unsavedMilk.getId());
        Assertions.assertThat(savedMilk.getMonthlyMilk())
                .isNotNull()
                .isNotEqualTo(unsavedMilk.getMonthlyMilk());
        Assertions.assertThat(savedMilk.getMonthlyMilk().getAllMilkQuantityInMonth())
                .isNotNull().
                isEqualTo(unsavedMilk.getQuantity());


    }

    @Test
    @DisplayName("save milk and create a monthly milk returns milk linked a monthly milk when successful ")
    void saveMilk_ReturnsMilkAndCreateAMonthlyMilk_WhenSuccessful() {
        Mockito.when(milkRepository.save(ArgumentMatchers.any()))
                .thenReturn(createValidMilkWithMonthlyMilk());
        Mockito.when(monthlyMilkRepository.findMonthlyMilkByMilkMonthAndMilkYear(ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt()))
                .thenReturn(null);


        Milk milkToBeSaved = milkService.save(createValidMilkPostRequestBody());

        Assertions.assertThat(milkToBeSaved)
                .isNotNull();
        Assertions.assertThat(milkToBeSaved.getMonthlyMilk())
                .isNotNull();
        Assertions.assertThat(milkToBeSaved.getMonthlyMilk().getMilkMonth())
                .isEqualTo(milkToBeSaved.getDate().getMonth().getValue());
        Assertions.assertThat(milkToBeSaved.getMonthlyMilk().getMilkYear())
                .isEqualTo(milkToBeSaved.getDate().getYear());

    }

    @Test
    @DisplayName("save milk and add value to allMilkMonthQuantity when successful")
    void saveMilk_ReturnsMilkAndAddValueToAllMilkMonthQuantity_WhenSuccessful() {
        Mockito.when(milkRepository.save(ArgumentMatchers.any()))
                .thenReturn(createValidMilkWithMonthlyMilk());
        Mockito.when(monthlyMilkRepository.findMonthlyMilkByMilkMonthAndMilkYear(ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt()))
                .thenReturn(createValidWithMonthPriceMonthlyMilk());

        Milk milkToBeSaved = milkService.save(createValidMilkPostRequestBody());

        Assertions.assertThat(milkToBeSaved)
                .isNotNull();
        Assertions.assertThat(milkToBeSaved.getMonthlyMilk())
                .isNotNull();
        Assertions.assertThat(milkToBeSaved.getMonthlyMilk().getAllMilkQuantityInMonth())
                .isNotNull();

    }
}