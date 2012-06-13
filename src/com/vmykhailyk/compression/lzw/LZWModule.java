package com.vmykhailyk.compression.lzw;

/**
 * Created by Volodymyr.Mykhailyk
 * Date: 01.12.11
 * Time: 16:05
 */

import com.vmykhailyk.compression.CompressorInterface;
import com.vmykhailyk.compression.DecompresorInterface;
import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.*;

public class LZWModule implements DecompresorInterface, CompressorInterface {
    /**
     * Compress a string to a list of output symbols.
     */
    private List<Integer> compressData(String uncompressed) {
        // Build the dictionary.
        int dictSize = 256;
        Map<String, Integer> dictionary = new HashMap<String, Integer>();
        for (int i = 0; i < 256; i++)
            dictionary.put("" + (char) i, i);

        String w = "";
        List<Integer> result = new ArrayList<Integer>();
        for (char c : uncompressed.toCharArray()) {
            String wc = w + c;
            if (dictionary.containsKey(wc))
                w = wc;
            else {
                result.add(dictionary.get(w));
                // Add wc to the dictionary.
                dictionary.put(wc, dictSize++);
                w = "" + c;
            }
        }

        // Output the code for w.
        if (!w.equals(""))
            result.add(dictionary.get(w));
        return result;
    }

    /**
     * Decompress a list of output ks to a string.
     */
    public String decompressData(List<Integer> compressed) {
        // Build the dictionary.
        int dictSize = 256;
        Map<Integer, String> dictionary = new HashMap<Integer, String>();
        for (int i = 0; i < 256; i++)
            dictionary.put(i, "" + (char) i);

        String w = "" + (char) (int) compressed.remove(0);
        String result = w;
        for (int k : compressed) {
            String entry;
            if (dictionary.containsKey(k))
                entry = dictionary.get(k);
            else if (k == dictSize)
                entry = w + w.charAt(0);
            else
                throw new IllegalArgumentException("Bad compressed k: " + k);

            result += entry;

            // Add w+entry[0] to the dictionary.
            dictionary.put(dictSize++, w + entry.charAt(0));

            w = entry;
        }
        return result;
    }

    public String decompress(String data) {
        try {
            String[] intsAsString = data.split(",");
            ArrayList<Integer> integers = new ArrayList<Integer>();
            for (String anIntsAsString : intsAsString) {
                integers.add(Integer.parseInt(anIntsAsString));
            }

            String decompressed = decompressData(integers);
            return new String(getCharsAsBytes(decompressed), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    private byte[] getCharsAsBytes(String decompressed) {
        int length = decompressed.length();
        ByteBuffer buffer = ByteBuffer.allocate(length);
        for (int i = 0; i < length; i++) {
            buffer.put((byte) decompressed.codePointAt(i));
        }
        return buffer.array();
    }

    public String compress(String data) {
        try {
            byte[] bytes = data.getBytes("UTF-8");
            data = convertToString(bytes);
            List<Integer> integers = compressData(data);
            String stringArray = integers.toString();
            String trimmed = stringArray.substring(1, stringArray.length() - 1);
            return trimmed.replace(", ", ",");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    private String convertToString(byte[] bytes) throws UnsupportedEncodingException {
        StringBuilder builder = new StringBuilder();
        for (byte aByte : bytes) {
            builder.append((char) (0xFF & aByte));
        }
        return builder.toString();
    }
}