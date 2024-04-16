package com.skypro.broadband.mapper;

import java.time.LocalDateTime;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import com.skypro.broadband.dto.UserCSVDto;
import com.skypro.broadband.entities.User;

@Mapper
public interface ProfileUsersExcelCompletionMapper {

    @Mapping(target = "id",source = "id")

    UserCSVDto setData(String id, String userId, String userType, String message,
    String topic, String link, String readFlag, String triggeredBy, LocalDateTime
    createdDate);
    // ProfileUsersExcelCompletionMapper INSTANCE = Mappers.getMapper(ProfileUsersExcelCompletionMapper.class);

    // @Mapping(source = "id", target = "id")
    // UserCSVDto userToUserCSVDto(User user);

    // List<UserCSVDto> usersToUserCSVDtos(List<User> users);

}
