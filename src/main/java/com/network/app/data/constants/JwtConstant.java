package com.network.app.data.constants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtConstant {
    public static final int JWT_EXPIRATION_TIME = 1000 * 60 * 24;
    public static final String JWT_SECRET = "JWT_SECRET";
}
