import java.util.*;
public class ColorfulMazeTwo {
	
	class Node {
		int r;
		int c;
		int colorBits;
		double probability;
		
		Node(int r, int c, int colorBits, double probability) {
			this.r = r;
			this.c = c;
			this.colorBits = colorBits;
			this.probability = probability;
		}
	}
	
	class NodeComparator implements Comparator<Node> {
		public int compare(Node n1, Node n2) {
			if (n1.probability == n2.probability) {
				return 0;
			} else {
				return (n1.probability < n2.probability) ? 1 : -1;
			}
		}
	}
	
	public double getProbability(String[] maze, int[] trap) {
		int rows = maze.length;
		int cols = maze[0].length();
		int colorMask = (1 << 7) - 1;
		int startR = 0;
		int startC = 0;
		int endR = 0;
		int endC = 0;
		for (int i = 0; i < rows; ++i) {
			for (int j = 0; j < cols; ++j) {
				if (maze[i].charAt(j) == '$') {
					startR = i;
					startC = j;
				} else if (maze[i].charAt(j) == '!') {
					endR = i;
					endC = j;
				}
			}
		}
		
		// dp[i][j][k] means the max probability of arriving at maze[i][j], having
		// tried colorBits k.
		double[][][] dp = new double[rows][cols][colorMask + 1];
		PriorityQueue<Node> queue = new PriorityQueue<Node>(1, new NodeComparator());
		dp[startR][startC][0] = 1.0;
		queue.add(new Node(startR, startC, 0, 1.0));
		while (!queue.isEmpty()) {
			Node node = queue.poll();
			if (dp[node.r][node.c][node.colorBits] > node.probability) {
				continue;
			}
			if (node.r == endR && node.c == endC) {
				break;
			}
			int[] dr = new int[]{0, 0, -1, 1};
			int[] dc = new int[]{-1, 1, 0, 0};
			for (int i = 0; i < 4; ++i) {
				int r = node.r + dr[i];
				int c = node.c + dc[i];
				if (r < 0 || r >= rows || c < 0 || c >= cols || maze[r].charAt(c) == '#') {
					continue;
				}
				double probability = node.probability;
				int colorBits = node.colorBits;
				if (maze[r].charAt(c) >= 'A' && maze[r].charAt(c) <= 'G') {
					int color = maze[r].charAt(c) - 'A';
					if ((node.colorBits & (1 << color)) == 0) {
						probability *= (1 - trap[color] * 0.01);
						colorBits |= 1 << color;
					}
				}
				if (dp[r][c][colorBits] < probability) {
					dp[r][c][colorBits] = probability;
					queue.add(new Node(r, c, colorBits, probability));
				}
			}
		}
		double result = 0.0;
		for (int i = 0; i <= colorMask; ++i) {
			result = Math.max(result, dp[endR][endC][i]);
		}
		return result;
	}
}