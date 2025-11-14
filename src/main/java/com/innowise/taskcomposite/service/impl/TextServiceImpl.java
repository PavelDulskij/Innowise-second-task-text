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
        log.info("Starting maxSentencesWithSameWordsCount...");

        List<TextComponent> sentences = getSentences(text);
        log.info("Extracted {} sentences", sentences.size());

        Map<String, Integer> wordFrequency = new HashMap<>();

        for (TextComponent sentence : sentences) {
            Set<String> uniqueWords = extractWords(sentence);
            log.info("Sentence has {} unique words: {}", uniqueWords.size(), uniqueWords);

            for (String w : uniqueWords) {
                wordFrequency.merge(w, 1, Integer::sum);
            }
        }

        int result = wordFrequency.values()
                .stream()
                .max(Integer::compareTo)
                .orElse(0);

        log.info("Max frequency of repeating words across sentences = {}", result);
        return result;
    }

    @Override
    public List<TextComponent> sortByLexemesCount(TextComposite text) {
        log.info("Sorting sentences by lexeme count...");

        List<TextComponent> sentences = getSentences(text);

        List<TextComponent> sorted = sentences.stream()
                .sorted(Comparator.comparingInt(this::countLexemes))
                .collect(Collectors.toList());

        log.info("Sorting completed. Sentence lexeme counts: {}",
                sorted.stream().map(this::countLexemes).toList());

        return sorted;
    }

    @Override
    public void swapFirstAndLastLexeme(TextComposite text) {
        log.info("Swapping first and last lexeme in each sentence...");

        List<TextComponent> sentences = getSentences(text);
        log.info("Total sentences: {}", sentences.size());

        for (TextComponent sentence : sentences) {

            List<TextComponent> lexemes = getLexemes(sentence);
            log.info("Sentence contains {} lexemes", lexemes.size());

            if (lexemes.size() < 2) {
                log.warn("Sentence has less than 2 lexemes. Skipping.");
                continue;
            }

            TextComponent first = lexemes.get(0);
            TextComponent last = lexemes.get(lexemes.size() - 1);

            List<TextComponent> children = sentence.getChildren();

            int i1 = children.indexOf(first);
            int i2 = children.indexOf(last);

            log.info("Swapping lexemes at indices {} and {}", i1, i2);

            Collections.swap(children, i1, i2);

            log.info("Swap complete for sentence");
        }
    }

    private List<TextComponent> getSentences(TextComposite text) {
        log.info("Collecting sentences from text...");
        List<TextComponent> sentences = new ArrayList<>();

        for (TextComponent paragraph : text.getChildren()) {
            if (paragraph.getType() == TextComponentType.PARAGRAPH) {
                sentences.addAll(paragraph.getChildren());
            } else {
                log.warn("Unexpected component type {} inside TEXT", paragraph.getType());
            }
        }

        log.info("Collected {} sentences", sentences.size());
        return sentences;
    }

    private Set<String> extractWords(TextComponent component) {
        Set<String> words = new HashSet<>();

        if (component.getType() == TextComponentType.LEXEME) {
            String lexeme = component.restore().toLowerCase();
            String word = lexeme.replaceAll(WORD_REGEX, "");
            log.info("Extracted word '{}' from lexeme '{}'", word, lexeme);

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

        log.info("Found {} lexemes in sentence", result.size());
        return result;
    }

    private int countLexemes(TextComponent sentence) {
        int count = (int) sentence.getChildren().stream()
                .filter(c -> c.getType() == TextComponentType.LEXEME)
                .count();

        log.info("Sentence has {} lexemes", count);
        return count;
    }
}
