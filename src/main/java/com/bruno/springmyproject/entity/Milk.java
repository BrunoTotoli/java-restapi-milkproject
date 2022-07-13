package com.bruno.springmyproject.entity;

import com.bruno.springmyproject.entity.enums.PeriodTime;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    private Double quantity;
    private PeriodTime periodTime;
    private LocalDate date;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "monthly_id")
    private MonthlyMilk monthlyMilk;
}
