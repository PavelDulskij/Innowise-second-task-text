package com.innowise.taskcomposite.parser;

import com.innowise.taskcomposite.component.TextComponentType;
import com.innowise.taskcomposite.component.TextComposite;

public class SentenceParser extends AbstractTextParser {
    public static final String SENTENCE_REGEX = "(?<=[.!?])\\s+";

    @Override
    public void parse(String line, TextComposite parentComposite) {
        String[] sentences = line.split(SENTENCE_REGEX);
        for (String sentence : sentences) {
            TextComposite composite = new TextComposite(TextComponentType.SENTENCE);
            parentComposite.add(composite);
            if (getNext() != null) {
                getNext().parse(sentence.trim(), composite);
            }
        }
    }
}
