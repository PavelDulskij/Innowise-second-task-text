package com.innowise.taskcomposite.parser;

import com.innowise.taskcomposite.component.TextComponentType;
import com.innowise.taskcomposite.component.TextComposite;

public class SentenceParser extends AbstractTextComponentParser{
    public static final String SENTENCE_REGEX = "(?<=[.!?])\\s+";

    public SentenceParser(AbstractTextComponentParser next) {
        super(next);
    }

    @Override
    public void parse(String line, TextComposite parentComposite) {
        String[] sentences = line.split(SENTENCE_REGEX);
        for (String sentence : sentences) {
            TextComposite composite = new TextComposite(TextComponentType.SENTENCE);
            parentComposite.add(composite);
            next.parse(sentence, composite);
        }
    }
}
