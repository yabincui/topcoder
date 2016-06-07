import java.util.*;

public class JohnnysPhone {
	public int minimizePushes(String[] dictionary, String message) {
		StringBuilder builder = new StringBuilder();
		for (String s : dictionary) {
			builder.append(s);
		}
		String[] words = builder.toString().split(" ");
		int wordCount = words.length;
		String[] digits = new String[wordCount];
		for (int i = 0; i < wordCount; ++i) {
			builder = new StringBuilder();
			for (int j = 0; j < words[i].length(); ++j) {
				builder.append(toDigit(words[i].charAt(j)));
			}
			digits[i] = builder.toString();
		}
		
		// dpWithoutSpace[i] means the min types to match message[0..i-1], and don't rely on a space in i.
		int[] dpWithoutSpace = new int[message.length() + 1];
		int[] dpWithSpace = new int[message.length() + 1];
		
		dpWithSpace[0] = 0;
		dpWithoutSpace[0] = 0;
		for (int i = 1; i <= message.length(); ++i) {
			if (message.charAt(i-1) == ' ') {
				if (dpWithoutSpace[i-1] == Integer.MAX_VALUE && dpWithSpace[i-1] == Integer.MAX_VALUE) {
					dpWithoutSpace[i] = dpWithSpace[i] = Integer.MAX_VALUE;
				} else {
					dpWithoutSpace[i] = dpWithSpace[i] = Math.min(dpWithoutSpace[i-1], dpWithSpace[i-1]) + 1;
				}
				continue;
			}
			int minCostWithoutSpace = Integer.MAX_VALUE;
			int minCostWithSpace = Integer.MAX_VALUE;
			for (int start = i-1; start >= 0; --start) {
				if (message.charAt(start) == ' ') {
					break;
				}
				int curCost = dpWithoutSpace[start];
				if (curCost == Integer.MAX_VALUE) {
					continue;
				}
				String curDigits = "";
				int matchPos = -1;
				for (int j = start; j <= i-1; ++j) {
					char newDigit = toDigit(message.charAt(j));
					curDigits += newDigit;
					if (matchPos != -1 && curDigits.length() <= words[matchPos].length() &&
							newDigit == digits[matchPos].charAt(curDigits.length() - 1)) {
						continue;
					}
					matchPos++;
					while (matchPos < words.length) {
						if (curDigits.length() <= digits[matchPos].length() &&
								curDigits.equals(digits[matchPos].substring(0, curDigits.length()))) {
							break;
						}
						matchPos++;
					}
					if (matchPos == words.length) {
						break;
					}
				}
				curCost += i - start;
				// Find match place using next button.
				String subMessage = message.substring(start, i);
				while (matchPos != words.length && curCost < Math.max(minCostWithoutSpace, minCostWithSpace)) {
					if (words[matchPos].substring(0, curDigits.length()).equals(subMessage)) {
						// 1 is the cost to press 'New word'.
						minCostWithoutSpace = Math.min(minCostWithoutSpace, curCost + 1);
						// Rely on the next Space.
						minCostWithSpace = Math.min(minCostWithSpace, curCost);
						break;
					}
					// Find the next match word.
					matchPos++;
					// Press 'Next'.
					curCost++;
					while (matchPos != words.length) {
						if (digits[matchPos].length() >= curDigits.length() &&
								digits[matchPos].substring(0, curDigits.length()).equals(curDigits)) {
							break;
						}
						matchPos++;
					}
				}
			}
			dpWithoutSpace[i] = minCostWithoutSpace;
			dpWithSpace[i] = minCostWithSpace;
		}
		return dpWithSpace[message.length()] == Integer.MAX_VALUE ? -1 : dpWithSpace[message.length()];
	}
	
	char toDigit(char ch) {
		char result = '?';
		if (ch <= 'c') {
			result = '2';
		} else if (ch <= 'f') {
			result = '3';
		} else if (ch <= 'i') {
			result = '4';
		} else if (ch <= 'l') {
			result = '5';
		} else if (ch <= 'o') {
			result = '6';
		} else if (ch <= 's') {
			result = '7';
		} else if (ch <= 'v') {
			result = '8';
		} else if (ch <= 'z') {
			result = '9';
		}
		return result;
	}
}