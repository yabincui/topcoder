public class VendingMachine {
	public int motorUse(String[] prices, String[] purchases) {
		int m = prices.length;
		int n = prices[0].split(" ").length;
		int[][] priceList = new int[m][n];
		for (int i = 0; i < m; ++i) {
			String[] strs = prices[i].split(" ");
			for (int j = 0; j < n; ++j) {
				priceList[i][j] = Integer.parseInt(strs[j]);
			}
		}
		int[] columnSum = new int[n];
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < m; ++j) {
				columnSum[i] += priceList[j][i];
			}
		}
		int totalMove = 0;
		int prevTime = -5;
		int prevColumn = 0;
		for (int i = 0; i <= purchases.length; ++i) {
			int time = 0;
			if (i == purchases.length) {
				time = prevTime + 5;
			} else {
				time = Integer.parseInt(purchases[i].split(":")[1]);
			}
			if (time - prevTime >= 5) {
				// Move to most expensive column.
				int target = 0;
				for (int j = 1; j < n; ++j) {
					if (columnSum[j] > columnSum[target]) {
						target = j;
					}
				}
				int move = 0;
				if (target < prevColumn) {
					move = Math.min(prevColumn - target, n - prevColumn + target);
				} else {
					move = Math.min(target - prevColumn, prevColumn + n - target);
				}
				totalMove += move;
				prevColumn = target;
			}
			if (i == purchases.length) {
				break;
			}
			// Move to user's requested column.
			String str = purchases[i].split(":")[0];
			int row = Integer.parseInt(str.split(",")[0]);
			int col = Integer.parseInt(str.split(",")[1]);
			int move = 0;
			if (col < prevColumn) {
				move = Math.min(prevColumn - col, n - prevColumn + col);
			} else {
				move = Math.min(col - prevColumn, n - col + prevColumn);
			}
			totalMove += move;
			if (priceList[row][col] == 0) {
				return -1;
			}
			columnSum[col] -= priceList[row][col];
			priceList[row][col] = 0;
			
			prevColumn = col;
			prevTime = time;
		}
		return totalMove;
	}
}
