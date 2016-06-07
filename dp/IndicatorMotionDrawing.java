import java.util.*;

public class IndicatorMotionDrawing {
	public int getMinSteps(String[] desiredState, char startState) {
		int rows = desiredState.length;
		int cols = desiredState[0].length();
		int mask = (1 << (rows * cols)) - 1;
		int n = rows * cols;
		boolean[][][] dp = new boolean[mask + 1][n + 1][4];
		char[] draw = new char[]{'\\', '|', '/', '-'};
		Queue<Integer> queue = new ArrayDeque<Integer>();
		int startDraw = 0;
		for (int i = 0; i < 4; ++i) {
			if (draw[i] == startState) {
				startDraw = i;
			}
		}
		int finishMask = 0;
		for (int i = 0; i < rows; ++i) {
			for (int j = 0; j < cols; ++j) {
				if (desiredState[i].charAt(j) != ' ') {
					finishMask |= 1 << (i * cols + j);
				}
			}
		}
		if (desiredState[0].charAt(0) != ' ') {
			int curFinish = 0;
			if (desiredState[0].charAt(0) == draw[startDraw]) {
				curFinish |= 1;
			}
			if (curFinish == finishMask) {
				return 0;
			}
			queue.add((curFinish << 16) | (0 << 2) | startDraw);
		}
		for (int step = 1; !queue.isEmpty(); ++step) {
			int size = queue.size();
			while (size-- > 0) {
				int value = queue.poll();
				int curFinish = value >> 16;
				int curPos = (value & 0xffff) >> 2;
				int curDraw = value & 3;
				int curRow = curPos / cols;
				int curCol = curPos % cols;
				// Try seven moves.
				// Left/Right/Flip.
				int[] addDraws = {3, 1, 2};
				for (int addDraw : addDraws) {
					int nextDraw = (curDraw + addDraw) % 4;
					int nextFinish = curFinish;
					if (draw[nextDraw] == desiredState[curRow].charAt(curCol)) {
						nextFinish |= 1 << curPos;
						if (nextFinish == finishMask) {
							return step;
						}
					} else {
						nextFinish &= ~(1 << curPos);
					}
					if (dp[nextFinish][curPos][nextDraw] == false) {
						dp[nextFinish][curPos][nextDraw] = true;
						queue.add((nextFinish << 16) | (curPos << 2) | nextDraw);
					}
				}
				// U/D/</>.
				int[][] addMoves = {
					{-1, 0}, {1, 0}, {0, -1}, {0, 1},
				};
				for (int i = 0; i < 4; ++i) {
					int nextRow = curRow + addMoves[i][0];
					int nextCol = curCol + addMoves[i][1];
					if (nextRow < 0 || nextRow >= rows || nextCol < 0 || nextCol >= cols ||
						desiredState[nextRow].charAt(nextCol) == ' ') {
						continue;
					}
					int nextPos = nextRow * cols + nextCol;
					int nextFinish = curFinish;
					if (draw[curDraw] == desiredState[nextRow].charAt(nextCol)) {
						nextFinish |= 1 << nextPos;
						if (nextFinish == finishMask) {
							return step;
						}
					} else {
						nextFinish &= ~(1 << nextPos);
					}
					if (dp[nextFinish][nextPos][curDraw] == false) {
						dp[nextFinish][nextPos][curDraw] = true;
						queue.add((nextFinish << 16) | (nextPos << 2) | curDraw);
					}
				}
			}
		}
		return -1;
	}
}
