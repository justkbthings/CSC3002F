import java.util.*;

public class FIFOPageReplacement {

    public static int fifo(int[] pages, int capacity) {
        Queue<Integer> memory = new LinkedList<>();
        Set<Integer> pageSet = new HashSet<>();
        int pageFaults = 0;

        for (int page : pages) {
            if (!pageSet.contains(page)) {
                pageFaults++;

                if (memory.size() == capacity) {
                    int removedPage = memory.poll();
                    pageSet.remove(removedPage);
                }

                memory.offer(page);
                pageSet.add(page);

                System.out.println("Page " + page + " -> FAULT");
            } else {
                System.out.println("Page " + page + " -> HIT");
            }

            System.out.println("Memory: " + memory);
        }

        return pageFaults;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter number of pages: ");
        int n = input.nextInt();

        int[] pages = new int[n];
        System.out.println("Enter page reference string:");
        for (int i = 0; i < n; i++) {
            pages[i] = input.nextInt();
        }

        System.out.print("Enter number of frames: ");
        int capacity = input.nextInt();

        int faults = fifo(pages, capacity);
        System.out.println("Total page faults = " + faults);

        input.close();
    }
}