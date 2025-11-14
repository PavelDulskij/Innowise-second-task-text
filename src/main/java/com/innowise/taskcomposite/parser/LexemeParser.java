package com.innowise.taskcomposite.parser;

import com.innowise.taskcomposite.component.TextComponentType;
import com.innowise.taskcomposite.component.TextComposite;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LexemeParser extends AbstractTextParser {

    private static final Logger log = LogManager.getLogger(LexemeParser.class);
    public static final String LEXEME_REGEX = "\\s+";

    @Override
    public void parse(String line, TextComposite parentComposite) {
        log.info("LexemeParser: splitting sentence into lexemes...");

        String[] lexemes = line.split(LEXEME_REGEX);
        log.info("Found {} lexemes", lexemes.length);

        for (String lexeme : lexemes) {
            TextComposite composite = new TextComposite(TextComponentType.LEXEME);
            parentComposite.add(composite);
            log.info("Added LEXEME: '{}'", lexeme);

            if (getNext() != null) {
                log.info("Passing lexeme to next parser...");
                getNext().parse(lexeme.trim(), composite);
            } else {
                log.warn("Next parser is NULL in LexemeParser");
            }
        }
    }
}
