package com.innowise.taskcomposite.service;

import com.innowise.taskcomposite.component.TextComponent;
import com.innowise.taskcomposite.component.TextComposite;

import java.util.List;

public interface TextService {
    int maxSentencesWithSameWordsCount(TextComposite text);
    List<TextComponent> sortByLexemesCount(TextComposite text);
    void swapFirstAndLastLexeme(TextComposite text);

}
