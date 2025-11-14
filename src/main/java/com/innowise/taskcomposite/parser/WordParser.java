package com.innowise.taskcomposite.parser;

import com.innowise.taskcomposite.component.TextComponentType;
import com.innowise.taskcomposite.component.TextComposite;

public class WordParser extends AbstractTextParser {
    public static final String WORD_REGEX = "\\p{Alpha}";

    @Override
    public void parse(String line, TextComposite parentComposite) {
        String[] words = line.split(WORD_REGEX);
        for (String word : words) {
            TextComposite composite = new TextComposite(TextComponentType.WORD);
            parentComposite.add(composite);
            if (getNext() != null) {
                getNext().parse(word.trim(), composite);
            }
        }
    }
}
