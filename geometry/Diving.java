import java.util.*;

public class Diving {
	public String needed(String difficulty, String need, String ratings) {
		int diff10 = 0;
		for (char c : difficulty.toCharArray()) {
			if (c >= '0' && c <= '9') {
				diff10 = diff10 * 10 + c - '0';
			}
		}
		
		int score100 = 0;
		for (char c : need.toCharArray()) {
			if (c >= '0' && c <= '9') {
				score100 = score100 * 10 + c - '0';
			}
		}
		
		int[] rate10 = new int[4];
		int j = 0;
		int tmp = -1;
		for (char c : ratings.toCharArray()) {
			if (c >= '0' && c <= '9') {
				if (tmp == -1) tmp = 0;
				tmp = tmp * 10 + c - '0';
			} else if (c == ' ') {
				if (tmp != -1) {
					//System.out.printf("rate10[%d] = %d\n", j, tmp);
					rate10[j++] = tmp;
					tmp = -1;
				}
			}
		}
		if (tmp != -1) {
			rate10[3] = tmp;
		}
		Arrays.sort(rate10);
		// see if we can fulfill it.
		tmp = 0;
		for (int i = 1; i < 4; ++i) {
			tmp += rate10[i];
		}
		if (tmp * diff10 < score100) {
			//System.out.printf("tmp = %d, diff10 = %d, score100 = %d\n", tmp, diff10, score100);
			//System.out.printf("-1.0\n");
			return "-1.0";
		}
		// ? rating is between [rate10[slot - 1], rate10[slot]]
		for (int slot = 0; slot < 4; ++slot) {
			int max_value = rate10[slot];
			tmp = rate10[1] + rate10[2] + max_value;
			//System.out.printf("slot = %d, max_value = %d, tmp = %d\n", slot, max_value, tmp);
			if (tmp * diff10 < score100) {
				//System.out.printf("tmp = %d, diff10 = %d, score100 = %d\n", tmp, diff10, score100);
				continue;
			}
			if (slot == 0) return "0.0";
			int min_value = rate10[slot-1] + 5;
			for (int value = min_value; value <= max_value; value += 5) {
				tmp = rate10[1] + rate10[2] + value;
				if (tmp * diff10 >= score100) {
					//System.out.printf("value = %d\n", value);
					return "" + (value / 10) + "." + (value % 10);
				}
			}
		}
		//System.out.printf("Impossible\n");
		return "";
	}
}
