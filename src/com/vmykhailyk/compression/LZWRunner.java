package com.vmykhailyk.compression;

import com.vmykhailyk.compression.deflate.DeflateCompressor;
import com.vmykhailyk.compression.deflate.DeflateDecompressor;
import com.vmykhailyk.compression.io.FileReader;
import com.vmykhailyk.compression.io.FileWriter;
import com.vmykhailyk.compression.lzw.LZWModule;
import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.util.zip.Adler32;

/**
 * Created by Volodymyr.Mykhailyk
 * Date: 15.10.11
 * Time: 16:13
 */
public class LZWRunner {
    public static void main(String[] args) throws UnsupportedEncodingException {
        LZWModule module = new LZWModule();
        String compressed = module.compress("test");
        System.out.println(compressed);
        String uncompressed = module.decompress(compressed);
        System.out.println(uncompressed);
    }

}
