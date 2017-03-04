package Week_2;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by Lizp on 2017/3/2.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] a =  (Item[])new Object[1];
    private int N = 0;
    private void resize(int max) {
        Item temp[] = (Item[])new Object[max];
        for (int i = 0, j = 0; i < N; i++) {
            if (a[i] != null) {
                temp[j ++] = a[i];
            }
        }
        a = temp;
    }
    public RandomizedQueue() { // construct an empty randomized queue

    }
    public boolean isEmpty() { // is the queue empty?
        return N == 0;
    }
    public int size() { // return the number of items on the queue
        return N;
    }
    public void enqueue(Item item) { // add the item
        if (N  == a.length)
            resize(a.length * 2);
        a[N ++] = item;
    }
    public Item dequeue() { // remove and return a random item
        int rand = StdRandom.uniform(0, N);
        Item item = a[rand];
        a[rand] = null;
        N --;
        if (N == a.length/4)
            resize(N * 2);
        return item;
    }
    public Item sample() { // return (but do not remove) a random item
        return a[StdRandom.uniform(0, N)];
    }
    public Iterator<Item> iterator() { // return an independent iterator over items in random order
        return new ArrayIterator();
    }
    private class ArrayIterator implements Iterator<Item>{
        private int current = 0;
        Item[] temps = a.clone();
        public boolean hasNext() {
            return current < N;
        }

        @Override
        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();
            int r = current + StdRandom.uniform(N - current);
            Item temp = temps[r];
            temps[r] = temps[current];
            current ++;
            return temp;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
    public static void main(String[] args) { // unit testing (optional)
        RandomizedQueue<String> q = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (item.equals("qqq"))
                break;
            else
                q.enqueue(item);
        }
        Iterator<String> i = q.iterator();
        while (i.hasNext())
            StdOut.println(i.next());

    }
}
