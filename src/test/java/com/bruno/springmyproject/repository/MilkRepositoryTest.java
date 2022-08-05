package com.bruno.springmyproject.repository;

import com.bruno.springmyproject.entity.Milk;
import com.bruno.springmyproject.entity.enums.PeriodTime;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@DataJpaTest
@DisplayName("Tests for milk repository")
class MilkRepositoryTest {


    @Autowired
    private MilkRepository milkRepository;

    @Test
    @DisplayName("Save persists milk when successful")
    void save_PersistMilk_WhenSuccessful() {
        Milk milkToBeSaved = createValidMilk();
        Milk milkSaved = milkRepository.save(milkToBeSaved);

        Assertions.assertThat(milkSaved).isNotNull();
        Assertions.assertThat(milkSaved.getId()).isNotNull();
        Assertions.assertThat(milkSaved.getDate()).isEqualTo(milkToBeSaved.getDate());
        Assertions.assertThat(milkSaved.getPeriodTime()).isEqualTo(milkToBeSaved.getPeriodTime());
        Assertions.assertThat(milkSaved.getQuantity()).isEqualTo(milkToBeSaved.getQuantity());

    }

    @Test
    @DisplayName("Save update milk when successful")
    void save_UpdateMilk_WhenSuccessful() {
        Milk milkToBeSaved = createValidMilk();
        Milk milkSaved = milkRepository.save(milkToBeSaved);
        milkSaved.setQuantity(180D);
        Milk updatedMilk = milkRepository.save(milkSaved);

        Assertions.assertThat(updatedMilk).isNotNull();
        Assertions.assertThat(milkSaved.getId()).isNotNull();
        Assertions.assertThat(milkSaved.getQuantity()).isEqualTo(updatedMilk.getQuantity());

    }

    @Test
    @DisplayName("Delete milk when successful")
    void delete_RemoveMilk_WhenSuccessful() {
        Milk milkToBeSaved = createValidMilk();
        Milk milkSaved = milkRepository.save(milkToBeSaved);
        milkRepository.delete(milkSaved);

        Optional<Milk> milkOptional = milkRepository.findById(milkSaved.getId());

        Assertions.assertThat(milkOptional).isEmpty();

    }

    @Test
    @DisplayName("FindById return milk when successful")
    void findById_ReturnMilk_WhenSuccessful() {
        Milk milkToBeSaved = createValidMilk();
        Milk milkSaved = milkRepository.save(milkToBeSaved);

        Optional<Milk> optionalMilk = milkRepository.findById(milkSaved.getId());

        Assertions.assertThat(optionalMilk).isNotEmpty().isNotNull().contains(milkSaved);
    }

    @Test
    @DisplayName("FindByDate return list of milk when successful")
    void findByDate_ReturnMilkList_WhenSuccessful() {
        Milk milkToBeSaved = createValidMilk();
        Milk milkSaved = milkRepository.save(milkToBeSaved);

        List<Milk> milkList = milkRepository.findByDate(milkSaved.getDate());

        Assertions.assertThat(milkList).isNotEmpty().isNotNull().contains(milkSaved);

    }

    @Test
    @DisplayName("FindByDate return empty list when milk date is not found")
    void findByDate_ReturnEmptyList_WhenSuccessful() {
        Milk milkToBeSaved = createValidMilk();
        Milk milkSaved = milkRepository.save(milkToBeSaved);

        List<Milk> emptyMilkList = milkRepository.findByDate(LocalDate.of(2022, 11, 1));

        Assertions.assertThat(emptyMilkList).isEmpty();
    }

    @Test
    @DisplayName("FindByPeriodTime return list of milk when successful")
    void findByPeriodTime_ReturnMilkList_WhenSuccessful() {
        Milk milkToBeSaved = createValidMilk();
        Milk milkSaved = milkRepository.save(milkToBeSaved);

        List<Milk> milkList = milkRepository.findByPeriodTime(PeriodTime.MANHA);

        Assertions.assertThat(milkList).isNotEmpty().isNotNull().contains(milkSaved);

    }

    @Test
    @DisplayName("FindByPeriodTime return empty list of milk when milk periodTime is not found")
    void findByPeriodTime_ReturnEmptyMilkList_WhenSuccessful() {
        Milk milkToBeSaved = createValidMilk();
        Milk milkSaved = milkRepository.save(milkToBeSaved);

        List<Milk> emptyMilkList = milkRepository.findByPeriodTime(PeriodTime.TARDE);

        Assertions.assertThat(emptyMilkList).isEmpty();

    }

    @Test
    @DisplayName("FindByDateBetween return list of milk when successful")
    void findByDateBetween_ReturnMilkList_WhenSuccessful() {
        Milk milkToBeSaved = createValidMilk();
        Milk milkSaved = milkRepository.save(milkToBeSaved);

        List<Milk> milkList = milkRepository.findByDateBetween(LocalDate.now(), LocalDate.now().plusYears(1L));

        Assertions.assertThat(milkList).isNotEmpty().isNotNull().contains(milkSaved);
    }

    @Test
    @DisplayName("FindByDateBetween return empty list of milk when between date is not found")
    void findByDateBetween_ReturnEmptyMilkList_WhenSuccessful() {
        Milk milkToBeSaved = createValidMilk();
        Milk milkSaved = milkRepository.save(milkToBeSaved);

        List<Milk> milkList = milkRepository.findByDateBetween(LocalDate.now().plusYears(1L), LocalDate.now().plusYears(2L));

        Assertions.assertThat(milkList).isEmpty();
    }


    private static Milk createValidMilk() {
        return Milk.builder()
                .monthlyMilk(null)
                .date(LocalDate.now())
                .periodTime(PeriodTime.MANHA)
                .quantity(120D)
                .id(1L)
                .build();
    }

}