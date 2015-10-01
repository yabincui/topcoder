public class DancingCouples {
	public int countPairs(String[] canDance, int K) {
		int n = canDance.length;
		int m = canDance[0].length();
		int mask = (1 << m) - 1;
		int[] ways = new int[mask + 1];
		ways[0] = 1;
		for (int boy = 0; boy < n; ++boy) {
			int[] nextWays = new int[mask + 1];
			for (int i = 0; i <= mask; ++i) {
				nextWays[i] = ways[i];
			}
			for (int girlMask = 0; girlMask <= mask; ++girlMask) {
				for (int girl = 0; girl < m; ++girl) {
					if ((girlMask & (1 << girl)) != 0) {
						continue;
					}
					if (canDance[boy].charAt(girl) == 'N') {
						continue;
					}
					nextWays[girlMask | (1 << girl)] += ways[girlMask];
				}
			}
			ways = nextWays;
		}
		int result = 0;
		for (int i = 0; i <= mask; ++i) {
			if (getBits(i, m) == K) {
				result += ways[i];
			}
		}
		return result;
	}
	
	private int getBits(int mask, int n) {
		int count = 0;
		for (int i = 0; i < n; ++i) {
			if ((mask & (1 << i)) != 0) {
				count++;
			}
		}
		return count;
	}
}