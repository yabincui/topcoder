import java.util.*;

public class MarbleCollectionGame {
	class Node {
		int index;
		BitSet selfBitset;
		BitSet gotoBitset;
		int value;
		
		Node(int index, int row, int col, int rows, int cols, BitSet gotoBitset) {
			this.index = index;
			this.selfBitset = new BitSet(rows * cols);
			this.selfBitset.set(row * cols + col, true);
			this.gotoBitset = gotoBitset;
			this.value = 0;
		}
	}
	
	public int collectMarble(String[] board) {
		int rows = board.length;
		int cols = board[0].length();
		BitSet[][] bitSets = new BitSet[rows][cols];
		for (int i = 0; i < rows; ++i) {
			for (int j = 0; j < cols; ++j) {
				BitSet set = new BitSet(rows * cols);
				char c = board[i].charAt(j);
				if ((c >= '0' && c <= '9') || (c == 'U') || (c == 'L')) {
					set.set(i * cols + j, true);
					if (j < cols - 1 && board[i].charAt(j + 1) != '#') {
						set.set(i * cols + (j + 1), true);
					}
					if (i < rows - 1 && board[i+1].charAt(j) != '#') {
						set.set((i + 1) * cols + j, true);
					}
					if (c == 'U' && i > 0 && board[i-1].charAt(j) != '#') {
						set.set((i-1) * cols + j, true); 
					}
					if (c == 'L' && j > 0 && board[i].charAt(j-1) != '#') {
						set.set(i * cols + j - 1, true);
					}
				}
				bitSets[i][j] = set;
			}
		}
		for (int step = 0; step < rows * cols; ++step) {
			for (int i = 0; i < rows; ++i) {
				for (int j = 0; j < cols; ++j) {
					char c = board[i].charAt(j);
					if (c != '#') {
						if (j < cols - 1) {
							bitSets[i][j].or(bitSets[i][j+1]);
						}
						if (i < rows - 1) {
							bitSets[i][j].or(bitSets[i+1][j]);
						}
						if (c == 'U' && i > 0) {
							bitSets[i][j].or(bitSets[i-1][j]);
						}
						if (c == 'L' && j > 0) {
							bitSets[i][j].or(bitSets[i][j-1]);
						}
					}
				}
			}
		}
		Node[][] nodeBoard = new Node[rows][cols];
		ArrayList<Node> nodes = new ArrayList<Node>();
		for (int i = 0; i < rows; ++i) {
			for (int j = 0; j < cols; ++j) {
				char c = board[i].charAt(j);
				if (c == '#') {
					nodeBoard[i][j] = null;
					continue;
				}
				boolean exist = false;
				for (Node node : nodes) {
					if (node.gotoBitset.equals(bitSets[i][j])) {
						exist = true;
						if (c >= '0' && c <= '9') {
							node.value += c - '0';
						}
						node.selfBitset.set(i * cols + j, true);
						nodeBoard[i][j] = node;
						break;
					}
				}
				if (!exist) {
					Node newNode = new Node(nodes.size(), i, j, rows, cols, bitSets[i][j]);
					if (c >= '0' && c <= '9') {
						newNode.value += c - '0';
					}
					nodes.add(newNode);
					nodeBoard[i][j] = newNode;
				}
			}
		}
		
		int nodeCount = nodes.size();
		boolean[][] connectMap = new boolean[nodeCount][nodeCount];
		for (int i = 0; i < rows; ++i) {
			for (int j = 0; j < cols; ++j) {
				char c = board[i].charAt(j);
				if (c != '#') {
					int from = nodeBoard[i][j].index;
					if (j < cols - 1 && nodeBoard[i][j+1] != null && nodeBoard[i][j+1] != nodeBoard[i][j]) {
						int to = nodeBoard[i][j+1].index;
						connectMap[from][to] = true;
					}
					if (i < rows - 1 && nodeBoard[i+1][j] != null && nodeBoard[i+1][j] != nodeBoard[i][j]) {
						int to = nodeBoard[i+1][j].index;
						connectMap[from][to] = true;
					}
				}
			}
		}
		// valid[i] means if node i is reachable from node 0.
		boolean[] valid = new boolean[nodeCount];
		for (int i = 0; i < nodeCount; ++i) {
			valid[i] = true;
		}
		Node startNode = nodes.get(0);
		for (Node node : nodes) {
			if (!node.selfBitset.intersects(startNode.gotoBitset)) {
				valid[node.index] = false;
				for (int i = 0; i < nodeCount; ++i) {
					connectMap[i][node.index] = connectMap[node.index][i] = false;
				}
			}
		}
		int[] inDegree = new int[nodeCount];
		for (int i = 0; i < nodeCount; ++i) {
			for (int j = 0; j < nodeCount; ++j) {
				if (connectMap[j][i]) {
					inDegree[i]++;
				}
			}
		}
		// dp[i] is the max value can get when arriving at node i.
		int[] dp = new int[nodeCount];
		boolean[] visited = new boolean[nodeCount];
		for (int i = 0; i < nodeCount; ++i) {
			if (!valid[i]) {
				visited[i] = true;
			}
		}
		while (true) {
			int curr = -1;
			for (int i = 0; i < nodeCount; ++i) {
				if (!visited[i] && inDegree[i] == 0) {
					curr = i;
					break;
				}
			}
			if (curr == -1) {
				break;
			}
			visited[curr] = true;
			int max = 0;
			for (int i = 0; i < nodeCount; ++i) {
				if (connectMap[i][curr]) {
					max = Math.max(max, dp[i]);
				}
			}
			dp[curr] = nodes.get(curr).value + max;
			for (int i = 0; i < nodeCount; ++i) {
				if (connectMap[curr][i]) {
					inDegree[i]--;
				}
			}
		}
		int result = 0;
		for (int i = 0; i < nodeCount; ++i) {
			result = Math.max(result, dp[i]);
		}
		return result;
	}
	
}