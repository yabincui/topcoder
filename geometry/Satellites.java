import java.util.*;

public class Satellites {
	static class Point {
		double x, y, z;
		
		Point(double x, double y, double z) {
			this.x = x;
			this.y = y;
			this.z = z;
		}
		
		static Point createFromLat(double latitude, double longtitude, double height) {
			double tx = height * Math.cos(latitude / 180.0 * Math.PI) * Math.cos(longtitude / 180.0 * Math.PI);
			double ty = height * Math.cos(latitude / 180.0 * Math.PI) * Math.sin(longtitude / 180.0 * Math.PI);
			double tz = height * Math.sin(latitude / 180.0 * Math.PI);
			return new Point(tx, ty, tz);
		}
		
		Point crossProduct(Point p) {
			double tx = y * p.z - z * p.y;
			double ty = x * p.z - z * p.x;
			double tz = x * p.y - y * p.x;
			return new Point(tx, ty, tz);
		}
		
		double length() {
			return Math.sqrt(x*x+y*y+z*z);
		}
		
		Point minus(Point p) {
			return new Point(x - p.x, y - p.y, z - p.z);
		}
	}
	
	public int[] detectable(String[] rockets, String[] satellites) {
		int m = rockets.length;
		Point[] rock = new Point[m];
		for (int i = 0; i < m; ++i) {
			String[] strs = rockets[i].split(" ");
			double latitude = Double.parseDouble(strs[0]);
			double longtitude = Double.parseDouble(strs[1]);
			double height = 400 + 6400;
			rock[i] = Point.createFromLat(latitude, longtitude, height);
		}
		int n = satellites.length;
		Point[] sat = new Point[n];
		for (int i = 0; i < n; ++i) {
			String[] strs = satellites[i].split(" ");
			double latitude = Double.parseDouble(strs[0]);
			double longtitude = Double.parseDouble(strs[1]);
			double height = Double.parseDouble(strs[2]);
			sat[i] = Point.createFromLat(latitude, longtitude, height + 6400);
		}
		ArrayList<Integer> canSee = new ArrayList<Integer>();
		for (int i = 0; i < m; ++i) {
			//System.out.printf("check rock %d\n", i);
			if (verifyCanSee(rock[i], sat)) {
				canSee.add(i);
				//System.out.printf("can see rock %d\n", i);
			}
		}
		int[] result = new int[canSee.size()];
		for (int i = 0; i < result.length; ++i) {
			result[i] = canSee.get(i);
		}
		return result;
	}
	
	private boolean verifyCanSee(Point x, Point[] ps) {
		int count = 0;
		double r = x.length();
		boolean show = false;
		if (x.x < 0) show = true;
		for (int i = 0; i < ps.length; ++i) {
			Point p = ps[i];
			Point cross = p.crossProduct(x);
			double area = cross.length();
			Point minus = p.minus(x);
			double len = minus.length();
			if (len < 1e-9) {
				count++;
			} else {
				double dist = area / len;
				if (show) {
					//System.out.printf("dist with sat %d is %f\n", i, dist);
				}
				double s = p.length();
				if (r*r + len*len < s*s) {
					dist = r;
					//System.out.printf("use dist of r\n");
				} else if (s*s + len*len < r*r) {
					dist = s;
					//System.out.printf("use dist of s\n");
				}
				if (dist > 6400) {
					count++;
					//System.out.printf("sat %d can see\n", i);
				}
			}
			if (count == 3) {
				return true;
			}
		}
		return false;
	}
}
