// package com.zyapaar.userservice.dto;

// import java.util.Set;
// import javax.validation.constraints.Email;
// import javax.validation.constraints.NotBlank;
// import javax.validation.constraints.Pattern;
// import javax.validation.constraints.Size;
// import com.zyapaar.userservice.validation.IdentityValidation;
// import com.zyapaar.userservice.validation.ValidateBuysSet;
// import com.zyapaar.userservice.validation.ValidateSalesSet;
// import lombok.AllArgsConstructor;
// import lombok.Builder;
// import lombok.Data;
// import lombok.NoArgsConstructor;

// @Data
// @NoArgsConstructor
// @AllArgsConstructor
// @Builder
// @IdentityValidation(identityNumber = "identityNumber")
// public class EntityRegistrationDto {

//   private String userId;
//   private String userMobile;

//   @NotBlank(message = "Enter designation")
//   @Size(min = 2, message = "Minimum 2 character required")
//   @Size(max = 150, message = "Maximum 150 character allowed")
//   private String designation;

//   @NotBlank(message = "Select verified by")
//   private VerifiedBy verifiedBy;

//   @NotBlank(message = "Enter entity name")
//   @Size(min = 2, message = "Minimum 2 character required")
//   @Size(max = 200, message = "Maximum 200 character allowed")
//   private String name;

//   @NotBlank(message = "Select entity type")
//   private EntityType type;

//   @NotBlank(message = "Select nature of business")
//   private NatureOfBusiness natureOfBusiness;

//   @NotBlank(message = "Enter entity address line 1")
//   @Size(min = 2, message = "Minimum 2 character required")
//   @Size(max = 150, message = "Maximum 150 character allowed")
//   private String addressLine1;

//   @Size(max = 150, message = "Maximum 150 character allowed")
//   private String addressLine2;

//   @NotBlank(message = "Enter entity pincode")
//   @Size(min = 6, max = 6, message = "Enter valid pin code")
//   @Pattern(regexp = "(\\d{6})", message = "Enter valid pinCode")
//   private String firmPincode;

//   @NotBlank(message = "Enter enity city")
//   @Size(min = 2, message = "Minimum 2 character required")
//   @Size(max = 100, message = "Maximum 100 character allowed")
//   private String city;

//   @Pattern(regexp = "(^$|^([0-9]{6,}))", message = "Number is invalid")
//   private String contactNo1;

//   @Pattern(regexp = "^(\\d{10})", message = "Number is invalid")
//   private String contactNo2;

//   @NotBlank(message = "Enter entity email")
//   @Email(message = "Email is invalid")
//   private String email;

//   @ValidateSalesSet
//   private Set<String> sales;

//   @ValidateBuysSet
//   private Set<String> buys;

//   @NotBlank(message = "Enter about your entity")
//   @Size(min = 10, message = "Minimum 10 character required")
//   @Size(max = 500, message = "Maximum 500 character allowed")
//   private String about;

//   private String identityNumber;

//   @Size(max = 100, message = "Maximum 100 character allowed for brandName")
//   private String brandName;

//   private String state;

//   @NotBlank(message = "Select your country")
//   private String country;
// }
