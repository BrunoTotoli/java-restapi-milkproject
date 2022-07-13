package com.bruno.springmyproject.service;

import com.bruno.springmyproject.entity.Milk;
import com.bruno.springmyproject.mapper.MilkMapper;
import com.bruno.springmyproject.repository.MilkRepository;
import com.bruno.springmyproject.request.MilkPostRequestBody;
import com.bruno.springmyproject.request.MilkPutRequestBody;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
@Log4j2
public class MilkService {

    private MilkRepository milkRepository;

    public List<Milk> findAll() {
        return milkRepository.findAll();
    }

    public Milk save(MilkPostRequestBody milkPostRequestBody) {
        return milkRepository.save(MilkMapper.INSTANCE.milkPostRequestBodyToMilk(milkPostRequestBody));
    }

    public Milk findById(Long id) {
        Optional<Milk> optMilk = milkRepository.findById(id);
        if (optMilk.isPresent())
            return optMilk.get();
        throw new IllegalArgumentException();
    }

    public void replace(MilkPutRequestBody milkPutRequestBody) {
        milkRepository.save(MilkMapper.INSTANCE.milkPutRequestBodyToMilk(milkPutRequestBody));
    }

    public void delete(Long id) {
        milkRepository.deleteById(id);
    }

    public List<Milk> getMilkListByDayYearAndMonth(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String[] splitDate = date.split(",");
        StringBuilder formatedString = new StringBuilder();

        for (int i = 0; i < splitDate.length; i++) {
            if (i != splitDate.length - 1) {
                formatedString.append(splitDate[i]).append("/");
            } else {
                formatedString.append(splitDate[i]);
            }
        }
        try {
            Date parsedDate = sdf.parse(formatedString.toString());
            LocalDate localDateTime = parsedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            return milkRepository.findByDate(localDateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
