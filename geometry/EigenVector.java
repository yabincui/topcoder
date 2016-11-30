public class EigenVector {
	
	int[][] A;

	public int[] findEigenVector(String[] A) {
		int n = A.length;
		this.A = new int[n][n];
		for (int i = 0; i < n; ++i) {
			String[] strs = A[i].split(" ");
			for (int j = 0; j < n; ++j) {
				this.A[i][j] = Integer.parseInt(strs[j]);
			}
		}
		
		int[] vector = new int[n];
		findVector(vector, 0, false, 0);
		for (int i = 0; i < n; ++i) {
			System.out.printf("%d ", vector[i]);
		}
		System.out.printf("\n");
		return vector;
	}
	
	boolean findVector(int[] vector, int cur,
						boolean hasNonZero, int sum) {
		if (cur == vector.length) {
			if (sum == 0) return false;
			return isSuitable(vector);
		}
		int start = 0;
		if (hasNonZero) {
			start = -(9 - sum);
		}
		for (; start <= 9 - sum; ++start) {
			vector[cur] = start;
			boolean newHasNonZero = hasNonZero || (start != 0);
			if (findVector(vector, cur + 1, newHasNonZero, sum + Math.abs(start))) {
				return true;
			}
			vector[cur] = 0;
		}
		return false;
	}
	
	boolean isSuitable(int[] vector) {
		int n = vector.length;
		int factor = Integer.MIN_VALUE;
		
		for (int i = 0; i < n; ++i) {
			int value = 0;
			for (int j = 0; j < n; ++j) {
				value += A[i][j] * vector[j];
			}
			if (vector[i] == 0 && value != 0) {
				return false;
			}
			if (vector[i] == 0 && value == 0) {
				continue;
			}
			if (value % vector[i] != 0) {
				return false;
			}
			int r = value / vector[i];
			if (factor == Integer.MIN_VALUE) {
				factor = r;
			} else if (factor != r) {
				return false;
			}
		}
		if (factor == 0) {
			return false;
		}
		return true;
	}
}
