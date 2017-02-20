package Week_1;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * Created by Lizp on 2017/2/20.
 */
public class Percolation {

    private int head, tail, n, num = 0;
    private boolean[] sites;
    private WeightedQuickUnionUF WqUF;

    public Percolation(int n) {
        WqUF = new WeightedQuickUnionUF(n*n + 2); // create n-by-n grid, with all sites blocked
        this.n = n;
        sites = new boolean[n*n+2];
        head = 0;
        tail = n*n + 1;
        for (int p = 1; p < n+1; p ++) {
            WqUF.union(head, p);
            WqUF.union(tail, tail-p);
        }
        for (int i = 1; i < tail; i ++)
            sites[i] = false;
    }

    public void open(int row, int col) { // open site (row, col) if it is not open already
        sites[n*(row-1)+col] = true;
        num ++;

        if (row-1 > 0 && isOpen(row-1, col))
            WqUF.union(n*(row-2)+col, n*(row-1)+col);
        if (col-1 > 0 && isOpen(row, col-1))
            WqUF.union(n*(row-1)+col-1, n*(row-1)+col);
        if (row < n && isOpen(row+1, col))
            WqUF.union(n*row+col, n*(row-1)+col);
        if (col < n && isOpen(row, col))
            WqUF.union(n*(row-1)+col+1, n*(row-1)+col);
    }

    public boolean isOpen(int row, int col) {
        return sites[n*(row-1)+col];
    }

    public boolean isFull(int row, int col) {
        return WqUF.connected(n*(row-1)+col, head) && this.isOpen(row, col);
    }

    public int numberOfOpenSites() {
        return num;
    }

    public boolean percolates() {
        return WqUF.connected(head, tail);
    }
    public static void main(String[] args) {
        Percolation test = new Percolation(20);

        while (!test.WqUF.connected(test.head, test.tail)) {
            int row = StdRandom.uniform(1, 21);
            int col = StdRandom.uniform(1, 21);

            if (!test.isOpen(row, col))
                test.open(row, col);
        }

        StdOut.println(test.numberOfOpenSites());
    }


}
