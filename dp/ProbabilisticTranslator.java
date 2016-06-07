import java.util.*;

public class ProbabilisticTranslator {
	public int maximumFidelity(String[] text, String[] dictionary, String[] frequencies) {
		ArrayList<String> words = new ArrayList<String>();
		for (String s : text) {
			String[] strs = s.split(" ");
			for (String word : strs) {
				words.add(word);
			}
		}
		HashMap<String, ArrayList<String>> dictMap = new HashMap<String, ArrayList<String>>();
		int maxTargetCount = 0;
		for (String s : dictionary) {
			String[] strs = s.split(":");
			String source = strs[0].trim();
			strs = strs[1].trim().split(" ");
			ArrayList<String> targets = new ArrayList<String>();
			for (String target : strs) {
				targets.add(target.trim());
			}
			dictMap.put(source, targets);
			maxTargetCount = Math.max(maxTargetCount, targets.size());
		}
		HashMap<String, Integer> freqMap = new HashMap<String, Integer>();
		for (String freq : frequencies) {
			String[] strs = freq.split(" ");
			freqMap.put(strs[0] + " " + strs[1], Integer.parseInt(strs[2]));
		}
		int[] dp = new int[maxTargetCount];
		ArrayList<String> curTargets = dictMap.get(words.get(0));
		for (int i = 1; i < words.size(); ++i) {
			ArrayList<String> nextTargets = dictMap.get(words.get(i));
			int[] nextDp = new int[maxTargetCount];
			for (int j = 0; j < nextTargets.size(); ++j) {
				for (int k = 0; k < curTargets.size(); ++k) {
					String freqKey = curTargets.get(k) + " " + nextTargets.get(j);
					Integer freq = freqMap.get(freqKey);
					if (freq != null) {
						nextDp[j] = Math.max(nextDp[j], dp[k] + freq);
					} else {
						nextDp[j] = Math.max(nextDp[j], dp[k]);
					}
				}
			}
			dp = nextDp;
			curTargets = nextTargets;
		}
		int result = 0;
		for (int i = 0; i < dp.length; ++i) {
			result = Math.max(result, dp[i]);
		}
		return result;
	}
}
