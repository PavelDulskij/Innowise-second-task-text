package com.innowise.taskcomposite.component;

import java.util.Collections;
import java.util.List;

public class TextLeaf implements TextComponent{
    private final TextComponentType type;
    private final char value;

    public TextLeaf(TextComponentType type, char value) {
        this.type = type;
        this.value = value;
    }

    @Override
    public void add(TextComponent component) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void remove(TextComponent component) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<TextComponent> getChildren() {
        return Collections.emptyList();
    }

    @Override
    public TextComponentType getType() {
        return type;
    }

    @Override
    public String restore() {
        return String.valueOf(value);
    }

    @Override
    public int getSymbolsCount() {
        return 1;
    }
}
