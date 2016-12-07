import java.util.*;

public class TurfRoller {
	class Point {
		double x, y;
		Point(double x, double y) {
			this.x = x;
			this.y = y;
		}
		
		Point mapToLine(Line l) {
			Line v = new Line(-l.b, l.a, -l.b * x + l.a * y);
			//System.out.printf("intersect %fx + %fy = %f with %fx + %fy = %f\n",
			//		v.a, v.b, v.c, l.a, l.b, l.c);
			Point result = v.intersect(l);
			//System.out.printf("result = %f, %f\n", result.x, result.y);
			return result;
		}
		
		double dist(Line l) {
			Point p = this.mapToLine(l);
			return dist(p);
		}
		
		double dist(Point p) {
			double dx = x - p.x;
			double dy = y - p.y;
			return Math.sqrt(dx*dx+dy*dy);
		}
	}
	
	class Line {
		// ax + by = c;
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
		Point intersect(Line l) {
			// a1x + b1y = c1
			// a2x + b2y = c2
			// (a1b2 - a2b1)x = c1b2 - c2b1
			// (a1b2 - a2b1)y = c2a1 - c1a2
			double t = a * l.b - l.a * b;
			if (Math.abs(t) < 1e-9) {
				return null;
			}
			double x = (c * l.b - l.c * b) / t;
			double y = (l.c * a - c * l.a) / t;
			return new Point(x, y);
		}
		
		Line move(double dx, double dy) {
			Point p1 = new Point(0, c / b);
			Point p2 = new Point(p1.x + dx, p1.y + dy);
			return new Line(a, b, a * p2.x + b * p2.y);
		}
	}
	
	int lawnWidth = 0;
	int lawnHeight = 0;
	int stripLength = 0;
	int stripBreadth = 0;
	double tanAngle = 0.0;
	double sinAngle = 0.0;
	double cosAngle = 0.0;
	Line leftL = null;
	Line downL = null;
	Line rightL = null;
	Line upL = null;
	int result = 0;
	
