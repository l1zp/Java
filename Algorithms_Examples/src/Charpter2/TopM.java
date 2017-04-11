package Charpter2;

import edu.princeton.cs.algs4.*;

import java.util.Stack;

/**
 * Created by Lizp on 2017/4/11.
 */
public class TopM {
    public static void main(String[] args) {
        int M = Integer.parseInt(args[0]);
        MinPQ<Transaction> pq = new MinPQ<>(M+1);
        In in = new In(args[1]);
        while (in.hasNextLine()) {
            pq.insert(new Transaction(in.readLine()));
            if (pq.size() > M)
                pq.delMin();
        }
        Stack<Transaction> stack = new Stack<>();
        while (!pq.isEmpty()) stack.push(pq.delMin());
        for (Transaction t : stack) StdOut.println(t);
    }
}
