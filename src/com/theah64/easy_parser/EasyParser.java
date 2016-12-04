package com.theah64.easy_parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by theapache64 on 4/12/16.
 */
class EasyParser {
    static List<String> parse(String fileData, String regEx, boolean isDontKeepDuplicate) {
        final Matcher matcher = Pattern.compile(regEx).matcher(fileData);
        List<String> nodes = null;
        while (matcher.find()) {
            if (nodes == null) {
                nodes = new ArrayList<>();
            }

            final StringBuilder sb = new StringBuilder();
            for (int i = 0; i < matcher.groupCount(); i++) {
                sb.append(matcher.group(i + 1));
            }

            final String data = sb.toString();
            if (!nodes.contains(data)) {
                nodes.add(data);
            }

        }
        return nodes;
    }
}
