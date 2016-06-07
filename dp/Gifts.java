import java.util.*;

public class Gifts {
	public int maxGifts(String[] city, int T) {
		int rows = city.length;
		int cols = city[0].length();
		int startRow = 0;
		int startCol = 0;
		int endRow = 0;
		int endCol = 0;
		ArrayList<Integer> giftRows = new ArrayList<Integer>();
		ArrayList<Integer> giftCols = new ArrayList<Integer>();
		for (int i = 0; i < rows; ++i) {
			for (int j = 0; j < cols; ++j) {
				if (city[i].charAt(j) == 'G') {
					giftRows.add(i);
					giftCols.add(j);
				} else if (city[i].charAt(j) == 'K') {
					startRow = i;
					startCol = j;
				} else if (city[i].charAt(j) == 'Q') {
					endRow = i;
					endCol = j;
				}
			}
		}
		int giftCount = giftRows.size();
		
		// minDists[i][j] is the min dist from pos(gift(i)) to pos(gift(j)).
		int[][] minDists = new int[giftCount][giftCount];
		
		// minStartDist[i] is the min dist from K to pos(gift(i)).
		int[] minStartDist = new int[giftCount];
		
		// minEndDist[i] is the min dist from Q to pos(gift(i)).
		int[] minEndDist = new int[giftCount];
		
		for (int i = 0; i < giftCount; ++i) {
			Arrays.fill(minDists[i], -1);
		}
		Arrays.fill(minStartDist, -1);
		Arrays.fill(minEndDist, -1);
		
		for (int i = 0; i < giftCount; ++i) {
			int[][] dist = searchShortestDist(city, giftRows.get(i), giftCols.get(i));
			for (int j = i + 1; j < giftCount; ++j) {
				minDists[i][j] = minDists[j][i] = dist[giftRows.get(j)][giftCols.get(j)];
			}
			minStartDist[i] = dist[startRow][startCol];
			minEndDist[i] = dist[endRow][endCol];
		}
		
		int giftMask = (1 << giftCount) - 1;
		// dp[i][j] is the min time to collect bits(j) gifts in pos (giftRows[i], giftCols[i]).
		int[][] dp = new int[giftCount][giftMask + 1];
		for (int i = 0; i < giftCount; ++i) {
			Arrays.fill(dp[i], -1);
		}
		for (int i = 0; i < giftCount; ++i) {
			if (minStartDist[i] <= T) {
				dp[i][(1 << i)] = minStartDist[i];
			}
		}
		for (int i = 0; i <= giftMask; ++i) {
			int bitCount = 0;
			for (int j = 0; j < giftCount; ++j) {
				if ((i & (1 << j)) != 0) {
					bitCount++;
				}
			}
			if (bitCount < 2) {
				continue;
			}
			for (int j = 0; j < giftCount; ++j) {
				if ((i & (1 << j)) == 0) {
					continue;
				}
				int lastBits = i & ~(1 << j);
				for (int k = 0; k < giftCount; ++k) {
					if (dp[k][lastBits] != -1) {
						int cost = dp[k][lastBits] + bitCount * minDists[k][j];
						if (cost > T) {
							continue;
						}
						if (dp[j][i] == -1 || dp[j][i] > cost) {
							dp[j][i] = cost;
						}
					}
				}
			}
		}
		int result = 0;
		for (int i = 0; i < giftCount; ++i) {
			for (int j = 0; j <= giftMask; ++j) {
				if ((j & (1 << i)) == 0) {
					continue;
				}
				if (dp[i][j] == -1) {
					continue;
				}
				int bitCount = 0;
				for (int k = 0; k < giftCount; ++k) {
					if ((j & (1 << k)) != 0) {
						bitCount++;
					}
				}
				int cost = dp[i][j] + ((bitCount + 1) * minEndDist[i]);
				if (bitCount > result && cost <= T) {
					result = bitCount;
				}
			}
		}
		return result;
	}
	
	class Node {
		int r;
		int c;
		int d;
		Node(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}
	}
	
	class NodeComparator implements Comparator<Node> {
		public int compare(Node n1, Node n2) {
			return n1.d - n2.d;
		}
	}
	
	int[][] searchShortestDist(String[] city, int startR, int startC) {
		int[][] dist = new int[city.length][city[0].length()];
		for (int i = 0; i < city.length; ++i) {
			Arrays.fill(dist[i], -1);
		}
		dist[startR][startC] = 0;
		PriorityQueue<Node> queue = new PriorityQueue<Node>(1, new NodeComparator());
		queue.add(new Node(startR, startC, 0));
		while (!queue.isEmpty()) {
			Node node = queue.poll();
			if (dist[node.r][node.c] < node.d) {
				continue;
			}
			int[] dr = new int[]{-1, 1, 0, 0};
			int[] dc = new int[]{0, 0, -1, 1};
			for (int i = 0; i < dr.length; ++i) {
				int r = node.r + dr[i];
				int c = node.c + dc[i];
				if (r < 0 || r >= city.length || c < 0 || c >= city[0].length() || city[r].charAt(c) == '#') {
					continue;
				}
				if (dist[r][c] == -1 || dist[r][c] > dist[node.r][node.c] + 1) {
					dist[r][c] = dist[node.r][node.c] + 1;
					queue.add(new Node(r, c, dist[r][c]));
				}
			}
		}
		return dist;
	}
}