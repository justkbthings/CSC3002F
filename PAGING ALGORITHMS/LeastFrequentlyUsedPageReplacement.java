import java.util.*;

public class LeastFrequentlyUsedPageReplacement {

    public static int lfu(int[] pages, int capacity) {
        List<Integer> memory = new ArrayList<>();
        Map<Integer, Integer> freq = new HashMap<>();
        int pageFaults = 0;

        for (int page : pages) {

            if (memory.contains(page)) {
                // HIT → increase frequency
                freq.put(page, freq.get(page) + 1);
                System.out.println("Page " + page + " -> HIT");
            } else {
                pageFaults++;

                if (memory.size() < capacity) {
                    memory.add(page);
                } else {
                    // find least frequently used
                    int minFreq = Integer.MAX_VALUE;
                    for (int p : memory) {
                        minFreq = Math.min(minFreq, freq.get(p));
                    }

                    // find first page with min frequency (FIFO tie-break)
                    for (int p : memory) {
                        if (freq.get(p) == minFreq) {
                            memory.remove(Integer.valueOf(p));
                            break;
                        }
                    }

                    memory.add(page);
                }

                freq.put(page, 1);
                System.out.println("Page " + page + " -> FAULT");
            }

            System.out.println("Memory: " + memory);
        }

        return pageFaults;
    }

    public static void main(String[] args) {
        int[] pages = {7, 0, 1, 2, 0, 3, 0, 4};
        int capacity = 3;

        int faults = lfu(pages, capacity);
        System.out.println("Total page faults = " + faults);
    }
}