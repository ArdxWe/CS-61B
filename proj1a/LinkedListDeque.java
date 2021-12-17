public class LinkedListDeque<T> {

    private class Node {
        private T item;
        private Node prev;
        private Node next;

        private Node(T item, Node prev, Node next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }

        private Node(T item) {
            this.item = item;
            this.prev = null;
            this.next = null;
        }

        private Node() { }
    }

    private Node head = null;
    private Node tail = null;
    private int size = 0;

    public LinkedListDeque() { }

    private LinkedListDeque(Node head, Node tail, int size) {
        this.head = head;
        this.tail = tail;
        this.size = size;
    }

    public void addFirst(T item) {
        Node node = new Node(item);

        if (head == null && tail == null) {
            head = tail = node;
        } else {
            assert head != null;
            head.prev = node;
            node.next = head;

            head = node;
        }

        size++;
    }

    public void addLast(T item) {
        Node node = new Node(item);

        if (head == null && tail == null) {
            head = tail = node;
        } else {
            tail.next = node;
            node.prev = tail;

            tail = node;
        }

        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void printDeque() {
        Node every = head;

        while (every != null) {
            System.out.print(every.item);
            if (every.next != null) {
                System.out.print(" ");
            }
            every = every.next;
        }
    }

    public T removeFirst() {
        if (head == null && tail == null) {
            return null;
        }

        assert head != null;
        T res = head.item;

        if (head == tail) {

            head = null;
            tail = null;
            size = 0;
        } else {
            head = head.next;
            head.prev = null;
            size--;
        }
        return res;
    }

    public T removeLast() {
        if (head == null && tail == null) {
            return null;
        }

        T res = tail.item;
        if (head == tail) {
            head = null;
            tail = null;
            size = 0;
        } else {
            tail = tail.prev;
            tail.next = null;
            size--;
        }
        return res;
    }

    public T get(int index) {
        if (index < 0 || index > size - 1) {
            return null;
        }

        Node every = head;

        for (int i = 0; i < index; i++) {
            every = every.next;
        }

        return every.item;
    }

    public T getRecursive(int index) {
        if (index < 0 || index > size - 1) {
            return null;
        }

        if (index == 0) {
            return head.item;
        }

        return new LinkedListDeque<T>(head.next, tail, size - 1).getRecursive(index - 1);
    }

    public int size() {
        return size;
    }

}
