// package com.zyapaar.userservice.filter;

// import javax.servlet.Filter;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.web.servlet.FilterRegistrationBean;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

// import com.zyapaar.userservice.mapper.ReferenceIpMapper;
// import com.zyapaar.userservice.repository.RequestIpRepository;

// import lombok.extern.slf4j.Slf4j;

// @Configuration
// @Slf4j
// public class  AppConfig {

// 	@Autowired
// 	private  RequestIpRepository requestIpRepository;
// 	@Autowired
// 	private  ReferenceIpMapper referenceIpMapper;

// 	@Bean
//     public FilterRegistrationBean filterRegistrationBean() {
//         FilterRegistrationBean registrationBean = new FilterRegistrationBean();
//         // ComapnyFilter customURLFilter = new ComapnyFilter();

//         registrationBean.setFilter(someFilter());
// 				log.info("[filterRegistrationBean] STARTED STARTED STARTED STARTED");
//         registrationBean.addUrlPatterns("/api/v1.1/users/*");

// 				registrationBean.setName("someFilter");
// 				registrationBean.setOrder(1);
//     return registrationBean;
// } 

// @Bean(name = "someFilter")
// public Filter someFilter() {
//     return new ComapnyFilter(requestIpRepository, referenceIpMapper);
// }

// }
