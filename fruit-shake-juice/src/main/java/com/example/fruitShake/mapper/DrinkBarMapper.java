package com.example.fruitShake.mapper;

import com.example.fruitShake.dto.DrinkBarRequestDto;
import com.example.fruitShake.dto.DrinkBarResponseDto;
import com.example.fruitShake.dto.DrinkStatus;
import com.example.fruitShake.dto.DrinkType;
import com.example.fruitShake.entities.DrinkBar;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import java.time.OffsetDateTime;
import java.util.List;

/**
 * Products mapper
 *
 */
@Mapper(componentModel = "spring")
public interface DrinkBarMapper   {

    DrinkBarMapper INSTANCE = Mappers.getMapper( DrinkBarMapper.class );

    @Mapping(target = "updatedOn", source = "createdOn")
    @Mapping(target = "createdOn", source = "createdOn")
    @Mapping(target = "dName", source = "dName")
    DrinkBar toDrinkBar(DrinkBarRequestDto addDrinks, OffsetDateTime createdOn);

    default String map(DrinkType dType){
        return dType.type();
    }
    default String map(DrinkStatus dStatus){
        return dStatus.status();
    }


}