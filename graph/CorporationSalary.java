public class CorporationSalary {
	public long totalSalary(String[] relations) {
		int n = relations.length;
		long[] salary = new long[n];
		int[] manager_count = new int[n];
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) {
				if (relations[i].charAt(j) == 'Y') {
					manager_count[i]++;
				}
			}
		}
		boolean[] used = new boolean[n];
		while (true) {
			int sel = -1;
			for (int i = 0; i < n; ++i) {
				if (!used[i] && manager_count[i] == 0) {
					sel = i;
					break;
				}
			}
			if (sel == -1) {
				break;
			}
			if (salary[sel] == 0) {
				salary[sel] = 1;
			}
			used[sel] = true;
			for (int i = 0; i < n; ++i) {
				if (relations[i].charAt(sel) == 'Y') {
					salary[i] += salary[sel];
					manager_count[i]--;
				}
			}
		}
		long result = 0;
		for (int i = 0; i < n; ++i) {
			result += salary[i];
		}
		return result;
	}
}
