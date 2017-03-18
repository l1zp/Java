package Week_3;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by Lizp on 2017/3/17.
 */
public class FastCollinearPoints {

    private LineSegment[] lineSegments;
    public FastCollinearPoints(Point[] points) { // finds all line segments containing 4 or more points
        ArrayList<LineSegment> segs = new ArrayList<LineSegment>();

        if (points == null)
            throw new NullPointerException();
        while (points.length > 3) {
            Arrays.sort(points);
            int i = 0;
            if (points[i] == null)
                throw new IllegalArgumentException();

            Arrays.sort(points, points[i].slopeOrder());

            int head = 1, tail = 1;
            for (i += 2; i < points.length; i ++) {
                double previousSlope = points[0].slopeTo(points[head]);
                if (previousSlope == Double.NEGATIVE_INFINITY)
                    throw new IllegalArgumentException();
                tail = i;
                double nextSlope = points[0].slopeTo(points[tail]);

                if ( previousSlope - nextSlope != 0 || tail == points.length - 1) {

                    if (tail == points.length - 1 && nextSlope == previousSlope)
                        tail += 1;
                    if (tail - head > 2) {
                        segs.add(new LineSegment(points[0], points[tail - 1]));
                        points = del(points, head, tail);
                        break;
                    }
                    previousSlope = nextSlope;
                    head = tail;
                }
            }
            points = del(points);
            //points = cp_points.toArray(new Point[cp_points.size()]);
        }
        lineSegments = segs.toArray(new LineSegment[segs.size()]);

    }
    private Point[] del(Point[] ps,int  head, int tail) {
        Point[] temps = new Point[ps.length-tail+head];
        for (int i = 0;i < head; i ++)
            temps[i] = ps[i];
        for (int i = head ;i < temps.length; i ++)
            temps[i] = ps[tail + i - head];
        return temps;
    }
    private Point[] del(Point[] ps) {
        Point[] temps = new Point[ps.length - 1];
        for (int i = 0; i < temps.length; i ++)
            temps[i] = ps[i + 1];
        return temps;
    }
    public int numberOfSegments() { // the number of line segments
        return lineSegments.length;
    }
    public LineSegment[] segments() { // the line segments
        return lineSegments;
    }

}
