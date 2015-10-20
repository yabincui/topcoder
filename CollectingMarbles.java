import java.util.*;

public class CollectingMarbles {
	
	public int mostMarbles(int[] marblesWeights, int bagCapacity, int numberOfBags) {
		int n = marblesWeights.length;
		int mask = (1 << n) - 1;
		boolean[] fillInOneBag = new boolean[mask + 1];
		for (int i = 0; i <= mask; ++i) {
			int sum = 0;
			for (int j = 0; j < n; ++j) {
				if ((i & (1 << j)) != 0) {
					sum += marblesWeights[j];
				}
			}
			if (sum <= bagCapacity) {
				fillInOneBag[i] = true;
			}
		}
		int[] minBags = new int[mask + 1];
		for (int i = 1; i <= mask; ++i) {
			int minValue = -1;
			for (int j = i; j > 0; --j) {
				if ((j & i) == 0) {
					continue;
				}
				if (fillInOneBag[j] && minBags[i & ~j] != -1) {
					if (minValue == -1 || minValue > minBags[i & ~j] + 1) {
						minValue = minBags[i & ~j] + 1;
					}
				}
			}
			minBags[i] = minValue;
		}
		int maxPack = 0;
		for (int i = 0; i <= mask; ++i) {
			if (minBags[i] > numberOfBags || minBags[i] == -1) {
				continue;
			}
			int bits = 0;
			for (int j = 0; j < n; ++j) {
				if ((i & (1 << j)) != 0) {
					bits++;
				}
			}
			maxPack = Math.max(maxPack, bits);
		}
		return maxPack;
	}
	
	/*
	public int mostMarbles(int[] marblesWeights, int bagCapacity, int numberOfBags) {
		TreeMap<Long, Integer> dp = new TreeMap<Long, Integer>();
		int[] bags = new int[numberOfBags];
		for (int i = 0; i < numberOfBags; ++i) {
			bags[i] = bagCapacity;
		}
		long firstKey = arrayToKey(bags);
		dp.put(firstKey, 0);
		int maxCount = 0;
		for (int i = 0; i < marblesWeights.length; ++i) {
			TreeMap<Long, Integer> nextDp = (TreeMap<Long, Integer>)dp.clone();
			int w = marblesWeights[i];
			for (Long key : dp.keySet()) {
				int count = dp.get(key);
				bags = keyToArray(key, numberOfBags);
				for (int j = 0; j < numberOfBags; ++j) {
					if ((j != 0 && bags[j] == bags[j-1]) || bags[j] < w) {
						continue;
					}
					int[] nextBags = (int[])bags.clone();
					nextBags[j] -= w;
					Arrays.sort(nextBags);
					long nextKey = arrayToKey(nextBags);
					if (!nextDp.containsKey(nextKey) || nextDp.get(nextKey) < count + 1) {
						nextDp.put(nextKey, count + 1);
						maxCount = Math.max(maxCount, count + 1);
					}
				}
			}
			dp = new TreeMap<Long, Integer>();
			for (Long key : nextDp.keySet()) {
				int count = nextDp.get(key);
				if (count + marblesWeights.length - i <= maxCount) {
					continue;
				}
				dp.put(key, count);
			}
		}
		return maxCount;
	}
	
	long arrayToKey(int[] bags) {
		long result = 0;
		for (int i = 0; i < bags.length; ++i) {
			result = (result << 5) | bags[i];
		}
		return result;
	}
	
	int[] keyToArray(long key, int n) {
		int[] bags = new int[n];
		for (int i = n - 1; i >= 0; --i) {
			bags[i] = (int)(key & 31);
			key >>= 5;
		}
		return bags;
	}
	
	*/
}