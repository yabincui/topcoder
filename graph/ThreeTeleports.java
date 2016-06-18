import java.util.*;

public class ThreeTeleports {
	public int shortestDistance(int xMe, int yMe, int xHome, int yHome, String[] teleports) {
		ArrayList<ArrayList<Integer>> nodes = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> node = new ArrayList<Integer>();
		node.add(xMe);
		node.add(yMe);
		nodes.add(node);
		node = new ArrayList<Integer>();
		node.add(xHome);
		node.add(yHome);
		nodes.add(node);
		for (String teleport : teleports) {
			String[] strs = teleport.split(" ");
			int[] values = new int[4];
			for (int i = 0; i < 4; ++i) {
				values[i] = Integer.parseInt(strs[i]);
			}
			node = new ArrayList<Integer>();
			node.add(values[0]);
			node.add(values[1]);
			nodes.add(node);
			node = new ArrayList<Integer>();
			node.add(values[2]);
			node.add(values[3]);
			nodes.add(node);
		}
		int n = nodes.size();
		long[][] adjMatrix = new long[n][n];
		for (int i = 0; i < n; ++i) {
			for (int j = i + 1; j < n; ++j) {
				long a = (long)Math.abs(nodes.get(i).get(0) - nodes.get(j).get(0)) +
						 (long)Math.abs(nodes.get(i).get(1) - nodes.get(j).get(1));
				adjMatrix[i][j] = adjMatrix[j][i] = a;
			}
		}
		for (int i = 2; i < n; i += 2) {
			long a = Math.min(adjMatrix[i][i + 1], 10);
			adjMatrix[i][i+1] = adjMatrix[i+1][i] = a;
		}
		long[] dist = new long[n];
		boolean[] visited = new boolean[n];
		for (int i = 0; i < n; ++i) {
			dist[i] = -1;
		}
		dist[0] = 0;
		while (true) {
			int selected = -1;
			for (int i = 0; i < n; ++i) {
				if (!visited[i] && dist[i] != -1 && (selected == -1 || dist[selected] > dist[i])) {
					selected = i;
				}
			}
			if (selected == 1) {
				return (int)dist[1];
			}
			visited[selected] = true;
			for (int i = 0; i < n; ++i) {
				if (!visited[i] && (dist[i] == -1 || dist[i] > dist[selected] + adjMatrix[selected][i])) {
					dist[i] = dist[selected] + adjMatrix[selected][i];
				}
			}
		}
	}
}
