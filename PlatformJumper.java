import java.util.*;

public class PlatformJumper {
	class Point {
		int x;
		int y;
		int coin;
		
		Point(int x, int y, int coin) {
			this.x = x;
			this.y = y;
			this.coin = coin;
		}
	}
	
	class PointComparator implements Comparator<Point> {
		public int compare(Point p1, Point p2) {
			return p1.y - p2.y;
		}
	}

	int v;
	int g;
	
	public int maxCoins(String[] platforms, int v, int g) {
		int n = platforms.length;
		this.v = v;
		this.g = g;
		Point[] points = new Point[n];
		for (int i = 0; i < n; ++i) {
			String[] strs = platforms[i].split(" ");
			points[i] = new Point(Integer.parseInt(strs[0]), Integer.parseInt(strs[1]), Integer.parseInt(strs[2]));
		}
		Arrays.sort(points, new PointComparator());
		boolean[][] connect = new boolean[n][n];
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < i; ++j) {
				if (canReach(points[i], points[j])) {
					connect[i][j] = true;
				}
			}
		}
		
		int[] dp = new int[n];
		for (int i = 0; i < n; ++i) {
			int addBelow = 0;
			for (int j = 0; j < i; ++j) {
				if (connect[i][j]) {
					addBelow = Math.max(addBelow, dp[j]);
				}
			}
			dp[i] = points[i].coin + addBelow;
		}
		int result = 0;
		for (int i = 0; i < n; ++i) {
			result = Math.max(result, dp[i]);
		}
		return result;
	}
	
	private boolean canReach(Point p1, Point p2) {
		if (p1.y <= p2.y) {
			return false;
		}
		int diffX = Math.abs(p1.x - p2.x);
		int diffY = p1.y - p2.y;
		double t = Math.sqrt((double)diffY * 2 / g);
		return (diffX / t <= v + 1e-9);
	}
}