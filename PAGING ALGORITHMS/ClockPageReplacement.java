import java.util.*;

public class ClockPageReplacement {

    public static int clock(int[] pages, int capacity) {
        int[] memory = new int[capacity];
        int[] ref = new int[capacity];

        Arrays.fill(memory, -1); // empty slots

        int pointer = 0;
        int pageFaults = 0;

        for (int page : pages) {
            int index = find(memory, page);

            if (index != -1) {
                // HIT
                ref[index] = 1;
                System.out.println("Page " + page + " -> HIT");
            } else {
                // FAULT
                pageFaults++;

                while (ref[pointer] == 1) {
                    ref[pointer] = 0;
                    pointer = (pointer + 1) % capacity;
                }

                memory[pointer] = page;
                ref[pointer] = 1;

                pointer = (pointer + 1) % capacity;

                System.out.println("Page " + page + " -> FAULT");
            }

            System.out.println("Memory: " + Arrays.toString(memory));
        }

        return pageFaults;
    }

    // helper to find page
    public static int find(int[] memory, int page) {
        for (int i = 0; i < memory.length; i++) {
            if (memory[i] == page)
                return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] pages = {7, 0, 1, 2, 0, 3, 0, 4};
        int capacity = 3;

        int faults = clock(pages, capacity);
        System.out.println("Total page faults = " + faults);
    }
}