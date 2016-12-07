import java.util.*;

public class Watchtower {
	class Point {
		double x, y;
		Point(double x, double y) {
			this.x = x;
			this.y = y;
		}
		Line getMiddleLine(Point p) {
			Line l = new Line(this, p);
			double mx = (x + p.x) / 2;
			double my = (y + p.y) / 2;
			return new Line(-l.b, l.a, -l.b * mx + l.a * my);
		}
		double dist(Point p) {
			double dx = x - p.x;
			double dy = y - p.y;
			return Math.sqrt(dx * dx + dy * dy);
		}
		
		boolean equals(Point p) {
			return Math.abs(x - p.x) < 1e-9 && Math.abs(y - p.y) < 1e-9;
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
			a = p2.y - p1.y;
			b = p1.x - p2.x;
			c = a * p1.x + b * p1.y;
		}
		Point intersect(Line l) {
			// a1x + b1y = c1
			// a2x + b2y = c2
			// (a1b2-a2b1)x = c1b2 - c2b1
			// (a1b2-a2b1)y = c2a1 - c1a2
			double t = a * l.b - l.a * b;
			if (Math.abs(t) < 1e-9) {
				return null;
			}
			double x = (c * l.b - l.c * b) / t;
			double y = (l.c * a - c * l.a) / t;
			return new Point(x, y);
		}
		boolean isSameLine(Line l) {
			if (Math.abs(a) < 1e-9) {
				if (Math.abs(l.a) > 1e-9) {
					return false;
				}
				double r = l.b / b;
				return Math.abs(l.c - c * r) < 1e-9;
			}
			if (Math.abs(l.a) < 1e-9) {
				return false;
			}
			double r = l.a / a;
			if (Math.abs(l.b - b * r) > 1e-9) {
				return false;
			}
			if (Math.abs(l.c - c * r) > 1e-9) {
				return false;
			}
			return true;
		}
		
		double getAngle() {
			if (Math.abs(b) < 1e-9) {
				return Math.PI / 2;
			}
			if (Math.abs(a) < 1e-9) {
				return 0;
			}
			double ang = Math.atan(-a / b);
			if (ang < 0) {
				ang += 2 * Math.PI;
			}
			return ang;
		}
	}
	
	class NextNode {
		Node n;
		double ang;
		NextNode(Node n, double ang) {
			this.n = n;
			this.ang = ang;
		}
	}
	
	int nodeCount = 0;
	class Node {
		int nodeId;
		Point p;
		double circleStartAng = 0;
		double visitedAngle = 0;
		// angle [0, 2PI)
		ArrayList<Double> neighborAngles = new ArrayList<Double>();
		ArrayList<Node> neighbors = new ArrayList<Node>();
		ArrayList<Boolean> neighborVisited = new ArrayList<Boolean>();
		Node(Point p) {
			this.nodeId = ++nodeCount;
			this.p = p;
		}
		void addNeighbor(double angle, Node n) {
			neighborAngles.add(angle);
			neighbors.add(n);
			neighborVisited.add(false);
		}
		
		NextNode getNextVisitNeighbor(double fromAng) {
			// visit in clockwise order.
			double ang = reverseAngle(fromAng);
			double clockWiseDist = Double.MAX_VALUE;
			int nextAngId = -1;
			for (int i = 0; i < neighbors.size(); ++i) {
				double curAng = neighborAngles.get(i);
				if (Math.abs(curAng - ang) < 1e-9) {
					continue;
				}
				double angDist = clockWiseDist(ang, curAng);
				if (angDist < clockWiseDist) {
					clockWiseDist = angDist;
					nextAngId = i;
				}
			}
			neighborVisited.set(nextAngId, true);
			visitedAngle += clockWiseDist;
			return new NextNode(neighbors.get(nextAngId), neighborAngles.get(nextAngId));
		}
		
		NextNode startVisitCircle() {
			for (int i = 0; i < neighbors.size(); ++i) {
				if (neighborVisited.get(i) == false) {
					neighborVisited.set(i, true);
					circleStartAng = neighborAngles.get(i);
					return new NextNode(neighbors.get(i), neighborAngles.get(i));
				}
			}
			return null;
		}
		
		void finishVisitCircle(double ang) {
			if (circleStartAng == -1) {
				System.exit(1);
			}
			ang = reverseAngle(ang);
			visitedAngle += clockWiseDist(ang, circleStartAng);
			circleStartAng = -1;
		}
	}
	
