package ch.fhnw.dist;

/**
 * @author Hasan Kara <hasan.kara@students.fhnw.ch>
 */
public class BloomFilter {

    boolean[] array;
    /**
     * k
     */
    final int numberOfHashingAlgs;


    /**
     *
     * @param size m
     * @param numberOfHashingAlgs k
     */
    public BloomFilter(int size, int numberOfHashingAlgs) {
        this.numberOfHashingAlgs = numberOfHashingAlgs;
        array = new boolean[size];
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
