import java.util.*;

public class OptimalPageReplacement {

    public static int optimal(int[] pages, int capacity) {
        List<Integer> memory = new ArrayList<>();
        int pageFaults = 0;

        for (int i = 0; i < pages.length; i++) {
            int page = pages[i];

            if (memory.contains(page)) {
                System.out.println("Page " + page + " -> HIT");
            } else {
                pageFaults++;

                if (memory.size() < capacity) {
                    memory.add(page);
                } else {
                    int indexToRemove = findOptimalPage(memory, pages, i + 1);
                    memory.set(indexToRemove, page);
                }

                System.out.println("Page " + page + " -> FAULT");
            }

            System.out.println("Memory: " + memory);
        }

        return pageFaults;
    }

    // This function finds which page to remove
    private static int findOptimalPage(List<Integer> memory, int[] pages, int start) {
        int farthestIndex = -1;
        int pageIndex = -1;

        for (int i = 0; i < memory.size(); i++) {
            int page = memory.get(i);
            int j;

            // Look for next use of this page
            for (j = start; j < pages.length; j++) {
                if (pages[j] == page) {
                    if (j > farthestIndex) {
                        farthestIndex = j;
                        pageIndex = i;
                    }
                    break;
                }
            }

            // If page is never used again, remove it immediately
            if (j == pages.length) {
                return i;
            }
        }

        return pageIndex;
    }

    public static void main(String[] args) {
        int[] pages = {7, 0, 1, 2, 0, 3, 0, 4};
        int capacity = 3;

        int faults = optimal(pages, capacity);
        System.out.println("Total page faults = " + faults);
    }
}