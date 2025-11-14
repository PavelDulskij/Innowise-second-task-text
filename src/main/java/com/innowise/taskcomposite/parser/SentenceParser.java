package com.innowise.taskcomposite.parser;

import com.innowise.taskcomposite.component.TextComponentType;
import com.innowise.taskcomposite.component.TextComposite;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SentenceParser extends AbstractTextParser {

    private static final Logger log = LogManager.getLogger(SentenceParser.class);
    public static final String SENTENCE_REGEX = "(?<=[.!?])\\s+";

    @Override
    public void parse(String line, TextComposite parentComposite) {
        log.info("SentenceParser: splitting text into sentences...");

        String[] sentences = line.split(SENTENCE_REGEX);
        log.info("Found {} sentences", sentences.length);

        for (String sentence : sentences) {
            TextComposite composite = new TextComposite(TextComponentType.SENTENCE);
            parentComposite.add(composite);
            log.info("Added SENTENCE component");

            if (getNext() != null) {
                log.info("Passing sentence to next parser...");
                getNext().parse(sentence.trim(), composite);
            } else {
                log.warn("Next parser is NULL in SentenceParser");
            }
        }
    }
}
