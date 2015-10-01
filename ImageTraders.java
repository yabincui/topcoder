import java.util.*;

public class ImageTraders {
	public int maxOwners(String[] price) {
		int n = price.length;
		int mask = (1 << n) - 1;
		// peopleCount[i][j] is the max number of people who has owned the picture,
		// when the last owner is i, why buys the picture at price j.
		int[][][] peopleCount = new int[n][mask + 1][10];
		
		peopleCount[0][1][0] = 1;
		for (int i = 0; i <= mask; ++i) {
			for (int j = 0; j < n; ++j) {
				if ((i & (1 << j)) != 0) {
					continue;
				}
				for (int k = 0; k < n; ++k) {
					if ((i & (1 << k)) == 0) {
						continue;
					}
					int newPrice = price[k].charAt(j) - '0';
					int max = 0;
					for (int t = 0; t <= newPrice; ++t) {
						if (peopleCount[k][i][t] > max) {
							max = peopleCount[k][i][t];
						}
					}
					if (max != 0) {
						int newMask = i | (1 << j);
						peopleCount[j][newMask][newPrice] = Math.max(peopleCount[j][newMask][newPrice],
								max + 1);
					}
				}
			}
		}
		int result = 0;
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j <= mask; ++j) {
				for (int k = 0; k < 10; ++k) {
					result = Math.max(peopleCount[i][j][k], result);
				}
			}
		}
		return result;
	}
}