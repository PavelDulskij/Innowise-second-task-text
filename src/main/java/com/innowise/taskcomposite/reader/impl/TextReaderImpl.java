package com.innowise.taskcomposite.reader.impl;

import com.innowise.taskcomposite.exception.TextException;
import com.innowise.taskcomposite.reader.TextReader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class TextReaderImpl implements TextReader {
    @Override
    public String readFile(String path) throws TextException {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            throw new TextException("Path " + path + " not found");
        }
    }
}
