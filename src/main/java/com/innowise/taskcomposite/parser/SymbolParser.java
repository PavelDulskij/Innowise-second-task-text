package com.innowise.taskcomposite.parser;

import com.innowise.taskcomposite.component.TextComponentType;
import com.innowise.taskcomposite.component.TextComposite;
import com.innowise.taskcomposite.component.TextLeaf;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SymbolParser extends AbstractTextParser {

    private static final Logger log = LogManager.getLogger(SymbolParser.class);

    @Override
    public void parse(String line, TextComposite parentComposite) {
        log.info("SymbolParser: parsing {} characters", line.length());

        for (int i = 0; i < line.length(); i++) {
            char symbol = line.charAt(i);
            TextLeaf leaf = new TextLeaf(TextComponentType.SYMBOL, symbol);
            parentComposite.add(leaf);

            log.info("Added SYMBOL '{}'", symbol);
        }
    }
}
