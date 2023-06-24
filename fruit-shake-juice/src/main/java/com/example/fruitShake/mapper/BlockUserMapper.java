// package com.zyapaar.userservice.mapper;


// import com.fasterxml.jackson.core.JsonProcessingException;
// import com.zyapaar.userservice.dto.BlockOrigin;
// import com.zyapaar.userservice.dto.BlockUserDto;
// import com.fasterxml.jackson.databind.ObjectMapper;
// import com.zyapaar.userservice.entities.BlockUser;
// import org.mapstruct.Mapper;
// import org.mapstruct.Mapping;

// import java.time.OffsetDateTime;

// /**
//  * Spam report mapper
//  * 
//  * @author Uday Halpara
//  */
// @Mapper
// public interface BlockUserMapper {

// 	ObjectMapper objectMapper = new ObjectMapper();

// 	@Mapping(target = "updatedOn", source = "createdOn")
//     @Mapping(target = "createdOn", source = "createdOn")
// 	@Mapping(target = "originId", source = "blockUserDto.originId")
// 	BlockUser setData(String fromUserId, String id, OffsetDateTime createdOn, BlockUserDto blockUserDto);

// 	default String map(BlockOrigin origin){
// 		return origin.origin();
// 	}

// }
