public class ConvexPolygon {
	class Vertex {
		int x, y;
		Vertex(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	public double findArea(int[] x, int[] y) {
		long area = 0;
		int n = x.length;
		for (int i = 1; i + 1 < n; ++i) {
			Vertex a = new Vertex(x[i] - x[0], y[i] - y[0]);
			Vertex b = new Vertex(x[i+1] - x[0], y[i+1] - y[0]);
			area += a.x * b.y - a.y * b.x;
		}
		double result = Math.abs(area / 2.0);
		return result;
	}
}
