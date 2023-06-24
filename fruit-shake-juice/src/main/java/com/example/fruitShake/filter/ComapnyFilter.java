// package com.zyapaar.userservice.filter;

// import java.io.IOException;
// import java.time.OffsetDateTime;
// import java.time.ZoneOffset;
// import java.util.Date;
// import javax.servlet.FilterChain;
// import javax.servlet.ServletException;
// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;
// import com.zyapaar.commons.utils.SequenceGenerator;
// import org.springframework.web.filter.OncePerRequestFilter;

// import com.zyapaar.userservice.entities.RequestIpAddress;
// import com.zyapaar.userservice.mapper.ReferenceIpMapper;
// import com.zyapaar.userservice.repository.RequestIpRepository;
// import lombok.RequiredArgsConstructor;
// import lombok.extern.slf4j.Slf4j;

// @Slf4j
// @RequiredArgsConstructor
// // @Component
// public class ComapnyFilter extends OncePerRequestFilter {

// 	private final RequestIpRepository requestIpRepository;
// 	private final ReferenceIpMapper referenceIpMapper;

// 	@Override
// 	protected void doFilterInternal(
// 			HttpServletRequest request,
// 			HttpServletResponse response,
// 			FilterChain filterChain) throws ServletException, IOException {
// 		String ipAddress = getIpAddress(request);
// 		String[] arr = request.getRequestURL().toString().split("/");

// 		String id = SequenceGenerator.getInstance().nextId();
// 		OffsetDateTime createdOn = new Date().toInstant().atOffset(ZoneOffset.UTC);
// 		RequestIpAddress requestIpAddr = referenceIpMapper.setData(id, null, arr[arr.length - 2], createdOn, ipAddress,
// 				"user");
// 		try {
// 			requestIpRepository.save(requestIpAddr);

// 		} catch (Exception e) {
// 			log.info("Exception: {}", e);
// 		}
// 		filterChain.doFilter(request, response);
// 	}

// 	@Override
// 	protected boolean shouldNotFilterAsyncDispatch() {
// 		return false;
// 	}

// 	@Override
// 	protected boolean shouldNotFilterErrorDispatch() {
// 		return false;
// 	}

// 	private static final String[] VALID_IP_HEADER_CANDIDATES = {
// 			"X-Forwarded-For",
// 			"Proxy-Client-IP",
// 			"WL-Proxy-Client-IP",
// 			"HTTP_X_FORWARDED_FOR",
// 			"HTTP_X_FORWARDED",
// 			"HTTP_X_CLUSTER_CLIENT_IP",
// 			"HTTP_CLIENT_IP",
// 			"HTTP_FORWARDED_FOR",
// 			"HTTP_FORWARDED",
// 			"HTTP_VIA",
// 			"REMOTE_ADDR"
// 	};

// 	private String getIpAddress(HttpServletRequest request) {

// 		// for (String header : VALID_IP_HEADER_CANDIDATES) {
// 		// String ipAddress = request.getHeader(header);
// 		// if (ipAddress != null && ipAddress.length() != 0 && !"unknown".equalsIgnoreCase(ipAddress)) {
// 		// return ipAddress;
// 		// }
// 		// }
// 		// return request.getRemoteAddr();
// 		if (request.getHeader("x-forwarded-for") == null) {
// 			return request.getRemoteAddr();
// 		}
// 		return request.getHeader("x-forwarded-for");

// 	}
// }
