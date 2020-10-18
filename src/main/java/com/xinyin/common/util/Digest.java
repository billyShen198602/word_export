package com.xinyin.common.util;


import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;

public class Digest {
    public static String digest(String str, String alg, String charencoding) {
        try {
            byte[] data = str.getBytes(charencoding);
            MessageDigest md = MessageDigest.getInstance(alg);
            return Hex.encodeHexString(md.digest(data));
        } catch (Exception var5) {
            throw new RuntimeException("digest fail!", var5);
        }
    }
}
