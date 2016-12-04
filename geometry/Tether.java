import java.util.*;

public class Tether {
	class Point {
		double x, y;
		Point(double x, double y) {
			this.x = x;
			this.y = y;
		}
		
		double dist(Point other) {
			double dx = x - other.x;
			double dy = y - other.y;
			return Math.sqrt(dx*dx + dy*dy);
		}
	}
	
	class Line {
		// ax + by = c
		double a, b, c;
		Line(double a, double b, double c) {
			this.a = a;
			this.b = b;
			this.c = c;
		}
		
		Line(Point p1, Point p2) {
			// ax1 + by1 = c
			// ax2 + by2 = c
			// a(x1-x2) = b(y2-y1)
			a = p2.y - p1.y;
			b = p1.x - p2.x;
			c = a * p1.x + b * p1.y;
		}
	}
	
	class Circle {
		// x^2 + y^2 = r^2
		double r;
		Circle(double r) {
			this.r = r;
		}
		
		Point[] intersect(Line l) {
			// x^2 + y^2 = r^2
			// ax + by = c
			// => a0x^2 + b0x + c0 = 0
			if (Math.abs(l.b) < 1e-9) {
				// ax = c
				double x = l.c / l.a;
				if (Math.abs(x) >= r + 1e-9) {
					// no points
					return null;
				}
				if (Math.abs(x) < r - 1e-9) {
					// two points
					Point[] result = new Point[2];
					double y = Math.sqrt(r*r - x*x);
					result[0] = new Point(x, y);
					result[1] = new Point(x, -y);
					return result;
				}
				// one point
				Point[] result = new Point[1];
				result[0] = new Point(x, 0.0);
				return result;
			}
			
			double a0 = l.a * l.a + l.b * l.b;
			double b0 = -2 * l.a * l.c;
			double c0 = l.c * l.c - r * r * l.b * l.b;
			double t = b0 * b0 - 4 * a0 * c0;
			if (Math.abs(t) < 1e-9) {
				// has only one intersect point.
				double x = -b0 / 2 / a0;
				double y = (l.c - l.a * x) / l.b;
				Point[] result = new Point[1];
				result[0] = new Point(x, y);
				return result;
			}
			if (t < -1e-9) {
				return null;
			}
			t = Math.sqrt(t);
			double x0 = (-b0 + t) / (2 * a0);
			double x1 = (-b0 - t) / (2 * a0);
			double y0 = (l.c - l.a * x0) / l.b;
			double y1 = (l.c - l.a * x1) / l.b;
			System.out.printf("a0 = %f, b0 = %f, c0 = %f\n", a0, b0, c0);
			System.out.printf("t = %f, x0 = %f, y0 = %f, x1 = %f, y1 = %f\n", t, x0, y0, x1, y1);
			Point[] result = new Point[2];
			result[0] = new Point(x0, y0);
			result[1] = new Point(x1, y1);
			return result;
		}
		
		double getCircleDist(Point p1, Point p2) {
			double angle1 = getAngle(p1);
			double angle2 = getAngle(p2);
			double angleDiff = Math.abs(angle1 - angle2);
			if (angleDiff > Math.PI) {
				angleDiff = 2 * Math.PI - angleDiff;
			}
			return angleDiff * r;
		}
		
		double getAngle(Point p) {
			// (-pi, pi].
			if (Math.abs(p.y) < 1e-9) {
				if (p.x > 0) return 0.0;
				return Math.PI;
			}
			double t = Math.asin(Math.abs(p.y) / r);
			if (p.x < 0) {
				t = Math.PI - t;
			}
			if (p.y < 0) {
				t = -t;
			}
			return t;
		}
	}

	public int deadTrees(int rope, int radius, int[] x, int[] y) {
		int n = x.length;
		Point[] ps = new Point[n];
		for (int i = 0; i < n; ++i) {
			ps[i] = new Point(x[i], y[i]);
		}
		Point goat = new Point(0.0, -radius);
		Circle circle = new Circle(radius);
		int result = 0;
		for (int i = 0; i < n; ++i) {
			double lineDist = ps[i].dist(goat);
			if (lineDist > rope + 1e-9) {
				continue;
			}
			Line line1 = new Line(ps[i], goat);
			Point[] inter = circle.intersect(line1);
			if (inter.length == 1 || (inter[0].dist(ps[i]) >= lineDist - 1e-9 &&
				inter[1].dist(ps[i]) >= lineDist - 1e-9)) {
				result++;
				continue;
			}
			Line line2 = new Line(ps[i].x, ps[i].y, radius*radius);
			System.out.printf("line2 = %fx + %fy = %f\n", line2.a, line2.b, line2.c);
			Point[] inter2 = circle.intersect(line2);
			double d0 = circle.getCircleDist(inter2[0], goat) + inter2[0].dist(ps[i]);
			double d1 = circle.getCircleDist(inter2[1], goat) + inter2[1].dist(ps[i]);
			System.out.printf("try point (%f, %f)\n", ps[i].x, ps[i].y);
			System.out.printf("inter2[0] (%f, %f), circleDist %f, lineDist %f, d0 = %f\n",
				inter2[0].x, inter2[0].y, circle.getCircleDist(inter2[0], goat), inter2[0].dist(ps[i]), d0);
			System.out.printf("inter2[1] (%f, %f), circleDist %f, lineDist %f, d1 = %f\n",
				inter2[1].x, inter2[1].y, circle.getCircleDist(inter2[1], goat), inter2[1].dist(ps[i]), d1);
			if (Math.min(d0, d1) < rope + 1e-9) {
				result++;
			}
		}
		return result;
	}
}