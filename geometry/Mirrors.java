
import org.junit.*;

import static org.junit.Assert.*;

public class Mirrors {
	class Point {
		double x, y;
		Point(double x, double y) {
			this.x = x;
			this.y = y;
		}
		
		double dist2(Point o) {
			return (o.x - x) * (o.x - x) + (o.y - y) * (o.y - y);
		}
	}
	
	class Line {
		// ax + by = c.
		double a, b, c;
		Line(Point p1, Point p2) {
			// ax1 + by1 = c
			// ax2 + by2 = c
			// a(x1-x2) = b(y2 - y1)
			// a = y2 - y1
			// b = x1 - x2
			// c = a*x1 + by1
			a = p2.y - p1.y;
			b = p1.x - p2.x;
			c = a * p1.x + b * p1.y;
		}
		
		Line(double a, double b, double c) {
			this.a = a;
			this.b = b;
			this.c = c;
		}
		
		Point intersect(Line o) {
			// a1x + b1y = c1
			// a2x + b2y = c2
			// (a1b2 - a2b1)x = c1b2 - c2b1
			// (b2a1 - b1a2)y = c2a1 - c1a2
			double div = a * o.b - o.a * b;
			if (doubleEqual(div, 0)) {
				return null;
			}
			Point p = new Point((c * o.b - b * o.c) / div, (a * o.c - c * o.a) / div); 
			System.out.printf("line (%f x + %f y = %f) and (%f x + %f y = %f) meet at %f %f\n",
					a, b, c, o.a, o.b, o.c, p.x, p.y);
			return p;
		}
	}
	
	class Segment {
		Point p1, p2;
		Line line;
		Segment(Point p1, Point p2) {
			this.p1 = p1;
			this.p2 = p2;
			line = new Line(p1, p2);
			System.out.printf("segment (%f %f - %f %f) has line %f x + %f y = %f\n",
					p1.x, p1.y, p2.x, p2.y, line.a, line.b, line.c); 
		}
		
		boolean contains(Point o) {
			if (o.x < Math.min(p1.x, p2.x) - 1e-9 || o.x > Math.max(p1.x, p2.x) + 1e-9 ||
					o.y < Math.min(p1.y, p2.y) - 1e-9 || o.y > Math.max(p1.y, p2.y) + 1e-9) {
				return false;
			}
			return true;
		}
		
		Point getRefPoint(Point p, Point meetPoint) {
			Line parallelLine = new Line(line.a, line.b, line.a * p.x + line.b * p.y);
			Line verbLine = new Line(line.b, -line.a, line.b * meetPoint.x - line.a * meetPoint.y);
			Point center = parallelLine.intersect(verbLine);
			return new Point(2 * center.x - p.x, 2 * center.y - p.y);
		}
	}
	
	class OneEndLine {
		Line line;
		Point p;
		double angle;
		
		OneEndLine(Point p, double angle) {
			this.p = p;
			this.angle = angle;
			Point p2 = null;
			if (doubleEqual(angle, Math.PI / 2)) {
				p2 = new Point(p.x, p.y + 1);
			} else if (doubleEqual(angle, Math.PI * 3 / 2)) {
				p2 = new Point(p.x, p.y - 1);
			} else {
				p2 = new Point(p.x + 1, p.y + Math.tan(angle));
			}
			line = new Line(p, p2);
		}
		OneEndLine(Point from, Point to) {
			this.p = from;
			line = new Line(from, to);
			double dx = to.x - from.x;
			double dy = to.y - from.y;
			if (Math.abs(dx) < 1e-9) {
				if (dy > 0) {
					this.angle = Math.PI / 2;
				} else {
					this.angle = Math.PI * 3 / 2;
				}
				return;
			}
			double ang = Math.atan(dy / dx);
			if (Math.abs(ang) < 1e-9) {
				if (dx > 0) {
					this.angle = 0;
				} else {
					this.angle = Math.PI;
				}
				return;
			}
			if (ang > 0) {
				if (dx > 0) {
					this.angle = ang;
				} else {
					this.angle = ang + Math.PI;
				}
			} else {
				if (dx > 0) {
					this.angle = ang + Math.PI * 2;
				} else {
					this.angle = ang + Math.PI;
				}
			}
		}
		
