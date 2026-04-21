private static int optimal(final Memory frames, final Integer[] pageReferences) {
    int pageFaults = 0;

    for (int current = 0; current < pageReferences.length; current++) {
        int page = pageReferences[current];

        if (frames.contains(page)) {
            System.out.println(page + ": -");
        } else {
            pageFaults++;

            int targetFrame = -1;

            // First try to use an empty frame
            for (int i = 0; i < frames.size(); i++) {
                if (frames.isEmpty(i)) {
                    targetFrame = i;
                    break;
                }
            }

            // If memory is full, choose the OPT victim
            if (targetFrame == -1) {
                int farthestNextUse = -1;

                for (int i = 0; i < frames.size(); i++) {
                    int pageInFrame = frames.get(i);
                    int nextUse = nextUseIndex(pageReferences, current + 1, pageInFrame);

                    // If this page is never used again, replace it immediately
                    if (nextUse == -1) {
                        targetFrame = i;
                        break;
                    }

                    // Otherwise pick the page used farthest in the future
                    if (nextUse > farthestNextUse) {
                        farthestNextUse = nextUse;
                        targetFrame = i;
                    }
                }
            }

            frames.put(targetFrame, page);
            System.out.println(page + ": " + frames.toString());
        }
    }

    return pageFaults;
}

private static int nextUseIndex(final Integer[] pageReferences, final int start, final int page) {
    for (int i = start; i < pageReferences.length; i++) {
        if (pageReferences[i] == page) {
            return i;
        }
    }
    return -1;
}