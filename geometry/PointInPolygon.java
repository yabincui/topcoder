import java.util.*;

public class PointInPolygon {
	
	class Point {
		int x, y;
		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public String testPoint(String[] vertices, int testPointX, int testPointY) {
		int n = vertices.length;
		Point[] a = new Point[n+1];
		for (int i = 0; i < n; ++i) {
			String[] strs = vertices[i].split(" ");
			a[i] = new Point(Integer.parseInt(strs[0]), Integer.parseInt(strs[1]));
		}
		a[n] = a[0];
		Point test = new Point(testPointX, testPointY);
		if (isInBoundary(a, test)) {
			return "BOUNDARY";
		}
		if (isInner(a, test)) {
			return "INTERIOR";
		}
		return "EXTERIOR";
	}
	
	private boolean isInBoundary(Point[] points, Point test) {
		for (int i = 0; i < points.length - 1; ++i) {
			if (between(points[i], points[i+1], test)) {
				return true;
			}
		}
		return false;
	}
	
	private boolean between(Point a, Point b, Point test) {
		if (test.x == a.x && a.x == b.x) {
			return test.y >= Integer.min(a.y, b.y) &&  test.y <= Integer.max(a.y, b.y);
		} else if (test.y == a.y && a.y == b.y) {
			return test.x >= Integer.min(a.x, b.x) && test.x <= Integer.max(a.x, b.x);
		}
		return false;
	}
	
	private boolean isInner(Point[] points, Point test) {
		Random r = new Random();
		while (true) {
			int randx = test.x + 1000;
			int randy = r.nextInt();
			if (randy == test.y) {
				continue;
			}
			int meetLineCount = getMeetLineCount(points, test, new Point(randx, randy));
			//System.out.printf("randx = %d, randy = %d, meetCount = %d\n", randx, randy, meetLineCount);
			if (meetLineCount < 0) {
				continue;
			}
			return meetLineCount % 2 == 1;
		}
	}
	
	private int getMeetLineCount(Point[] points, Point test, Point rand) {
		// Ax + By = C
		double a0 = (double)rand.y - test.y;
		double b0 = (double)test.x - rand.x;
		double c0 = (double)test.x * rand.y - (double)test.y * rand.x;
		int meetLineCount = 0;
		for (int i = 0; i < points.length - 1; ++i) {
			double a1 = (double)points[i+1].y - points[i].y;
			double b1 = (double)points[i].x - points[i+1].x;
			double c1 = (double)points[i].x * points[i+1].y - (double)points[i].y * points[i+1].x;
			double x = (double)(b0*c1-c0*b1) / (a1*b0 - a0*b1);
			double y = (double)(c0*a1-c1*a0) / (a1*b0 - a0*b1);
			//System.out.printf("line [%d, %d] - [%d, %d], at [%f, %f]\n", points[i].x, points[i].y, points[i+1].x, points[i+1].y, x, y);
			if (x < test.x + 1e-6) {
				continue;
			}
			if (Math.abs(x - points[i].x) < 1e-6 && Math.abs(y - points[i].y) < 1e-6) {
				return -1;
			}
			if (Math.abs(x - points[i+1].x) < 1e-6 && Math.abs(y - points[i+1].y) < 1e-6) {
				return -1;
			}
			if (points[i].x == points[i+1].x && y > Math.min(points[i].y, points[i+1].y) - 1e-6 && y < Math.max(points[i].y, points[i+1].y) + 1e-6) {
				meetLineCount++;
			}
			if (points[i].y == points[i+1].y && x > Math.min(points[i].x, points[i+1].x) - 1e-6 && x < Math.max(points[i].x, points[i+1].x) + 1e-6) {
				meetLineCount++;
			}
		}
		return meetLineCount;
	}
}
