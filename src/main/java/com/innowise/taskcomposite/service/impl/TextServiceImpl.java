package com.innowise.taskcomposite.service.impl;

import com.innowise.taskcomposite.component.TextComponent;
import com.innowise.taskcomposite.component.TextComponentType;
import com.innowise.taskcomposite.component.TextComposite;
import com.innowise.taskcomposite.service.TextService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.stream.Collectors;

public class TextServiceImpl implements TextService {
    public static final String WORD_REGEX = "[^\\p{Alpha}]+";
    public static final Logger log = LogManager.getLogger();

    @Override
    public int maxSentencesWithSameWordsCount(TextComposite text) {
        List<TextComponent> sentences = getSentences(text);

        Map<String, Integer> wordFrequency = new HashMap<>();

        for (TextComponent sentence : sentences) {
            Set<String> uniqueWords = extractWords(sentence);

            for (String w : uniqueWords) {
                wordFrequency.merge(w, 1, Integer::sum);
            }
        }

        return wordFrequency.values()
                .stream()
                .max(Integer::compareTo)
                .orElse(0);
    }

    @Override
    public List<TextComponent> sortByLexemesCount(TextComposite text) {
        List<TextComponent> sentences = getSentences(text);

        return sentences.stream()
                .sorted(Comparator.comparingInt(this::countLexemes))
                .collect(Collectors.toList());
    }

    @Override
    public void swapFirstAndLastLexeme(TextComposite text) {
        List<TextComponent> sentences = getSentences(text);

        for (TextComponent sentence : sentences) {

            List<TextComponent> lexemes = getLexemes(sentence);

            if (lexemes.size() < 2) continue;

            TextComponent first = lexemes.get(0);
            TextComponent last = lexemes.get(lexemes.size() - 1);

            List<TextComponent> children = sentence.getChildren();
            int i1 = children.indexOf(first);
            int i2 = children.indexOf(last);

            Collections.swap(children, i1, i2);
        }
    }
    private List<TextComponent> getSentences(TextComposite text) {
        List<TextComponent> sentences = new ArrayList<>();

        for (TextComponent paragraph : text.getChildren()) {
            if (paragraph.getType() == TextComponentType.PARAGRAPH) {
                sentences.addAll(paragraph.getChildren());
            }
        }
        return sentences;
    }
    private Set<String> extractWords(TextComponent component) {
        Set<String> words = new HashSet<>();

        if (component.getType() == TextComponentType.LEXEME) {
            String lexeme = component.restore().toLowerCase();
            String word = lexeme.replaceAll(WORD_REGEX, "");
            if (!word.isEmpty()) {
                words.add(word);
            }
            return words;
        }

        for (TextComponent child : component.getChildren()) {
            words.addAll(extractWords(child));
        }

        return words;
    }
    private List<TextComponent> getLexemes(TextComponent sentence) {
        List<TextComponent> result = new ArrayList<>();

        for (TextComponent child : sentence.getChildren()) {
            if (child.getType() == TextComponentType.LEXEME) {
                result.add(child);
            }
        }

        return result;
    }
    private int countLexemes(TextComponent sentence) {
        return (int) sentence.getChildren().stream()
                .filter(c -> c.getType() == TextComponentType.LEXEME)
                .count();
    }
}
