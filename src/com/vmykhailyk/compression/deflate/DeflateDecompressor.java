package com.vmykhailyk.compression.deflate;

import com.vmykhailyk.compression.CompressorInterface;
import org.apache.commons.codec.binary.Base64;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

/**
 * Created by Volodymyr.Mykhailyk
 * Date: 15.10.11
 * Time: 16:17
 */
public class DeflateDecompressor {
    private Inflater decompressor;
    private ByteArrayOutputStream outputStream;

    public DeflateDecompressor() {
        decompressor = new Inflater();
        outputStream = new ByteArrayOutputStream();
    }

    public String decompress(String data) {
        try {
            decompressor.reset();
            outputStream.reset();
            decompressor.setInput(Base64.decodeBase64(data));

            byte[] buffer = new byte[1024];
            while (!decompressor.finished()) {
                int dataLength = decompressor.inflate(buffer);
                System.out.println("eeee: "+dataLength);
                if (dataLength == 0) {
                    System.out.println("eeee: "+decompressor.needsInput());
                    return new String(outputStream.toByteArray(), "UTF-8");
                }
                outputStream.write(buffer, 0, dataLength);
            }

            return new String(outputStream.toByteArray(), "UTF-8");
        } catch (DataFormatException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
