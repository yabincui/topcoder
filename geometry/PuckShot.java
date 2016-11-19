public class PuckShot {
	class Point {
		double x, y;
		Point(double x, double y) {
			this.x = x;
			this.y = y;
		}
	}
	class Line {
		double a, b, c;
		Line(Point p1, Point p2) {
			// ax1 + by1 = c
			// ax2 + by2 = c
			// a = y1 - y2
			// b = x2 - x1
			// c = ax1 + by1
			a = p1.y - p2.y;
			b = p2.x - p1.x;
			c = a * p1.x + b * p1.y;
		}
		Point getIntersect(Line o) {
			// a1x + b1y = c1
			// a2x + b2y = c2
			// (a1b2 - a2b1)x = c1b2 - c2b1
			// (b1a2 - b2a1)y = c1a2 - c2a1
			double d = a * o.b - o.a * b;
			if (Math.abs(d) < 1e-9) {
				return null;
			}
			double x = (c * o.b - o.c * b) / d;
			double y = (o.c * a - c * o.a) / d;
			return new Point(x, y);
		}
	}
	class Segment {
		Line l;
		Point p1, p2;
		Segment(Point p1, Point p2) {
			this.p1 = p1;
			this.p2 = p2;
			l = new Line(p1, p2);
		}
		boolean isIntersect(Segment s) {
			Point p = l.getIntersect(s.l);
			if (p == null) {
				return false;
			}
			if (p.x >= Math.min(p1.x, p2.x) - 1e-9 &&
				p.x <= Math.max(p1.x, p2.x) + 1e-9 &&
				p.y >= Math.min(p1.y, p2.y) - 1e-9 &&
				p.y <= Math.max(p1.y, p2.y) + 1e-9 &&
        p.x >= Math.min(s.p1.x, s.p2.x) - 1e-9 &&
        p.x <= Math.max(s.p1.x, s.p2.x) + 1e-9 &&
        p.y >= Math.min(s.p1.y, s.p2.y) - 1e-9 &&
        p.y <= Math.max(s.p1.y, s.p2.y) + 1e-9) {
        //System.out.printf("line (%f, %f - %f, %f) and line (%f, %f - %f, %f) hit at point (%.10f, %.10f)\n",
        //    p1.x, p1.y, p2.x, p2.y, s.p1.x, s.p1.y, s.p2.x, s.p2.y, p.x, p.y);
				return true;
			}
			return false;
		}
	}
  // We can optimize by masking blocked areas to corresponding areas in goal area
	public double caromAngle(int puckCoord, int[] xCoords, int[] yCoords) {
		int n = xCoords.length;
		Segment[] blocks = new Segment[n];
		for (int i = 0; i < n; ++i) {
			int sx = xCoords[i] - 25;
			int ex = xCoords[i] + 25;
			int y = yCoords[i];
			blocks[i] = new Segment(new Point(sx, y), new Point(ex, y));
		}
		Point start = new Point(puckCoord, 0);
		Point goal = new Point(0, 1733);
		double leftG = 1500 - 183.0 / 2;
		double rightG = 1500 + 183.0 / 2;
		boolean found = false;
		for (double i = rightG; i >= leftG; i -= 1e-6) {
			goal.x = i;
			if (isGoalOk(goal, start, blocks)) {
				found = true;
				break;
			}
		}
    /*
    double th = 47.022170720170784;
    double hitY = (3000 - start.x) * Math.tan(th*Math.PI / 180.0);
    double xrange = (1733 - hitY) / Math.tan(th*Math.PI / 180.0);
    goal.x = 3000 - xrange;
    System.out.printf("Math.tan(th*Math.PI / 180.0) = %.10f, start.x = %.10f\n",
        Math.tan(th*Math.PI / 180.0), start.x);
    System.out.printf("hitY = %.10f, xrange = %.10f, goal.x = %.10f\n", hitY, xrange,
        goal.x);
    if (isGoalOk(goal, start, blocks)) {
      found = true;
    }
    */
		if (!found) {
			return -1.0;
		}
		double low = goal.x;
		double high = Math.min(goal.x + 1e-6, rightG);
		while (low + 1e-9 < high) {
			double mid = (low + high) / 2;
			goal.x = mid;
			if (isGoalOk(goal, start, blocks)) {
				low = mid;
			} else {
				high = mid;
			}
		}
		goal.x = low;
		Point hit = getHitPoint(goal, start);
		double theta = Math.atan(hit.y / (hit.x - start.x)) * 180.0 / Math.PI;
		return theta;
	}
	
	boolean isGoalOk(Point goal, Point start, Segment[] blocks) {
		Point hit = getHitPoint(goal, start);
		Segment s1 = new Segment(goal, hit);
		Segment s2 = new Segment(start, hit);
		if (isBlocked(s1, blocks) || isBlocked(s2, blocks)) {
			return false;
		}
		return true;
	}
	
	Point getHitPoint(Point goal, Point start) {
		double wallX = 3000;
		double dG = wallX - goal.x;
		double dS = wallX - start.x;
		double totalH = 1733;
		double hitY = totalH * dS / (dG + dS);
    double angle = Math.atan((hitY / (wallX - start.x))) * 180.0 / Math.PI;
    //System.out.printf("try angle %.10f, goal.x = %.10f\n", angle, goal.x);
		return new Point(wallX, hitY);
	}
	
	boolean isBlocked(Segment s, Segment[] blocks) {
		for (Segment b : blocks) {
			if (s.isIntersect(b)) {
				return true;
			}
		}
		return false;
	}

  public static void main(String[] args) {
    PuckShot obj = new PuckShot();
    double ret = obj.caromAngle(2833, new int[]{1500, 1580}, new int[]{1730, 1730});
    System.out.printf("ret = %.10f\n", ret);
  }
}
