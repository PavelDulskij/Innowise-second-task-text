package com.innowise.taskcomposite.component;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TextCompositeTest {
    @Test
    void whenAddComponentThenShouldAppearInChildren() {
        TextComposite sentence = new TextComposite(TextComponentType.SENTENCE);
        TextComponent symbol = new TextLeaf(TextComponentType.SYMBOL,'A');

        sentence.add(symbol);

        assertEquals(1, sentence.getChildren().size());
        assertEquals(symbol, sentence.getChildren().get(0));
    }

    @Test
    void whenRemoveComponentThenChildrenShouldBeEmpty() {
        TextComposite sentence = new TextComposite(TextComponentType.SENTENCE);
        TextComponent symbol = new TextLeaf(TextComponentType.SYMBOL,'A');

        sentence.add(symbol);
        sentence.remove(symbol);

        assertTrue(sentence.getChildren().isEmpty());
    }

    @Test
    void whenRestoreLexemeThenShouldReturnJoinedSymbols() {
        TextComposite lexeme = new TextComposite(TextComponentType.LEXEME);
        lexeme.add(new TextLeaf(TextComponentType.SYMBOL,'H'));
        lexeme.add(new TextLeaf(TextComponentType.SYMBOL,'i'));

        assertEquals("Hi", lexeme.restore());
    }

    @Test
    void whenRestoreSentenceThenLexemesSeparatedBySpace() {
        TextComposite sentence = new TextComposite(TextComponentType.SENTENCE);

        TextComposite lex1 = new TextComposite(TextComponentType.LEXEME);
        lex1.add(new TextLeaf(TextComponentType.SYMBOL,'A'));

        TextComposite lex2 = new TextComposite(TextComponentType.LEXEME);
        lex2.add(new TextLeaf(TextComponentType.SYMBOL,'B'));

        sentence.add(lex1);
        sentence.add(lex2);

        assertEquals("A B", sentence.restore());
    }

    @Test
    void whenRestoreTextThenParagraphsSeparatedByNewLine() {
        TextComposite paragraph1 = new TextComposite(TextComponentType.PARAGRAPH);
        TextComposite paragraph = new TextComposite(TextComponentType.PARAGRAPH);
        TextComposite sentence1 = new TextComposite(TextComponentType.SENTENCE);
        sentence1.add(new TextLeaf(TextComponentType.SYMBOL,'A'));
        paragraph1.add(sentence1);

        TextComposite paragraph2 = new TextComposite(TextComponentType.PARAGRAPH);
        TextComposite sentence2 = new TextComposite(TextComponentType.SENTENCE);
        sentence2.add(new TextLeaf(TextComponentType.SYMBOL,'B'));
        paragraph2.add(sentence2);
        paragraph.add(paragraph1);
        paragraph.add(paragraph2);

        assertEquals("A\nB", paragraph.restore());
    }

    @Test
    void whenRestoreNestedComponentsThenShouldReconstructFullText() {
        TextComposite paragraph = new TextComposite(TextComponentType.PARAGRAPH);
        TextComposite sentence = new TextComposite(TextComponentType.SENTENCE);
        TextComposite lexeme = new TextComposite(TextComponentType.LEXEME);

        lexeme.add(new TextLeaf(TextComponentType.SYMBOL,'H'));
        lexeme.add(new TextLeaf(TextComponentType.SYMBOL,'i'));

        sentence.add(lexeme);
        paragraph.add(sentence);

        assertEquals("Hi", paragraph.restore());
    }

    @Test
    void whenGetSymbolsCountThenShouldSumAllSymbolLeaves() {
        TextComposite lexeme = new TextComposite(TextComponentType.LEXEME);

        lexeme.add(new TextLeaf(TextComponentType.SYMBOL,'X'));
        lexeme.add(new TextLeaf(TextComponentType.SYMBOL,'Y'));
        lexeme.add(new TextLeaf(TextComponentType.SYMBOL,'Z'));

        assertEquals(3, lexeme.getSymbolsCount());
    }

    @Test
    void whenToStringCalledThenShouldReturnSameAsRestore() {
        TextComposite lexeme = new TextComposite(TextComponentType.LEXEME);
        lexeme.add(new TextLeaf(TextComponentType.SYMBOL,'O'));
        lexeme.add(new TextLeaf(TextComponentType.SYMBOL,'K'));

        assertEquals("OK", lexeme.toString());
    }

    @Test
    void whenCompositeEmptyThenRestoreReturnsEmptyString() {
        TextComposite composite = new TextComposite(TextComponentType.PARAGRAPH);
        assertEquals("", composite.restore());
    }

    @Test
    void whenSingleSymbolThenRestoreReturnsIt() {
        TextComposite lexeme = new TextComposite(TextComponentType.LEXEME);
        lexeme.add(new TextLeaf(TextComponentType.SYMBOL,'X'));

        assertEquals("X", lexeme.restore());
    }
}