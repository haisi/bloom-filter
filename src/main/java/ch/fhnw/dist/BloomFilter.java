package ch.fhnw.dist;

import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

import java.nio.charset.Charset;

/**
 * @author Hasan Kara <hasan.kara@students.fhnw.ch>
 */
public class BloomFilter {

    HashFunction[] hashes;
    boolean[] array;
    final int numberOfHashingAlgs;

    /**
     *
     * @param n number of expected entries
     * @param p wanted errorProbability
     */
    public BloomFilter(int n, double p) {

        BloomFilterSettings bloomFilterSettings = optimalBloomFilter(n, p);

        this.numberOfHashingAlgs = bloomFilterSettings.k;
        array = new boolean[bloomFilterSettings.m];

        createHashers(this.numberOfHashingAlgs);
    }

    private void createHashers(int numberOfHashingAlgs) {
        hashes = new HashFunction[this.numberOfHashingAlgs];

        for (int i = 0; i < numberOfHashingAlgs; i++) {
            hashes[i] = Hashing.murmur3_128(i);
        }

    }

    /**
     * @param expectedNumberOfElements n
     * @param errorProbability p
     * @return m & k
     */
    public static BloomFilterSettings optimalBloomFilter(int expectedNumberOfElements, double errorProbability) {

        final int n = expectedNumberOfElements;
        final double p = errorProbability;

        // Always round down (cast to int)
        int m = (int) - ((n * Math.log(p)) / Math.pow(Math.log(2), 2));

        // Always round down (cast to int)
        int k = (int) ((m / n) * Math.log(2));

        return new BloomFilterSettings(m, k);
    }

    public void put(String word) {
        for (HashFunction hash : hashes) {
            int position = getPosition(word, hash);
            array[position] = true;
        }
    }

    public boolean mightContain(String word) {
        for (HashFunction hash : hashes) {
            int position = getPosition(word, hash);

            if (!array[position]) {
                return false;
            }
        }

        return true;
    }

    private int getPosition(String word, HashFunction hash) {
        int hashCode = hash.hashString(word, Charset.defaultCharset()).asInt();
        return hashCode % array.length;
    }

    public static class BloomFilterSettings {
        public final int m;
        public final int k;

        public BloomFilterSettings(int m, int k) {
            this.m = m;
            this.k = k;
        }

        @Override
        public String toString() {
            return "BloomFilterSettings{" +
                    "m=" + m +
                    ", k=" + k +
                    '}';
        }
    }

}
