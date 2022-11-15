package com.bruno.springmyproject.service;

import com.bruno.springmyproject.entity.Milk;
import com.bruno.springmyproject.exception.MilkNotFoundException;
import com.bruno.springmyproject.mapper.MilkMapper;
import com.bruno.springmyproject.repository.MilkRepository;
import com.bruno.springmyproject.repository.MonthlyMilkRepository;
import com.bruno.springmyproject.util.MilkCreator;
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

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.bruno.springmyproject.util.MilkCreator.*;
import static com.bruno.springmyproject.util.MonthlyMilkCreator.createValidMonthlyMilk;
import static com.bruno.springmyproject.util.MonthlyMilkCreator.createValidWithMonthPriceMonthlyMilk;

@ExtendWith(SpringExtension.class)
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
        Mockito.when(milkRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(createValidMilk()));
        Mockito.when(milkRepository.findAll())
                .thenReturn(List.of(createValidMilk()));
        Mockito.doNothing()
                .when(milkRepository)
                .delete(ArgumentMatchers.any());
        Mockito.when(milkRepository.findByDateBetween(ArgumentMatchers.any(), ArgumentMatchers.any()))
                .thenReturn(List.of(createValidMilk()));
        Mockito.when(milkRepository.findByDate(ArgumentMatchers.any()))
                .thenReturn(List.of(createValidMilk()));
    }

    @Test
    @DisplayName("save milk and link a monthly milk created returns milk when successful")
    void saveMilk_ReturnsMilkLinkedAMonthlyMilkCreated_WhenSuccessful() {

        Mockito.when(monthlyMilkRepository.findMonthlyMilkByMilkMonthAndMilkYear(ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt()))
                .thenReturn(null);

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
                .isNotNull();


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

    @Test
    @DisplayName("findByIrOrThrowMilkNotFoundException returns milk when successful")
    void findByIrOrThrowMilkNotFoundException_ReturnsMilk_WhenSuccessful() {
        Long id = createValidMilk().getId();
        Milk milk = milkService.findByIdOrElseThrowMilkNotFoundException(1L);

        Assertions.assertThat(milk)
                .isNotNull();
        Assertions.assertThat(milk.getId())
                .isNotNull()
                .isEqualTo(id);
    }

    @Test
    @DisplayName("findByIrOrThrowMilkNotFoundException throws MilkNotFoundException when milk not found")
    void findByIrOrThrowMilkNotFoundException_ThrowMilkNotFoundException_WhenMilkNotFound() {
        Mockito.when(milkRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.empty());

        Assertions.assertThatExceptionOfType(MilkNotFoundException.class)
                .isThrownBy(() -> milkService.findByIdOrElseThrowMilkNotFoundException(1L));
    }

    @Test
    @DisplayName("findAll returns milk list when successful")
    void findAll_returnsMilkList_whenSuccessful() {
        Milk milk = createValidMilk();
        List<Milk> milkList = milkService.findAll();

        Assertions.assertThat(milkList)
                .isNotNull()
                .isNotEmpty()
                .containsOnly(milk);

        Assertions.assertThat(milkList.get(0).getId())
                .isNotNull()
                .isEqualTo(milk.getId());
    }

    @Test
    @DisplayName("replace update milk when successful")
    void replace_UpdateMilk_WhenSuccessful() {
        Milk milkToUpdate = createValidMilk();
        Milk milkUpdated = milkService.replace(MilkCreator.createValidMilkPutRequestBody());

        Assertions.assertThat(milkUpdated).isNotNull()
                .isNotEqualTo(milkToUpdate);

        Assertions.assertThat(milkUpdated.getId())
                .isNotNull()
                .isEqualTo(milkToUpdate.getId());

        Assertions.assertThat(milkUpdated.getQuantity())
                .isNotNull()
                .isEqualTo(milkToUpdate.getQuantity());

    }

    @Test
    @DisplayName("delete remove milk when successful")
    void delete_RemoveMilk_WhenSuccessful() {
        Assertions.assertThatCode(() -> milkService.delete(1L))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("findMilkListByMonthAndYear returns milk list when successful ")
    void findMilkListByMonthAndYear_ReturnsMilkList_WhenSuccessful() {
        Milk milk = createValidMilk();
        List<Milk> milkList = milkService.findMilkListByMonthAndYear(12, 2022);

        Assertions.assertThat(milkList)
                .isNotNull()
                .isNotEmpty()
                .containsOnly(milk);

        Assertions.assertThat(milkList.get(0).getId())
                .isNotNull()
                .isEqualTo(milk.getId());
    }

    @Test
    @DisplayName("findMilkListByDayYearAndMonthUsingString returns milk list when successful")
    void findMilkListByDayYearAndMonth_ReturnsMilkList_WhenSuccessful() {
        Milk milk = createValidMilk();
        List<Milk> milkList = milkService.findMilkListByDayYearAndMonth("10102022");

        Assertions.assertThat(milkList)
                .isNotNull()
                .isNotEmpty()
                .containsOnly(milk);
        Assertions.assertThat(milkList.get(0).getId())
                .isNotNull();

        Assertions.assertThat(milkList.get(0).getDate())
                .isNotNull()
                .isEqualTo(milk.getDate());
    }

    @Test
    @DisplayName("findMilkListByDayYearAndMonthUsingString returns empty milk list when successful")
    void findMilkListByDayYearAndMonth_ReturnsEmptyMilkList_WhenSuccessful() {
        Mockito.when(milkRepository.findByDate(ArgumentMatchers.any()))
                .thenReturn(Collections.emptyList());

        Milk milk = createValidMilk();
        List<Milk> milkList = milkService.findMilkListByDayYearAndMonth("10102022");

        Assertions.assertThat(milkList).isEmpty();
    }
}