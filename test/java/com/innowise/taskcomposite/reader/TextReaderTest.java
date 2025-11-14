package com.innowise.taskcomposite.reader;

import com.innowise.taskcomposite.exception.TextException;
import com.innowise.taskcomposite.reader.impl.TextReaderImpl;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class TextReaderTest {
    @Test
    void whenReadExistingFileThenReturnContent() throws Exception {
        Path temp = Files.createTempFile("test", ".txt");
        Files.writeString(temp, "Hello Reader!");

        TextReader reader = new TextReaderImpl();
        String content = reader.readFile(temp.toString());

        assertEquals("Hello Reader!", content);
    }

    @Test
    void whenFileNotFoundThenThrowTextException() {
        TextReader reader = new TextReaderImpl();

        TextException ex = assertThrows(TextException.class,
                () -> reader.readFile("non_existing_file.txt")
        );

        assertTrue(ex.getMessage().contains("not found"));
    }

    @Test
    void whenReadEmptyFileThenReturnEmptyString() throws Exception {
        Path temp = Files.createTempFile("empty", ".txt");
        Files.writeString(temp, "");

        TextReader reader = new TextReaderImpl();
        String content = reader.readFile(temp.toString());

        assertEquals("", content);
    }

    @Test
    void whenReadMultilineFileThenReturnAllText() throws Exception {
        Path temp = Files.createTempFile("multi", ".txt");
        Files.writeString(temp, "Line1\nLine2\nLine3");

        TextReader reader = new TextReaderImpl();
        String content = reader.readFile(temp.toString());

        assertEquals("Line1\nLine2\nLine3", content);
    }
}