	public int stripNum(int lawnWidth, int lawnHeight, int stripAngle, int stripLength, int stripBreadth) {
		if (stripAngle == 0) {
			int width = (lawnWidth + stripLength - 1) / stripLength;
			int height = (lawnHeight + stripBreadth - 1) / stripBreadth;
			return width * height;
		} else if (stripAngle == 90) {
			int width = (lawnWidth + stripBreadth - 1) / stripBreadth;
			int height = (lawnHeight + stripLength - 1) / stripLength;
			return width * height;
		}
		this.lawnWidth = lawnWidth;
		this.lawnHeight = lawnHeight;
		this.stripLength = stripLength;
		this.stripBreadth = stripBreadth;
		tanAngle = Math.tan(stripAngle * Math.PI / 180);
		sinAngle = Math.sin(stripAngle * Math.PI / 180);
		cosAngle = Math.cos(stripAngle * Math.PI / 180);
		leftL = new Line(1, 0, 0);
		rightL = new Line(1, 0, lawnWidth);
		downL = new Line(0, 1, 0);
		upL = new Line(0, 1, lawnHeight);
		result = 0;
		
		Point downRightP = new Point(lawnWidth, 0);
		Line line = new Line(downRightP, new Point(downRightP.x + cosAngle,
				downRightP.y + sinAngle));
		double dist = new Point(0, lawnHeight).dist(line);
		System.out.printf("dist = %f\n", dist);
		for (int count = 1; ; ++count) {
			// make first time uses count strips exactly.
			double x = count * stripLength * cosAngle;
			double xLimit = stripBreadth / sinAngle;
			boolean noMore = false;
			if (x > xLimit) {
				noMore = true;
				x = xLimit;
			}
			double step = x * sinAngle;
			tryDistToUpLeft(step);
			
			if (noMore) {
				break;
			}
		}
		Point downLeftP = new Point(0, 0);
		line = new Line(downLeftP, new Point(downLeftP.x + cosAngle,
				downLeftP.y + sinAngle));
		double distToDownLeftLine = new Point(0, lawnHeight).dist(line);
		Point upRightP = new Point(lawnWidth, lawnHeight);
		line = new Line(upRightP, new Point(upRightP.x + cosAngle,
				upRightP.y + sinAngle));
		double distToUpRightLine = new Point(0, lawnHeight).dist(line);
		double maxDist = 0.0;
		if (lawnHeight >= lawnWidth * tanAngle) {
			double t1 = Math.min((lawnHeight - lawnWidth * tanAngle) * cosAngle, stripBreadth);
			//System.out.printf("t1 = %f, tanAngle = %f, t1 * tanAngle = %f\n", t1, tanAngle, t1 * tanAngle);
			maxDist = lawnWidth / cosAngle + t1 * tanAngle;	
		} else {
			double t1 = Math.min((lawnWidth - lawnHeight / tanAngle) * sinAngle, stripBreadth);
			maxDist = lawnHeight / sinAngle + t1 / tanAngle;
		}
		//System.out.printf("maxDist = %f\n", maxDist);
		//System.out.printf("distToDownLeftLine = %f, distToUpRightLine = %f\n", distToDownLeftLine,
		//		distToUpRightLine);
		for (int count = 1; ; ++count) {
			double len = stripLength * count;
			double h = len * sinAngle;
			double w = len * cosAngle;
			//System.out.printf("count = %d, len = %f, h = %f, w = %f\n", count, len, h, w);
			double step = 0.0;
			if (len > maxDist + 1e-9) {
				break;
			}
			if (w > lawnWidth + 1e-9) {
				double leftLen = len - lawnWidth/cosAngle;
				double d = leftLen / tanAngle;
				step = d + distToUpRightLine;
			} else if (h > lawnHeight + 1e-9) {
				double leftLen = len - lawnHeight / sinAngle;
				double d = leftLen * tanAngle;
				step = d + distToDownLeftLine;
			} else {
				step = w * sinAngle;
			}
			//System.out.printf("count = %d, step = %f, dist - step = %f\n", count, step,
			//		dist - step);
			tryDistToUpLeft(step);
			tryDistToUpLeft(dist - step);
		}
		
		return result;
	}
	
	private void tryDistToUpLeft(double step) {
		Point upLeftP = new Point(0, lawnHeight);
		step -= ((int)(step / stripBreadth)) * stripBreadth;
		step = stripBreadth - step;
		if (step >= stripBreadth - 1e-9) {
			step = 0.0;
		}
		Point p = new Point(upLeftP.x - sinAngle * step, upLeftP.y + cosAngle * step);
		Line lastLine = new Line(p, new Point(p.x + cosAngle, p.y + sinAngle));
		int result = tryLine(lastLine);
		System.out.printf("tryStep %f, result = %d\n", step, result);
		if (this.result == 0 || this.result > result) {
			this.result = result;
		}
	}
	
