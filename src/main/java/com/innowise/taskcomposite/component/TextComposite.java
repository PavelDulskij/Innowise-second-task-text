package com.innowise.taskcomposite.component;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class TextComposite implements TextComponent {
    private static final Logger log = LogManager.getLogger(TextComposite.class);
    private static final String PARAGRAPH_SIGN = "\n";
    private static final String SPACE_SIGN = " ";
    private final List<TextComponent> components = new ArrayList<>();
    private final TextComponentType type;

    public TextComposite(TextComponentType type) {
        this.type = type;
        log.info("Created TextComposite of type {}", type);
    }

    @Override
    public void add(TextComponent component) {
        if (component == null) {
            log.warn("Attempted to add null component to {}", type);
            return;
        }
        components.add(component);
        log.info("Added component of type {} to {}", component.getType(), type);
    }

    @Override
    public void remove(TextComponent component) {
        if (component == null) {
            log.warn("Attempted to remove null component from {}", type);
            return;
        }
        if (components.remove(component)) {
            log.info("Removed component of type {} from {}", component.getType(), type);
        } else {
            log.warn("Component not found in {} when attempting to remove", type);
        }
    }

    @Override
    public List<TextComponent> getChildren() {
        log.info("Returned children list of size {} for {}", components.size(), type);
        return components;
    }

    @Override
    public TextComponentType getType() {
        return type;
    }

    @Override
    public String restore() {
        log.info("Restoring text for component of type {}", type);

        StringBuilder builder = new StringBuilder();

        for (TextComponent component : components) {
            builder.append(component.restore());

            switch (component.getType()) {
                case PARAGRAPH -> builder.append(PARAGRAPH_SIGN);
                case SENTENCE -> builder.append(SPACE_SIGN);
                case LEXEME -> builder.append(SPACE_SIGN);
            }
        }

        String result = builder.toString().trim();
        log.info("Restoration completed for {}. Result length: {}", type, result.length());
        return result;
    }

    @Override
    public int getSymbolsCount() {
        int count = 0;
        for (TextComponent component : components) {
            count += component.getSymbolsCount();
        }
        log.info("Calculated symbol count {} for component type {}", count, type);
        return count;
    }

    @Override
    public String toString() {
        log.info("toString() called for {}", type);
        return restore();
    }
}
