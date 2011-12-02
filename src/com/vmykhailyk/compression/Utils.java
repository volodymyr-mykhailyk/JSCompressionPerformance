package com.vmykhailyk.compression;

/**
 * Created by Volodymyr.Mykhailyk
 * Date: 01.12.11
 * Time: 16:03
 */
public class Utils {

    public static long adler32(byte[] data) {
        long MOD_ADLER = 65521;
        long a = 1, b = 0;
        int index;

        /* Process each byte of the data in order */
        for (index = 0; index < data.length; ++index) {
            a = (a + data[index]) % MOD_ADLER;
            b = (b + a) % MOD_ADLER;
        }

        return (b << 16) | a;
    }

    public static void printBytes(byte[] bytes) {
        for (byte aByte : bytes) {
            String hex = Integer.toHexString(0xFF & aByte);
            if (hex.length() == 1) {
                // could use a for loop, but we're only dealing with a single byte
                hex = '0' + hex;
            }
            System.out.print(hex + " ");
        }
        System.out.println();
    }

}
