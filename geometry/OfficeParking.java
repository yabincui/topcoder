public class OfficeParking {
	public int spacesUsed(String[] events) {
		int n = events.length;
		String[] used = new String[n];
		int max_used = 0;
		for (int i = 0; i < n; ++i) {
			String[] strs = events[i].split(" ");
			if (strs[1].equals("arrives")) {
				for (int j = 0; j < n; ++j) {
					if (used[j] == null) {
						used[j] = strs[0];
						max_used = Math.max(j+1, max_used);
						break;
					}
				}
			} else {
				for (int j = 0; j < n; ++j) {
					if (used[j] != null && used[j].equals(strs[0])) {
						used[j] = null;
						break;
					}
				}
			}
		}
		return max_used;
	}
}