		Point findMeetPoint(Segment s) {
			//System.out.printf("findMeetPoint segment\n");
			Point intersect = line.intersect(s.line);
			if (intersect == null) {
				//System.out.printf("no intersect\n");
				return null;
			}
			if (!s.contains(intersect)) {
				//System.out.printf("intersect %f, %f not contained in segment\n", intersect.x, intersect.y);
				return null;
			}
			if (!contains(intersect)) {
				//System.out.printf("intersect %f, %f not contained in oneEndLine\n", intersect.x, intersect.y);
				return null;
			}
			return intersect;
		}
		
		Point findMeetPoint(Circle c) {
			Point intersect = c.intersect(line);
			if (intersect == null) {
				return null;
			}
			if (!c.contains(intersect) || !contains(intersect)) {
				System.out.printf("not meet\n");
				return null;
			}
			System.out.printf("meet\n");
			return intersect;
		}
		
		boolean contains(Point o) {
			//System.out.printf("ang = %f, 90 = %f 180 = %f 270 = %f\n", angle, Math.PI / 2, Math.PI,
			//		Math.PI * 3 / 2.0);
			//System.out.printf("p = %f, %f, o = %f %f\n", p.x, p.y, o.x, o.y);
			if (angle <= Math.PI / 2 || angle >= Math.PI * 3 / 2.0) {
				if (o.x < p.x - 1e-9) {
					//System.out.printf("t1\n");
					return false;
				}
			} else {
				if (o.x > p.x + 1e-9) {
					//System.out.printf("t2\n");
					return false;
				}
			}
			if (angle >= 0 && angle <= Math.PI) {
				if (o.y < p.y - 1e-9) {
					//System.out.printf("t3\n");
					return false;
				}
			} else {
				if (o.y > p.y + 1e-9) {
					//System.out.printf("t4\n");
					return false;
				}
			}
			return true;
		}
	}
	
	class Circle {
		String name;
		Point p;
		Circle(String name, Point p) {
			this.name = name;
			this.p = p;
		}
		
		Point intersect(Line o) {
			// find dist from p to line o.
			// ax + by = c  y = -a/b * x
			// bx - ay = c2   y = b/a * x
			double a2 = o.b;
			double b2 = -o.a;
			double c2 = a2 * p.x + b2 * p.y;
			Line line = new Line(a2, b2, c2);
			Point result = line.intersect(o);
			System.out.printf("circle %s %f %f meet line (%f x + %f y = %f at %f %f\n",
					name, p.x, p.y, o.a, o.b, o.c, result != null ? result.x : 0.0,
							result != null ? result.y : 0.0); 
			return result;
		}
		
		boolean contains(Point o) {
			double d2 = p.dist2(o);
			System.out.printf("check circle contain d = %f\n", d2);
			return d2 <= 1 + 1e-9;
		}
	}
	
	private boolean doubleEqual(double a, double b) {
		return Math.abs(a - b) < 1e-9;
	}

