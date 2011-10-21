package com.vmykhailyk.compression.deflate;

import org.apache.commons.codec.binary.Base64;

import com.vmykhailyk.compression.CompressorInterface;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.zip.Deflater;

/**
 * Created by Volodymyr.Mykhailyk
 * Date: 15.10.11
 * Time: 16:17
 */
public class DeflateCompressor implements CompressorInterface {
    private Deflater deflater;
    private ByteArrayOutputStream outputStream;
    private int level;

    public DeflateCompressor(int level) {
        this.level = level;
        deflater = new Deflater(level);
        outputStream = new ByteArrayOutputStream();
    }

    public String compress(String data) {
        try {
            deflater = new Deflater(level);
            outputStream = new ByteArrayOutputStream();
            byte[] input = data.getBytes("UTF-8");
            deflater.setInput(input);
            deflater.finish();

            outputStream.reset();

            byte[] buffer = new byte[1024];
            while (!deflater.finished()) {
                int dataLength = deflater.deflate(buffer);
                if (dataLength == 0) {
                    throw new Exception("Compressed data is truncated. Cannot decompress");
                }
                outputStream.write(buffer, 0, dataLength);
            }

            return new String(Base64.encodeBase64(outputStream.toByteArray()));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
