package com.bruno.springmyproject.controller;

import com.bruno.springmyproject.entity.Milk;
import com.bruno.springmyproject.request.MilkPostRequestBody;
import com.bruno.springmyproject.request.MilkPutRequestBody;
import com.bruno.springmyproject.service.MilkService;
import com.bruno.springmyproject.util.MilkCreator;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Milk Controller Tests")
class MilkControllerTest {

    @Mock
    private MilkService milkService;

    @InjectMocks
    private MilkController milkController;

    @BeforeEach
    void setUp() {
        Mockito.when(milkService.findAll())
                .thenReturn(List.of(MilkCreator.createValidMilk()));
        Mockito.when(milkService.findByIdOrElseThrowMilkNotFoundException(ArgumentMatchers.anyLong()))
                .thenReturn(MilkCreator.createValidMilk());
        Mockito.when(milkService.save(ArgumentMatchers.any(MilkPostRequestBody.class)))
                .thenReturn(MilkCreator.createValidMilk());
        Mockito.when(milkService.findMilkListByDayYearAndMonth(ArgumentMatchers.anyString()))
                .thenReturn(List.of(MilkCreator.createValidMilkWithMonthlyMilk()));
        Mockito.when(milkService.findMilkListByMonthAndYear(ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt()))
                .thenReturn(List.of(MilkCreator.createValidMilkWithMonthlyMilk()));
        Mockito.when(milkService.replace(ArgumentMatchers.any(MilkPutRequestBody.class)))
                .thenReturn(MilkCreator.createValidMilkWithMonthlyMilk());
        Mockito.doNothing().when(milkService).delete(ArgumentMatchers.anyLong());
    }

    @Test
    @DisplayName("findAll returns list of milk when successful")
    void findAll_ReturnsListOfMilk_WhenSuccessful() {
        Milk milk = MilkCreator.createValidMilk();
        List<Milk> bodyMilk = milkController.findAll().getBody();

        Assertions.assertThat(bodyMilk)
                .isNotNull()
                .isNotEmpty()
                .containsOnly(milk)
                .hasSize(1);
    }

    @Test
    @DisplayName("findById returns milk when successful")
    void findById_ReturnsMilk_WhenSuccessful() {
        Milk milk = MilkCreator.createValidMilk();
        Milk bodyMilk = milkController.findById(1L).getBody();

        Assertions.assertThat(bodyMilk)
                .isNotNull()
                .isEqualTo(milk);

        Assertions.assertThat(bodyMilk.getId())
                .isNotNull()
                .isEqualTo(milk.getId());
    }

    @Test
    @DisplayName("save milk when successful")
    void save_SaveMilk_WhenSuccessful (){
        Milk milk = MilkCreator.createValidMilk();
        Milk bodyMilk = milkController.save(MilkCreator.createValidMilkPostRequestBody()).getBody();

        Assertions.assertThat(bodyMilk)
                .isNotNull()
                .isEqualTo(milk);

        Assertions.assertThat(bodyMilk.getId())
                .isNotNull()
                .isEqualTo(milk.getId());

    }

    @Test
    @DisplayName("replace update milk when successful")
    void replace_UpdateMilk_WhenSuccessful() {
        Assertions.assertThatCode(() -> milkController.replace(MilkCreator.createValidMilkPutRequestBody()))
                .doesNotThrowAnyException();

        ResponseEntity<Void> body = milkController.replace(MilkCreator.createValidMilkPutRequestBody());

        Assertions.assertThat(body)
                .isNotNull();
        Assertions.assertThat(body.getStatusCode())
                .isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    @DisplayName("delete removes milk when successful")
    void delete_RemovesMilk_WhenSuccessful() {
        Assertions.assertThatCode(() -> milkController.delete(1L))
                .doesNotThrowAnyException();

        ResponseEntity<Void> body = milkController.delete(1L);

        Assertions.assertThat(body)
                .isNotNull();
        Assertions.assertThat(body.getStatusCode())
                .isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    @DisplayName("findByYearAndMonth returns milk list when successful")
    void findByYearAndMonth_ReturnsMilkList_WhenSuccessful() {
        Milk milk = MilkCreator.createValidMilkWithMonthlyMilk();
        List<Milk> body = milkController.findByYearAndMonth(12, 2022).getBody();

        Assertions.assertThat(body)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1)
                .containsOnly(milk);
        Assertions.assertThat(body.get(0).getId())
                .isNotNull()
                .isEqualTo(milk.getId());

    }

    @Test
    @DisplayName("findByDayYearAndMonth returns milk list when successful")
    void findByDayYearAndMonth_ReturnsMilkList_WhenSuccessful() {
        Milk milk = MilkCreator.createValidMilkWithMonthlyMilk();
        List<Milk> body = milkController.findByDayYearAndMonth("12042022").getBody();

        Assertions.assertThat(body)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1)
                .containsOnly(milk);
        Assertions.assertThat(body.get(0).getId())
                .isNotNull()
                .isEqualTo(milk.getId());

    }
}