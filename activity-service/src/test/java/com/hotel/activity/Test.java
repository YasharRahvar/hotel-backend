package com.hotel.activity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static junit.framework.TestCase.assertTrue;

public class Test {

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String result = encoder.encode("activityServ1ce");
        System.out.println(result);
        assertTrue(encoder.matches("activityServ1ce", result));

        //$2a$04$e/c1/RfsWuThaWFCrcCuJeoyvwCV0URN/6Pn9ZFlrtIWaU/vj/BfG
    }

    private static BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

}
