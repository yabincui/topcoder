import java.util.*;

public class SortingGame {
	public int fewestMoves(int[] board, int k) {
		int n = board.length;
		int start = arrayToValue(board);
		Arrays.sort(board);
		int target = arrayToValue(board);
		if (start == target) {
			return 0;
		}
		HashSet<Integer> hitSet = new HashSet<Integer>();
		hitSet.add(start);
		Queue<Integer> queue = new ArrayDeque<Integer>();
		queue.add(start);
		for (int step = 1; !queue.isEmpty(); step++) {
			int size = queue.size();
			while (size-- > 0) {
				int value = queue.poll();
				valueToArray(value, board);
				for (int i = 0; i <= n - k; ++i) {
					reverseArray(board, i, k);
					value = arrayToValue(board);
					if (value == target) {
						return step;
					}
					if (!hitSet.contains(value)) {
						hitSet.add(value);
						queue.add(value);
					}
					reverseArray(board, i, k);
				}
			}
		}
		return -1;
	}
	
	int arrayToValue(int[] board) {
		int value = 0;
		for (int i = 0; i < board.length; ++i) {
			value = (value << 4) | board[i];
		}
		return value;
	}
	
	void valueToArray(int value, int[] board) {
		for (int i = board.length - 1; i >= 0; --i) {
			board[i] = value & 0xf;
			value >>= 4;
		}
	}
	
	void reverseArray(int[] board, int start, int count) {
		int end = start + count - 1;
		while (start < end) {
			int tmp = board[start];
			board[start] = board[end];
			board[end] = tmp;
			start++;
			end--;
		}
	}
}
