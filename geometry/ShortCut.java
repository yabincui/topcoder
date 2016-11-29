/*
 *
 * 1. The shortest path to go from a point to an end point on a line, is to always choose
 * the a/sqrt(3) position.
 * 2. To go from a point  on a line to another point on another line, is to always go
 * at the shortest distance, use a a/sqrt(3) position to arrive directly.
 * 3. So we can find all the important points, calculate their distance. Then use dijistra
 * algorithm to calculate shortest path.
 *    The important points are end points, or mapped from an end point to an line? I can't
 *    find a counterexample.
 */

import java.util.*;

public class ShortCut {

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

		boolean between(Point p1, Point p2) {
			double minx = Math.min(p1.x, p2.x);
			double maxx = Math.max(p1.x, p2.x);
			double miny = Math.min(p1.y, p2.y);
			double maxy = Math.max(p1.y, p2.y);
			if (x >= minx - 1e-9 && x <= maxx + 1e-9 &&
				y >= miny - 1e-9 && y <= maxy + 1e-9) {
				return true;
			}
			return false;
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
			// a(x1-x2) + b(y1-y2) = 0
			a = p2.y - p1.y;
			b = p1.x - p2.x;
			c = a*p1.x+b*p1.y;
		}

		Point getVerticalPoint(Point p) {
			Line t = new Line(-b, a, -b*p.x+a*p.y);
			return intersect(t);
		}

