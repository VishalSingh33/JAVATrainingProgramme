// package com.zyapaar.userservice.consumer;

// import com.zyapaar.serde.SignUpPhaseOneAvro;
// import com.zyapaar.userservice.properties.B2bProperties;

// // import org.apache.kafka.streams.kstream.KStream;
// // import org.apache.kafka.streams.kstream.Materialized;
// import org.springframework.cloud.stream.annotation.EnableBinding;
// import org.springframework.cloud.stream.annotation.Input;
// import org.springframework.cloud.stream.annotation.StreamListener;
// import org.springframework.stereotype.Component;

// import lombok.RequiredArgsConstructor;
// import lombok.extern.slf4j.Slf4j;

// /**
//  * stream class
//  * 
//  */
// @Slf4j
// @Component
// @RequiredArgsConstructor
// // @EnableBinding(ConsumerBinding.class)
// public class DataConsumer {

//   // private final B2bProperties b2bProperties;

//   // /**
//   //  * Streams
//   //  */
//   // @StreamListener
//   // public void process(
//   //     @Input("sign-up-phase-one-channel") KStream<String, SignUpPhaseOneAvro> signUpStream) {

//   //   log.info("[process] streams processing");

//   //   // signUpStream.toTable(Materialized.as(b2bProperties.getStore().getSignupPhaseOneStore()));
//   // }
// }
