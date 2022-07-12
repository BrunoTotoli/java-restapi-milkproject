package com.bruno.springmyproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class MonthlyMilk {

    @Id
    private Long id;
    @OneToMany
    private List<Milk> milkList;
    private Integer month;
    private Integer year;


}
