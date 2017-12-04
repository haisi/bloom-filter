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

        double errorProbability = 1.0E-03;
        int numberOfNonExistingEntries = 100_000;

        BloomFilter bloomFilter = new BloomFilter(words.size(), errorProbability);

        for (String word : words) {
            bloomFilter.put(word);
        }

        int fp = 0;
        for (int i = 0; i < numberOfNonExistingEntries; i++) {
            if (bloomFilter.mightContain(String.valueOf(i))) {
                fp++;
            }
        }

        double errorRate = 100 / (double) words.size() * (double) fp;

        System.out.println("number of non existing entries=" + numberOfNonExistingEntries);
        System.out.println("n=" + words.size());
        System.out.println("p=" + errorProbability);
        System.out.println("false positives=" + fp);
        System.out.println("errorRate in percent=" + errorRate);
        System.out.println(bloomFilter);
    }

}
