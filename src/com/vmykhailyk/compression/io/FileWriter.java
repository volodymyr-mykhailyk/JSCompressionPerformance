package com.vmykhailyk.compression.io;

/**
 * Created by Volodymyr.Mykhailyk
 * Date: 15.10.11
 * Time: 16:26
 */

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileWriter {
    private FileOutputStream outputStream = null;
    private String outputPath;
    private BufferedWriter out;

    public FileWriter(String theOutputPath) {
        outputPath = theOutputPath;
        try {
            createDirs(outputPath);
            File file = new File(outputPath);
            file.createNewFile();
            out = new BufferedWriter(new java.io.FileWriter(outputPath));
            this.outputStream = new FileOutputStream(outputPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createDirs(String outputPath) {
        File file = new File(outputPath);
        String[] split = file.getAbsolutePath().split("\\\\");
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < split.length - 1; i++) {
            String s = split[i];
            buffer.append(s);
            buffer.append("\\");
        }
        File dirs = new File(buffer.toString());
        dirs.mkdirs();
    }

    public void writeData(String result) {
        try {
/*
            this.out.write(result);
            this.out.close();
*/
            this.outputStream.write(result.getBytes("UTF-8"));
            this.outputStream.flush();
            this.outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
