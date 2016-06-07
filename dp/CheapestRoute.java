import java.util.*;

public class CheapestRoute {
	public int[] routePrice(int[] cellPrice, int[] enterCell, int[] exitCell, int teleportPrice) {
		int n = cellPrice.length;
		// costs[i][j] is the min cost to arrive cell i, with j teleports used.
		int[][] costs = new int[n][n + 1];
		int[][] steps = new int[n][n + 1];
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j <= n; ++j) {
				costs[i][j] = -1;
			}
		}
		costs[0][0] = 0;
		for (int teleNum = 0; teleNum < n; ++teleNum) {
			for (int i = 1; i < n; ++i) {
				if (cellPrice[i] == -1) {
					continue;
				}
				// Come from i - 1.
				if (costs[i-1][teleNum] != -1) {
					int newCost = costs[i-1][teleNum] + cellPrice[i];
					if (costs[i][teleNum] == -1 || costs[i][teleNum] > newCost) {
						costs[i][teleNum] = newCost;
						steps[i][teleNum] = steps[i-1][teleNum] + 1;
					} else if (costs[i][teleNum] == newCost) {
						steps[i][teleNum] = Math.min(steps[i][teleNum], steps[i-1][teleNum] + 1);
					}
				}
			}
			for (int i = n - 2; i > 0; --i) {
				if (cellPrice[i] == -1) {
					continue;
				}
				// Come from i + 1.
				if (costs[i+1][teleNum] != -1) {
					int newCost = costs[i+1][teleNum] + cellPrice[i];
					if (costs[i][teleNum] == -1 || costs[i][teleNum] > newCost) {
						costs[i][teleNum] = newCost;
						steps[i][teleNum] = steps[i+1][teleNum] + 1;
					} else if (costs[i][teleNum] == newCost) {
						steps[i][teleNum] = Math.min(steps[i][teleNum], steps[i+1][teleNum] + 1);
					}
				}
			}
			for (int i = 0; i < n; ++i) {
				if (cellPrice[i] == -1) {
					continue;
				}
				// Come from a teleport, build for next level of teleNum.
				for (int j = 0; j < exitCell.length; ++j) {
					if (exitCell[j] == i) {
						int from = enterCell[j];
						if (costs[from][teleNum] != -1) {
							int newCost = costs[from][teleNum] + teleportPrice + teleNum;
							if (costs[i][teleNum + 1] == -1 || costs[i][teleNum + 1] > newCost) {
								costs[i][teleNum + 1] = newCost;
								steps[i][teleNum + 1] = steps[from][teleNum] + 1;
							} else if (costs[i][teleNum + 1] == newCost) {
								steps[i][teleNum + 1] = Math.min(steps[i][teleNum + 1], steps[from][teleNum] + 1);
							}
						}
					}
				}
			}
		}
		int minCost = -1;
		int minSteps = -1;
		for (int i = 0; i <= n; ++i) {
			if (costs[n-1][i] != -1) {
				if (minCost == -1) {
					minCost = costs[n-1][i];
					minSteps = steps[n-1][i];
				} else if (minCost > costs[n-1][i]) {
					minCost = costs[n-1][i];
					minSteps = steps[n-1][i];
				} else if (minCost == costs[n-1][i]) {
					minSteps = Math.min(minSteps, steps[n-1][i]);
				}
			}
		}
		if (minCost == -1) {
			return new int[]{};
		}
		return new int[]{minCost, minSteps};
	}
}