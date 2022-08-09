package com.bruno.springmyproject.service;

import com.bruno.springmyproject.repository.MilkRepository;
import com.bruno.springmyproject.repository.MonthlyMilkRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;


class MilkServiceTest {

    @Mock
    private MilkRepository milkRepository;
    @Mock
    private MonthlyMilkRepository monthlyMilkRepository;
    @InjectMocks
    private MilkService milkService;




}