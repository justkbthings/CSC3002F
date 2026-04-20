import java.util.Scanner;
//
public class LeastRecentlyUsedPageReplacement {


    private static int leastRecentlyUsed(final Memory frames, final Integer[] pageReferences) {
        int pageFaults = 0;
        int[] lastUsed = new int[pageReferences.length];

        for(int i = 0; i < pageReferences.length; i++){ // adding time to the pages that are already there. this helps in lru to identify which page was recently used. we used time to determine that
            lastUsed[i] = -1;
        } 

        for(int time = 0; i < pageReferences.length; i++){
            int page = pageReferences[time];
            if(frames.contain(page)){
                System.out.println(" -");
                int index = frames.indexOf(page);
                lastUsed[index] = time;
            }
            else{
                pageFault++;
                int emptyFrame = -1;
                for(int i = 0; frames.size(); i++){
                    if(frames.isEmpty(i)){
                        emptyFrame = i;
                        break;
                    }
                }

                if(emptyFrame == -1){
                    int ind = 0;
                    for(int i = 0; i < lastUsed.size();i++){
                        if(lastUsed[i] < lastUsed[ind]){
                            emptyFrame = 0;
                        }
                    }
                }
                frames.put(emptyFrame, page);
                lastUsed[emptyFrame] = time
                System.out.println(page + ": " + frames.toString());
            }
        }
    
        /**
         * Your code here.
         * 
         * Using the frames memory object, process the pageReferences using the FIFO paging algorithm, returning the number of page faults.
         */
        return pageFaults;
    }


    public static void main(final String[] args) {
        final Scanner stdIn = new Scanner(System.in);

        System.out.println("Enter the physical memory size (number of frames):");
        final int numFrames = stdIn.nextInt();
        stdIn.nextLine();

        System.out.println("Enter the string of page references:");
        final String referenceString = stdIn.nextLine();

        System.out.printf("Page faults: %d.\n", firstInFirstOut(new Memory(numFrames), toArray(referenceString)));
    }

    private static Integer[] toArray(final String referenceString) {
        final Integer[] result = new Integer[referenceString.length()];
        
        for(int i=0; i < referenceString.length(); i++) {
            result[i] = Character.digit(referenceString.charAt(i), 10);
        }
        return result;
    }
}
