import java.util.*;

public class CirclesCountry {

	class Circle {
		int x, y, r;
		Circle(int x, int y, int r) {
			this.x = x;
			this.y = y;
			this.r = r;
		}
		
		private double dist(int x1, int y1, int x2, int y2) {
			return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
		}
		
		boolean contains(Circle other) {
			return dist(x, y, other.x, other.y) + other.r < r;
		}
		
		boolean contains(int x1, int y1) {
			return dist(x, y, x1, y1) < r;
		}
	}
	
	class CircleComparator implements Comparator<Circle> {
		public int compare(Circle c1, Circle c2) {
			return c1.r - c2.r;
		}
	}
	
	public int leastBorders(int[] X, int[] Y, int[] R, int x1, int y1, int x2, int y2) {
		int n = X.length;
		Circle[] circles = new Circle[n];
		for (int i = 0; i < n; ++i) {
			circles[i] = new Circle(X[i], Y[i], R[i]);
		}
		Arrays.sort(circles, new CircleComparator());
		int[] parent = new int[n];
		for (int i = 0; i < n; ++i) {
			parent[i] = -1;
		}
		for (int i = 0; i < n; ++i) {
			for (int j = i + 1; j < n; ++j) {
				if (circles[j].r == circles[i].r) {
					continue;
				}
				if (circles[j].contains(circles[i])) {
					parent[i] = j;
					break;
				}
			}
		}
		int ca = -1;
		for (int i = 0; i < n; ++i) {
			if (circles[i].contains(x1, y1)) {
				ca = i;
				break;
			}
		}
		ArrayList<Integer> pathA = new ArrayList<Integer>();
		while (ca != -1) {
			pathA.add(ca);
			ca = parent[ca];
		}
		int cb = -1;
		for (int i = 0; i < n; ++i) {
			if (circles[i].contains(x2, y2)) {
				cb = i;
				break;
			}
		}
		ArrayList<Integer> pathB = new ArrayList<Integer>();
		while (cb != -1) {
			pathB.add(cb);
			cb = parent[cb];
		}
		int endA = pathA.size();
		int endB = pathB.size();
		while (endA != 0 && endB != 0 && pathA.get(endA - 1) == pathB.get(endB - 1)) {
			endA--;
			endB--;
		}
		return endA + endB;
	}
}
