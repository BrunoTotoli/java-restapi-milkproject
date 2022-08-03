package com.bruno.springmyproject.entity;

import com.bruno.springmyproject.entity.enums.PeriodTime;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "tb_milk")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Milk {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Quantity is mandatory")
    private Double quantity;

    @NotNull(message = "PeriodTime is mandatory")
    private PeriodTime periodTime;

    @NotNull(message = "Date is mandatory")
    private LocalDate date;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "monthly_id")
    private MonthlyMilk monthlyMilk;
}