	private int tryLine(Line lastLine) {
		int result = 0;
		while (true) {
			//System.out.printf("lastLine = %fx + %fy = %f\n", lastLine.a, lastLine.b, lastLine.c);
			Point p = new Point(lawnWidth, 0).mapToLine(lastLine);
			if (p.x >= lawnWidth - 1e-9) {
				break;
			}
			Line nextLine = lastLine.move(stripBreadth * sinAngle, -stripBreadth * cosAngle);
			//System.out.printf("nextLine = %fx + %fy = %f\n", nextLine.a, nextLine.b, nextLine.c);
			Point leftestP = null;
			p = lastLine.intersect(leftL);
			//System.out.printf("p1 = %f, %f\n", p.x, p.y);
			if (p.y >= -1e-9 && p.y <= lawnHeight + 1e-9) {
				Point q = p.mapToLine(nextLine);
				//System.out.printf("q = %f, %f\n", q.x, q.y);
				if (leftestP == null || q.x < leftestP.x) {
					//System.out.printf("t1\n");
					leftestP = q;
				}
			}
			p = nextLine.intersect(leftL);
			//System.out.printf("p2 = %f, %f\n", p.x, p.y);
			if (p.y >= -1e-9 && p.y <= lawnHeight + 1e-9) {
				Point q = p.mapToLine(nextLine);
				//System.out.printf("q = %f, %f\n", q.x, q.y);
				if (leftestP == null || q.x < leftestP.x) {
					//System.out.printf("t2\n");
					leftestP = q;
				}
			}
			if (p.y < 1e-9 && lastLine.intersect(leftL).y > -1e-9) {
				Point q = new Point(0, 0).mapToLine(nextLine);
				if (leftestP == null || q.x < leftestP.x) {
					//System.out.printf("t2.5\n");
					leftestP = q;
				}
			}
			p = lastLine.intersect(downL);
			//System.out.printf("p3 = %f, %f\n", p.x, p.y);
			if (p.x >= -1e-9 && p.x <= lawnWidth + 1e-9) {
				Point q = p.mapToLine(nextLine);
				if (leftestP == null || q.x < leftestP.x) {
					//System.out.printf("t3\n");
					leftestP = q;
				}
			}
			p = nextLine.intersect(downL);
			//System.out.printf("p4 = %f, %f", p.x, p.y);
			if (p.x >= -1e-9 && p.x <= lawnWidth + 1e-9) {
				Point q = p.mapToLine(nextLine);
				if (leftestP == null || q.x < leftestP.x) {
					//System.out.printf("t4\n");
					leftestP = q;
				}
			}
			Line vLine = new Line(-nextLine.b, nextLine.a, -nextLine.b * leftestP.x + nextLine.a * leftestP.y);
			//System.out.printf("leftestP = %f, %f, vLine = %fx + %fy = %f\n",
			//		leftestP.x, leftestP.y, vLine.a, vLine.b, vLine.c);
			int count = 0;
			while (true) {
				count++;
				result++;
				vLine = vLine.move(stripLength * cosAngle, stripLength * sinAngle);
				Point p1 = vLine.intersect(lastLine);
				//System.out.printf("vLine = %fx + %fy = %f\n", vLine.a, vLine.b, vLine.c);
				int okCount = 0;
				//System.out.printf("p = %f, %f\n", p.x, p.y);
				if (p1.x >= lawnWidth - 1e-9 || p1.y >= lawnHeight - 1e-9) {
					okCount++;
				}
				Point p2 = vLine.intersect(nextLine);
				//System.out.printf("p = %f, %f\n", p.x, p.y);
				if (p2.x >= lawnWidth - 1e-9 || p2.y >= lawnHeight - 1e-9) {
					okCount++;
				}
				if (okCount == 2) {
					Point p3 = vLine.intersect(upL);
					Point p4 = vLine.intersect(rightL);
					if (p3.x < lawnWidth - 1e-9 && p3.x > p1.x + 1e-9 && p3.x < p2.x - 1e-9) {
						okCount = 0;
					}
					if (p4.y < lawnHeight - 1e-9 && p4.y > p2.y + 1e-9 && p4.y < p1.y - 1e-9) {
						okCount = 0;
					}
					if (okCount == 2) {
						break;
					}
				}
			}
			//System.out.printf("used count %d\n", count);
			lastLine = nextLine;
		}
		return result;
	}
	
	public static void main(String[] args) {
		TurfRoller obj = new TurfRoller();
		//int result = obj.stripNum(2,  3, 45, 2, 1);
		//int result = obj.stripNum(47, 29, 38, 24, 21);
		int result = obj.stripNum(80, 90, 23, 94, 19);
		System.out.printf("result = %d\n", result);
	}
}