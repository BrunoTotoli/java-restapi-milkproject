package com.bruno.springmyproject.integration;


import com.bruno.springmyproject.entity.Milk;
import com.bruno.springmyproject.repository.MilkRepository;
import com.bruno.springmyproject.request.MilkPostRequestBody;
import com.bruno.springmyproject.util.BasicAuthHeaders;
import com.bruno.springmyproject.util.MilkCreator;
import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@Log4j2
class MilkControllerIT {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private MilkRepository milkRepository;

    private static final HttpEntity<Object> httpEntityWithBasicAuth = new HttpEntity<>(BasicAuthHeaders.createHeaders("admin", "senha"));


    @Test
    @DisplayName("findAll returns list of milk when successful")
    void findAll_ReturnsListOfMilk_WhenSuccessful() {
        Milk savedMilk = milkRepository.save(MilkCreator.createValidMilk());

        ResponseEntity<List<Milk>> responseMilk = testRestTemplate.exchange("/v1/milk/", HttpMethod.GET, httpEntityWithBasicAuth, new ParameterizedTypeReference<List<Milk>>() {
        });

        List<Milk> milkList = responseMilk.getBody();

        Assertions.assertThat(milkList)
                .isNotNull()
                .isNotEmpty()
                .containsOnly(savedMilk)
                .hasSize(1);
        Assertions.assertThat(responseMilk.getStatusCode())
                .isEqualTo(HttpStatus.OK);
    }

    @Test
    @DisplayName("findById returns milk when successful")
    void findById_ReturnsMilk_WhenSuccessful() {
        Milk savedMilk = milkRepository.save(MilkCreator.createValidMilk());
        Long id = savedMilk.getId();

        ResponseEntity<Milk> responseMilk = testRestTemplate.exchange("/v1/milk/{id}", HttpMethod.GET, httpEntityWithBasicAuth, new ParameterizedTypeReference<Milk>() {
        }, id);

        Milk milk = responseMilk.getBody();

        Assertions.assertThat(milk)
                .isNotNull()
                .isEqualTo(savedMilk);
        Assertions.assertThat(milk.getId())
                .isNotNull()
                .isEqualTo(savedMilk.getId());
        Assertions.assertThat(responseMilk.getStatusCode())
                .isEqualTo(HttpStatus.OK);
    }

    @Test
    @DisplayName("save milk when successful")
    void save_SaveMilk_WhenSuccessful() {
        MilkPostRequestBody milkPostRequestBody = MilkCreator.createValidMilkPostRequestBody();

        ResponseEntity<Milk> responseMilk = testRestTemplate.exchange("/v1/milk", HttpMethod.POST, new HttpEntity<>(milkPostRequestBody, BasicAuthHeaders.createHeaders("admin", "senha")), Milk.class);

        Milk savedMilk = responseMilk.getBody();

        Assertions.assertThat(savedMilk)
                .isNotNull();
        Assertions.assertThat(savedMilk.getId())
                .isNotNull();
        Assertions.assertThat(responseMilk.getStatusCode())
                .isEqualTo(HttpStatus.CREATED);

    }
//
//    @Test
//    @DisplayName("replace update milk when successful")
//    void replace_UpdateMilk_WhenSuccessful() {
//        Assertions.assertThatCode(() -> milkController.replace(MilkCreator.createValidMilkPutRequestBody()))
//                .doesNotThrowAnyException();
//
//        ResponseEntity<Void> body = milkController.replace(MilkCreator.createValidMilkPutRequestBody());
//
//        Assertions.assertThat(body)
//                .isNotNull();
//        Assertions.assertThat(body.getStatusCode())
//                .isEqualTo(HttpStatus.NO_CONTENT);
//    }
//
//    @Test
//    @DisplayName("delete removes milk when successful")
//    void delete_RemovesMilk_WhenSuccessful() {
//        Assertions.assertThatCode(() -> milkController.delete(1L))
//                .doesNotThrowAnyException();
//
//        ResponseEntity<Void> body = milkController.delete(1L);
//
//        Assertions.assertThat(body)
//                .isNotNull();
//        Assertions.assertThat(body.getStatusCode())
//                .isEqualTo(HttpStatus.NO_CONTENT);
//    }
//
//    @Test
//    @DisplayName("findByYearAndMonth returns milk list when successful")
//    void findByYearAndMonth_ReturnsMilkList_WhenSuccessful() {
//        Milk milk = MilkCreator.createValidMilkWithMonthlyMilk();
//        List<Milk> body = milkController.findByYearAndMonth(12, 2022).getBody();
//
//        Assertions.assertThat(body)
//                .isNotNull()
//                .isNotEmpty()
//                .hasSize(1)
//                .containsOnly(milk);
//        Assertions.assertThat(body.get(0).getId())
//                .isNotNull()
//                .isEqualTo(milk.getId());
//
//    }
//
//    @Test
//    @DisplayName("findByDayYearAndMonth returns milk list when successful")
//    void findByDayYearAndMonth_ReturnsMilkList_WhenSuccessful() {
//        Milk milk = MilkCreator.createValidMilkWithMonthlyMilk();
//        List<Milk> body = milkController.findByDayYearAndMonth("12042022").getBody();
//
//        Assertions.assertThat(body)
//                .isNotNull()
//                .isNotEmpty()
//                .hasSize(1)
//                .containsOnly(milk);
//        Assertions.assertThat(body.get(0).getId())
//                .isNotNull()
//                .isEqualTo(milk.getId());
//
//    }


}
