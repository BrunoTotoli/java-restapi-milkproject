package com.bruno.springmyproject.controller;


import com.bruno.springmyproject.entity.Milk;
import com.bruno.springmyproject.pdf.MilkPDFExporter;
import com.bruno.springmyproject.request.MilkPostRequestBody;
import com.bruno.springmyproject.request.MilkPutRequestBody;
import com.bruno.springmyproject.service.MilkService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("v1/milk")
@Log4j2
public class MilkController {

    private MilkService milkService;

    @GetMapping("/")
    public ResponseEntity<List<Milk>> findAll() {
        return new ResponseEntity<>(milkService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Milk> findById(@PathVariable Long id) {
        return new ResponseEntity<>(milkService.findByIdOrElseThrowMilkNotFoundException(id), HttpStatus.OK);
    }

    @PostMapping()
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

    @GetMapping("/date")
    public ResponseEntity<List<Milk>> findByYearAndMonth(@RequestParam Integer month, @RequestParam Integer year) {
        return ResponseEntity.ok().body(milkService.findMilkListByMonthAndYear(month, year));
    }

    @GetMapping("/fulldate")
    public ResponseEntity<List<Milk>> findByDayYearAndMonth(@RequestParam String date) {
        return ResponseEntity.ok().body(milkService.findMilkListByDayYearAndMonth(date));
    }



    @GetMapping("/pdf")
    public void exportToPDF(HttpServletResponse response) {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=milk_list_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<Milk> listMilks = milkService.findAll()
                .stream()
                .sorted(Comparator.comparing(Milk::getDate))
                .toList();

        MilkPDFExporter exporter = new MilkPDFExporter();
        exporter.export(response, listMilks);
    }

}
