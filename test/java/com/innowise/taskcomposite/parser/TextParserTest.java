package com.innowise.taskcomposite.parser;

import com.innowise.taskcomposite.component.TextComponent;
import com.innowise.taskcomposite.component.TextComponentType;
import com.innowise.taskcomposite.component.TextComposite;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TextParserTest {
    @Test
    void testParseLines() {
        TextComposite root = new TextComposite(TextComponentType.TEXT);

        ParagraphParser paragraph = new ParagraphParser();
        SentenceParser sentence = new SentenceParser();
        LexemeParser lexeme = new LexemeParser();

        paragraph.setNextParser(sentence);
        sentence.setNextParser(lexeme);

        String text = "Hello world!\nI am from Belarus.";

        paragraph.parse(text, root);

        assertEquals(2, root.getChildren().size());

        TextComponent p1 = root.getChildren().get(0);
        assertEquals(1, p1.getChildren().size());

        TextComponent s1 = p1.getChildren().get(0);
        assertEquals(2, s1.getChildren().size());

        assertEquals(TextComponentType.LEXEME, s1.getChildren().get(0).getType());
    }
}