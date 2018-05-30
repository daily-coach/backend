package br.com.rodrigocardoso.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * Created by rodri on 26/05/2018.
 */
public class JwtUtils {

    private static final String secret = "Daily_COACH_LIve";
    private static final String issuer = "daily_coach_back";

    public static String createToken(String login) throws Exception {
        Algorithm algorithm = Algorithm.HMAC512(secret);

        return JWT.create()
                .withIssuer(issuer)
                .withSubject(login)
                .withExpiresAt(Date.from(LocalDateTime.now().plusSeconds(30).atZone(ZoneId.systemDefault()).toInstant()))
                .sign(algorithm);
    }

    public static String verify(String token) throws Exception {
        Algorithm algorithm = Algorithm.HMAC512(secret);

        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer(issuer)
                .build();

        DecodedJWT decodedJWT = verifier.verify(token);
        return decodedJWT.getSubject();
    }

}
