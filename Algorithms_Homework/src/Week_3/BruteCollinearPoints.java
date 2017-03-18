package Week_3;

/**
 * Created by Lizp on 2017/3/17.
 */
import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
    //private int N = 0;
    private LineSegment[] segs;

    public BruteCollinearPoints(Point[] points) { // finds all line segments containing 4 points
        ArrayList<LineSegment> temps = new ArrayList<LineSegment>();
        if (points == null)
            throw new NullPointerException();
        Arrays.sort(points);
        for (int p = 0; p < points.length; p ++) {
            if (points[p] == null)
                throw new NullPointerException();
            for (int q = p + 1; q < points.length; q ++) {
                for (int r = q + 1; r < points.length ; r ++) {
                    for (int s = r + 1; s < points.length ; s ++) {
                        if (points[p].compareTo(points[q]) == 0 )
                            throw new IllegalArgumentException();
                        if (points[p].slopeTo(points[q]) == points[q].slopeTo(points[r]) &&
                                points[q].slopeTo(points[r]) == points[r].slopeTo(points[s])) {
                            temps.add(new LineSegment(points[p], points[s]));
                            // temps.add(new LineSegment(points[q], points[r]));

                        }
                    }
                }
            }
        }
        segs = temps.toArray(new LineSegment[temps.size()]);

    }
    private Point[] del(Point[] ps) {
        Point[] temps = new Point[ps.length - 1];
        for (int i = 0; i < temps.length; i ++)
            temps[i] = ps[i + 1];
        return temps;
    }
    public int numberOfSegments() { // the number of line segments
        return segs.length;
    }
    public LineSegment[] segments() { // the line segments
        return segs;
    }
}
