import java.util.*;

public class CostOfDancing {
	public int minimum(int K, int[] danceCost) {
		Arrays.sort(danceCost);
		int result = 0;
		for (int i = 0; i < K; ++i) {
			result += danceCost[i];
		}
		return result;
	}
}
