package com.mvp.demo.utils;

import android.util.Base64;



import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class DigestUtils {
    public static String encryptKey = "a!s@d#f$";
    /**
     * Used to build output as Hex
     */
    private static final char[] DIGITS_LOWER = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
            'e', 'f'};

    private DigestUtils() {
        throw new AssertionError();
    }

    /**
     * encode By MD5
     *
     * @param str
     * @return String
     */
    public static String md5(String str) {
        if (str == null) {
            return null;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(str.getBytes());
            return new String(encodeHex(messageDigest.digest()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Converts an array of bytes into an array of characters representing the hexadecimal values of each byte in order.
     * The returned array will be double the length of the passed array, as it takes two characters to represent any
     * given byte.
     *
     * @param data a byte[] to convert to Hex characters
     * @return A char[] containing hexadecimal characters
     */
    protected static char[] encodeHex(final byte[] data) {
        final int l = data.length;
        final char[] out = new char[l << 1];
        // two characters form the hex value.
        for (int i = 0, j = 0; i < l; i++) {
            out[j++] = DIGITS_LOWER[(0xF0 & data[i]) >>> 4];
            out[j++] = DIGITS_LOWER[0x0F & data[i]];
        }
        return out;
    }

    private static byte[] iv1 = {(byte) 0x12, (byte) 0x34, (byte) 0x56, (byte) 0x78, (byte) 0x90, (byte) 0xAB, (byte) 0xCD, (byte) 0xEF};

    public static String decrypt(String decryptString, String decryptKey, boolean isDes) throws Exception {

        if (!isDes)
            return decryptString;


        IvParameterSpec iv = new IvParameterSpec(iv1);
        SecretKeySpec key = new SecretKeySpec(decryptKey.getBytes(), "DES");
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, key, iv);



        return new String(cipher.doFinal(Base64.decode(decryptString, Base64.DEFAULT)));
    }

    public static boolean isDes = false;


    public static String encrypt(String encryptString, String encryptKey, boolean isDes)
            throws Exception {
        if (!isDes)
            return encryptString;
        IvParameterSpec iv = new IvParameterSpec(iv1);
        DESKeySpec dks = new DESKeySpec(encryptKey.getBytes());
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey key = keyFactory.generateSecret(dks);
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key, iv);

        byte[] sdf = cipher.doFinal(encryptString.getBytes());
        return new String(Base64.encode(sdf, Base64.DEFAULT));//.encodeToString(sdf, Base64.DEFAULT);
    }
	
	public String getNewString(String str) {
        String newStr = "{\"DesEncToDes\":\"" + str.trim() + "\"}";
        return newStr;
    }
	//http://219.143.6.91:8766/Api/Customer/Logins
}
