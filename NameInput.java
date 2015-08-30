import java.util.Arrays;

public class NameInput {
	public int countUpDownKeyPresses(String[] predictionSequence, String[] name) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < predictionSequence.length; ++i) {
			builder.append(predictionSequence[i]);
		}
		char[] prediction = builder.toString().toCharArray();
		builder = new StringBuilder();
		for (int i = 0; i < name.length; ++i) {
			builder.append(name[i]);
		}
		char[] s = builder.toString().toCharArray();
		int[] cost = new int[prediction.length];
		Arrays.fill(cost, -1);
		cost[0] = 0;
		for (int i = 0; i < s.length; ++i) {
			int[] nextCost = new int[prediction.length];
			Arrays.fill(nextCost, -1);
			
			// Left to right.
			int min = -1;
			for (int j = 0; j < prediction.length; ++j) {
				if (cost[j] == -1) {
					continue;
				}
				int move = (j == 0 ? 0 : prediction.length - j);
				if (min == -1 || min > move + cost[j]) {
					min = move + cost[j];
				}
			}
			for (int j = 0; j < prediction.length; ++j) {
				if (cost[j] != -1 && (min == -1 || cost[j] < min)) {
					min = cost[j];
				}
				if (prediction[j] == s[i] && min != -1) {
					if (nextCost[j] == -1 || nextCost[j] > min) {
						nextCost[j] = min;
					}
				}
				if (min != -1) {
					min++;
				}
			}
			
			// Right to left.
			min = -1;
			for (int j = 0; j < prediction.length; ++j) {
				if (cost[j] == -1) {
					continue;
				}
				int move = (j == prediction.length - 1 ? 0 : j + 1);
				if (min == -1 || min > move + cost[j]) {
					min = move + cost[j];
				}
			}
			for (int j = prediction.length - 1; j >= 0; --j) {
				if (cost[j] != -1 && (min == -1 || cost[j] < min)) {
					min = cost[j];
				}
				if (prediction[j] == s[i] && min != -1) {
					if (nextCost[j] == -1 || nextCost[j] > min) {
						nextCost[j] = min;
					}
				}
				if (min != -1) {
					min++;
				}
			}
			cost = nextCost;
		}
		int result = -1;
		for (int i = 0; i < prediction.length; ++i) {
			if (cost[i] != -1 && (result == -1 || cost[i] < result)) {
				result = cost[i];
			}
		}
		return result;
	}
}