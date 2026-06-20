package com.gavin.rinnepay.common.util;

import java.security.SecureRandom;
import java.util.Base64;

public class RandomizerUtil {

    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    //    returned Length = ceil(inputLength * 4 / 3)
    public static String randomBase64(int length){
        byte[] buffer = new byte[length];
        SECURE_RANDOM.nextBytes(buffer);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(buffer);
    }
}
