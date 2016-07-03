import java.util.*;

public class MovingRooksDiv2 {
	public String move(int[] Y1, int[] Y2) {
		HashSet<Integer> hitSet = new HashSet<Integer>();
		int source = getValue(Y1);
		int target = getValue(Y2);
		if (source == target) {
			return "Possible";
		}
		Queue<Integer> queue = new ArrayDeque<Integer>();
		queue.add(source);
		while (!queue.isEmpty()) {
			int value = queue.poll();
			getY(value, Y1);
			for (int i = 0; i < Y1.length; ++i) {
				for (int j = i + 1; j < Y1.length; ++j) {
					if (Y1[i] > Y1[j]) {
						swap(Y1, i, j);
						value = getValue(Y1);
						if (value == target) {
							return "Possible";
						}
						if (!hitSet.contains(value)) {
							hitSet.add(value);
							queue.add(value);
						}
						swap(Y1, i, j);	
					}
				}
			}
		}
		return "Impossible";
	}
	
	int getValue(int[] Y) {
		int value = 0;
		for (int i = 0; i < Y.length; ++i) {
			value = (value << 3) | Y[i];
		}
		return value;
	}
	void getY(int value, int[] Y) {
		for (int i = Y.length - 1; i >= 0; --i) {
			Y[i] = value & 7;
			value >>= 3;
		}
	}
	
	void swap(int[] Y, int i, int j) {
		int tmp = Y[i];
		Y[i] = Y[j];
		Y[j] = tmp;
	}
}
