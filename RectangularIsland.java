import java.math.*;
import java.util.*;

public class RectangularIsland {
	public double theProbablity1(int width, int height, int x, int y,
			int steps) {
		double[][] m = new double[height][width];
		m[y][x] = 1.0;
		int[] dy = new int[]{-1, 1, 0, 0};
		int[] dx = new int[]{0, 0, -1, 1};
		for (int i = 0; i < steps; ++i) {
			double[][] next = new double[height][width];
			for (int j = 0; j < height; ++j) {
				for (int k = 0; k < width; ++k) {
					double sum = 0;
					for (int t = 0; t < 4; ++t) {
						int ty = j + dy[t];
						int tx = k + dx[t];
						if (ty >= 0 && ty < height && tx >= 0 && tx < width) {
							sum += m[ty][tx];
						}
					}
					next[j][k] = sum / 4;
				}
			}
			m = next;
		}
		double sum = 0;
		for (int i = 0; i < height; ++i) {
			for (int j = 0; j < width; ++j) {
				sum += m[i][j];
			}
		}
		return sum;
	}
	
	// http://d.hatena.ne.jp/kusano_prog/20100606/1275839942
	public double theProbablity(int width, int height, int x, int y,
			int steps) {
		double[] yInLimit = new double[steps + 1];
		double[] yPro = new double[height];
		yInLimit[0] = 1.0;
		yPro[y] = 1.0;
		for (int i = 1; i <= steps; ++i) {
			double[] nextYPro = new double[height];
			for (int j = 0; j < height; ++j) {
				double sum = 0;
				if (j > 0) {
					sum += yPro[j - 1];
				}
				if (j < height - 1) {
					sum += yPro[j + 1];
				}
				nextYPro[j] = sum / 2.0;
			}
			yPro = nextYPro;
			double sum = 0;
			for (int j = 0; j < height; ++j) {
				sum += yPro[j];
			}
			yInLimit[i] = sum;
		}
		
		double[] xInLimit = new double[steps + 1];
		double[] xPro = new double[width];
		xInLimit[0] = 1.0;
		xPro[x] = 1.0;
		for (int i = 1; i <= steps; ++i) {
			double[] nextXPro = new double[width];
			for (int j = 0; j < width; ++j) {
				double sum = 0;
				if (j > 0) {
					sum += xPro[j - 1];
				}
				if (j < width - 1) {
					sum += xPro[j + 1];
				}
				nextXPro[j] = sum / 2.0;
			}
			xPro = nextXPro;
			double sum = 0;
			for (int j = 0; j < width; ++j) {
				sum += xPro[j];
			}
			xInLimit[i] = sum;
		}
		
		BigDecimal sum = new BigDecimal(0.0);
		BigDecimal[] C = generateC(steps);
		BigDecimal factor = new BigDecimal(2.0, MathContext.DECIMAL128).pow(-steps,
				MathContext.DECIMAL128);
		for (int i = 0; i <= steps; ++i) {
			BigDecimal p = C[i].multiply(new BigDecimal(yInLimit[i]),
					MathContext.DECIMAL128)
					.multiply(new BigDecimal(xInLimit[steps - i]), MathContext.DECIMAL128)
							.multiply(factor, MathContext.DECIMAL128);
			sum = sum.add(p);
		}
		return sum.doubleValue();
	}
	
	BigDecimal[] generateC(int n) {
		BigDecimal[] C = new BigDecimal[n+1];
		C[0] = new BigDecimal(1.0);
		for (int i = 1; i <= n; ++i) {
			C[i] = C[i - 1].multiply(new BigDecimal(n - i + 1), MathContext.DECIMAL128)
					.divide(new BigDecimal(i), MathContext.DECIMAL128);
		}
		return C;
	}
}