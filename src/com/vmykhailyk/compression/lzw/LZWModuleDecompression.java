package com.vmykhailyk.compression.lzw;

/**
 * Created by Volodymyr.Mykhailyk
 * Date: 01.12.11
 * Time: 16:05
 */

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LZWModuleDecompression {
    /**
     * Decompress a list of output ks to a string.
     */
    private String decompressData(List<Integer> compressed) {
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
}