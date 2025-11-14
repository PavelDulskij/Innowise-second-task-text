package com.innowise.taskcomposite.parser;

import com.innowise.taskcomposite.component.TextComponentType;
import com.innowise.taskcomposite.component.TextComposite;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WordParser extends AbstractTextParser {

    private static final Logger log = LogManager.getLogger(WordParser.class);
    public static final String WORD_REGEX = "\\p{Alpha}";

    @Override
    public void parse(String line, TextComposite parentComposite) {
        log.info("WordParser: splitting lexeme into word tokens...");

        String[] words = line.split(WORD_REGEX);
        log.info("Found {} word parts", words.length);

        for (String w : words) {
            TextComposite composite = new TextComposite(TextComponentType.WORD);
            parentComposite.add(composite);
            log.info("Added WORD component with raw data '{}'", w);

            if (getNext() != null) {
                log.info("Passing word part to next parser...");
                getNext().parse(w.trim(), composite);
            } else {
                log.warn("Next parser is NULL in WordParser");
            }
        }
    }
}