		Point intersect(Line other) {
			// a1x + b1y = c1
			// a2x + b2y = c2
			// (a1b2 - a2b1)x = c1b2 - c2b1
			// (a1b2 - b1a2)y = c2a1 - c1a2
			double t = a * other.b - other.a * b;
			if (Math.abs(t) < 1e-9) {
				return null;
			}
			double x = (c * other.b - other.c * b) / t;
			double y = (other.c * a - c * other.a) / t;
			return new Point(x, y);
		}
	}

  class Segment {
    Point from;
    Point to;
    Line line;
    Segment(Point from, Point to) {
      this.from = from;
      this.to = to;
      this.line = new Line(from, to);
    }

    double len() {
      double dx = from.x - to.x;
      double dy = from.y - to.y;
      return Math.sqrt(dx*dx + dy*dy);
    }

    double dx() {
      return (to.x - from.x) / len();
    }

    double dy() {
      return (to.y - from.y) / len();
    }
  }

  class SegmentPoint {
    Point p;
    Segment s1;
    Segment s2;

    SegmentPoint(Point p, Segment s1, Segment s2) {
      this.p = p;
      this.s1 = s1;
      this.s2 = s2;
    }

    boolean onSegment(Segment s) {
      return s1 == s || s2 == s;
    }

    SegmentPoint goToSegment(Segment s) {
      Point meetP = s.line.getVerticalPoint(p);
      double a = meetP.dist(p);
      double d = a/Math.sqrt(3);
      double dx = s.dx() * d;
      double dy = s.dy() * d;
      Point gotoP = new Point(meetP.x + dx, meetP.y + dy);
      if (gotoP.between(s.from, s.to)) {
        return new SegmentPoint(gotoP, null, s);
      }
      return null;
    }

    SegmentPoint goFromSegment(Segment s) {
      Point meetP = s.line.getVerticalPoint(p);
      double a = meetP.dist(p);
      double d = a/Math.sqrt(3);
      double dx = s.dx() * d;
      double dy = s.dy() * d;
      Point goFromP = new Point(meetP.x - dx, meetP.y - dy);
      if (goFromP.between(s.from, s.to)) {
        return new SegmentPoint(goFromP, null, s);
      }
      return null;
    }

    double distTo(SegmentPoint other) {
      // On the same segment.
      if (s1 != null && other.onSegment(s1)) {
        if (other.p.between(p, s1.to)) {
          return p.dist(other.p);
        }
      }
      if (s2 != null && other.onSegment(s2)) {
        if (other.p.between(p, s2.to)) {
          return p.dist(other.p);
        }
      }
      return p.dist(other.p) * 2;
    }
  }

  class QueueNode {
    int id;
    double dist;

    QueueNode(int id, double dist) {
      this.id = id;
      this.dist = dist;
    }
  }

  class QueueNodeComparator implements Comparator<QueueNode> {
    public int compare(QueueNode n1, QueueNode n2) {
      if (n1.dist != n2.dist) {
          return n1.dist < n2.dist ? -1 : 1;
      }
      return 0;
    }
  }

  public double suvTime(int[] roadX, int[] roadY) {
    int n = roadX.length;
    Point[] ps = new Point[n];
    for (int i = 0; i < n; ++i) {
      ps[i] = new Point(roadX[i], roadY[i]);
    }
    Segment[] segments = new Segment[n-1];
    for (int i = 0; i < n - 1; ++i) {
      segments[i] = new Segment(ps[i], ps[i+1]);
    }
    ArrayList<SegmentPoint> sps = new ArrayList<SegmentPoint>();
    for (int i = 0; i < n; ++i) {
      Segment prev = (i == 0 ? null : segments[i-1]);
      Segment next = (i == n - 1 ? null : segments[i]);
      sps.add(new SegmentPoint(ps[i], prev, next));
    }
    SegmentPoint start = sps.get(0);
    SegmentPoint end = sps.get(n-1);
    // add more SegmentPoints by mapping end points to each segment.
    for (int i = 0; i < n; ++i) {
      SegmentPoint sp = sps.get(i);
      for (int j = 0; j < n - 1; ++j) {
        Segment s = segments[j];
        if (sp.onSegment(s)) continue;
        // go from sp to segment s, go in one direction.
        SegmentPoint mapSp = sp.goToSegment(s);
        if (mapSp != null) {
          System.out.printf("sps %d (%f, %f) is point %d map to line %d\n", sps.size(), mapSp.p.x,
              mapSp.p.y, i, j);
          sps.add(mapSp);
        }
        // go frm segment s to sp, go in one direction.
        mapSp = sp.goFromSegment(s);
        if (mapSp != null) {
          System.out.printf("sps %d (%f, %f) is line %d map to point %d\n", sps.size(), mapSp.p.x,
              mapSp.p.y, j, i);
          sps.add(mapSp);
        }
      }
    }
    int m = sps.size();
    double[][] dist = new double[m][m];
    for (int i = 0; i < m; ++i) {
      for (int j = 0; j < m; ++j) {
        if (i == j) {
          dist[i][i] = 0.0;
        } else {
          dist[i][j] = sps.get(i).distTo(sps.get(j));
          //System.out.printf("dist[%d][%d] = %f\n", i, j, dist[i][j]);
        }
      }
    }
    double[] spDist = new double[m];
    for (int i = 0; i < m; ++i) {
      spDist[i] = Double.MAX_VALUE;
    }
    spDist[0] = dist[0][0];
    boolean[] visited = new boolean[m];
    int[] prevId = new int[m];
    for (int i = 0; i < m; ++i) {
      prevId[i] = -1;
    }
    PriorityQueue<QueueNode> queue = new PriorityQueue<QueueNode>(m, new QueueNodeComparator());
    queue.add(new QueueNode(0, spDist[0]));
    while (!queue.isEmpty()) {
      QueueNode cur = queue.poll();
      //System.out.printf("poll node %d, %f\n", cur.id, cur.dist);
      if (cur.id == n - 1) {
        break;
      }
      if (visited[cur.id]) {
        continue;
      }
      visited[cur.id] = true;
      for (int i = 0; i < m; ++i) {
        double newDist = cur.dist + dist[cur.id][i];
        if (newDist < spDist[i]) {
          spDist[i] = newDist;
          prevId[i] = cur.id;
          queue.add(new QueueNode(i, newDist));
          //System.out.printf("add node (%d, %f)\n", i, newDist);
        }
      }
    }
    for (int i = 0; i < m; ++i) {
      System.out.printf("minDist to point %d (%f, %f) is %f, prevId %d\n",
          i, sps.get(i).p.x, sps.get(i).p.y,
          spDist[i], prevId[i]);
    }
    double minDist = spDist[n-1];
    double normalDist = 0.0;
    for (int i = 1; i < n; ++i) {
      normalDist += sps.get(i-1).distTo(sps.get(i));
    }
    System.out.printf("normalDist = %f, expectedMinDist = %f, minDist = %f\n",
        normalDist, normalDist * 0.03679765825083864, minDist);
    return minDist / normalDist;
  }

  public static void main(String[] args) {
    ShortCut obj = new ShortCut();
    double rate = obj.suvTime(new int[]{0, 0, -100, -100, 100, 100, -100, -100, 100, 100, -20, -20},
      new int[]{0, -10, -10, 4, 4, 5, 5, 6, 6, 20, 20, 7});
    System.out.printf("rate = %f, expected %f\n", rate, 0.03679765825083864);
    /*
    double rate = obj.suvTime(new int[]{0, 5, -9, 1, -6, 8, -10},
                              new int[]{0, 10, -6, 3, -4, -8, -10});
    System.out.printf("rate = %f, expected %f\n", rate, 0.2585980834);
    */
  }
}
