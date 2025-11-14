package com.innowise.taskcomposite.reader.impl;

import com.innowise.taskcomposite.exception.TextException;
import com.innowise.taskcomposite.reader.TextReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class TextReaderImpl implements TextReader {

    private static final Logger log = LogManager.getLogger(TextReaderImpl.class);

    @Override
    public String readFile(String path) throws TextException {
        log.info("Attempting to read file from path: {}", path);

        try {
            String content = Files.readString(Path.of(path));
            log.info("Successfully read file. Content length: {}", content.length());
            return content;

        } catch (IOException e) {
            log.warn("Failed to read file at path {}. Reason: {}", path, e.getMessage());
            throw new TextException("Path " + path + " not found");
        }
    }
}
