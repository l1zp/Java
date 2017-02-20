package Week_1;

/**
 * Created by Lizp on 2017/2/20.
 */
public class Percolation {
    boolean[][] siteOpen, siteFull;
    int n, num=0;

    public Percolation(int n){//create n-by-n grid, with all sites blocked
        this.n = n;
        for(int row = 0; row < n; row ++) {
            for(int col = 0; col < n; col ++) {
                siteOpen[row][col] = false;
                siteFull[row][col] = false;
            }
        }
    }

    public void open(int row, int col) {//open site (row, col) if it is not open already
        siteOpen[row][col] = true;
        num += 1;
    }

    public boolean isOpen(int row, int col) {
        return siteOpen[row][col];
    }

    public boolean isFull(int row, int col) {
        return siteFull[row][col];
    }

    public int numberOfOpenSites() {
        return num;
    }

    public boolean percolates() {
        for(int col = 0; col < n; col ++) {
            if(siteFull[n][col])
                return siteFull[n][col];
        }
        return false;
    }




}
