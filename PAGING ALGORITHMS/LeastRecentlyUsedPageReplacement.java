import java.util.*;

public class LeastRecentlyUsedPageReplacement {

    public static int lru(int[] pages, int capacity) {
        List<Integer> memory = new ArrayList<>();
        int pageFaults = 0;

        for (int page : pages) {
            if (memory.contains(page)) {
                // Page hit: move page to the end (most recently used)
                memory.remove(Integer.valueOf(page));
                memory.add(page);
                System.out.println("Page " + page + " -> HIT");
            } else {
                // Page fault
                pageFaults++;

                if (memory.size() == capacity) {
                    memory.remove(0); // remove least recently used
                }

                memory.add(page);
                System.out.println("Page " + page + " -> FAULT");
            }

            System.out.println("Memory: " + memory);
        }

        return pageFaults;
    }

    public static void main(String[] args) {
        int[] pages = {7, 0, 1, 2, 0, 3, 0, 4};
        int capacity = 3;

        int faults = lru(pages, capacity);
        System.out.println("Total page faults = " + faults);
    }
}