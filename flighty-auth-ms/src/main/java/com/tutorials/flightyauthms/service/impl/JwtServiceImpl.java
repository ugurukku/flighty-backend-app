package com.tutorials.flightyauthms.service.impl;

import com.tutorials.flightyauthms.model.ExtractJwtRqModel;
import com.tutorials.flightyauthms.model.ExtractJwtRsModel;
import com.tutorials.flightyauthms.model.GenerateJwtRqModel;
import com.tutorials.flightyauthms.model.GenerateJwtRsModel;
import com.tutorials.flightyauthms.security.FUserDetailsService;
import com.tutorials.flightyauthms.service.JwtService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {
    private final FUserDetailsService fUserDetailsService;

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiry.default}")
    private long expiryDefault;

    @Value("${jwt.expiry.remember}")
    private long expiryRememberMe;

    @Value("${jwt.prefix}")
    private String prefix;

    @Override
    public GenerateJwtRsModel generateToken(GenerateJwtRqModel requestBody) {
        final Date now = new Date();
        final long delta = Boolean.TRUE.equals(requestBody.getRememberMe()) ? expiryRememberMe : expiryDefault;

        var token = Jwts.builder()
                .setSubject(requestBody.getUsername())
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + delta))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();

        return GenerateJwtRsModel.builder().token(String.format("%s %s", prefix, token)).build();
    }

    @Override
    public ExtractJwtRsModel extractToken(ExtractJwtRqModel requestBody) {
        var token = requestBody.getToken().substring(prefix.length());
        var claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
        var subject = claims.getBody().getSubject();
        var userDetails = fUserDetailsService.loadUserByUsername(subject);

        return ExtractJwtRsModel.builder()
                .username(userDetails.getUsername())
                .roles(userDetails.getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .toList())
                .build();
    }

}
