package com.vmykhailyk.compression;

import com.vmykhailyk.compression.deflate.DeflateCompressor;
import com.vmykhailyk.compression.deflate.DeflateDecompressor;
import com.vmykhailyk.compression.io.FileReader;
import com.vmykhailyk.compression.io.FileWriter;
import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.zip.Adler32;

/**
 * Created by Volodymyr.Mykhailyk
 * Date: 15.10.11
 * Time: 16:13
 */
public class Runner {
    public static void main(String[] args) throws UnsupportedEncodingException {
//        compressDeflate();
        testCompression();
        FileReader reader = new FileReader("data/compressed/deflate/originalData1.txt");
        FileWriter writer = new FileWriter("data/compressed/deflate/originalData2.html");
        DeflateDecompressor decompressor = new DeflateDecompressor();
        String decompress = decompressor.decompress(reader.readData());
        writer.writeData(decompress);
    }

    private static void testCompression() throws UnsupportedEncodingException {
        DeflateCompressor compressor = new DeflateCompressor(8);
/*
        Adler32 adler32 = new Adler32();
        byte[] textBytes = "test".getBytes("UTF-8");
        adler32.update(textBytes);
        printBytes(textBytes);
*/
//        printBytes(longToByteArray(adler32.getValue()));
        printValue("abc");
        printValue("a b c");
        printValue("<>/!@#$%^&*()_-+=");
        printValue("QWEкенгшщзхїФІВАПРОЛmnb");
        printValue("<html>abc</html>");
/*
        System.out.println(compressor.compress("abc"));
        System.out.println(compressor.compress("a b c"));
        System.out.println(compressor.compress("<>/!@#$%^&*()_-+="));
        System.out.println(compressor.compress("QWEкенгшщзхїФІВАПРОЛmnb"));
        System.out.println(compressor.compress("<html>abc</html>"));
        printBytes(Base64.decodeBase64(compressor.compress("фіва")));
        printBytes(Base64.decodeBase64("eNq72HJx2oVNFzYAABmmBb8="));
*/
    }

    private static void printValue(String value) {
        char[] chars = value.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char aChar = chars[i];
            System.out.print("\\u" + Integer.toHexString(aChar));
        }
        System.out.println();
        DeflateCompressor compressor = new DeflateCompressor(8);
        System.out.println(compressor.compress(value));

        try {
            Adler32 adler32 = new Adler32();
            adler32.update(value.getBytes("UTF-8"));
            System.out.println(Base64.encodeBase64String(longToByteArray(adler32.getValue())));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    private static long adler32(byte[] data)
{
    long MOD_ADLER = 65521;
    long a = 1, b = 0;
    int index;

    /* Process each byte of the data in order */
    for (index = 0; index < data.length; ++index)
    {
        a = (a + data[index]) % MOD_ADLER;
        b = (b + a) % MOD_ADLER;
    }

    return (b << 16) | a;
}

    public static byte[] longToByteArray(long data) {
        return new byte[]{
                (byte) ((data >> 24) & 0xff),
                (byte) ((data >> 16) & 0xff),
                (byte) ((data >> 8) & 0xff),
                (byte) ((data >> 0) & 0xff),
        };
    }

    private static void printBytes(byte[] bytes) {
        for (int i = 0; i < bytes.length; i++) {
            byte aByte = bytes[i];
            String hex = Integer.toHexString(0xFF & aByte);
            if (hex.length() == 1) {
                // could use a for loop, but we're only dealing with a single byte
                hex = '0'+hex;
            }
            System.out.print(hex + " ");
        }
        System.out.println();
    }

    private static void compressDeflate() {
        int compresionLevel = 0;
        String inputPrefix = "data/original/originalData";
        String outputPrefix = "data/compressed/deflate/" + compresionLevel + "/originalData";
        for (int i = 1; i < 6; i++) {
            String inputPath = inputPrefix + i + ".txt";
            String theOutputPath = outputPrefix + i + ".js";
            System.out.println("Converting: " + inputPath);
            System.out.println("Writing: " + theOutputPath);
            FileReader reader = new FileReader(inputPath);
            FileWriter writer = new FileWriter(theOutputPath);
            DeflateCompressor compressor = new DeflateCompressor(compresionLevel);
            String data = reader.readData();
            StringBuilder builder = new StringBuilder(compressor.compress(data));
            builder.insert(0, "var compressedData" + i + " = \"");
            builder.append("\";");
            writer.writeData(builder.toString());
        }
    }
}
