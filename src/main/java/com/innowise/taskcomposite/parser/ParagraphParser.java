package com.innowise.taskcomposite.parser;

import com.innowise.taskcomposite.component.TextComponentType;
import com.innowise.taskcomposite.component.TextComposite;

public class ParagraphParser extends AbstractTextComponentParser{
    public static final String PARAGRAPH_REGEX = ("\\R+");

    public ParagraphParser(AbstractTextComponentParser next) {
        super(next);
    }

    @Override
    public void parse(String text, TextComposite parentComposite) {
        String[] paragraphs = text.split(PARAGRAPH_REGEX);
        for (String paragraph : paragraphs) {
            TextComposite composite = new TextComposite(TextComponentType.PARAGRAPH);
            parentComposite.add(composite);
            next.parse(paragraph, composite);
        }
    }
}
