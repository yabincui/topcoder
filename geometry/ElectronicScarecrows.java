
import java.util.*;
import org.junit.*;
import org.omg.CORBA.PUBLIC_MEMBER;

import static org.junit.Assert.*;

import java.util.Arrays;

public class ElectronicScarecrows {
	class Point {
		long x, y;
		Point(long x, long y) {
			this.x = x;
			this.y = y;
		}
	}
	
	Point[] points;
	long[][] pointsMul;
	double[][] pointsAngle; // pointsAngle[i][j] is angle of line from points[i] to points[j].
							// range 90 -> 0 -> -90 -> -180 -> -270
	long[][] pointsDist;  // pointsDist[i][j] is dist between points[i] and points[j]
	double maxArea;
	public double largestArea(int[] x, int[] y, int n) {
		points = new Point[x.length];
		pointsMul = new long[x.length][x.length];
		for (int i = 0; i < x.length; ++i) {
			points[i] = new Point(x[i], y[i]);
		}
		for (int i = 0; i < x.length; ++i) {
			for (int j = 0; j < x.length; ++j) {
				pointsMul[i][j] = x[i]*y[j] - x[j]*y[i];
			}
		}
		pointsAngle = new double[x.length][x.length];
		pointsDist = new long[x.length][x.length];
		for (int i = 0; i < x.length; ++i) {
			for (int j = 0; j < x.length; ++j) {
				long dist = (x[i]-x[j]) * (x[i]-x[j]) + (y[i]-y[j])*(y[i]-y[j]);
				pointsDist[i][j] = pointsDist[j][i] = dist;
				if (x[i] == x[j]) {
					if (y[i] <= y[j]) {
						pointsAngle[i][j] = Math.PI / 2.0;
					} else {
						pointsAngle[i][j] = -Math.PI / 2.0;
					}
				} else if (x[i] < x[j]) {
					double ang = Math.atan((double)(y[j]-y[i]) / (x[j]-x[i]));
					pointsAngle[i][j] = ang;
				} else {
					double ang = Math.atan((double)(y[j]-y[i]) / (x[j]-x[i]));
					pointsAngle[i][j] = ang - Math.PI;
				}
			}
		}
		maxArea = 0;
		for (int i = 0; i < points.length; ++i) {
			searchFromPoint(i, n);
		}
		return maxArea;
	}
	
	class PointIndex {
		int index;
		double angleFromStart;
		long distFromStart;
		PointIndex(int index, double angleFromStart, long distFromStart) {
			this.index = index;
			this.angleFromStart = angleFromStart;
			this.distFromStart = distFromStart;
		}
	}
	
	class PointIndexComparator implements Comparator<PointIndex> {
		public int compare(PointIndex p1, PointIndex p2) {
			if (Math.abs(p1.angleFromStart - p2.angleFromStart) > 1e-9) {
				if (p1.angleFromStart > p2.angleFromStart) {
					return -1;
				}
				return 1;
			}
			if (p1.distFromStart > p2.distFromStart) {
				return 1;
			}
			return -1;
		}
	}
	