	class NodeComparator implements Comparator<Node> {

		@Override
		public int compare(Node n1, Node n2) {
			if (Math.abs(n1.p.x - n2.p.x) < 1e-9) {
				return n1.p.y > n2.p.y ? 1 : -1;
			}
			return n1.p.x > n2.p.x ? 1 : -1;
		}
	}
	
	private double reverseAngle(double ang) {
		if (ang < Math.PI) {
			return ang + Math.PI;
		}
		return ang - Math.PI;
	}
	
	private double clockWiseDist(double fromAng, double toAng) {
		if (fromAng <= toAng) {
			return toAng - fromAng;
		}
		return toAng - fromAng + 2 * Math.PI;
	}

	int n;
	private Point[] towers;
	
	public int[] orderByArea(int[] x, int[] y) {
		n = x.length;
		towers = new Point[n];
		for (int i = 0; i < n; ++i) {
			towers[i] = new Point(x[i], y[i]);
		}
		Line[] lines = buildLines();
		//System.out.printf("lines.length = %d\n", lines.length);
		//for (int i = 0; i < lines.length; ++i) {
		//	System.out.printf("lines[%d] = %f x + %f y = %f\n", i, lines[i].a, lines[i].b, lines[i].c);
		//}
		ArrayList<Node> nodes = buildNodes(lines);
		double[] towerArea = new double[n];
		for (int i = 0; i < nodes.size(); ++i) {
			while (true) {
				NextNode next = nodes.get(i).startVisitCircle();
				if (next == null) {
					double ang = nodes.get(i).visitedAngle;
					if (Math.abs(ang - Math.PI * 2) > 1e-9) {
						//System.out.printf("visited Angle for node %d (%f, %f) is %f\n", nodes.get(i).nodeId,
						//		nodes.get(i).p.x, nodes.get(i).p.y, nodes.get(i).visitedAngle);
					}
					break;
				}
				Node start = nodes.get(i);
				double area = 0;
				double sumX = start.p.x;
				double sumY = start.p.y;
				int count = 1;
				//System.out.printf("start (%d)%.10f, %.10f -> ", start.nodeId, start.p.x, start.p.y);
				//HashSet<Node> path = new HashSet<Node>();
				while (next != null) {
					Node cur = next.n;
					/*
					if (path.contains(cur)) {
						System.out.printf("path conflict\n");
						System.exit(1);
					}
					path.add(cur);
					*/
					sumX += cur.p.x;
					sumY += cur.p.y;
					//System.out.printf("(%d)%.10f, %.10f -> ", cur.nodeId, cur.p.x, cur.p.y);
					count++;
					next = cur.getNextVisitNeighbor(next.ang);
					if (next.n == start) {
						start.finishVisitCircle(next.ang);
						break;
					}
					area += getArea(start.p, cur.p, next.n.p);
				}
				area = Math.abs(area) / 2.0;
				//System.out.printf("area = %f\n", area);
				Point p = new Point(sumX / count, sumY / count);
				int id = getNearestTower(p);
				towerArea[id] += area;
			}
		}
		int[] result = new int[n];
		boolean[] used = new boolean[n];
		double sumArea = 0.0;
		for (int i = 0; i < n; ++i) {
			double maxArea = -1;
			int sel = -1;
			for (int j = 0; j < n; ++j) {
				if (!used[j] && towerArea[j] > maxArea) {
					maxArea = towerArea[j];
					sel = j;
				}
			}
			used[sel] = true;
			result[i] = sel;
			//System.out.printf("result[%d] = %d, area %f\n", i, sel, maxArea);
			sumArea += maxArea;
		}
		//System.out.printf("sumArea = %f\n", sumArea);
		return result;
	}
	
	int getNearestTower(Point p) {
		int selId = -1;
		double minDist = Double.MAX_VALUE;
		for (int i = 0; i < n; ++i) {
			double d = p.dist(towers[i]);
			if (d < minDist) {
				minDist = d;
				selId = i;
			}
		}
		return selId;
	}
	
	double getArea(Point p, Point q1, Point q2) {
		double x1 = q1.x - p.x;
		double y1 = q1.y - p.y;
		double x2 = q2.x - p.x;
		double y2 = q2.y - p.y;
		return x1 * y2 - x2 * y1;
	}
	
