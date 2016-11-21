import java.util.*;

public class DogWoods {

	class Point {
		double x, y;
		Point(double x, double y) {
			this.x = x;
			this.y = y;
		}
	}
	
	class Circle {
		double x0;
		double y0;
		double r0;
		
		Circle(double x0, double y0, double r0) {
			this.x0 = x0;
			this.y0 = y0;
			this.r0 = r0;
		}
		
		Point[] findIntersectsWithCenterCircle(double r1) {
			double d = x0 * x0 + y0 * y0;
			double p2 = ((r0 + r1) * (r0 + r1) - d) * (d - (r1 - r0) * (r1 - r0));
			if (p2 < 0) {
				return null;
			}
			double p = Math.sqrt(p2);
			double x = x0 / 2 - x0 * (r0 * r0 - r1 * r1) / (2*d) + y0 * p / (2*d);
			double y = y0 / 2 - y0 * (r0 * r0 - r1 * r1) / (2*d) - x0 * p / (2*d);
			Point[] result = new Point[2];
			result[0] = new Point(x, y);
			x = x - y0 * p / d;
			y = y + x0 * p / d;
			result[1] = new Point(x, y);
			return result;
		}
		
		double getRadius(Point p) {
			double dx = p.x - x0;
			double dy = p.y - y0;
			double radius = 0;
			if (Math.abs(dx) < 1e-6) {
				if (dy > 0) {
					radius = Math.PI / 2;
				} else {
					radius = Math.PI * 3 / 2;
				}
			} else if (Math.abs(dy) < 1e-6) {
				if (dx > 0) {
					radius = 0.0;
				} else {
					radius = Math.PI;
				}
			} else {
				radius = Math.atan(Math.abs(dy / dx));
				if (dy > 0 && dx < 0) {
					radius = Math.PI - radius;
				} else if (dy < 0 && dx < 0) {
					radius = Math.PI + radius;
				} else if (dy < 0 && dx > 0) {
					radius = 2 * Math.PI - radius;
				}
			}
			return radius;
		}
	}
	
	double radiusDistance(double radiusFrom, double radiusTo, boolean clockwise) {
		if (Math.abs(radiusFrom - radiusTo) < 1e-6) {
			return 0;
		}
		if (clockwise) {
			if (radiusFrom > radiusTo) {
				return radiusFrom - radiusTo;
			} else {
				return radiusFrom + 2 * Math.PI - radiusTo;
			}
		}
		if (radiusFrom < radiusTo) {
			return radiusTo - radiusFrom;
		} else {
			return 2 * Math.PI + radiusTo - radiusFrom;
		}
	}
	
	double getDistance(double radiusDist, double r) {
		return radiusDist * r;
	}
	
	public double howFar(int[] x, int[] y, int[] diameter, int startx, int starty) {
		Circle center = new Circle(0, 0, 10);
		int n = x.length;
		Circle[] trees = new Circle[n];
		for (int i = 0; i < n; ++i) {
			trees[i] = new Circle(x[i], y[i], diameter[i] / 2.0);
		}
		boolean[] visitedTree = new boolean[n];
		for (int i = 0; i < n; ++i) {
			visitedTree[i] = false;
		}
		double result = 0.0;
		Point dog = new Point(startx, starty);
		while (true) {
			// find nearest intersect tree.
			double minRadiusDist = 0.0;
			Point meetPoint = null;
			int meetTree = -1;
			double dogR = Math.sqrt(dog.x * dog.x + dog.y * dog.y);
			if (dogR < 10 + 1e-6) {
				break;
			}
			double dogRadius = center.getRadius(dog);
			System.out.printf("dogRadius = %f\n", dogRadius);
			for (int i = 0; i < n; ++i) {
				if (visitedTree[i]) {
					continue;
				}
				Point[] ps = trees[i].findIntersectsWithCenterCircle(dogR);
				if (ps == null) {
					continue;
				}
				for (Point p : ps) {
					double radius = center.getRadius(p);
					double radiusDist = radiusDistance(dogRadius, radius, true);
					if (meetPoint == null || (radiusDist < minRadiusDist)) {
						minRadiusDist = radiusDist;
						meetPoint = p;
						meetTree = i;
					}
				}
			}
			if (meetPoint == null) {
				return -1;
			}
			visitedTree[meetTree] = true;
			// calculate distance
			double dist = getDistance(minRadiusDist, dogR);
			result += dist;
			System.out.printf("before meetTree %d, radiusDist = %f, dist = %f, result = %f\n",
				meetTree, minRadiusDist, dist, result);
			// go anti-clockwise.
			double treeRadius = trees[meetTree].getRadius(meetPoint);
			// Can go clockwise again.
			double r0 = Math.sqrt(trees[meetTree].x0 * trees[meetTree].x0 +
								  trees[meetTree].y0 * trees[meetTree].y0);
			double rate = (r0 - trees[meetTree].r0) / r0;
			Point leavePoint = new Point(rate * trees[meetTree].x0,
										 rate * trees[meetTree].y0);
			System.out.printf("meetTree %d, x0 %f, y0 %f\n", meetTree, trees[meetTree].x0,
				trees[meetTree].y0);
			System.out.printf("treeRadius = %f, r0 = %f, rate = %f, leavePoint %f, %f\n",
				treeRadius, r0, rate, leavePoint.x, leavePoint.y);
			double leaveTreeRadius = trees[meetTree].getRadius(leavePoint);
			double leaveTreeRadiusDist = radiusDistance(treeRadius, leaveTreeRadius, false);
			System.out.printf("leaveTreeRadiusDist = %f\n", leaveTreeRadiusDist);
			// Does the dog go within 10 units of the center before leaving the tree?
			Point[] pt = trees[meetTree].findIntersectsWithCenterCircle(10.0);
			if (pt != null) {
				double minTreeRadiusDist = 100.0;
				for (Point p : pt) {
					double tmpRadius = trees[meetTree].getRadius(p);
					double tmpRadiusDist = radiusDistance(treeRadius, tmpRadius, false);
					if (tmpRadiusDist < minTreeRadiusDist) {
						minTreeRadiusDist = tmpRadiusDist;
					}
					System.out.printf("tree[%d] meet with center 10units at %f,%f\n",
						meetTree, p.x, p.y);
				}
				if (minTreeRadiusDist < leaveTreeRadiusDist) {
					result += getDistance(minTreeRadiusDist, trees[meetTree].r0);
					break;
				}
			}
			result += getDistance(leaveTreeRadiusDist, trees[meetTree].r0);
			dog = leavePoint;
			System.out.printf("dog = %f, %f, result = %f\n", dog.x, dog.y, result);
		}
		return result;
	}
}
