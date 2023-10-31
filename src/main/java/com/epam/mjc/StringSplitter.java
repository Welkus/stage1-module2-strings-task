package com.epam.mjc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;

public class StringSplitter {

    /**
     * Splits given string applying all delimeters to it. Keeps order of result substrings as in source string.
     *
     * @param source source string
     * @param delimiters collection of delimiter strings
     * @return List of substrings
     */
    public List<String> splitByDelimiters(String source, Collection<String> delimiters) {

        if(source == null || delimiters == null){
            throw new UnsupportedOperationException("You should implement this method.");

        }
        List<String> list = new ArrayList<>();

        StringBuilder regexBuilder = new StringBuilder();

        for (String delimiter : delimiters){
            if(regexBuilder.length() > 0){
                regexBuilder.append("|");
            }
            regexBuilder.append(delimiter);
        }

        String regex = regexBuilder.toString();

        String [] done = source.split(regex);

        for (String s : done){
           if(!s.isEmpty()) list.add(s);

        }

        return  list;

    }
}
