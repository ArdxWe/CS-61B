public class ArrayDeque<T> {
    private static final int USAGE_FACTOR = 4;  // min is 1/4
    private static final int INITIAL_SIZE = 8;

    private T[] data = null;
    private int counts = 0;
    private int first = 0;

    public ArrayDeque() {
        data = (T[]) new Object[INITIAL_SIZE];
    }

    private void resize() {
        assert counts == data.length;

        T[] newData = (T[]) new Object[counts * 2];
        for (int i = 0; i < counts; i++) {
            newData[i] = data[(first + i) % data.length];
        }

        data = newData;
        first = 0;
    }

    public void addFirst(T item) {
        // full
        if (counts == data.length) {
            resize();
        }

        int newFirstIndex = (first - 1 + data.length) % data.length;
        data[newFirstIndex] = item;
        first = newFirstIndex;

        counts++;
    }

    public void addLast(T item) {
        if (counts == data.length) {
            resize();
        }

        int index = (first + counts) % data.length;
        data[index] = item;

        counts++;
    }

    public boolean isEmpty() {
        return counts == 0;
    }

    public int size() {
        return counts;
    }

    public void printDeque() {
        for (int i = 0; i < counts; i++) {
            System.out.print(data[(first + i) % data.length]);

            if (i != counts - 1) {
                System.out.print(" ");
            }
        }
    }

    private void compact() {
        assert counts * USAGE_FACTOR < data.length;

        T[] newData = (T[]) new Object[data.length / 2];

        for (int i = 0; i < counts; i++) {
            newData[i] = data[(first + i) % data.length];
        }

        data = newData;

        first = 0;
    }

    public T removeFirst() {
        if (counts == 0) {
            return null;
        }

        T res = data[first % data.length];
        counts--;
        first++;

        if (data.length == INITIAL_SIZE) {
            return res;
        }

        if (counts * USAGE_FACTOR < data.length) {
            compact();
        }
        return res;
    }

    public T removeLast() {
        if (counts == 0) {
            return null;
        }

        T res = data[(first + counts - 1) % data.length];
        counts--;

        if (data.length == INITIAL_SIZE) {
            return res;
        }

        if (counts * USAGE_FACTOR < data.length) {
            compact();
        }
        return res;
    }

    public T get(int index) {
        if (index < 0 || index > counts - 1) {
            return null;
        }
        return data[(first + index) % data.length];
    }
}
