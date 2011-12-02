package com.vmykhailyk.compression.lzw;

/**
 * Created by Volodymyr.Mykhailyk
 * Date: 01.12.11
 * Time: 16:05
 */
import com.vmykhailyk.compression.CompressorInterface;
import com.vmykhailyk.compression.DecompresorInterface;
import org.apache.commons.codec.binary.Base64;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.*;

public class LZWModule implements DecompresorInterface, CompressorInterface {
    /** Compress a string to a list of output symbols. */
    private List<Integer> compressData(String uncompressed) {
        // Build the dictionary.
        int dictSize = 256;
        Map<String,Integer> dictionary = new HashMap<String,Integer>();
        for (int i = 0; i < 256; i++)
            dictionary.put("" + (char)i, i);

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

    /** Decompress a list of output ks to a string. */
    public String decompressData(List<Integer> compressed) {
        // Build the dictionary.
        int dictSize = 256;
        Map<Integer,String> dictionary = new HashMap<Integer,String>();
        for (int i = 0; i < 256; i++)
            dictionary.put(i, "" + (char)i);

        String w = "" + (char)(int)compressed.remove(0);
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
        ByteBuffer buffer = ByteBuffer.wrap(Base64.decodeBase64(data));
        IntBuffer intBuffer = buffer.asIntBuffer();
        ArrayList<Integer> result = new ArrayList<Integer>();
        while (intBuffer.hasRemaining()) {
            result.add(intBuffer.get());
        }

        return decompressData(result);
    }

    public String compress(String data) {
        List<Integer> integers = compressData(data);
        ByteBuffer buffer = ByteBuffer.allocate(integers.size() * 4);
        for (Integer integer : integers) {
            buffer.putInt(integer);
        }
        return Base64.encodeBase64String(buffer.array());
    }
}