package com.bruno.springmyproject.util;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpHeaders;

import java.nio.charset.StandardCharsets;

public class BasicAuthHeaders {

    public static HttpHeaders createHeaders(String username, String password) {
        return new HttpHeaders() {{
            String auth = username + ":" + password;
            byte[] encodedAuth = Base64.encodeBase64(
                    auth.getBytes(StandardCharsets.US_ASCII));
            String authHeader = "Basic " + new String(encodedAuth);
            set("Authorization", authHeader);
        }};
    }
}
