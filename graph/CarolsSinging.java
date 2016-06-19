public class CarolsSinging {
	public int choose(String[] lyrics) {
		int people = lyrics.length;
		int songs = lyrics[0].length();
		int mask = (1 << songs) - 1;
		int[] dp = new int[mask + 1];
		for (int i = 0; i < songs; ++i) {
			int people_mask = 0;
			for (int j = 0; j < people; ++j) {
				if (lyrics[j].charAt(i) == 'Y') {
					people_mask |= (1 << j);
				}
			}
			dp[1 << i] = people_mask;
		}
		for (int i = 1; i <= mask; ++i) {
			for (int j = 0; j < songs; ++j) {
				if ((i & (1 << j)) != 0) {
					dp[i] = dp[1 <<j] | dp[i & ~(1 << j)];
					break;
				}
			}
		}
		int people_mask = (1 << people) - 1;
		int result = -1;
		for (int i = 0; i <= mask; ++i) {
			if (dp[i] == people_mask) {
				int song_count = 0;
				int t = i;
				while (t != 0) {
					song_count++;
					t &= t - 1;
				}
				if (result == -1 || result > song_count) {
					result = song_count;
				}
			}
		}
		return result;
	}
}
