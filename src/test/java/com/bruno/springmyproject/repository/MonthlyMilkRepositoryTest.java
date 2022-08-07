package com.bruno.springmyproject.repository;

import com.bruno.springmyproject.entity.MonthlyMilk;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
@DisplayName("Tests for Monthly Milk")
class MonthlyMilkRepositoryTest {

    @Autowired
    private MonthlyMilkRepository monthlyMilkRepository;

    @Test
    @DisplayName("Save persists monthly_milk when successful")
    void save_PersistMonthlyMilk_WhenSuccessful() {
        MonthlyMilk monthlyMilkToBeSaved = createValidMonthlyMilk();
        MonthlyMilk monthlyMilkSaved = monthlyMilkRepository.save(monthlyMilkToBeSaved);

        Assertions.assertThat(monthlyMilkSaved).isNotNull();
        Assertions.assertThat(monthlyMilkSaved.getId()).isNotNull();
        Assertions.assertThat(monthlyMilkSaved.getMilkMonth()).isEqualTo(monthlyMilkToBeSaved.getMilkMonth());
        Assertions.assertThat(monthlyMilkSaved.getMilkYear()).isEqualTo(monthlyMilkToBeSaved.getMilkYear());

    }

    @Test
    @DisplayName("Save update monthly_milk when successful")
    void save_UpdateMonthlyMilk_WhenSuccessful() {
        MonthlyMilk monthlyMilkToBeSaved = createValidMonthlyMilk();
        MonthlyMilk monthlyMilkSaved = monthlyMilkRepository.save(monthlyMilkToBeSaved);
        monthlyMilkSaved.setMilkMonthPrice(6.0D);
        MonthlyMilk updatedMilk = monthlyMilkRepository.save(monthlyMilkSaved);

        Assertions.assertThat(updatedMilk).isNotNull();
        Assertions.assertThat(monthlyMilkSaved.getId()).isNotNull();
        Assertions.assertThat(monthlyMilkSaved.getMilkMonthPrice()).isEqualTo(updatedMilk.getMilkMonthPrice());

    }

    @Test
    @DisplayName("Delete monthly_milk when successful")
    void delete_RemoveMonthlyMilk_WhenSuccessful() {
        MonthlyMilk monthlyMilkToBeSaved = createValidMonthlyMilk();
        MonthlyMilk monthlyMilkSaved = monthlyMilkRepository.save(monthlyMilkToBeSaved);
        monthlyMilkRepository.delete(monthlyMilkSaved);

        Optional<MonthlyMilk> milkOptional = monthlyMilkRepository.findById(monthlyMilkSaved.getId());

        Assertions.assertThat(milkOptional).isEmpty();

    }

    @Test
    @DisplayName("FindById return monthly_milk when successful")
    void findById_ReturnMonthlyMilk_WhenSuccessful() {
        MonthlyMilk monthlyMilkToBeSaved = createValidMonthlyMilk();
        MonthlyMilk monthlyMilkSaved = monthlyMilkRepository.save(monthlyMilkToBeSaved);

        Optional<MonthlyMilk> optionalMilk = monthlyMilkRepository.findById(monthlyMilkSaved.getId());

        Assertions.assertThat(optionalMilk).isNotEmpty().isNotNull().contains(monthlyMilkSaved);
    }

    @Test
    @DisplayName("FindMonthlyMilkByMilkMonthAndYear return monthly_milk when successful")
    void findMonthlyMilkByMilkMonthAndMilkYear_ReturnMonthlyMilk_WhenSuccessful() {
        MonthlyMilk monthlyMilkToBeSaved = createValidMonthlyMilk();
        MonthlyMilk monthlyMilkSaved = monthlyMilkRepository.save(monthlyMilkToBeSaved);

        MonthlyMilk monthlyMilk = monthlyMilkRepository.findMonthlyMilkByMilkMonthAndMilkYear(monthlyMilkSaved.getMilkMonth(), monthlyMilkSaved.getMilkYear());

        Assertions.assertThat(monthlyMilk).isNotNull();
        Assertions.assertThat(monthlyMilk.getId()).isNotNull();
        Assertions.assertThat(monthlyMilk).isEqualTo(monthlyMilkSaved);
        Assertions.assertThat(monthlyMilk.getMilkMonth()).isEqualTo(monthlyMilkSaved.getMilkMonth());
        Assertions.assertThat(monthlyMilk.getMilkYear()).isEqualTo(monthlyMilkSaved.getMilkYear());

    }

    private MonthlyMilk createValidMonthlyMilk() {
        return MonthlyMilk.builder()
                .milkMonthPrice(4.20D)
                .milkMonth(12)
                .milkYear(2022)
                .milkList(null)
                .allMilkQuantityInMonth(null)
                .allMilkQuantityInMonthPriceValue(null)
                .build();
    }


}