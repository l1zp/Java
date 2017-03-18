package Week_3;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Comparator;

/**
 * Created by Lizp on 2017/3/17.
 */
public class Point implements Comparable<Point> {
    private final int x;
    private final int y;

    public Point(int x, int y) { // constructs the point (x, y)
        this.x = x;
        this.y = y;
    }

    public void draw() { // draws this point
        StdDraw.point(x, y);
    }
    public void drawTo(Point that) { // draws the line segment from this point to that point
        StdDraw.line(this.x, this.y, that.x, that.y);
    }
    public String toString() { // string representation
        return "(" + x + ", " + y + ")";
    }

    public int compareTo(Point that) { // compare two points by y-coordinates, breaking ties by x-coordinates
        if (this.y < that.y)
            return -1;
        else if (this.y == that.y) {
            if (this.x < that.x)
                return -1;
            else if (this.x == that.x)
                return 0;
        }
        return 1;

    }
    public double slopeTo(Point that) { // the slope between this point and that point
        if (this.compareTo(that) == 0)
            return Double.NEGATIVE_INFINITY;
        if (this.x == that.x)
            return Double.POSITIVE_INFINITY;
        if (this.y == that.y)
            return 0.0;
        double result = (double) (that.y - this.y) / (double) (that.x - this.x);
        return result;
    }
    public Comparator<Point> slopeOrder() {
        return new Comparator<Point>() {
            @Override
            public int compare(Point p1, Point p2) {
                if (p1 == null || p2 == null)
                    throw new NullPointerException();
                double diff = slopeTo(p1) - slopeTo(p2);
                if (diff > 0)
                    return 1;
                else if(diff < 0)
                    return -1;
                else
                    return 0;
            }
        };
    }

    static public void main(String[] args) {
        Point p0 = new Point(0 ,0);
        Point p1 = new Point(1, 2);
        Point p2 = new Point(2, 1);
        StdOut.println(p0.slopeOrder().compare(p1, p2));
    }
}
