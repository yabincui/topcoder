public class Egalitarianism {
	public int maxDifference(String[] isFriend, int d) {
		int max_diff = 0;
		int n = isFriend.length;
		for (int start = 0; start < n; ++start) {
			int diff = findShortestPathToFarestNode(isFriend, d, start);
			if (diff == -1) {
				return -1;
			}
			if (max_diff < diff) {
				max_diff = diff;
			}
		}
		return max_diff;
	}
	
	int findShortestPathToFarestNode(String[] isFriend, int d, int start) {
		int n = isFriend.length;
		int[] dist = new int[n];
		for (int i = 0; i < n; ++i) {
			dist[i] = -1;
		}
		boolean[] visited = new boolean[n];
		dist[start] = 0;
		for (int step = 0; step < n; ++step) {
			int selected = -1;
			for (int i = 0; i < n; ++i) {
				if (!visited[i] && dist[i] != -1) {
					if (selected == -1 || dist[i] < dist[selected]) {
						selected = i;
					}
				}
			}
			if (selected == -1) {
				return -1;
			}
			visited[selected] = true;
			for (int i = 0; i < n; ++i) {
				if (!visited[i] && isFriend[selected].charAt(i) == 'Y') {
					int limit = dist[selected] + d;
					if (dist[i] == -1 || dist[i] > limit) {
						dist[i] = limit;
					}
				}
			}
		}
		int result = 0;
		for (int i = 0; i < n; ++i) {
			if (result < dist[i]) {
				result = dist[i];
			}
		}
		return result;
	}
}
