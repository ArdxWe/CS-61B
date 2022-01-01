package synthesizer;
import java.util.Iterator;

public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        rb = (T[]) new Object[capacity];

        first = 0;
        last = 0;
        this.fillCount = 0;
        this.capacity = capacity;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    @Override
    public void enqueue(T x) {
        if (fillCount == capacity) {
            throw new RuntimeException();
        }

        rb[last] = x;
        last = (last + 1) % capacity;

        fillCount++;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    @Override
    public T dequeue() {
        if (fillCount <= 0) {
            throw new RuntimeException();
        }

        T res = rb[first];
        first = (first + 1) % capacity;
        fillCount--;

        return res;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    @Override
    public T peek() {

        if (fillCount == 0) {
            throw new RuntimeException();
        }
        return rb[first];

    }

    @Override
    public java.util.Iterator<T> iterator() {
        return new InterIterator();
    }

    private class InterIterator implements Iterator {

        int index;

        private InterIterator() {
            index = first;
        }

        @Override
        public boolean hasNext() {
            return index != last;
        }

        @Override
        public T next() {
            T res = (T) rb[index];
            index = (index + 1) % capacity;

            return res;
        }
    }
}
