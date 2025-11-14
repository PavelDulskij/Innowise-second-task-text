package com.innowise.taskcomposite.parser;

import com.innowise.taskcomposite.component.TextComponentType;
import com.innowise.taskcomposite.component.TextComposite;

public class LexemeParser extends AbstractTextParser {
    public static final String LEXEME_REGEX = "\\s+";

    @Override
    public void parse(String line, TextComposite parentComposite) {
        String[] lexemes = line.split(LEXEME_REGEX);
        for(String lexeme: lexemes) {
            TextComposite composite = new TextComposite(TextComponentType.LEXEME);
            parentComposite.add(composite);
            if (getNext() != null) {
                getNext().parse(lexeme.trim(), composite);
            }
        }
    }
}
