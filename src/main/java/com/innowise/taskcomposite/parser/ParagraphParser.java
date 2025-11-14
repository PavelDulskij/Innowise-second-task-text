package com.innowise.taskcomposite.parser;

import com.innowise.taskcomposite.component.TextComponentType;
import com.innowise.taskcomposite.component.TextComposite;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ParagraphParser extends AbstractTextParser {

    private static final Logger log = LogManager.getLogger(ParagraphParser.class);
    public static final String PARAGRAPH_REGEX = "\\R+";

    @Override
    public void parse(String text, TextComposite parentComposite) {
        log.info("ParagraphParser: parsing text into paragraphs...");

        String[] paragraphs = text.split(PARAGRAPH_REGEX);
        log.info("Found {} paragraphs", paragraphs.length);

        for (String paragraph : paragraphs) {
            TextComposite composite = new TextComposite(TextComponentType.PARAGRAPH);
            parentComposite.add(composite);
            log.info("Added PARAGRAPH component");

            if (getNext() != null) {
                log.info("Passing paragraph to next parser...");
                getNext().parse(paragraph.trim(), composite);
            } else {
                log.warn("Next parser is NULL in ParagraphParser");
            }
        }
    }
}
