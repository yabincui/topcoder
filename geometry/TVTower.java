public class TVTower {
	class Point {
		double x, y;
		Point(double x, double y) {
			this.x = x;
			this.y = y;
		}
		
		Point middle(Point other) {
			return new Point((x + other.x) / 2, (y + other.y) / 2);
		}
		
		double dist(Point other) {
			return Math.sqrt((x - other.x) * (x - other.x) + (y - other.y) * (y - other.y));
		}
	}
	public double minRadius(int[] x, int[] y) {
		int n = x.length;
		Point[] v = new Point[n];
		for (int i = 0; i < n; ++i) {
			v[i] = new Point(x[i], y[i]);
		}
		double minRadius = 0;
		// Try center of 2 points.
		for (int i = 0; i < n; ++i) {
			for (int j = i + 1; j < n; ++j) {
				Point center = v[i].middle(v[j]);
				double r = radiusForCenter(v, center);
				if (minRadius == 0) {
					minRadius = r;
				} else {
					minRadius = Math.min(r, minRadius);
				}
			}
		}
		// Try circle center of 3 points.
		for (int i = 0; i < n; ++i) {
			for (int j = i + 1; j < n; ++j) {
				for (int k = j + 1; k < n; ++k) {
					Point center = findCircleCenter(v[i], v[j], v[k]);
					double r = radiusForCenter(v, center);
					if (minRadius == 0) {
						minRadius = r;
					} else {
						minRadius = Math.min(r, minRadius);
					}
				}
			}
		}
		return minRadius;
	}
	
	double radiusForCenter(Point[] v, Point center) {
		double r = 0;
		for (int i = 0; i < v.length; ++i) {
			r = Math.max(r, center.dist(v[i]));
		}
		return r;
	}
	
	Point findCircleCenter(Point a, Point b, Point c) {
		Point p1 = a.middle(b);
		Point p2 = b.middle(c);
		double a1 = a.x - b.x;
		double b1 = a.y - b.y;
		double c1 = a1 * p1.x + b1 * p1.y;
		double a2 = b.x - c.x;
		double b2 = b.y - c.y;
		double c2 = a2 * p2.x + b2 * p2.y;
		double delta = a1 * b2 - a2 * b1;
		if (Math.abs(delta) < 1e-6) {
			return new Point(0, 0);
		}
		double x = (b2 * c1 - b1 * c2) / delta;
		double y = (a1 * c2 - a2 * c1) / delta;
		return new Point(x, y);
	}
}
