package com.innowise.taskcomposite.parser;

import com.innowise.taskcomposite.component.TextComponentType;
import com.innowise.taskcomposite.component.TextComposite;

public class WordParser extends AbstractTextComponentParser{
    public static final String WORD_REGEX = "\\p{L}+(?:['’\\-]\\p{L}+)*";

    public WordParser(AbstractTextComponentParser next) {
        super(next);
    }

    @Override
    public void parse(String line, TextComposite parentComposite) {
        String[] words = line.split(WORD_REGEX);
        for (String word : words) {
            TextComposite composite = new TextComposite(TextComponentType.WORD);
            composite.add(composite);
            next.parse(word, composite);
        }
    }
}
