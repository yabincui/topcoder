import java.util.*;
// enumerate every situation, there are two parallel lines, at least one line
// contains two special points, and the other contains one special point.

// Another situation is the truck just pass between two points. So the line is
// vertical to a line containing two points.

public class Warehouse {
	
	class Point {
		double x, y;
		Point(double x, double y) {
			this.x = x;
			this.y = y;
		}
		
		double getDist(Point other) {
			double dx = x - other.x;
			double dy = y - other.y;
			return Math.sqrt(dx*dx + dy*dy);
		}
	}
	
	class Line {
		// ax + by = c.
		double a, b, c;
		Line(double a, double b, double c) {
			this.a = a;
			this.b = b;
			this.c = c;
		}
		
		Line(Point p1, Point p2) {
			// ax1 + by1 = c
			// ax2 + by2 = c
			// a(x1-x2) = b(y2 - y1)
			a = p2.y - p1.y;
			b = p1.x - p2.x;
			c = a * p1.x + b * p1.y;
		}
		
		double signDist(Point p) {
			Line l2 = new Line(-b, a, -b * p.x + a * p.y);
			Point meet = getIntersect(l2);
			double dist = p.getDist(meet);
			if (p.y > meet.y) {
				return dist;
			}
			return -dist;
		}
		
		Point getIntersect(Line other) {
			//a1x + b1y = c1
			//a2x + b2y = c2
			//(a1b2-a2b1)x = c1b2 - c2b1
			//(a1b2-a2b1)y = c2a1 - c1a2
			double t = a*other.b - other.a * b;
			if (Math.abs(t) < 1e-9) {
				return null;
			}
			double x = (c * other.b - other.c * b) / t;
			double y = (other.c * a - c * other.a) / t;
			return new Point(x, y);
		}
	}
	
	int getTrackWidth(double width) {
		int tmp = (int)width;
		if (Math.abs(width - tmp)  < 1e-6) {
			return tmp - 1;
		}
		return tmp;
	}
	
	Point upLeft = new Point(0, 200);
	Point downLeft = new Point(0, 0);
	Point upRight = new Point(200, 200);
	Point downRight = new Point(200, 0);
	int n;
	Point[] points;
	double maxResult;
	
	public int feetWide(int[] x, int[] y) {
		n = x.length;
		points = new Point[n];
		for (int i = 0; i < n; ++i) {
			points[i] = new Point(x[i], y[i]);
		}

		// try the special line connecting downLeft and downRight.
		// try the special line connecting upLeft and upRight.
		double minY = y[0];
		double maxY = y[0];
		for (int i = 1; i < n; ++i) {
			minY = Math.min(minY, y[i]);
			maxY = Math.max(maxY, y[i]);
		}
		maxResult = Math.max(minY, 200 - maxY);
		
		for (int i = 0; i < n; ++i) {
			tryLine(new Line(points[i], upLeft));
			tryLine(new Line(points[i], upRight));
			tryLine(new Line(points[i], downLeft));
			tryLine(new Line(points[i], downRight));
			for (int j = i + 1; j < n; ++j) {
        Line joinLine = new Line(points[i], points[j]);
        tryLine(joinLine);
        Line vertLine1 = new Line(-joinLine.b, joinLine.a, -joinLine.b * points[i].x +
            joinLine.a * points[i].y);
        tryLine(vertLine1);
        Line vertLine2 = new Line(-joinLine.b, joinLine.a, -joinLine.b * points[j].x +
            joinLine.a * points[j].y);
        tryLine(vertLine2);
			}
		}
		return getTrackWidth(maxResult);
	}
	
	void tryLine(Line l1) {
		// enumerate all situations meething with left wall and right wall.
		Line leftWall = new Line(1, 0, 0);
		Line rightWall = new Line(1, 0, 200);
		Point meetLeft = l1.getIntersect(leftWall);
		if (meetLeft == null) {
			return;
		}
		Point meetRight = l1.getIntersect(rightWall);
		if (meetLeft.y >= 200) {
			if (meetRight.y > 0) {
				Line l2 = new Line(l1.a, l1.b, l1.b * 200);
				findMinDist(l2, false, true);
			}
		} else if (meetLeft.y >= 0) {
			if (meetRight.y >= 200) {
				Line l2 = new Line(l1.a, l1.b, l1.a * 200 + l1.b * 200);
				findMinDist(l2, false, true);
			} else if (meetRight.y >= 0) {
				findMinDist(l1, true, true);
			} else {
				Line l2 = new Line(l1.a, l1.b, l1.a * 200);
				findMinDist(l2, true, false);
			}
		} else {
			if (meetRight.y < 200) {
				Line l2 = new Line(l1.a, l1.b, 0);
				findMinDist(l2, true, false);
			}
		}
	}
	
	void findMinDist(Line l1, boolean above, boolean under) {
		double minAboveDist = -1;
		double minUnderDist = -1;
		double tmp;
		if (above) {
			tmp = l1.signDist(upLeft);
			if (tmp <= 1e-6) {
				above = false;
			} else {
				minAboveDist = tmp;
			}
			tmp = l1.signDist(upRight);
			if (tmp <= 1e-6) {
				above = false;
			} else {
				minAboveDist = Math.min(minAboveDist, tmp);
			}
		}
		if (under) {
			tmp = l1.signDist(downLeft);
			if (tmp >= -1e-6) {
				under = false;
			} else {
				minUnderDist = -tmp;
			}
			tmp = l1.signDist(downRight);
			if (tmp >= 1e-6) {
				under = false;
			} else {
				minUnderDist = Math.min(minUnderDist, -tmp);
			}
		}
		for (int i = 0; i < n; ++i) {
			tmp = l1.signDist(points[i]);
			if (tmp >= 1e-6) {
				if (above) {
					minAboveDist = Math.min(minAboveDist, tmp);
				}
			} else if (tmp <= -1e-6) {
				if (under) {
					minUnderDist = Math.min(minUnderDist, -tmp);
				}
			}
		}
		if (above) {
			maxResult = Math.max(maxResult, minAboveDist);
		}
		if (under) {
			maxResult = Math.max(maxResult, minUnderDist);
		}
	}

  public static void main(String[] args) {
    Warehouse obj = new Warehouse();
    int res = obj.feetWide(new int[]{100, 120}, new int[]{60, 140});
    System.out.printf("res = %d\n", res);
    res = obj.feetWide(new int[]{100, 100, 120}, new int[]{60, 140, 180});
    System.out.printf("res = %d\n", res);
  }
}
