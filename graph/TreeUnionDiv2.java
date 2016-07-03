import java.util.*;

public class TreeUnionDiv2 {
	String[] tree1;
	String[] tree2;
	int K;
	int maxCycles;
	
	public int maximumCyclesTimeout(String[] tree1, String[] tree2, int K) {
		this.tree1 = tree1;
		this.tree2 = tree2;
		this.K = K;
		this.maxCycles = 0;
		int n = tree1.length;
		ArrayList<Integer> perm = new ArrayList<Integer>();
		boolean[] used = new boolean[n];
		permutate(n, used, perm);
		return maxCycles;
	}
	
	private void permutate(int leftCount, boolean[] used, ArrayList<Integer> perm) {
		if (leftCount == 0) {
			tryPermute(perm);
			return;
		}
		for (int i = 0; i < used.length; ++i) {
			if (!used[i]) {
				used[i] = true;
				perm.add(i);
				permutate(leftCount - 1, used, perm);
				used[i] = false;
				perm.remove(perm.size() - 1);
			}
		}
	}
	
	private void tryPermute(ArrayList<Integer> perm) {
		// build neighbor matrix.
		int n = tree1.length;
		int nn = n * 2;
    ArrayList<ArrayList<Integer>> neighbors = new ArrayList<ArrayList<Integer>>();
    for (int i = 0; i < nn; ++i) {
      neighbors.add(new ArrayList<Integer>());
    }
		for (int i = 0; i < n; ++i) {
			for (int j = i + 1; j < n; ++j) {
				if (tree1[i].charAt(j) == 'X') {
          neighbors.get(i).add(j);
          neighbors.get(j).add(i);
				}
				if (tree2[i].charAt(j) == 'X') {
          neighbors.get(i + n).add(j + n);
          neighbors.get(j + n).add(i + n);
				}
			}
		}
		for (int i = 0; i < n; ++i) {
			int j = n + perm.get(i);
      neighbors.get(i).add(j);
      neighbors.get(j).add(i);
		}
		long cycleSum = 0;
		boolean[] visited = new boolean[nn];
		for (int start = 0; start < nn; ++start) {
			visited[start] = true;
			int cycles = findCycle(start, start, K - 1, visited, neighbors);
			//System.out.printf("start = %d, cycles = %d / %d = %d\n", start, cycles, 2, cycles / 2);
			cycles /= 2;
			cycleSum += cycles;
      break;
		}
		//System.out.printf("cycleSum = %d / %d = %d\n", cycleSum, K, cycleSum / K);
		//cycleSum /= K;
		maxCycles = Math.max(maxCycles, (int)cycleSum);
	}
	
	int findCycle(int cur, int target, int leftCount, boolean[] visited, ArrayList<ArrayList<Integer>> neighbors) {
		if (leftCount == 0) {
      for (Integer i : neighbors.get(cur)) {
        if (i == target) {
          return i;
        }
      }
      return 0;
		}
		int result = 0;
    for (Integer i : neighbors.get(cur)) {
			if (!visited[i]) {
				visited[i] = true;
				result += findCycle(i, target, leftCount - 1, visited, neighbors);
				visited[i] = false;
			}
		}
		return result;
	}
	
	int[][] distTree1;
	int[][] distTree2;
	
	public int maximumCycles(String[] tree1, String[] tree2, int K) {
		// we only need to calculate loops crossing the boundary (it must cross twice).
		this.tree1 = tree1;
		this.tree2 = tree2;
		this.K = K;
		this.maxCycles = 0;
		int n = tree1.length;
		
		distTree1 = new int[n][n];
		distTree2 = new int[n][n];
		for (int i = 0; i < n; ++i) {
			for (int j = i + 1; j < n; ++j) {
				distTree1[i][j] = distTree1[j][i] = Integer.MAX_VALUE;
				distTree2[i][j] = distTree2[j][i] = Integer.MAX_VALUE;
			}
		}
		for (int i = 0; i < n; ++i) {
			for (int j = i + 1; j < n; ++j) {
				if (tree1[i].charAt(j) == 'X') {
					distTree1[i][j] = distTree1[j][i] = 1;
				}
				if (tree2[i].charAt(j) == 'X') {
					distTree2[i][j] = distTree2[j][i] = 1;
				}
			}
		}
		for (int k = 0; k < n; ++k) {
			for (int i = 0; i < n; ++i) {
				for (int j = i + 1; j < n; ++j) {
					if (i == k || j == k) {
						continue;
					}
					if (distTree1[i][k] != Integer.MAX_VALUE && distTree1[k][j] != Integer.MAX_VALUE) {
						distTree1[i][j] = distTree1[j][i] = Math.min(distTree1[i][j], distTree1[i][k] + distTree1[k][j]);
					}
					if (distTree2[i][k] != Integer.MAX_VALUE && distTree2[k][j] != Integer.MAX_VALUE) {
						distTree2[i][j] = distTree2[j][i] = Math.min(distTree2[i][j], distTree2[i][k] + distTree2[k][j]);
					}
				}
			}
		}
		
		ArrayList<Integer> perm = new ArrayList<Integer>();
		boolean[] used = new boolean[n];
		permutate2(n, used, perm);
		return maxCycles;
	}
	
	private void permutate2(int leftCount, boolean[] used, ArrayList<Integer> perm) {
		if (leftCount == 0) {
			tryPermute2(perm);
			return;
		}
		for (int i = 0; i < used.length; ++i) {
			if (!used[i]) {
				used[i] = true;
				perm.add(i);
				permutate2(leftCount - 1, used, perm);
				used[i] = false;
				perm.remove(perm.size() - 1);
			}
		}
	}
	
	private void tryPermute2(ArrayList<Integer> perm) {
		int cycles = 0;
		int n = perm.size();
		for (int i = 0; i < n; ++i) {
			for (int j = i + 1; j < n; ++j) {
				int d = distTree1[i][j] + distTree2[perm.get(i)][perm.get(j)] + 2;
				if (d == K) {
					cycles++;
				}
			}
		}
		maxCycles = Math.max(maxCycles, cycles);
	}
}

