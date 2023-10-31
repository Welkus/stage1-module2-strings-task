package com.epam.mjc;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     * 1. access modifier - optional, followed by space: ' '
     * 2. return type - followed by space: ' '
     * 3. method name
     * 4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     * accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     * private void log(String value)
     * Vector3 distort(int x, int y, int z, float magnitude)
     * public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {
        List<MethodSignature.Argument> list = new ArrayList<>();

        Pattern pattern = Pattern.compile("^(\\w+ )?(\\w+ )?(\\w+)\\(([^)]*)\\)");
        Matcher matcher = pattern.matcher(signatureString);

        if (!matcher.find()) {
            throw new IllegalArgumentException("Invalid method signature");
        }

        String accessModifier = null;
        String returnType;
        String name;

        if (matcher.group(2) == null) {
            returnType = matcher.group(1);
            name = matcher.group(3);
        } else {
            accessModifier = matcher.group(1).trim();
            returnType = matcher.group(2).trim();
            name = matcher.group(3);
        }

        String[] signatureParameters = matcher.group(4).trim().split(", ");

        for (int i = 0; i < signatureParameters.length; i++) {
            String[] argPair = signatureParameters[i].split(" ");
            if (argPair.length == 2) {
                list.add(new MethodSignature.Argument(argPair[0], argPair[1]));
            }
        }

        MethodSignature ms = new MethodSignature(name, list);

        if (accessModifier != null) {
            ms.setAccessModifier(accessModifier.trim());
        }
        if (returnType != null) {
            ms.setReturnType(returnType.trim());
        }

        return ms;
    }
}
