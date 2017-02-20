package Week_1;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/**
 * Created by Lizp on 2017/2/20.
 */
public class PercolationStats {
    private double[] results;
    private int t;
    public PercolationStats(int n, int trials) {
        results = new double[trials];
        this.t = trials;
        for (int i = 0; i < trials; i++) {
            Percolation pe = new Percolation(n);

            while (!pe.percolates()) {
                int row = StdRandom.uniform(1, n+1);
                int col = StdRandom.uniform(1, n+1);

                if(!pe.isOpen(row, col))
                    pe.open(row, col);
            }
            results[i] = (double)pe.numberOfOpenSites()/(n*n);
        }
    }

    public double mean() {
        return StdStats.mean(results);
    }

    public double stddev() {
        return StdStats.stddev(results);
    }

    public double confidenceLo() {
        return this.mean()-1.96 * this.stddev() / Math.sqrt(t);
    }

    public double confidenceHi() {
        return this.mean()+1.96 * this.stddev() / Math.sqrt(t);
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);


        PercolationStats ps = new PercolationStats(n, t);
        StdOut.println(ps.mean());
        StdOut.println(ps.stddev());
        StdOut.println(ps.confidenceHi());
        StdOut.println(ps.confidenceLo());

    }
}
