package com.innowise.taskcomposite.parser;

import com.innowise.taskcomposite.component.TextComposite;

public abstract class AbstractTextParser {
    private AbstractTextParser next;

    public AbstractTextParser() { }

    public AbstractTextParser setNextParser(AbstractTextParser nextParser) {
        this.next = nextParser;
        return nextParser;
    }

    public AbstractTextParser getNext() {
        return next;
    }

    public abstract void parse(String line, TextComposite parentComposite);
}
