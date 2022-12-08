package com.tutorials.msexceladapter.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tutorials.msexceladapter.model.ErrorResponseModel;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class AuthorizationFilter extends OncePerRequestFilter {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${security.internal.header.name}")
    private String headerName;

    @Value("${security.internal.header.value}")
    private String headerValue;

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {
        if (!headerValue.equals(req.getHeader(headerName))) {
            handleExternalRequest(res);
        } else {
            chain.doFilter(req, res);
        }
    }

    private void handleExternalRequest(HttpServletResponse res) throws IOException {
        res.setStatus(HttpStatus.UNAUTHORIZED.value());
        res.setContentType("application/json");
        objectMapper.writeValue(
                res.getOutputStream(),
                ErrorResponseModel.builder()
                        .code(9999)
                        .message("Unauthorized request!")
                        .build());

        logger.error("Unauthorized request!");
    }

}
