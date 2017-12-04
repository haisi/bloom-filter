package ch.fhnw.dist;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Hasan Kara <hasan.kara@students.fhnw.ch>
 */
public class Main {

    public static void main(String[] args) throws IOException {
        new Main().start();
    }

    private void start() throws IOException {

        Path path = Paths.get("./words.txt");
        String wordsContent = new String(Files.readAllBytes(path));
        List<String> words = Files.lines(path).collect(Collectors.toList());

        System.out.println(words);

    }

}
