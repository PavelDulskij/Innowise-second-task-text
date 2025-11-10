package com.innowise.taskcomposite.parser;

import com.innowise.taskcomposite.component.TextComposite;

public abstract class AbstractTextComponentParser {
    public AbstractTextComponentParser(AbstractTextComponentParser next) {
        this.next = next;
    }

    protected AbstractTextComponentParser next;

    public abstract void parse(String line, TextComposite parentComposite);
}
