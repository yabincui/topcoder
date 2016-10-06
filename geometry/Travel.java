import java.util.*;
public class Travel {
	class Point {
		double x, y, z;
		Point(double x, double y, double z) {
			this.x = x;
			this.y = y;
			this.z = z;
		}
		
		double dot(Point p) {
			return x * p.x + y * p.y + z * p.z;
		}
		
		double len() {
			return Math.sqrt(x*x + y*y + z*z);
		}
	}
	public int shortest(String[] cities, int radius) {
		int n = cities.length;
		Point[] points = new Point[n];
		for (int i = 0; i < n; ++i) {
			String[] strs = cities[i].split(" ");
			int lat = Integer.parseInt(strs[0]);
			int lon = Integer.parseInt(strs[1]);
			double x = radius * Math.cos(lat * Math.PI / 180.0) * Math.cos(lon * Math.PI / 180.0);
			double y = radius * Math.cos(lat * Math.PI / 180.0) * Math.sin(lon * Math.PI / 180.0);
			double z = radius * Math.sin(lat * Math.PI / 180.0);
			points[i] = new Point(x, y, z);
		}
		double[][] dist = new double[n][n];
		for (int i = 0; i < n; ++i) {
			for (int j = i + 1; j < n; ++j) {
				double theta = Math.acos(points[i].dot(points[j]) / points[i].len() / points[j].len());
				double d = theta * radius;
				dist[i][j] = dist[j][i] = d;
			}
		}
		boolean[] used = new boolean[n];
		used[0] = true;
		double min = permute(0, 1, used, dist, 0.0, Double.MAX_VALUE);
		return (int)Math.round(min);
	}
	
	private double permute(int cur, int used_count, boolean[] used, double[][] dist, double cur_dist, double min_dist) {
		if (cur_dist >= min_dist) {
			return min_dist;
		}
		if (used_count == used.length) {
			cur_dist += dist[0][cur];
			return Math.min(cur_dist, min_dist);
		}
		for (int i = 0; i < used.length; ++i) {
			if (!used[i]) {
				used[i] = true;
				min_dist = permute(i, used_count + 1, used, dist, cur_dist + dist[cur][i], min_dist);
				used[i] = false;
			}
		}
		return min_dist;
	}
}
