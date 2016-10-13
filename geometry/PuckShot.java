// learns:
// 1. how to judge properly if a segment intersects another segment:
//     when a segment is parallel to x, when a segment is parallel to y, other case.
// 2. Try all situations, as there are two lines a ball should run, a player can block either line.
import org.junit.*;
import static org.junit.Assert.*;

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
			if (isLinePointInSegment(p) && s.isLinePointInSegment(p)) {
				//System.out.printf("segment (%.10f, %.10f - %.10f, %.10f) intersects (%.10f, %.10f - %.10f, %.10f) at (%.10f, %.10f)\n",
				//		p1.x, p1.y, p2.x, p2.y, s.p1.x, s.p1.y, s.p2.x, s.p2.y, p.x, p.y);
				return true;
			}
     		//System.out.printf("segment (%.10f, %.10f - %.10f, %.10f) no intersects (%.10f, %.10f - %.10f, %.10f) at (%.10f, %.10f)\n",
			//			p1.x, p1.y, p2.x, p2.y, s.p1.x, s.p1.y, s.p2.x, s.p2.y, p.x, p.y);
			return false;
		}
		
		boolean isLinePointInSegment(Point p) {
			if (p1.x == p2.x) {
				return p.y >= Math.min(p1.y, p2.y) - 1e-9 &&
						p.y <= Math.max(p1.y, p2.y) + 1e-9;
			}
			if (p1.y == p2.y) {
				return p.x >= Math.min(p1.x, p2.x) - 1e-9 &&
						p.x <= Math.max(p1.x, p2.x) + 1e-9;
			}
			return (p.x >= Math.min(p1.x, p2.x) - 1e-9 &&
					p.x <= Math.max(p1.x, p2.x) + 1e-9 &&
					p.y >= Math.min(p1.y, p2.y) - 1e-9 &&
					p.y <= Math.max(p1.y, p2.y) + 1e-9) ;
		}
	}
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
		
		double maxGoalX = -1.0;
		// Try rightmost place.
		maxGoalX = Math.max(maxGoalX, tryPlace(1591.5 - 1e-6, 1591.5, start, blocks));
		// The player can block the ball in two cases:
		// 1. When the ball is passing from y = 0 to x = 3000.
		// 2. When the ball is passing from x = 3000 to y = 1733.
		// We should try edges for both of two cases.
		// Try two edges of blocks for passing ball from y = 0 to x = 3000.
		for (int i = 0; i < blocks.length; ++i) {
			if (blocks[i].p1.x > start.x) {
				double hy = (3000 - start.x) * blocks[i].p1.y / (blocks[i].p1.x - start.x);
				if (hy < 1733) {
					double gx = 3000 - (3000 - start.x) * (1733 - hy) / hy;
					if (gx >= 1408.5 && gx <= 1591.5 - 1e-6) {
						maxGoalX = Math.max(maxGoalX, tryPlace(gx, gx + 1e-6, start, blocks));
					}
				}
			}
			if (blocks[i].p2.x > start.x) {
				double hy = (3000 - start.x) * blocks[i].p2.y / (blocks[i].p2.x - start.x);
				if (hy < 1733) {
					double gx = 3000 - (3000 - start.x) * (1733 - hy) / hy;
					if (gx >= 1408.5 + 1e-6 && gx <= 1591.5) {
						maxGoalX = Math.max(maxGoalX, tryPlace(gx - 1e-6, gx, start, blocks));
					}
				}
			}
		}
		
		// Try two edges of blocks for passing ball from x = 3000 to y = 1733.
		for (int i = 0; i < blocks.length; ++i) {
			double tx = (3000 - blocks[i].p1.x);
			double sx = (3000 - start.x);
			double sy = (sx / (sx + tx)) * blocks[i].p1.y;
			double ty = blocks[i].p1.y - sy;
			double gx = 3000 - tx * (1733 - sy) / ty; 
			if (gx >= 1408.5 + 1e-6 && gx <= 1591.5) {
				maxGoalX = Math.max(maxGoalX,  tryPlace(gx - 1e-6, gx, start, blocks));
			}
			tx = (3000 - blocks[i].p2.x);
			sy = (sx / (sx + tx)) * blocks[i].p2.y;
			ty = blocks[i].p2.y - sy;
			double gx2 = 3000 - tx * (1733 - sy) / ty;
			//System.out.printf("segment (%.10f, %.10f - %.10f, %.10f) covered (%.10f - %.10f)\n",
			//		blocks[i].p1.x, blocks[i].p1.y, blocks[i].p2.x, blocks[i].p2.y, gx, gx2);
			gx = gx2;
			if (gx >= 1408.5 && gx <= 1591.5 - 1e-6) {
				maxGoalX = Math.max(maxGoalX, tryPlace(gx, gx + 1e-6, start, blocks));
			}
		}
		if (maxGoalX == -1.0) {
			return -1.0;
		}
		//System.out.printf("maxGoalX = %.10f\n", maxGoalX);
		double ey = (3000 - start.x) / (3000 - start.x + 3000 - maxGoalX) * 1733;
		double ang = Math.atan(ey / (3000 - start.x)) * 180.0 / Math.PI;
		return ang;
	}
	
	double tryPlace(double start, double end, Point x, Segment[] blocks) {
		//System.out.printf("tryPlace(%.10f - %.10f\n", start, end);
		Point goal = new Point(0, 1733);
		for (double i = end; i >= start; i -= 1e-9) {
			goal.x = i;
			if (isGoalOk(goal, x, blocks)) {
				//System.out.printf("ok place %.10f\n", i);
				return i;
			}
		}
		return -1.0;
	}
	
	boolean isGoalOk(Point goal, Point start, Segment[] blocks) {
		Point hit = getHitPoint(goal, start);
		//System.out.printf("try goal %.10f, hit point is %f, %f\n", goal.x, hit.x, hit.y);
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
		double res;
		res = obj.caromAngle(2833, new int[]{1500, 1580}, new int[]{1730, 1730});
		assertEquals(47.022170720170784, res, res * 1e-9);
		res = obj.caromAngle(2833, new int[]{2690,  2676}, new int[]{500, 500});
		assertEquals(-1.0, res, 1e-9);
		res = obj.caromAngle(
				2128, new int[]{1590, 1528, 2653, 2657, 2804, 2604, 2796, 2743, 2850},
				new int[]{1730, 1707, 219, 414, 178, 303, 168, 215, 213});
		assertEquals(36.7704834376051, res, 1e-9 * 36);
		res = obj.caromAngle(1000,
				new int[]{1400, 1450, 1500, 1550, 1600},
				new int[]{1733, 1733, 1733, 1733, 1733});
		assertEquals(-1.0, res, 1e-9);
	}
}