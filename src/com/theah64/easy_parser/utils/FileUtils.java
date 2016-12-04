package com.theah64.easy_parser.utils;

import java.io.*;

/**
 * Created by theapache64 on 4/12/16.
 */
public class FileUtils {
    public static String read(String inputFile) throws IOException {
        final BufferedReader br = new BufferedReader(new FileReader(inputFile));
        final StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line).append("\n");
        }
        br.close();
        return sb.toString();
    }
}
