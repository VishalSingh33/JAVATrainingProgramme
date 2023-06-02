// package com.zyapaar.userservice.consumer;

// import org.apache.kafka.streams.kstream.KStream;
// import org.springframework.cloud.stream.annotation.Input;
// import com.zyapaar.serde.SignUpPhaseOneAvro;

// /**
//  * Stream binding class
//  * 
//  */
// public interface ConsumerBinding {

//   @Input("sign-up-phase-one-channel")
//   KStream<String, SignUpPhaseOneAvro> signUpPhaseOneProcess();

// }
