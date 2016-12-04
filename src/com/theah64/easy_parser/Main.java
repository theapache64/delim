package com.theah64.easy_parser;

import com.theah64.easy_parser.utils.FileUtils;
import org.apache.commons.cli.*;

import java.io.IOException;
import java.util.List;

//target syntax: ep (-f (file-path)) (-r (reg-ex)) (-o "@%s,")
public class Main {

    private static final String FLAG_INPUT_FILE = "f";
    private static final String FLAG_REGEX = "r";
    private static final String FLAG_OUTPUT_FORMAT = "o";
    private static final String FLAG_DONT_KEEP_DUPLICATES = "d";

    private static final Options OPTIONS = new Options()
            .addOption(FLAG_INPUT_FILE, true, "Input file path")
            .addOption(FLAG_REGEX, true, "Regular expression")
            .addOption(FLAG_DONT_KEEP_DUPLICATES, false, "Don't keep dupilicate nodes")
            .addOption(FLAG_OUTPUT_FORMAT, true, "Ouput format");

    private static final CommandLineParser parser = new DefaultParser();

    public static void main(String[] args) {

        //args = new String[]{"-f", "/home/theapache64/Desktop/test_file.html", "-r", "<a href=\"\\/(.+)\" class=\"author\">", "-o", "@%s,"};

        try {
            final CommandLine cl = parser.parse(OPTIONS, args);

            final String inputFile = cl.getOptionValue(FLAG_INPUT_FILE);

            //checking inpout file
            if (inputFile != null && !inputFile.isEmpty()) {

                final String regEx = cl.getOptionValue(FLAG_REGEX);

                //checking regEx
                if (regEx != null && !regEx.isEmpty()) {

                    final String outputFormat = cl.getOptionValue(FLAG_OUTPUT_FORMAT);

                    if (outputFormat != null && !outputFormat.isEmpty()) {

                        //Reading file data
                        final String fileData = FileUtils.read(inputFile);
                        final boolean isDontKeepDuplicate = cl.hasOption(FLAG_DONT_KEEP_DUPLICATES);

                        final List<String> dataList = EasyParser.parse(fileData, regEx, isDontKeepDuplicate);

                        if (dataList != null) {

                            for (final String data : dataList) {
                                System.out.print(String.format(outputFormat, data));
                            }

                        } else {
                            throw new EasyParserException("No match found");
                        }


                    } else {
                        throw new EasyParserException("Output format not given (-o flag)");
                    }

                } else {
                    throw new EasyParserException("RegEx not given (-r flag)");
                }


            } else {
                throw new EasyParserException("Input file not given (-f flag)");
            }
        } catch (ParseException | EasyParserException | IOException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    private static class EasyParserException extends Exception {
        public EasyParserException(String message) {
            super(message);
        }
    }
}
