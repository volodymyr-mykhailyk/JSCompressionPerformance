package com.vmykhailyk.compression.io;

import java.io.*;

/**
 * Created by Volodymyr.Mykhailyk
 * Date: 15.10.11
 * Time: 16:27
 */
public class FileReader {
    String inputPath;
    private BufferedReader bufferedReader;

    public FileReader(String inputPath) {
        try {
            this.inputPath = inputPath;
            FileInputStream fstream = new FileInputStream(this.inputPath);
            bufferedReader = new BufferedReader(new InputStreamReader(fstream));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String readData() {
        try {
            StringBuilder builder = new StringBuilder();
            String strLine;
            while ((strLine = bufferedReader.readLine()) != null) {
                // Print the content on the console
                builder.append(strLine);
                builder.append("\n");
            }
            return builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
