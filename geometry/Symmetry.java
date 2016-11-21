import java.util.*;

public class Symmetry {
	class Line {
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
			// a = y2 - y1
			// b = x1 - x2
			// c = ax1 + by1
			a = p2.y - p1.y;
			b = p1.x - p2.x;
			c = a * p1.x + b * p1.y;
		}
		
		Point getRefPoint(Point o) {
			double pa = -b;
			double pb = a;
			double pc = pa * o.x + pb * o.y;
			Line parallelLine = new Line(pa, pb, pc);
			Point intersect = getIntersect(parallelLine);
			return new Point(intersect.x * 2 - o.x, intersect.y * 2 - o.y);
		}
		
		Point getIntersect(Line o) {
			// a1x + b1y = c1
			// a2x + b2y = c2
			// (a1b2 - a2b1) x = c1b2 - c2b1
			// (a1b2 - a2b1) y = c2a1 - c1a2
			double d = a*o.b - o.a * b;
			if (Math.abs(d) < 1e-9) {
				return null;
			}
			return new Point((c*o.b - o.c*b) / d, (o.c*a - c*o.a) / d);
		}
		
		boolean contains(Point p) {
			double r = a * p.x + b * p.y;
			return Math.abs(r - c) < 1e-9;
		}
	}

	class Point {
		double x, y;
		Point(double x, double y) {
			this.x = x;
			this.y = y;
		}
		Line getMiddleLine(Point o) {
			Point center = new Point((x + o.x) / 2, (y + o.y) / 2);
			Line parallelLine = new Line(this, o);
			double a = -parallelLine.b;
			double b = parallelLine.a;
			double c = a * center.x + b * center.y;
			return new Line(a, b, c);
		}
		
		boolean equals(Point o) {
			if (Math.abs(x - o.x) < 1e-9 && Math.abs(y - o.y) < 1e-9) {
				return true;
			}
			return false;
		}
	}
	
	public int countLines(String[] points) {
		ArrayList<Point> ps = new ArrayList<Point>();
		for (String s : points) {
			String[] strs = s.split(" ");
			for (int i = 0; i < strs.length; i += 2) {
				ps.add(new Point(Integer.parseInt(strs[i]), Integer.parseInt(strs[i+1])));
			}
		}
		int n = ps.size();
		boolean[][] hit = new boolean[n][n];
		int result = 0;
		// try the middle line of every two points.
		for (int i = 0; i < n; ++i) {
			for (int j = i + 1; j < n; ++j) {
				if (!hit[i][j]) {
					Line line = ps.get(i).getMiddleLine(ps.get(j));
					//System.out.printf("genfrom p%f, %f, p%f,%f\n", ps.get(i).x, ps.get(i).y,
					//	ps.get(j).x, ps.get(j).y);
					//System.out.printf("tryLine %f x + %f y = %f\n", line.a, line.b, line.c);
					int[] others = new int[n];
					Arrays.fill(others, -1);
					others[i] = j;
					others[j] = i;
					int k;
					for (k = 0; k < n; ++k) {
						if (others[k] != -1) {
							continue;
						}
						Point refK = line.getRefPoint(ps.get(k));
						boolean found = false;
						for (int t = k; t < n; ++t) {
							if (others[t] != -1) {
								continue;
							}
							if (refK.equals(ps.get(t))) {
								others[t] = k;
								others[k] = t;
								found = true;
								break;
							}
						}
						if (!found) {
							//System.out.printf("can't find ref point for %f,%f, which is %f,%f\n",
							//	ps.get(k).x, ps.get(k).y, refK.x, refK.y);
							break;
						}
					}
					if (k == n) {
						//System.out.printf("success\n");
						for (k = 0; k < n; ++k) {
							hit[k][others[k]] = true;
						}
						result++;
					}
				}
			}
		}
		// test if all points are on the same line.
		{
			Line line = new Line(ps.get(0), ps.get(1));
			int i;
			for (i = 2; i < ps.size(); ++i) {
				if (!line.contains(ps.get(i))) {
					break;
				}
			}
			if (i == ps.size()) {
				result++;
			}
		}
		return result;
	}
}
