package com.bruno.springmyproject.entity;

import com.bruno.springmyproject.entity.enums.PeriodTime;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "tb_milk")
public class Milk {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double quantity;
    private PeriodTime periodTime;
    private LocalDate date;

}
