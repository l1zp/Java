package Week_2;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

/**
 * Created by Lizp on 2017/3/2.
 */
public class Deque<Item> implements Iterable<Item> {

    private Node first, last;
    private int N;

    private class Node { // private class Node
        Item item;
        Node next, prev;
    }
    public Deque() { // construct an empty deque

    }
    public boolean isEmpty() { // is the deque empty?
        return N == 0;
    }
    public int size() { // return the number of items on the deque
        return N;
    }
    public void addFirst(Item item) { // add the item to the front
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        first.prev = null;
        // 需要判断特殊情况
        if(isEmpty())
            last = first;
        else
            oldfirst.prev = first;
        N ++;
    }
    public void addLast(Item item) { // add the item to the end
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        last.prev = oldlast;
        if (isEmpty())
            first = last;
        else
            oldlast.next = last;
        N ++;
    }
    public Item removeFirst() { // remove and return the item from the front
        Item item = first.item;
        first = first.next;
        first.prev = null;
        if (isEmpty())
            last = null;
        N --;
        return item;
    }
    public Item removeLast() { // remove and return the item from the end
        Item item = last.item;
        last = last.prev;
        last.next = null;
        if (isEmpty())
            first = null;
        N --;
        return item;
    }
    public Iterator<Item> iterator() { // return an iterator over items in order from front to end
        return new ListIterator();
    }
    private class ListIterator implements Iterator<Item> {
        private Node current = first;
        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {

        }
    }
    public static void main(String[] args) { // unit testing (optional)
        Deque<String> q = new Deque<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (item.equals("qqq"))
                break;
            else
                q.addLast(item);
        }
        while (!q.isEmpty())
            StdOut.println(q.removeFirst());

    }
}