	void searchFromPoint(int start, int usePointCount) {
		int n = points.length;
		// sort points based on angle with start point, points in the same line based on
		// their dist with start point.
		PointIndex[] indexes = new PointIndex[n];
		for (int i = 0; i < n; ++i) {
			indexes[i] = new PointIndex(i, pointsAngle[start][i], pointsDist[start][i]);
		}
		Arrays.sort(indexes, new PointIndexComparator());
		if (indexes[0].index != start) {
			System.err.printf("error\n");
		}
		
		// dpMax[i][j] is the max area value for using i points and
		// reaching points[j].
		// dpMin[i][j] is the min area value for using i points and
		// reaching points[j].
		long[][] dpMax = new long[usePointCount + 1][n];
		long[][] dpMin = new long[usePointCount + 1][n];
		//System.out.printf("start = %d\n", start);
		for (int i = 1; i < n; ++i) {
			dpMax[2][i] = dpMin[2][i] = pointsMul[start][indexes[i].index];
			//System.out.printf("dp[2][%d] = %d\n", i, dpMax[2][i]);
		}
		for (int count = 3; count <= usePointCount; ++count) {
			for (int i = 1; i < n; ++i) {
				long maxValue = Long.MIN_VALUE;
				long minValue = Long.MAX_VALUE;
				int t1 = -1;
				int t2 = -1;
				for (int j = 1; j < i; ++j) {
					long add = pointsMul[indexes[j].index][indexes[i].index];
					if (dpMax[count-1][j] != Long.MIN_VALUE) {
						long value = dpMax[count-1][j] + add;
						maxValue = Math.max(maxValue, value);
						if (maxValue == value) {
							t1 = j;
						}
						minValue = Math.min(minValue, value);
						if (minValue == value) {
							t2 = j;
						}
					}
					if (dpMin[count-1][j] != Long.MAX_VALUE) {
						long value = dpMin[count-1][j] + add;
						maxValue = Math.max(maxValue, value);
						if (maxValue == value) {
							t1 = -j;
						}
						minValue = Math.min(minValue, value);
						if (minValue == value) {
							t2 = -j;
						}
					}
				}
				dpMax[count][i] = maxValue;
				dpMin[count][i] = minValue;
				//System.out.printf("dpMax[%d][%d]=%d, dpMin[%d][%d]=%d, t1 = %d, t2 = %d\n",
				//		count, i, maxValue, count, i, minValue, t1, t2);
			}
		}
		long area = 0;
		for (int count = 3; count <= usePointCount; ++count) {
			for (int i = 1; i < n; ++i) {
				long add = pointsMul[indexes[i].index][start];
				if (dpMax[count][i] != Long.MIN_VALUE) {
					long value = dpMax[count][i] + add;
					area = Math.max(area, Math.abs(value));
				}
				if (dpMin[count][i] != Long.MAX_VALUE) {
					long value = dpMin[count][i] + add;
					area = Math.max(area, Math.abs(value));
				}
				/*
				double d = Math.abs(dpMax[count][i] + add);
				area = Math.max(area, d);
				System.out.printf("count = %d, i = %d, d = %f, area = %f\n",
					count, i, d, area);
				d = Math.abs(dpMin[count][i] + add);
				area = Math.max(area, d);
				System.out.printf("count = %d, i = %d, d = %f, area = %f\n",
					count, i, d, area);
				*/
			}
		}
		maxArea = Math.max(maxArea, area / 2.0);
		//System.out.printf("area = %f, maxArea = %f\n", area / 2.0, maxArea);
	}
	
	public static void main(String[] args) {
		ElectronicScarecrows obj = new ElectronicScarecrows();
		assertEquals(24.0,
				obj.largestArea(new int[]{2,1,6,5,3,7,9},
							    new int[]{2,5,1,5,7,6,4},
							    4), 1e-9);
							  
		assertEquals(347200.0, obj.largestArea(new int[]{183,229,723,510,395,936,447,328},
				new int[]{1000,823,0,412,786,446,312,286}, 15), 1e-9);
		assertEquals(740.5, obj.largestArea(new int[]{33,36,26,8,12,8,28,19,8,37,9,22,31,30,25},
				new int[]{12,8,6,16,27,7,31,33,35,22,22,36,29,22,32}, 8), 1e-9);
		assertEquals(685819.5, obj.largestArea(new int[]{327,196,744,91,709,38,944,300,927,715,835,874,958,667,748,511,377,956,184,956,
 809,925,12,45,184,180,169,374,914,398,954,875,286,422,76,315,497,209,512,938},
				new int[]
						{913,843,73,213,903,444,444,905,352,54,194,207,373,57,105,959,932,480,843,424,
 140,661,578,616,851,132,135,936,676,23,578,737,74,959,724,924,955,854,958,376},
						25), 1e-9);
	}
}