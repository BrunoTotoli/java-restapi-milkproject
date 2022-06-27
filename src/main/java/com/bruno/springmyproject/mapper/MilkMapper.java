package com.bruno.springmyproject.mapper;

import com.bruno.springmyproject.entity.Milk;
import com.bruno.springmyproject.request.MilkPostRequestBody;
import com.bruno.springmyproject.request.MilkPutRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MilkMapper {

    MilkMapper INSTANCE = Mappers.getMapper(MilkMapper.class);

    Milk milkPostRequestBodyToMilk(MilkPostRequestBody milkPostRequestBody);

    Milk milkPutRequestBodyToMilk(MilkPutRequestBody milkPutRequestBody);

}
