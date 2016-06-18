public class CellRemoval {
	public int cellsLeft(int[] parent, int deletedCell) {
		parent[deletedCell] = -2;
		int n = parent.length;
		boolean[] leaf = new boolean[n];
		for (int i = 0; i < n; ++i) {
			leaf[i] = true;
		}
		for (int i = 0; i < n; ++i) {
			int cur = i;
			while (parent[cur] >= 0) {
				cur = parent[cur];
				leaf[cur] = false;
			}
			int value = parent[cur];
			cur = i;
			while (parent[cur] != value) {
				int next = parent[cur];
				parent[cur] = value;
				cur = next;
			}
		}
		int count = 0;
		for (int i = 0; i < n; ++i) {
			if (leaf[i] && parent[i] == -1) {
				count++;
			}
		}
		return count;
	}
}
