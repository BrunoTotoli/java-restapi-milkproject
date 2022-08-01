package com.bruno.springmyproject.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "tb_monthly")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MonthlyMilk {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer milkMonth;
    private Integer milkYear;
    private Double milkMonthPrice;
    private Double allMilkQuantityInMonth;
    private Double allMilkQuantityInMonthPriceValue;

    @OneToMany(mappedBy = "monthlyMilk", cascade = CascadeType.ALL)
    private List<Milk> milkList;

}
