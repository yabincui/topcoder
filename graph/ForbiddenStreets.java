public class ForbiddenStreets {
	public int[] find(int N, int[] A, int[] B, int[] time) {
		int M = A.length;
		int[][] dist = findShortestPath(N, A, B, time);
		int[] result = new int[M];
		for (int i = 0; i < M; ++i) {
			int old_time = time[i];
			time[i] = -1;
			int[][] new_dist = findShortestPath(N, A, B, time);
			for (int k = 0; k < N; ++k) {
				for (int j = k + 1; j < N; ++j) {
					if (dist[k][j] != new_dist[k][j]) {
						result[i]++;
					}
				}
			}
			time[i] = old_time;
		}
		return result;
	}
	
	int[][] findShortestPath(int N, int[] A, int[] B, int[] time) {
		int[][] dist = new int[N][N];
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < N; ++j) {
				dist[i][j] = -1;
			}
			dist[i][i] = 0;
		}
		int M = A.length;
		for (int i = 0; i < M; ++i) {
			if (time[i] != -1) {
				dist[A[i]][B[i]] = dist[B[i]][A[i]] = time[i];
			}
		}
		for (int k = 0; k < N; ++k) {
			for (int i = 0; i < N; ++i) {
				if (i == k || dist[i][k] == -1) {
					continue;
				}
				for (int j = i + 1; j < N; ++j) {
					if (j == k || dist[k][j] == -1) {
						continue;
					}
					if (dist[i][j] == -1 || dist[i][j] > dist[i][k] + dist[k][j]) {
						dist[i][j] = dist[j][i] = dist[i][k] + dist[k][j];
					}
				}
			}
		}
		return dist;
	}
}
