package edu.hw3;

import java.util.Collection;

public class Task8 {

    static class BackwardIterator<T> {
        private Collection<T> internalCollection;
        private int currentIndex;

        BackwardIterator(Collection<T> receivedCollection) {
            internalCollection = receivedCollection;
            currentIndex = receivedCollection.size() - 1;
        }

        public boolean hasNext() {
            return currentIndex >= 0 && internalCollection.toArray()[currentIndex] != null;
        }

        public T next() {
            return (T) internalCollection.toArray()[currentIndex--];
        }
    }
}
