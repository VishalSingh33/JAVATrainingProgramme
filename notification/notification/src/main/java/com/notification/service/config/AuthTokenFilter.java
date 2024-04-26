package com.notification.service.config;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthTokenFilter extends OncePerRequestFilter {

private final TokenProvider tokenProvider;

  @SuppressWarnings("null")
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    try {
      Optional<String> accessToken = parseJwt(request);
        HeaderMapRequestWrapper requestWrapper = new HeaderMapRequestWrapper(request);
      if (accessToken.isPresent() && tokenProvider.validateToken(accessToken.get())) {
        String userId = tokenProvider.getUsername(accessToken.get());

       // if(!"OPTIONS".equalsIgnoreCase(request.getMethod())) {
          requestWrapper.putHeader("Z-AUTH-USERID", userId);
        //}

        //UserDetailsImpl userDetails = userDetailsService.loadUserByUserId(userId);
        Claims claims = tokenProvider.getAllClaimsFromToken(accessToken.get());
        String roles =  claims.get("roles", String.class);
        List<String> rolesMap = Stream.of(roles.split(",")).collect(Collectors.toList());

        UsernamePasswordAuthenticationToken authentication =
            new UsernamePasswordAuthenticationToken(
                userId, null,
                rolesMap.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));

        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(null)); //null->request

        SecurityContextHolder.getContext().setAuthentication(authentication);
      }

      filterChain.doFilter(requestWrapper, response);
    } catch (Exception e) {
      logger.error("Cannot set user authentication: {}", e);
    }
  }

  private Optional<String> parseJwt(HttpServletRequest request) {
    String authorizationHeader = request.getHeader("Authorization");

    if (StringUtils.hasText(authorizationHeader) && authorizationHeader.startsWith("Bearer ")) {
      return Optional.of(authorizationHeader.replace("Bearer ", ""));
    }

    return Optional.empty();
  }

    public class HeaderMapRequestWrapper extends HttpServletRequestWrapper {
        // holds custom header and value mapping
        private final Map<String, String> customHeaders;

        public HeaderMapRequestWrapper(HttpServletRequest request){
            super(request);
            this.customHeaders = new HashMap<String, String>();
        }

        public void putHeader(String name, String value){
            this.customHeaders.put(name, value);
        }

        @Override
        public Enumeration<String> getHeaders(String name) {
            try{
                String values = getHeader(name);
                if (customHeaders.containsKey(name)) {
                    Set<String> set = Arrays.asList(values.split(",")).stream().collect(Collectors.toSet());
                    return Collections.enumeration(set);
                }
            } catch (Exception e) {
                log.info("Request header was: {}", name);
            }
            return super.getHeaders(name);
        }

        public String getHeader(String name) {
            // check the custom headers first
            String headerValue = customHeaders.get(name);

            if (headerValue != null){
                return headerValue;
            }
            // else return from into the original wrapped object
            return ((HttpServletRequest) getRequest()).getHeader(name);
        }

        public Enumeration<String> getHeaderNames() {
            // create a set of the custom header names
            Set<String> set = new HashSet<String>(customHeaders.keySet());

            // now add the headers from the wrapped request object
            Enumeration<String> e = ((HttpServletRequest) getRequest()).getHeaderNames();
            while (e.hasMoreElements()) {
                // add the names of the request headers into the list
                String n = e.nextElement();
                set.add(n);
            }

            // create an enumeration from the set and return
            return Collections.enumeration(set);
        }
    }

}
