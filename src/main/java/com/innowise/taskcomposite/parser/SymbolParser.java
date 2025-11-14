package com.innowise.taskcomposite.parser;

import com.innowise.taskcomposite.component.TextComponentType;
import com.innowise.taskcomposite.component.TextComposite;
import com.innowise.taskcomposite.component.TextLeaf;

public class SymbolParser extends AbstractTextParser {

    @Override
    public void parse(String line, TextComposite parentComposite) {
        for (int i = 0; i < line.length(); i++) {
            char symbol = line.charAt(i);
            TextLeaf leaf = new TextLeaf(TextComponentType.SYMBOL, symbol);
            parentComposite.add(leaf);
        }
    }
}
