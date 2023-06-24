// package com.example.fruitShake.properties;

// import org.springframework.boot.context.properties.ConfigurationProperties;
// import org.springframework.stereotype.Component;

// import lombok.Getter;
// import lombok.Setter;

// /**
//  * Property class
//  * 
//  * @author UDaY HaLPaRa
//  */
// @ConfigurationProperties(prefix = "app")
// @Getter
// @Component
// public class B2bProperties {

//   private Topic topic = new Topic();
//   private Store store = new Store();
//   private Api api = new Api();
//   private Paging paging = new Paging();
//   private Size size = new Size();
//   private EmailNotification emailNotification = new EmailNotification();
//   private DefaultImages defaultImages = new DefaultImages();

//   @Getter
//   @Setter
//   public static class Size {

//     private long image;

//   }

//   @Getter
//   @Setter
//   public static class Topic {

//     private String signupPhaseOne;
//     // private String userRegistration;
//     // private String companyRegistration;
//     // private String connectionRequest;
//     // private String userOverview;
//     // private String usersWiseIndustry;
//     // private String userConnection;
//     // private String companyWiseIndustry;
//     // private String emailNotification;
//     // private String userFollowyEvent;
//     private String profileViewEvent;
//     // private String userConnectionRequestEvent;
//     // private String teamRequestEvent;

//   }

//   @Getter
//   @Setter
//   public static class Store {

//     private String signupPhaseOneStore;
//     // private String userConnection;
//     // private String userOverview;

//   }

//   @Getter
//   @Setter
//   public static class Api {

//     // private String notificationsOtp;
//     // private String notificationsOtpVerify;
//     // private String companyLogoUpload;
//     private String userProfileUpload;
//     private String identityValidation;
//     // private String productMap;
//     // private String productNames;
//     // private String industry;
//     // private String states;

//   }

//   @Getter
//   @Setter
//   public static class Paging {

//     private Integer connectionSize;
//     private Integer recommendationSize;
//     private Integer profileViewer;
//     private Integer userConnection;
//     private Integer userIndustry;
//     private Integer followySize;

//   }

//   @Getter
//   @Setter
//   public static class EmailNotification {

//     private String welcomeEmail;
//     private String connectionRequestAccept;
//     private String connectionRequest;
//     private String viewProfile;

//   }

//   @Getter
//   @Setter
//   public static class DefaultImages {

//     private String user;

//   }

// }
