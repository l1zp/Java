package Week_2;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by Lizp on 2017/3/2.
 */
public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);

        RandomizedQueue<String> q = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            q.enqueue(item);
        }
        for(int i = 0; i < k; i++)
            StdOut.println(q.iterator().next());
    }
}
