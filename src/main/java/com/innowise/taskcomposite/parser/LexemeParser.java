package com.innowise.taskcomposite.parser;

import com.innowise.taskcomposite.component.TextComponentType;
import com.innowise.taskcomposite.component.TextComposite;

public class LexemeParser extends AbstractTextComponentParser{
    public static final String LEXEME_REGEX = "\\s+";

    public LexemeParser(AbstractTextComponentParser next) {
        super(next);
    }

    @Override
    public void parse(String line, TextComposite parentComposite) {
        String[] lexemes = line.split(LEXEME_REGEX);
        for(String lexeme: lexemes) {
            TextComposite composite = new TextComposite(TextComponentType.LEXEME);
            composite.add(composite);
            next.parse(lexeme, composite);
        }
    }
}