	private ArrayList<Node> buildNodes(Line[] lines) {
		int lineN = lines.length;
		ArrayList<Node> result = new ArrayList<Node>();
		ArrayList<ArrayList<Node>> lineNodes = new ArrayList<ArrayList<Node>>();
		for (int i = 0; i < lineN; ++i) {
			lineNodes.add(new ArrayList<Node>());
		}
		for (int i = 0; i < lineN; ++i) {
			for (int j = i + 1; j < lineN; ++j) {
				Point p = lines[i].intersect(lines[j]);
				if (p == null || p.x < -1e-9 || p.x > 100 + 1e-9 || p.y < -1e-9 || p.y > 100 + 1e-9) {
					continue;
				}
				Node n = null;
				for (int k = 0; k < lineNodes.get(i).size(); ++k) {
					if (lineNodes.get(i).get(k).p.equals(p)) {
						n = lineNodes.get(i).get(k);
						break;
					}
				}
				if (n == null) {
					n = new Node(p);
					lineNodes.get(i).add(n);
					result.add(n);
				}
				//System.out.printf("line %d %d intersect node %d (%.10f, %.10f)\n", i, j, n.nodeId,
				//		n.p.x, n.p.y);
				boolean used = false;
				for (int k = 0; k < lineNodes.get(j).size(); ++k) {
					if (lineNodes.get(j).get(k) == n) {
						used = true;
						break;
					}
				}
				if (!used) {
					lineNodes.get(j).add(n);
				}
			}
		}
		NodeComparator comparator = new NodeComparator();
		for (int i = 0; i < lineN; ++i) {
			ArrayList<Node> nodes = lineNodes.get(i);
			Collections.sort(nodes, comparator);
			double ang = lines[i].getAngle();
			double revAng = reverseAngle(ang);
			boolean omitForward = false;
			boolean omitBackward = false;
			if (i == 0 || i == 3) {
				omitBackward = true;
			} else if (i == 1 || i == 2) {
				omitForward = true;
			}
			for (int j = 0; j < nodes.size() - 1; ++j) {
				if (!omitForward) {
					nodes.get(j).addNeighbor(ang, nodes.get(j+1));
				}
				if (!omitBackward) {
					nodes.get(j+1).addNeighbor(revAng, nodes.get(j));
				}
			}
		}
		return result;
	}
	
	private Line[] buildLines() {
		ArrayList<Line> lines = new ArrayList<Line>();
		lines.add(new Line(1, 0, 0));
		lines.add(new Line(1, 0, 100));
		lines.add(new Line(0, 1, 0));
		lines.add(new Line(0, 1, 100));
		for (int i = 0; i < n; ++i) {
			for (int j = i + 1; j < n; ++j) {
				lines.add(towers[i].getMiddleLine(towers[j]));
			}
		}
		//System.out.printf("line count %d\n", lines.size());
		int count = 0;
		for (int i = 0; i < lines.size(); ++i) {
			if (lines.get(i) == null) {
				continue;
			}
			for (int j = i + 1; j < lines.size(); ++j) {
				if (lines.get(j) != null && lines.get(i).isSameLine(lines.get(j))) {
					//System.out.printf("isSameLine %fx+%fy=%f, %fx+%fy=%f\n",
					//		lines.get(i).a, lines.get(i).b, lines.get(i).c,
					//		lines.get(j).a, lines.get(j).b, lines.get(j).c);
					lines.set(j, null);
				}
			}
			//System.out.printf("left line %f x + %fy = %f\n", lines.get(i).a, lines.get(i).b, lines.get(i).c);
			count++;
		}
		//System.out.printf("line count %d\n", count);
		Line[] res = new Line[count];
		int j = 0;
		for (int i = 0; i < lines.size(); ++i) {
			if (lines.get(i) != null) {
				res[j++] = lines.get(i);
				//System.out.printf("res[%d]\n", j - 1);
			}
		}
		return res;
	}
	
	public static void main(String[] args) {
		Watchtower obj = new Watchtower();
		int[] result = obj.orderByArea(new int[]

{14, 16, 31, 39, 42, 64, 70, 90, 97}, new int[]{28, 41, 28, 91, 60, 41, 48, 11, 86});

		System.out.printf("result= ");
		for (int i = 0; i < result.length; ++i) {
			System.out.printf("%d ", result[i]);
		}
		System.out.printf("\n");
	}
}