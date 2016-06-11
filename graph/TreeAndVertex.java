public class TreeAndVertex {
	public int get(int[] tree) {
		int n = tree.length;
		int[] count_array = new int[n + 1];
		for (int i = 0; i < n; ++i) {
			count_array[i + 1]++;
			count_array[tree[i]]++;
		}
		int max_value = 0;
		for (int value : count_array) {
			max_value = Math.max(value, max_value);
		}
		return max_value;
	}
}
