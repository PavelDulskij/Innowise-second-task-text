package com.innowise.taskcomposite.reader;

import com.innowise.taskcomposite.exception.TextException;

public interface TextReader {
    String readFile(String path) throws TextException;
}
