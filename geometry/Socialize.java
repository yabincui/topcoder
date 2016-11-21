import java.util.*;

public class Socialize {
	class Pos {
		int r, c;
		Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	int[] dr = new int[]{-1, 1, 0, 0};
	int[] dc = new int[]{0, 0, -1, 1};

	public int average(String[] layout) {
		int rows = layout.length;
		int cols = layout[0].length();
		int sum = 0;
		int count = 0;
		int[][] dist = new int[rows][cols];
		for (int si = 0; si < rows; ++si) {
			for (int sj = 0; sj < cols; ++sj) {
				if (layout[si].charAt(sj) != 'P') {
					continue;
				}
				Queue<Pos> q = new ArrayDeque<Pos>();
				for (int i = 0; i < rows; ++i) {
					Arrays.fill(dist[i], Integer.MAX_VALUE);
				}
				dist[si][sj] = 0;
				q.add(new Pos(si, sj));
				while (!q.isEmpty()) {
					Pos cur = q.poll();
					for (int d = 0; d < 4; ++d) {
						int r = cur.r + dr[d];
						int c = cur.c + dc[d];
						if (r < 0 || r >= rows || c < 0 || c >= cols || layout[r].charAt(c) == '#') {
							continue;
						}
						if (dist[r][c] == Integer.MAX_VALUE) {
							dist[r][c] = dist[cur.r][cur.c] + 1;
							q.add(new Pos(r, c));
							if (layout[r].charAt(c) == 'P' && (r > si || (r == si && c > sj))) {
								sum += dist[r][c];
								count++;
							}
						}
					}
				}
			}
		}
		if (count == 0) {
			return 0;
		}
		double ret = (double)sum / count;
		//System.out.printf("sum = %d, count = %d, ret = %f\n", sum, count, ret);
		return (int)(ret + 0.5 + 1e-9);
	}
}
