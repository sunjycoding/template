package com.example.template.modules.authentication.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.template.common.util.DateUtils;
import com.example.template.modules.authentication.service.TokenService;
import com.example.template.modules.user.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author created by sunjy on 12/21/23
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class TokenServiceImpl implements TokenService {

    private static final String ISSUER = "app";

    private static final String SECRET = "0123456789";

    private static final String USERNAME_CLAIM_KEY = "username";

    @Override
    public String createToken(UserDTO userDTO) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expiredTime = now.plusDays(1);
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        return JWT.create()
                .withIssuer(ISSUER)
                .withIssuedAt(DateUtils.localDateTimeToDate(now))
                .withSubject(userDTO.getId())
                .withExpiresAt(DateUtils.localDateTimeToDate(expiredTime))
                .withClaim(USERNAME_CLAIM_KEY, userDTO.getUsername())
                .sign(algorithm);
    }

    @Override
    public DecodedJWT verifyToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer(ISSUER)
                .build();
        return verifier.verify(token);
    }
}
