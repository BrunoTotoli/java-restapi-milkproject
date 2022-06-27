package com.bruno.springmyproject.controller;


import com.bruno.springmyproject.entity.Milk;
import com.bruno.springmyproject.request.MilkPostRequestBody;
import com.bruno.springmyproject.request.MilkPutRequestBody;
import com.bruno.springmyproject.service.MilkService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("v1/milk")
public class MilkController {

    private MilkService milkService;

    @GetMapping
    public ResponseEntity<List<Milk>> findAll() {
        return new ResponseEntity<>(milkService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Milk> findById(@PathVariable Long id) {
        return new ResponseEntity<>(milkService.findById(id), HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<Milk> save(@RequestBody MilkPostRequestBody milkPostRequestBody) {
        return new ResponseEntity<>(milkService.save(milkPostRequestBody), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody MilkPutRequestBody milkPutRequestBody) {
        milkService.replace(milkPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        milkService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
