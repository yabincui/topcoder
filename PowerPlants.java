import java.util.*;

public class PowerPlants {
	int n;
	int mask;
	int[] cost;
	boolean[] visited;
	int[][] connection;
	int numPlants;
	
	public int minCost(String[] connectionCost, String plantList, int numPlants) {
		n = plantList.length();
		mask = (1 << n) - 1;
		cost = new int[mask + 1];
		visited = new boolean[mask + 1];
		connection = new int[n][n];
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) {
				char c = connectionCost[i].charAt(j);
				int connect = 0;
				if (c >= '0' && c <= '9') {
					connect = c - '0';
				} else if (c >= 'A' && c <= 'Z') {
					connect = c - 'A' + 10;
				}
				connection[i][j] = connect;
			}
		}
		this.numPlants = numPlants;
		int state = 0;
		for (int i = 0; i < n; ++i) {
			if (plantList.charAt(i) == 'Y') {
				state |= (1 << i);
			}
		}
		if (getBits(state) == 0) {
			return -1;
		}
		return getValue(state);
	}
	
	private int getValue(int state) {
		if (visited[state]) {
			return cost[state];
		}
		visited[state] = true;
		if (getBits(state) >= numPlants) {
			cost[state] = 0;
		} else {
			int result = Integer.MAX_VALUE;
			for (int i = 0; i < n; ++i) {
				if ((state & (1 << i)) == 0) {
					int min = Integer.MAX_VALUE;
					for (int j = 0; j < n; ++j) {
						if ((state & (1 << j)) != 0) {
							min = Math.min(min, connection[j][i]);
						}
					}
					int value = getValue(state | (1 << i));
					result = Math.min(result, value + min);
				}
			}
			cost[state] = result;
		}
		return cost[state];
	}
	
	private int getBits(int state) {
		int bit = 0;
		for (int i = 0; i < n; ++i) {
			if ((state & (1 << i)) != 0) {
				bit++;
			}
		}
		return bit;
	}
	
//public int minCost(String[] connectionCost, String plantList, int numPlants) {
//		int n = plantList.length();
//		int[] dist = new int[n];
//		boolean[] powered = new boolean[n];
//		int poweredCount = 0;
//		for (int i = 0; i < n; ++i) {
//			dist[i] = Integer.MAX_VALUE;
//			powered[i] = false;
//		}
//		for (int i = 0; i < n; ++i) {
//			if (plantList.charAt(i) == 'Y') {
//				powered[i] = true;
//				poweredCount++;
//				dist[i] = 0;
//				for (int j = 0; j < n; ++j) {
//					if (powered[j]) {
//						continue;
//					}
//					char c = connectionCost[i].charAt(j);
//					int connect = 0;
//					if (c >= '0' && c <= '9') {
//						connect = c - '0';
//					} else if (c >= 'A' && c <= 'Z') {
//						connect = c - 'A' + 10;
//					}
//					dist[j] = Math.min(dist[j], connect);
//				}
//			}
//		}
//		for (int i = 0; i < n; ++i) {
//			System.out.printf("dist[%d] = %d\n", i, dist[i]);
//		}
//		int cost = 0;
//		while (poweredCount < numPlants) {
//			int cur = -1;
//			int curCost = Integer.MAX_VALUE;
//			for (int i = 0; i < n; ++i) {
//				if (!powered[i] && dist[i] < curCost) {
//					curCost = dist[i];
//					cur = i;
//				}
//			}
//			if (cur == -1) {
//				return -1;
//			}
//			powered[cur] = true;
//			cost += curCost;
//			for (int i = 0; i < n; ++i) {
//				if (powered[i]) {
//					continue;
//				}
//				int connect = 0;
//				char c = connectionCost[cur].charAt(i);
//				if (c >= '0' && c <= '9') {
//					connect = c - '0';
//				} else if (c >= 'A' && c <= 'Z') {
//					connect = c - 'A' + 10;
//				}
//				dist[i] = Math.min(dist[i], connect);
//			}
//			poweredCount++;
//		}
//		return cost;
//	}
}