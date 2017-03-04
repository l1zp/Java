package Week_2;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

/**
 * Created by Lizp on 2017/3/2.
 */
public class ArrayQueue<Item> implements Iterable<Item> {
    private Item[] a =  (Item[]) new Object[2];
    private int tail = 0, head = 0;
    private void resize(int max) {
        int N = tail - head;
        Item temp[] = (Item[]) new Object[max];
        for (int i = tail; i > head; i--)
            temp[i - head] = a[i];
        a = temp;
        head = 0;
        tail = head + N;
    }
    public ArrayQueue() { // construct an empty randomized queue

    }
    public boolean isEmpty() { // is the queue empty?
        return tail - head == 0;
    }
    public int size() { // return the number of items on the queue
        return tail - head;
    }
    public void enqueue(Item item) { // add the item
        if (tail - head == a.length - 1)
            resize(a.length * 2);
        a[++tail] = item;
    }
    public Item dequeue() { // remove and return a random item
        Item item = a[++head];
        a[head] = null;
        if (tail - head == a.length/4)
            resize((tail - head) * 2);
        return item;
    }
    public Item sample() { // return (but do not remove) a random item
        return a[StdRandom.uniform(1, tail-head+1)];
    }
    public Iterator<Item> iterator() { // return an independent iterator over items in random order
        return new ArrayIterator();
    }
    private class ArrayIterator implements Iterator<Item>{
        int current = head;
        @Override
        public boolean hasNext() {
            return current != tail;
        }

        @Override
        public Item next() {
            return a[++head];
        }

        @Override
        public void remove() {

        }
    }
    public static void main(String[] args) { // unit testing (optional)
        RandomizedQueue<String> q = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-"))
                q.enqueue(item);
            else if (!q.isEmpty())
                StdOut.print(q.dequeue() + " ");
        }
    }
}
