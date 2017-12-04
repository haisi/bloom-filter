package ch.fhnw.dist;

import java.io.IOException;
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
        List<String> words = Files.lines(path).collect(Collectors.toList());

        double errorProbability = 1.0E-06;

        BloomFilter bloomFilter = new BloomFilter(words.size(), errorProbability);

        for (String word : words) {
            bloomFilter.put(word);
        }

        System.out.println("p=" + errorProbability);
        System.out.println(bloomFilter);
    }

}
