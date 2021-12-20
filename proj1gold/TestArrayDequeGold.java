import static org.junit.Assert.*;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

public class TestArrayDequeGold {

    private static final String[] identifier = {"addFirst", "addLast", "removeFirst", "removeLast"};

    private String formatHelper(Queue<Integer> oldQueue, Queue<Integer> oldQueueValue) {

        Queue<Integer> queue = copy(oldQueue);
        Queue<Integer> queueValue = copy(oldQueueValue);

        String res = "";
        while(!queue.isEmpty()){
            int index = queue.dequeue();
            int value = queueValue.dequeue();

            switch (index) {
                case 0:
                    res += identifier[0] + "(" + value + ")" + "\n";
                    break;
                case 1:
                    res += identifier[1] + "(" + value + ")" + "\n";
                    break;
                case 2:
                    res += identifier[2] + "()" + "\n";
                    break;
                case 3:
                    res += identifier[3] + "()" + "\n";
                    break;
                default:
                    break;
            }
        }
        return res;
    }

    private Queue<Integer> copy(Queue<Integer> s) {
        Queue<Integer> res = new Queue<>();
        for (Integer i : s) {
            res.enqueue(i);
        }

        return res;
    }

    @Test
    public void testStudent() {
        StudentArrayDeque<Integer> studentDeque = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> solutionDeque = new ArrayDequeSolution<>();

        Queue<Integer> queue = new Queue<>();
        Queue<Integer> queueValues = new Queue<>();
        int now = 0;
        int value = 0;

        int counts = 0;
        while (counts < 0x10000) {
            counts++;

            now = StdRandom.uniform(4);

            value = StdRandom.uniform(0x10000);
            switch (now) {
                case 0:
                    studentDeque.addFirst(value);
                    solutionDeque.addFirst(value);

                    queue.enqueue(0);
                    queueValues.enqueue(value);
                    break;
                case 1:
                    studentDeque.addLast(value);
                    solutionDeque.addLast(value);

                    queue.enqueue(1);
                    queueValues.enqueue(value);
                    break;
                case 2:
                    if (!solutionDeque.isEmpty()) {
                        Integer testValue = studentDeque.removeFirst();
                        Integer expected = solutionDeque.removeFirst();

                        queue.enqueue(2);
                        queueValues.enqueue(value);

                        assertEquals(formatHelper(queue, queueValues), testValue, expected);
                    }
                    break;
                case 3:
                    if (!solutionDeque.isEmpty()) {
                        Integer testValue = studentDeque.removeLast();
                        Integer expected = solutionDeque.removeLast();

                        queue.enqueue(3);
                        queueValues.enqueue(value);

                        assertEquals(formatHelper(queue, queueValues), testValue, expected);
                    }
                default:
                    break;
            }
        }
    }
}
