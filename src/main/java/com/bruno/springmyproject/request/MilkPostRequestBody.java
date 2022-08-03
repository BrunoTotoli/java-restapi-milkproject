package com.bruno.springmyproject.request;

import com.bruno.springmyproject.entity.enums.PeriodTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Builder
public class MilkPostRequestBody {
    private Double quantity;
    private PeriodTime periodTime;
    private LocalDate date;


}