	public String seen(String[] mirrors, String[] objects, int[] start) {
		int mlen = mirrors.length;
		Segment[] segments = new Segment[mlen];
		for (int i = 0; i < mlen; ++i) {
			String[] strs = mirrors[i].split(" ");
			segments[i] = new Segment(new Point(Integer.parseInt(strs[0]),
				Integer.parseInt(strs[1])), new Point(Integer.parseInt(strs[2]),
				Integer.parseInt(strs[3])));
		}
		int olen = objects.length;
		Circle[] circles = new Circle[olen + 1];
		for (int i = 0; i < olen; ++i) {
			String[] strs = objects[i].split(" ");
			circles[i] = new Circle(strs[0], new Point(Integer.parseInt(strs[1]),
					Integer.parseInt(strs[2])));
		}
		circles[olen] = new Circle("me", new Point(start[0], start[1]));
		OneEndLine curLine = new OneEndLine(new Point(start[0], start[1]), start[2] * Math.PI / 180.0);
		int fromSegment = -1;
		int fromCircle = olen;
		for (int step = 1; step < 100; step++) {
			System.out.printf("step = %d\n", step);
			System.out.printf("curLine = (%f x + %f y = %f)\n", curLine.line.a, curLine.line.b, curLine.line.c);
			System.out.printf("curline = p %f %f, ang %f\n", curLine.p.x, curLine.p.y, curLine.angle);
			// Find where to meet each Segment and Circle.
			int meetSegmentId = -1;
			Point meetSegmentPoint = null;
			double meetSegmentDist2 = Double.MAX_VALUE;
			for (int i = 0; i < mlen; ++i) {
				if (fromSegment == i) {
					continue;
				}
				Point p = curLine.findMeetPoint(segments[i]);
				if (p != null) {
					double d = p.dist2(curLine.p);
					if (meetSegmentPoint == null || d < meetSegmentDist2) {
						meetSegmentPoint = p;
						meetSegmentDist2 = d;
						meetSegmentId = i;
					}
				}
			}
			if (meetSegmentId != -1) {
				System.out.printf("meetSegmentId = %d, point (%f, %f), dist %f)\n", meetSegmentId, meetSegmentPoint.x,
						meetSegmentPoint.y, meetSegmentDist2);
			}
			int meetCircleId = -1;
			Point meetCirclePoint = null;
			double meetCircleDist2 = Double.MAX_VALUE;
			for (int i = 0; i < olen + 1; ++i) {
				if (fromCircle == i) {
					continue;
				}
				Point p = curLine.findMeetPoint(circles[i]);
				if (p != null) {
					double d = p.dist2(curLine.p);
					System.out.printf("circle dist (%f,%f - %f, %f) = %f\n", p.x, p.y, curLine.p.x, curLine.p.y,
							d);
					if (meetCirclePoint == null || d < meetCircleDist2) {
						meetCirclePoint = p;
						meetCircleDist2 = d;
						meetCircleId = i;
					}
				}
			}
			if (meetCircleId != -1) {
				System.out.printf("meetCircleId = %d, point (%f, %f), dist %f\n", meetCircleId,
						meetCirclePoint.x, meetCirclePoint.y, meetCircleDist2);
			}
			
			// If meet nothing
			if (meetCirclePoint == null && meetSegmentPoint == null) {
				return "space";
			}
			// If meet with a circle, break
			if (meetCircleDist2 < meetSegmentDist2) {
				return circles[meetCircleId].name;
			}
			fromCircle = -1;
			fromSegment = meetSegmentId;
			System.out.printf("refPoint\n");
			// If meet with a segment, reflect and get new OneEndLine.
			Point refPoint = segments[meetSegmentId].getRefPoint(curLine.p, meetSegmentPoint);
			System.out.printf("reflect Point %f, %f\n", refPoint.x, refPoint.y);
			curLine = new OneEndLine(meetSegmentPoint, refPoint);
		}
		return "nothing";
	}
	
	public static void main(String[] args) {
		Mirrors obj = new Mirrors();
		//assertEquals("a", obj.seen(new String[]{"0 0 100 100"}, new String[]{"a 15 10"}, new int[]{10,5,90}));
		//assertEquals("space", obj.seen(new String[]{"0 0 100 100"}, new String[]{"a 15 10"}, new int[]{10,5,180}));
		//assertEquals("a", obj.seen(new String[]{"0 0 100 100"}, new String[]{"a 15 10"}, new int[]{20,5,135}));
		//assertEquals("a", obj.seen(new String[]{"0 0 0 1000","1000 0 999 1000"},
		//		new String[]{"a 500 152"}, new int[]{2,0,1}));
		//assertEquals("a", obj.seen(new String[]{"10 0 20 0"}, new String[]{"a 30 0"},
		//		new int[]{0,0,0}));
		assertEquals("ae", obj.seen(new String[]{"459 456 730 185", "81 354 599 540"},
				new String[]{"aa 20 640", "ab 530 405", "ac 91 539", "ad 619 495",
						"ae 416 340", "af 752 938", "ag 674 233"},
				new int[]{586, 251, 128}));
	}
}


