package com.innowise.taskcomposite.service;

import com.innowise.taskcomposite.component.TextComponent;
import com.innowise.taskcomposite.component.TextComponentType;
import com.innowise.taskcomposite.component.TextComposite;
import com.innowise.taskcomposite.service.impl.TextServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TextServiceTest {

    private final TextServiceImpl service = new TextServiceImpl();



    @Test
    void testMaxSentencesWithSameWordsCount() {
        TextComponent s1 = sentence("Hello", "world");
        TextComponent s2 = sentence("Hello", "Belarus");
        TextComponent s3 = sentence("world", "world");

        TextComposite p = paragraph(s1, s2, s3);
        TextComposite root = text(p);

        int result = service.maxSentencesWithSameWordsCount(root);

        assertEquals(2, result);
    }

    @Test
    void testSortByLexemesCount() {
        TextComponent s1 = sentence("A");
        TextComponent s2 = sentence("A", "B");
        TextComponent s3 = sentence("A", "B", "C");

        TextComposite p = paragraph(s1, s2, s3);
        TextComposite root = text(p);

        List<TextComponent> sorted = service.sortByLexemesCount(root);

        assertEquals(s1, sorted.get(0));
        assertEquals(s2, sorted.get(1));
        assertEquals(s3, sorted.get(2));
    }

    @Test
    void testSwapFirstAndLastLexeme() {
        TextComponent s = sentence("Hello", "my", "friend");
        TextComposite p = paragraph(s);
        TextComposite root = text(p);

        service.swapFirstAndLastLexeme(root);

        TextComponent resultSentence = p.getChildren().get(0);
        List<TextComponent> lexemes = resultSentence.getChildren();

        assertEquals("friend", lexemes.get(0).restore());
        assertEquals("my", lexemes.get(1).restore());
        assertEquals("Hello", lexemes.get(2).restore());
    }

    @Test
    void testSwapIgnoredIfOnlyOneLexeme() {
        TextComponent s = sentence("OnlyOne");
        TextComposite p = paragraph(s);
        TextComposite root = text(p);

        service.swapFirstAndLastLexeme(root);

        TextComponent resultSentence = p.getChildren().get(0);
        List<TextComponent> lexemes = resultSentence.getChildren();

        assertEquals(1, lexemes.size());
        assertEquals("OnlyOne", lexemes.get(0).restore());
    }
    private TextComponent lexeme(String text) {
        TextComposite lexeme = new TextComposite(TextComponentType.LEXEME);
        for (char c : text.toCharArray()) {
            lexeme.add(new com.innowise.taskcomposite.component.TextLeaf(TextComponentType.SYMBOL, c));
        }
        return lexeme;
    }

    private TextComponent sentence(String... lexemes) {
        TextComposite s = new TextComposite(TextComponentType.SENTENCE);
        for (String l : lexemes) {
            s.add(lexeme(l));
        }
        return s;
    }

    private TextComposite paragraph(TextComponent... sentences) {
        TextComposite p = new TextComposite(TextComponentType.PARAGRAPH);
        for (TextComponent s : sentences) {
            p.add(s);
        }
        return p;
    }

    private TextComposite text(TextComposite... paragraphs) {
        TextComposite t = new TextComposite(TextComponentType.TEXT);
        for (TextComposite p : paragraphs) {
            t.add(p);
        }
        return t;
    }
}
