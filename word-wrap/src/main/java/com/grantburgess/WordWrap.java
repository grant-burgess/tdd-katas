package com.grantburgess;

public class WordWrap {
    private final int length;

    private WordWrap(int length) {
        this.length = length;
    }

    static String wrap(String sentence, int length) {
        return new WordWrap(length).wrap(sentence);
    }

    public String wrap(String sentence) {
        if (sentence == null)
            return "";

        String trimmedSentence = sentence.trim();

        if (trimmedSentence.length() <= length)
            return trimmedSentence;

        return breakLine(trimmedSentence);
    }

    private String breakLine(String trimmedSentence) {
        int breakPoint = trimmedSentence.substring(0, length).lastIndexOf(" ");

        return sentenceIncludesSpace(breakPoint) ?
                breakLine(trimmedSentence, breakPoint) :
                breakLine(trimmedSentence, length);
    }

    private boolean sentenceIncludesSpace(int space) {
        return space != -1;
    }

    private String breakLine(String trimmedSentence, int breakPoint) {
        return trimmedSentence.substring(0, breakPoint) + "\n" + wrap(trimmedSentence.substring(breakPoint), length);
    }
}
