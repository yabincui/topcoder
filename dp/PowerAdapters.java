import java.util.*;

public class PowerAdapters {
	public int needed(String[] adapters, String[] itinerary, String homeCountry) {
		HashMap<String, Integer> idMap = new HashMap<String, Integer>();
		for (String s : adapters) {
			String[] strs = s.split(" ");
			for (String country : strs) {
				if (!idMap.containsKey(country)) {
					idMap.put(country, idMap.size());
				}
			}
		}
		for (String country : itinerary) {
			if (!idMap.containsKey(country)) {
				idMap.put(country, idMap.size());
			}
		}
		if (!idMap.containsKey(homeCountry)) {
			idMap.put(homeCountry, idMap.size());
		}
		int n = idMap.size();
		boolean[][] connectMap = new boolean[n][n];
		for (String s : adapters) {
			String[] strs = s.split(" ");
			int from = idMap.get(strs[0]);
			int to = idMap.get(strs[1]);
			connectMap[from][to] = true;
		}
		
		// followConnectMap[i][j] is true if accessing i can make accessing j available.
		boolean[][] followConnectMap = new boolean[n][n];
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) {
				followConnectMap[i][j] = connectMap[i][j];
			}
			followConnectMap[i][i] = true;
		}
		for (int step = 0; step < n; step++) {
			for (int i = 0; i < n; ++i) {
				for (int j = 0; j < n; ++j) {
					if (followConnectMap[i][j]) {
						// copy map[j] -> map[i].
						for (int k = 0; k < n; ++k) {
							followConnectMap[i][k] |= followConnectMap[j][k];
						}
					}
				}
			}
		}
		
		int neededCount = itinerary.length;
		int[] needed = new int[neededCount];
		for (int i = 0; i < neededCount; ++i) {
			needed[i] = idMap.get(itinerary[i]);
		}

		// neededFollowMask[i] is the bit mask of needed countries that can be accessed if country i is accessed.
		int[] neededFollowMask = new int[n];
		for (int i = 0; i < n; ++i) {
			int neededMask = 0;
			for (int j = 0; j < neededCount; ++j) {
				if (followConnectMap[i][needed[j]]) {
					neededMask |= (1 << j);
				}
			}
			neededFollowMask[i] = neededMask;
		}
		
		int startNeededMask = neededFollowMask[idMap.get(homeCountry)];
		
		int mask = (1 << neededCount) - 1;
		// dp[i] is the min adapters to buy in order to connect needed bit mask i.
		int[] dp = new int[mask + 1];
		for (int i = 1; i <= mask; ++i) {
			if ((i & ~startNeededMask) == 0) {
				dp[i] = 0;
				continue;
			}
			dp[i] = Integer.MAX_VALUE;
			for (int id = 0; id < n; ++id) {
				int removeMask = neededFollowMask[id];
				if ((i & removeMask) == 0) {
					continue;
				}
				int j = i & ~removeMask;
				dp[i] = Math.min(dp[i], dp[j] + 1);
			}
		}
		return dp[mask];
	}
	
	
	public int needed2(String[] adapters, String[] itinerary, String homeCountry) {
		HashMap<String, Integer> idMap = new HashMap<String, Integer>();
		for (String s : adapters) {
			String[] strs = s.split(" ");
			for (String country : strs) {
				if (!idMap.containsKey(country)) {
					idMap.put(country, idMap.size());
				}
			}
		}
		for (String country : itinerary) {
			if (!idMap.containsKey(country)) {
				idMap.put(country, idMap.size());
			}
		}
		if (!idMap.containsKey(homeCountry)) {
			idMap.put(homeCountry, idMap.size());
		}
		int n = idMap.size();
		boolean[][] connectMap = new boolean[n][n];
		for (String s : adapters) {
			String[] strs = s.split(" ");
			int from = idMap.get(strs[0]);
			int to = idMap.get(strs[1]);
			connectMap[from][to] = true;
		}
		int[] connectMask = new int[n];
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) {
				if (connectMap[i][j]) {
					connectMask[i] |= (1 << j);
				}
			}
		}
		// followMask[i] is the bit mask of countries that can be accessed if country i is accessed.
		int[] followMask = new int[n];
		for (int i = 0; i < n; ++i) {
			followMask[i] = (1 << i) | connectMask[i];
		}
		for (int step = 0; step < n; ++step) {
			for (int i = 0; i < n; ++i) {
				int value = followMask[i];
				for (int j = 0; j < n; ++j) {
					if ((value & (1 << j)) != 0) {
						followMask[i] |= followMask[j];
					}
				}
			}
		}
		
		int neededMask = 0;
		for (String country : itinerary) {
			neededMask |= (1 << idMap.get(country));
		}
		int startMask = followMask[idMap.get(homeCountry)];
		
		int mask = (1 << n) - 1;
		// dp[i] is the min adapters to buy in order to connect bit mask i.
		int[] dp = new int[mask + 1];
		for (int i = 1; i <= mask; ++i) {
			if ((i & ~startMask) == 0) {
				dp[i] = 0;
				continue;
			}
			dp[i] = Integer.MAX_VALUE;
			for (int j = 0; j < n; ++j) {
				if ((i & (1 << j)) == 0) {
					continue;
				}
				int k = i & ~followMask[j];
				dp[i] = Math.min(dp[i], dp[k] + 1);
			}
		}
		int result = Integer.MAX_VALUE;
		for (int i = 0; i <= mask; ++i) {
			if ((neededMask & ~i) == 0) {
				result = Math.min(result, dp[i]);
			}
		}
		return result;
	}
	
}