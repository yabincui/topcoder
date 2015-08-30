public class DonutsOnTheGrid {
	public long calc(int H, int W, int seed, int threshold) {
		char[][] map = new char[H][W];
		long x = seed;
		for (int i = 0; i < H; ++i) {
			for (int j = 0; j < W; ++j) {
				x = (x * 25173 + 13849) % 65536;
				if (x >= threshold) {
					map[i][j] = '.';
				} else {
					map[i][j] = '0';
				}
			}
		}
		
		// columnEnds[i][j] = k, if map[i..k][j] == '0', otherwise, k = -1.
		int[][] columnEnds = new int[H][W];
		for (int i = H - 1; i >= 0; --i) {
			for (int j = 0; j < W; ++j) {
				if (map[i][j] == '.') {
					columnEnds[i][j] = -1;
				} else {
					if (i == H - 1 || columnEnds[i + 1][j] == -1) {
						columnEnds[i][j] = i;
					} else {
						columnEnds[i][j] = columnEnds[i + 1][j];
					}
				}
			}
		}
		
		int result = 0;
		for (int i = 0; i < H; ++i) {
			for (int j = i + 2; j < H; ++j) {
				int connectColumns = 0;
				int nearConnectColumns = 0;
				int lastConnectColumn = -1;
				int startColumn = -1;
				for (int k = 0; k < W; ++k) {
					if (map[i][k] == '.' || map[j][k] == '.') {
						if (startColumn != -1) {
							int add = (connectColumns * (connectColumns - 1) / 2) - nearConnectColumns;
							result += add;
						}
						startColumn = -1;
						continue;
					}
					if (startColumn == -1) {
						startColumn = k;
						connectColumns = 0;
						nearConnectColumns = 0;
						lastConnectColumn = -1;
					}
					if (columnEnds[i][k] >= j) {
						connectColumns++;
						if (lastConnectColumn != -1 && lastConnectColumn == k - 1) {
							nearConnectColumns++;
						}
						lastConnectColumn = k;
					}
				}
				if (startColumn != -1) {
					int add = (connectColumns * (connectColumns - 1) / 2) - nearConnectColumns;
					result += add;
				}
			}
		}
		
		return result;
	}
}