// package com.skypro.broadband.dto;

// import javax.validation.constraints.NotBlank;
// import javax.validation.constraints.Pattern;
// import javax.validation.constraints.Size;

// import lombok.AllArgsConstructor;
// import lombok.Builder;
// import lombok.Data;
// import lombok.NoArgsConstructor;

// /**
//  * add user dto
//  * 
//  * @author Uday Halpara
//  */
// @Data
// @NoArgsConstructor
// @AllArgsConstructor
// @Builder
// public class StudentDto {

//   @NotBlank(message = "Please enter firstName")
//   @Size(min = 2, message = "Minimum 2 character required for firstName")
//   @Size(max = 50, message = "Maximum 50 character allow for firstName")
//   private String firstName;

//   @NotBlank(message = "Please enter lastName")
//   @Size(min = 2, message = "Minimum 2 character required for lastName")
//   @Size(max = 50, message = "Maximum 50 character allow for lastName")
//   private String lastName;

//   @NotBlank(message = "Please enter mobile number")
//   @Pattern(regexp = "^([9876]{1})(\\d{9})", message = "Please enter valid mobile number")
//   private String mobileNo;

//   @Pattern(
//     regexp = "(^[\\w!#$%&’*+/=?`{|}~^-]+(?:.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+.)+([a-zA-Z]{2,6})$)*", 
//     message = "Please enter valid emailId"
//   )
//   private String emailId;

//   @NotBlank(message = "Please provide company identity")
//   private String identityNumber;
// }
