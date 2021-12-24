package com.example.finalproject.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vacancy {
    private Integer id;
    private String name;
    private Integer salary;
    private BigDecimal points;
}
