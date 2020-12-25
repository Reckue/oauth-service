package com.reckue.oauth.factory.grant;

import com.reckue.oauth.factory.PayloadFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class RefreshTokenFactory implements PayloadFactory<String, String> {

    @Autowired
    private JwtFactory jwtFactory;

    @Override
    public String produce(String secret) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("expiresInDays", 30);
        claims.put("secret", secret);
        return jwtFactory.produce(claims);
    }
}
