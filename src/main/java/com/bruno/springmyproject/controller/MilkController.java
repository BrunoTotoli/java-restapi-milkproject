package com.bruno.springmyproject.controller;


import com.bruno.springmyproject.entity.Milk;
import com.bruno.springmyproject.service.MilkService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("v1/milk")
public class MilkController {

    private MilkService milkService;

    @GetMapping("/listAll")
    public ResponseEntity<List<Milk>> findAll() {
        return new ResponseEntity<>(milkService.findAll(), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Milk> save(@RequestBody Milk milk) {
        return new ResponseEntity<>(milkService.save(milk), HttpStatus.CREATED);
    }

}
