package com.deliverykim.deliverykim.global.auth.service;

import com.deliverykim.deliverykim.domain.member.model.define.MemberRole;
import com.deliverykim.deliverykim.global.auth.model.dto.AuthInfo;
import com.deliverykim.deliverykim.global.auth.model.dto.TokenInfo;
import com.deliverykim.deliverykim.global.auth.model.dto.VerifyTokenInfo;
import com.deliverykim.deliverykim.global.exception.ResponseCode;
import com.deliverykim.deliverykim.global.exception.custom.AuthenticationException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

/**
 * @author : leeyounggyo
 * @package : com.deliverykim.deliverykim.global.auth.service
 * @since : 2024. 11. 2.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class TokenManager {

    private static final String ACCESS_TOKEN_SECRET = "access_token_secret_access_token_secret_access_token_secret";
    private static final String REFRESH_TOKEN_SECRET = "refresh_token_secret_refresh_token_secret_refresh_token_secret";
    private static final String ROLE = "role";


    public TokenInfo generateAuthenticationToken(AuthInfo authInfo) {
        final String accessToken = createAccessTokens(authInfo, Date.from(Instant.now().plus(30, ChronoUnit.MINUTES)));
        final String refreshToken = createRefreshToken(authInfo, Date.from(Instant.now().plus(4, ChronoUnit.DAYS)));

        return TokenInfo.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public VerifyTokenInfo getCurrentRequestMeberInfo() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .currentRequestAttributes())
                .getRequest();

        return verifyAccessToken(request.getHeader(AUTHORIZATION));
    }

    private String createAccessTokens(AuthInfo authInfo, Date expiration) {
        return Jwts.builder()
                .subject(authInfo.getEmail())
                .claim(ROLE, authInfo.getMemberRole())
                .expiration(expiration)
                .issuedAt(Date.from(Instant.now()))
                .signWith(Keys.hmacShaKeyFor(ACCESS_TOKEN_SECRET.getBytes()), Jwts.SIG.HS256)
                .compact()
                ;
    }

    private String createRefreshToken(AuthInfo authInfo, Date expiration) {
        return Jwts.builder()
                .subject(authInfo.getEmail())
                .claim(ROLE, authInfo.getMemberRole())
                .expiration(expiration)
                .issuedAt(Date.from(Instant.now()))
                .signWith(Keys.hmacShaKeyFor(REFRESH_TOKEN_SECRET.getBytes()), Jwts.SIG.HS256)
                .compact()
                ;
    }

    public VerifyTokenInfo verifyAccessToken(String accessToken) {
        final SecretKey secureAccessSecret = Keys.hmacShaKeyFor(ACCESS_TOKEN_SECRET.getBytes());
        Jws<Claims> claimsJws = verifyToken(accessToken, secureAccessSecret);

        Claims payload = claimsJws.getPayload();
        String email = payload.getSubject();
        String memberRole = payload.get(ROLE, String.class);
        Date expirateDate = payload.getExpiration();

        return VerifyTokenInfo.builder()
                .email(email)
                .memberRole(MemberRole.valueOf(memberRole))
                .expirateDate(expirateDate)
                .build();
    }

    public VerifyTokenInfo verifyRefreshToken(String refreshToken) {
        final SecretKey secureRefreshSecret = Keys.hmacShaKeyFor(REFRESH_TOKEN_SECRET.getBytes());
        Jws<Claims> claimsJws = verifyToken(refreshToken, secureRefreshSecret);

        Claims payload = claimsJws.getPayload();
        String email = payload.getSubject();
        String memberRole = payload.get(ROLE, String.class);
        Date expirateDate = payload.getExpiration();

        return VerifyTokenInfo.builder()
                .email(email)
                .memberRole(MemberRole.valueOf(memberRole))
                .expirateDate(expirateDate)
                .build();
    }

    private Jws<Claims> verifyToken(String token, SecretKey secureSecretKey) {
        Jws<Claims> claimsJws;
        try {
            claimsJws = Jwts.parser().verifyWith(secureSecretKey).build().parseSignedClaims(token);
        } catch (SignatureException e) {
            throw new AuthenticationException(ResponseCode.ACCESS_TOKEN_INVALID);
        } catch (ExpiredJwtException e) {
            throw new AuthenticationException(ResponseCode.ACCESS_TOKEN_EXPIRED_ERROR);
        } catch (Exception e) {
            throw new AuthenticationException(ResponseCode.INVALID_TOKEN);
        }

        return claimsJws;
    }

}
