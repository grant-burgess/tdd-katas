package com.grantburgess;

import java.util.Arrays;

public class NameInverter {
    public static final String HONORIFICS = "mr\\.|mrs\\.|dr\\.";

    public String invertName(String name) {
        if (isNullOrBlank(name))
            return "";

        return formatName(removeHonorifics(splitNames(name)));
    }

    private String[] removeHonorifics(String[] names) {
        return Arrays.stream(names)
                .filter(this::isNotHonorific)
                .toArray(String[]::new);
    }

    private boolean isNotHonorific(String x) {
        return !x.toLowerCase().matches(HONORIFICS);
    }

    private String formatName(String[] names) {
        if (names.length == 1)
            return names[0];

        return formatMultiElementName(names);
    }

    private String formatMultiElementName(String[] names) {
        String lastName = names[1];
        String firstName = names[0];
        String postNominals = getPostNominals(names);

        return lastName + ", " + firstName + postNominals;
    }

    private String getPostNominals(String[] names) {
        String result = "";
        for (String postNominal : Arrays.asList(names).subList(2, names.length))
            result += " " + postNominal;
        return result;
    }

    private String[] splitNames(String name) {
        return name.trim().split("\\s+");
    }

    private boolean isNullOrBlank(String name) {
        return null == name || "".equals(name.trim());
    }
}
