package com.example.activity_maim.signup;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashService {

    public static String sha256(String password) {
        String SHA = "";
        try {
            MessageDigest sh = MessageDigest.getInstance("SHA-256");
            sh.update(password.getBytes());
            byte byteData[] = sh.digest();
            StringBuffer sb = new StringBuffer();
            for (byte data : byteData) {
                sb.append(Integer.toString((data & 0xff) + 0x100, 16).substring(1));
            }
            SHA = sb.toString().toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            SHA = null;
        }
        System.out.println(SHA);
        return SHA;
    }
}
