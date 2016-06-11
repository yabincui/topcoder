public class FriendScore {
	public int highestScore(String[] friends) {
		int max_count = 0;
		int n = friends.length;
		for (int i = 0; i < n; ++i) {
			int count = 0;
			for (int j = 0; j < n; ++j) {
				if (i == j) continue;
				if (friends[i].charAt(j) == 'Y') {
					count++;
				} else {
					for (int k = 0; k < n; ++k) {
						if (i == k || k == j) {
							continue;
						}
						if (friends[k].charAt(i) == 'Y' && friends[k].charAt(j) == 'Y') {
							count++;
							break;
						}
					}
				}
			}
			max_count = Math.max(max_count, count);
		}
		return max_count;
	}
}
