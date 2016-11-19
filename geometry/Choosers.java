public class Choosers {
	class Node {
		boolean chooseLeft;
		int left;
		int right;
		Node(boolean chooseLeft, int left, int right) {
			this.chooseLeft = chooseLeft;
			this.left = left;
			this.right = right;
		}
	}

	public int length(String[] game, int start) {
		int n = game.length;
		int mask = (1 << n) - 1;
		boolean[][] visited = new boolean[n][mask + 1];
		Node[] nodes = new Node[n];
		for (int i = 0; i < n; ++i) {
			String[] strs = game[i].split(" ");
			boolean chooseLeft = (strs[0].equals("L"));
			int left = -1;
			if (!strs[1].equals("X")) {
				left = Integer.parseInt(strs[1]);
			}
			int right = -1;
			if (!strs[2].equals("X")) {
				right = Integer.parseInt(strs[2]);
			}
			nodes[i] = new Node(chooseLeft, left, right);
		}
		int curState = 0;
		for (int i = 0; i < n; ++i) {
			curState |= (nodes[i].chooseLeft ? 0 : (1 << i));
		}
		visited[start][curState] = true;
		int curPos = start;
		int step = 1;
		for (;; step++) {
			int next = nodes[curPos].chooseLeft ? nodes[curPos].left : nodes[curPos].right;
			if (next == -1) {
				break;
			}
			nodes[curPos].chooseLeft = !nodes[curPos].chooseLeft;
			curState = (curState & ~(1<<curPos));
			if (!nodes[curPos].chooseLeft) {
				curState |= 1 << curPos;
			}
			if (visited[next][curState]) {
				return -1;
			}
			visited[next][curState] = true;
			curPos = next;
		}
		return step;
	}
}