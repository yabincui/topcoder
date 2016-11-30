import java.util.*;

public class SquarePoints {
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

    boolean equal(Point other) {
      return Math.abs(x - other.x) < 1e-9 && Math.abs(y - other.y) < 1e-9;
    }

    boolean between(Point p1, Point p2) {
      double minx = Math.min(p1.x, p2.x);
      double maxx = Math.max(p1.x, p2.x);
      double miny = Math.min(p1.y, p2.y);
      double maxy = Math.max(p1.y, p2.y);
      return (x >= minx - 1e-9 && x <= maxx + 1e-9 &&
          y >= miny - 1e-9 && y <= maxy + 1e-9);
    }

    boolean inside(Point p1, Point p2) {
      return between(p1, p2) && !equal(p1) && !equal(p2);
    }

    Point mapToLine(Line line) {
      Line verticalLine = new Line(-line.b, line.a, -line.b * x + line.a * y);
      return line.intersect(verticalLine);
    }

    double distToLine(Line line) {
      Point meetP = mapToLine(line);
      return dist(meetP);
    }
	}

	class Vector {
		double x, y;
		Vector(Point from, Point to) {
			x = to.x - from.x;
			y = to.y - from.y;
		}
		Vector(double x, double y) {
			this.x = x;
			this.y = y;
		}

		double crossProduct(Vector other) {
			return x * other.y - y * other.x;
		}

    	void rescale(double newSize) {
      		double size = Math.sqrt(x*x + y*y);
      		x = x * newSize / size;
      		y = y * newSize / size;
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
			a = p1.y - p2.y;
			b = p2.x - p1.x;
			c = a * p1.x + b * p1.y;
		}

		boolean contains(Point p) {
			return Math.abs(c - a * p.x - b * p.y) < 1e-9;
		}

    Point intersect(Line other) {
      // a1x + b1y = c1
      // a2x + b2y = c2
      // (a1b2 - a2b1)x = c1b2 - c2b1
      // (a1b2 - a2b1)y = c2a1 - c1a2
      double t = a * other.b - other.a * b;
      if (Math.abs(t) < 1e-9) {
        return null;
      }
      double x = (c * other.b - other.c * b) / t;
      double y = (other.c * a - c * other.a) / t;
      return new Point(x, y);
    }

    boolean equal(Line other) {
      if (Math.abs(a) < 1e-9) {
        if (Math.abs(other.a) > 1e-9) {
          return false;
        } else {
          return Math.abs(c / b - other.c / other.b) < 1e-9;
        }
      }
      if (Math.abs(a) < 1e-9) {
        return false;
      }
      if (Math.abs(b/a - other.b / other.a) > 1e-9 ||
          Math.abs(c/a - other.c / other.a) > 1e-9) {
        return false;
      }
      return true;
    }

    double dist(Line other) {
      // the two lines must be parallel.
      Point p = getOneFarPointOnLine();
      return p.distToLine(other);
    }

    Point getOneFarPointOnLine() {
      if (Math.abs(b) < 1e-9)
        return new Point((c - b * 10000 ) / a, 10000);
      return new Point(10000, (c - a * 10000) / b);
	}
	}

  class Rectangle {
    ArrayList<Line> lines = new ArrayList<Line>();

    void addLine(Line line) {
      lines.add(line);
    }

    boolean equal(Rectangle other) {
      for (int i = 0; i < 4; ++i) {
        boolean hasMatch = false;
        for (int j = 0; j < 4; ++j) {
          if (lines.get(i).equal(other.lines.get(j))) {
            hasMatch = true;
          }
        }
        if (!hasMatch) return false;
      }
      return true;
    }
  }

  /*
	public String determineOld(int[] x, int[] y) {
		int n = x.length;
		Point[] ps = new Point[n];
		for (int i = 0; i < n; ++i) {
			ps[i] = new Point(x[i], y[i]);
		}
		// find the leftmost, lowest point.
		int startPoint = 0;
		for (int i = 1; i < n; ++i) {
			if (ps[i].x < ps[startPoint].x) {
				startPoint = i;
			} else if (ps[i].x == ps[startPoint].x &&
				ps[i].y < ps[startPoint].y) {
				startPoint = i;
			}
		}
		// find the point that can be connected from the startPoint
		// with the biggestTheta ( -90 < theta <= 90).
		int biggestPoint = -1;
		Vector biggestVector = null;
		for (int i = 0; i < n; ++i) {
			if (i == startPoint) continue;
			if (biggestPoint == -1) {
				biggestPoint = i;
				biggestVector = new Vector(ps[startPoint], ps[i]);
			} else {
				Vector curV = new Vector(ps[startPoint], ps[i]);
				if (biggestVector.crossProduct(curV) >= 0.0) {
					biggestPoint = i;
					biggestVector = curV;
				}
			}
		}
		// build a line of startPoint and biggestPoint.
		Line firstLine = new Line(ps[startPoint], ps[biggestPoint]);
		int [] used = new int[n];
		Point firstLineP1 = ps[startPoint];
		Point firstLineP2 = ps[startPoint]; // lineP2 is farest to startPoint
		double distP2 = 0.0;
		int usedCount = 0;
		for (int i = 0; i < n; ++i) {
			if (firstLine.contains(ps[i])) {
				double d = ps[i].dist(firstLineP1);
				if (d > distP2) {
					distP2 = d;
					firstLineP2 = ps[i];
				}
				used[i] = 1;
				usedCount++;
			}
		}
		if (usedCount == n) {
			// all points on the same line.
			return "ambiguous";
		}
    final int NO_MATCH = 0;
    final int ONE_MATCH = 1;
    // If there is no points on the opposite edge of firstLine, all left points have to be mapped
    // to <= 2 vertical lines.
    int resultForBigEdge = NO_MATCH;
    {
      ArrayList<Line> verticalLines = markVerticalLines(ps, used, firstLine);
      if (verticalLines.size() == 1) {
        Point meetP = firstLine.intersect(verticalLines.get(0));
        if (!meetP.inside(firstLineP1, firstLineP2)) {
          return "ambiguous";
        }
      } else if (verticalLines.size() == 2) {
        Point meetP1 = firstLine.intersect(verticalLines.get(0));
        Point meetP2 = firstLine.intersect(verticalLines.get(1));
        if (firstLineP1.between(meetP1, meetP2) &&
            firstLineP2.between(meetP1, meetP2)) {
          double edge = meetP1.dist(meetP2);
          double maxDist = 0.0;
          // check max dist to firstLine
          for (int i = 0; i < n; ++i) {
            if (used[i] != 1) {
              maxDist = Math.max(maxDist, ps[i].distToLine(firstLine));
            }
          }
          if (maxDist < edge + 1e-9) {
            resultForBigEdge = ONE_MATCH;
          }
        }
      }
    }
    for (int i = 0; i < n; ++i) {
      if (used[i] != 1) {
        used[i] = 0;
      }
    }
    // We have the oppsite line to firstLine, use the point having the max dist to firstLine.
    int resultForFixedEdge = NO_MATCH;
    do {
      double edge = 0.0;
      Point oppositeP = null;
      for (int i = 0; i < n; ++i) {
        if (used[i] != 1) {
          double d = ps[i].distToLine(firstLine);
          if (d > edge) {
            edge = d;
            oppositeP = ps[i];
          }
        }
      }
      if (edge < firstLineP1.dist(firstLineP2)) {
        break;
      }
      Line oppositeLine = new Line(firstLine.a, firstLine.b, firstLine.a * oppositeP.x +
          firstLine.b * oppositeP.y);
      for (int i = 0; i < n; ++i) {
        if (used[i] == 0) {
          if (oppositeLine.contains(ps[i])) {
            used[i] = 4;
            usedCount++;
          }
        }
      }
      if (usedCount == n) {
        // map every point to firstLine, get the maxDist between two points.
        // if maxDist < edge, ambiguous, maxDist == edge, ONE_MATCH, maxDist > edge, NO_MATCH.
        double maxDist = getMaxDistBetweenPoints(ps, firstLine);
        if (Math.abs(maxDist - edge) < 1e-9) {
          resultForFixedEdge = ONE_MATCH;
        } else if (maxDist < edge) {
          return "ambiguous";
        }
        break;
      }
      ArrayList<Line> verticalLines = markVerticalLines(ps, used, firstLine);
      for (int i = 0; i < n; ++i) {
      	System.out.printf("used[%d] = %d\n", i, used[i]);
      }
      if (verticalLines.size() > 2) {
        break;
      } else if (verticalLines.size() == 1) {
        Point p = firstLineP1;
        if (firstLineP1.distToLine(verticalLines.get(0)) < 1e-9) {
          p = firstLineP2;
        }
        Point from = p.mapToLine(verticalLines.get(0));
        Vector v = new Vector(from, p);
        v.rescale(edge);
        Point to = new Point(from.x + v.x, from.y + v.y);
        Line line1 = verticalLines.get(0);
        Line line = new Line(line1.a, line1.b, line1.a * to.x + line1.b * to.y);
        verticalLines.add(line);
      }
      {
        Line line1 = verticalLines.get(0);
        Line line2 = verticalLines.get(1);
        Point p = line1.intersect(firstLine);
        if (Math.abs(p.distToLine(line2) - edge) > 1e-9) {
          break;
        }
        Point[] s = new Point[n+1];
        for (int i = 0; i < n; ++i) {
          s[i] = ps[i];
        }
        s[n] = p.mapToLine(line2);
        double maxDist = getMaxDistBetweenPoints(s, firstLine);
        if (Math.abs(maxDist - edge) < 1e-9) {
          resultForFixedEdge = ONE_MATCH;
        }
      }
    } while (false);
    int result = resultForBigEdge + resultForFixedEdge;
    if (result == 0) {
      return "inconsistent";
    } else if (result == 1) {
      return "consistent";
    }
    return "ambiguous";
	}
  */

	public String determine(int[] x, int[] y) {
		int n = x.length;
		Point[] ps = new Point[n];
		for (int i = 0; i < n; ++i) {
			ps[i] = new Point(x[i], y[i]);
		}
    // we have at least 5 points, so at least 2 points are at the same line.
    // try all the cases.
    boolean hasRectangle = false;
    Rectangle existRectangle = null;
    for (int i = 0; i < n; ++i) {
      for (int j = i + 1; j < n; ++j) {
        Rectangle rectangle = new Rectangle();
        Line line = new Line(ps[i], ps[j]);
        int result = findRectangleFromLine(ps, line, rectangle);
        //System.out.printf("result = %d, hasRectangle = %b\n", result, hasRectangle);
        //System.out.printf("from line %f x + %f y = %f\n", line.a, line.b, line.c);
        if (result == 0) {
          continue;
        } else if (result == 1) {
          /*
          System.out.printf("rect:\n");
          for (int t = 0; t < 4; ++t) {
            Line l = rectangle.lines.get(t);
            System.out.printf("line %f x + %f y = %f\n", l.a, l.b, l.c);
          }
          */
          if (!hasRectangle) {
            hasRectangle = true;
            existRectangle = rectangle;
          } else if (!existRectangle.equal(rectangle)) {
            return "ambiguous";
          }
        } else {
          return "ambiguous";
        }
      }
    }
    if (hasRectangle) {
      return "consistent";
    }
    return "inconsistent";
  }


  int findRectangleFromLine(Point[] ps, Line firstLine, Rectangle rectangle) {
    int n = ps.length;
		int [] used = new int[n];
		int usedCount = 0;
    ArrayList<Point> firstLinePoints = new ArrayList<Point>();
		for (int i = 0; i < n; ++i) {
			if (firstLine.contains(ps[i])) {
				used[i] = 1;
				usedCount++;
        firstLinePoints.add(ps[i]);
			}
		}
		if (usedCount == n) {
			// all points on the same line.
			return 2;
		}
    Point[] tmpPs = getMaxDistPointsOnLine(firstLinePoints);
    Point firstLineP1 = tmpPs[0];
    Point firstLineP2 = tmpPs[1];

    // Test if other points are at the same direction of the first line.
    if (!isAllPointsOnTheSameSideOfLine(ps, firstLine)) {
      return 0;
    }

    final int NO_MATCH = 0;
    final int ONE_MATCH = 1;
    // If there is no points on the opposite edge of firstLine, all left points have to be mapped
    // to <= 2 vertical lines.
    int resultForBigEdge = NO_MATCH;
    Rectangle rect1 = null;
    //System.out.printf("test for resultForBigEdge\n");
    {
      ArrayList<Line> verticalLines = markVerticalLines(ps, used, firstLine);
      if (verticalLines.size() == 1) {
        if (isAllPointsOnTheSameSideOfLine(ps, verticalLines.get(0))) {
          System.out.printf("t1\n");
          return 2;
        }
      } else if (verticalLines.size() == 2) {
        if (isAllPointsOnTheSameSideOfLine(ps, verticalLines.get(0)) &&
            isAllPointsOnTheSameSideOfLine(ps, verticalLines.get(1))) {
          double edge = verticalLines.get(0).dist(verticalLines.get(1));
          Vector v = null;
          for (int i = 0; i < n; ++i) {
            if (used[i] != 1) {
              Point p = ps[i].mapToLine(firstLine);
              v = new Vector(p, ps[i]);
              break;
            }
          }
          v.rescale(edge);
          Point farP = firstLine.getOneFarPointOnLine();
          Point p2 = new Point(farP.x + v.x, farP.y + v.y);
          Line oppositeLine = new Line(firstLine.a, firstLine.b, firstLine.a * p2.x + firstLine.b * p2.y);
          if (isAllPointsOnTheSameSideOfLine(ps, oppositeLine)) {
            rect1 = new Rectangle();
            rect1.addLine(firstLine);
            rect1.addLine(verticalLines.get(0));
            rect1.addLine(verticalLines.get(1));
            rect1.addLine(oppositeLine);
            resultForBigEdge = ONE_MATCH;
          }
        }
      }
    }
    for (int i = 0; i < n; ++i) {
      if (used[i] != 1) {
        used[i] = 0;
      }
    }
    // We have the oppsite line to firstLine, use the point having the max dist to firstLine.
    //System.out.printf("test for FixedEdge\n");
    int resultForFixedEdge = NO_MATCH;
    Rectangle rect2 = null;
    do {
      double edge = 0.0;
      Point oppositeP = null;
      for (int i = 0; i < n; ++i) {
        if (used[i] != 1) {
          double d = ps[i].distToLine(firstLine);
          if (d > edge) {
            edge = d;
            oppositeP = ps[i];
          }
        }
      }
      if (edge < firstLineP1.dist(firstLineP2)) {
        break;
      }
      Line oppositeLine = new Line(firstLine.a, firstLine.b, firstLine.a * oppositeP.x +
          firstLine.b * oppositeP.y);
      for (int i = 0; i < n; ++i) {
        if (used[i] == 0) {
          if (oppositeLine.contains(ps[i])) {
            used[i] = 4;
          }
        }
      }
      ArrayList<Line> verticalLines = markVerticalLines(ps, used, firstLine);
      if (verticalLines.size() == 0) {
        // map every point to firstLine, get the maxDist between two points.
        // if maxDist < edge, ambiguous, maxDist == edge, ONE_MATCH, maxDist > edge, NO_MATCH.
        Point[] s = getMaxDistPointsMappedOnLine(ps, firstLine);
        double d = s[0].dist(s[1]);
        if (d > edge + 1e-9) {
          break;
        } else if (d < edge - 1e-9) {
          System.out.printf("t2\n");
          return 2;
        }
        verticalLines.add(new Line(firstLine.b, -firstLine.a,
              firstLine.b * s[0].x - firstLine.a * s[0].y));
        verticalLines.add(new Line(firstLine.b, -firstLine.a,
              firstLine.b * s[1].x - firstLine.a * s[1].y));
      } else if (verticalLines.size() == 1) {
        Point p = firstLineP1;
        if (firstLineP1.distToLine(verticalLines.get(0)) < 1e-9) {
          p = firstLineP2;
        }
        Point from = p.mapToLine(verticalLines.get(0));
        Vector v = new Vector(from, p);
        v.rescale(edge);
        Point to = new Point(from.x + v.x, from.y + v.y);
        Line line1 = verticalLines.get(0);
        Line line = new Line(line1.a, line1.b, line1.a * to.x + line1.b * to.y);
        verticalLines.add(line);
      } else if (verticalLines.size() > 2) {
        break;
      }
      {
        if (Math.abs(verticalLines.get(0).dist(verticalLines.get(1)) - edge) < 1e-9 &&
            isAllPointsOnTheSameSideOfLine(ps, verticalLines.get(0)) &&
            isAllPointsOnTheSameSideOfLine(ps, verticalLines.get(1))) {
            rect2 = new Rectangle();
            rect2.addLine(firstLine);
            rect2.addLine(verticalLines.get(0));
            rect2.addLine(verticalLines.get(1));
            rect2.addLine(oppositeLine);
            resultForFixedEdge = ONE_MATCH;
        }
      }
    } while (false);
    if (resultForBigEdge == 0 && resultForFixedEdge == 0) {
      return 0;
    } else if (resultForBigEdge == 1 && resultForFixedEdge == 0) {
      rectangle.lines = rect1.lines;
      return 1;
    } else if (resultForBigEdge == 0 && resultForFixedEdge == 1) {
      rectangle.lines = rect2.lines;
      return 1;
    } else {
      if (rect1.equal(rect2)) {
        rectangle.lines = rect1.lines;
        return 1;
      }
      return 2;
    }
	}


  boolean isAllPointsOnTheSameSideOfLine(Point[] ps, Line line) {
    //System.out.printf("test isAllPointsOnTheSameSideOfLine: %f x + %f y = %f\n", line.a, line.b, line.c);
    Point farP = line.getOneFarPointOnLine();
    Vector v = new Vector(line.b, -line.a);
    boolean hasDelta = false;
    double existDelta = 0.0;
    for (int i = 0; i < ps.length; ++i) {
        Vector v2 = new Vector(farP, ps[i]);
        double delta = v2.crossProduct(v);
        if (Math.abs(delta) < 1e-9) {
          // on line
          continue;
        }
        if (!hasDelta) {
          hasDelta = true;
          existDelta = delta;
        } else if (delta * existDelta < 0) {
          //System.out.printf("return false\n");
          return false;
        }
    }
    //System.out.printf("return true, hasDelta = %b, existDelta = %f\n", hasDelta, existDelta);
    return true;
  }

  ArrayList<Line> markVerticalLines(Point[] ps, int[] used, Line horizonalLine) {
    ArrayList<Line> verticalLines = new ArrayList<Line>();
    int n = used.length;
    for (int i = 0; i < n; ++i) {
      if (used[i] == 0) {
        Line line = new Line(-horizonalLine.b, horizonalLine.a,
        -horizonalLine.b * ps[i].x + horizonalLine.a * ps[i].y);
        verticalLines.add(line);
        used[i] = verticalLines.size() + 1;
        for (int j = i + 1; j < n; ++j) {
          if (used[j] == 0 && line.contains(ps[j])) {
            used[j] = verticalLines.size() + 1;
          }
        }
      }
    }
    return verticalLines;
  }

  Point[] getMaxDistPointsOnLine(ArrayList<Point> ps) {
    int n = ps.size();
    Point pMin = null;
    Point pMax = null;
    for (int i = 0; i < n; ++i) {
      Point p = ps.get(i);
      if (pMin == null || pMin.x > p.x + 1e-9 || (Math.abs(pMin.x - p.x) < 1e-9 && pMin.y > p.y)) {
        pMin = p;
      }
      if (pMax == null || pMax.x < p.x - 1e-9 || (Math.abs(pMax.x - p.x) < 1e-9 && pMax.y < p.y)) {
        pMax = p;
      }
    }
    return new Point[]{pMin, pMax};
  }

  Point[] getMaxDistPointsMappedOnLine(Point[] ps, Line firstLine) {
    int n = ps.length;
    ArrayList<Point> s = new ArrayList<Point>();
    for (int i = 0; i < n; ++i) {
      s.add(ps[i].mapToLine(firstLine));
    }
    return getMaxDistPointsOnLine(s);
  }

  double getMaxDistBetweenPoints(Point[] ps, Line firstLine) {
    Point[] result = getMaxDistPointsMappedOnLine(ps, firstLine);
    return result[0].dist(result[1]);
  }

  public static void main(String[] args) {
    SquarePoints obj = new SquarePoints();
      /*
    String s = obj.determine(new int[]{0,0,2,3,5,5,3,2},
        new int[]{2,3,0,0,3,2,5,5});
    System.out.printf("s = %s\n", s);
    String t = obj.determine(new int[]{ 0,4,8,-5,-1,3,16}, new int[]
{ 0,3,6,15,18,21,12});
    System.out.printf("t = %s\n", t);
    String m = obj.determine(new int[]{0,1,2,0,2,0}, new int[]
{0,0,0,2,2,3});
    System.out.printf("m = %s\n", m);
    */
    String m2 = obj.determine(new int[]{0,0,10,10,5}, new int[]
{0, 10, 0, 10, 0});
    System.out.printf("m = %s\n", m2);
  }
}


















