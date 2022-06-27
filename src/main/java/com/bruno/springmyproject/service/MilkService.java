package com.bruno.springmyproject.service;

import com.bruno.springmyproject.entity.Milk;
import com.bruno.springmyproject.repository.MilkRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class MilkService {

    private MilkRepository milkRepository;

    public List<Milk> findAll() {
        return milkRepository.findAll();
    }

    public Milk save(Milk milk) {
        return milkRepository.save(milk);
    }
}
