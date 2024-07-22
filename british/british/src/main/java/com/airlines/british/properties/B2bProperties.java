// package com.airlines.british.properties;

// import org.springframework.boot.context.properties.ConfigurationProperties;
// import org.springframework.stereotype.Component;

// import lombok.Getter;
// import lombok.Setter;

// /**
//  * Property class
//  * 
//  * @author Uday Halpara
//  */
// @ConfigurationProperties(prefix = "app")
// @Getter
// @Setter
// @Component
// public class B2bProperties {

//   private Topic topic = new Topic();
//   private Api api = new Api();
//   private MediaSize mediaSize = new MediaSize();
//   private Store store = new Store();
//   private Paging paging = new Paging();
//   private NotificationMsg notificationMsg = new NotificationMsg();
//   // private SmsMsg smsMsg = new SmsMsg();
//   private Produce produce = new Produce();
//   private SmsTemplate smsTemplate = new SmsTemplate();


//   @Getter
//   @Setter
//   public static class SmsTemplate {
//     private String postComment;
//     private String replyComment;
//   }

//   @Getter
//   @Setter
//   public static class Produce {
//     private Boolean smsProduce;
    
//   }

//   // @Getter
//   // @Setter
//   // public static class SmsMsg {
//   //   private String comment;
//   //   private String reply;
//   // }

//   @Getter
//   @Setter
//   public static class Topic {

//     private String post;
//     private String comment;
//     private String commentReaction;
//     private String postReaction;
//     private String commentCount;
//     private String commentReactionCount;
//     private String postReactionCount;
//     private String feed;
//     private String feedComment;
//     private String notifications;
//     private String hashTag;
//     private String hashTagCount;
//     private String commentReplyCount;
//     private String userActivity;
//     private String userOverview;
//     private String lastPostViewCount;
//     private String postReactionEvent;
//     private String postCommentEvent;
//     private String commentReactionEvent;
//     private String commentReplyEvent;
//     private String postTagEvent;
//     private String commentTagEvent;
//     private String notificationsV2;
//     private String postShareEvent;
//     private String pageCommentEvent;
//     private String pageCommentTagEvent;
//     private String pageCommentReplyEvent;
//     private String pageReactionEvent;
//     private String pageCommentReactionEvent;
//     private String pageTagEvent;
//     private String smsNotification;

//   }

//   @Getter
//   @Setter
//   public static class Api {

//     private String userPostUpload;
//     private String userName;
//     private String user;
//     private String userIndustry;
//     private String industryUsers;

//   }

//   @Getter
//   @Setter
//   public static class MediaSize {

//     private long image;
//     private long video;
//     private long pdf;

//   }

//   @Getter
//   @Setter
//   public static class Store {

//     private String postReactionStore;
//     private String commentReactionStore;
//     private String postReactionCountStore;

//   }

//   @Getter
//   @Setter
//   public static class Paging {

//     private Integer feedSize;
//     private Integer commentSize;
//     private Integer activitySize;
//     private Integer hashTagSize;

//   }

//   @Getter
//   @Setter
//   public static class NotificationMsg {

//     private String message;

//   }

// }
