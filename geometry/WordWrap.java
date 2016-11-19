import java.util.*;

public class WordWrap {
	public String[] justify(String[] lines, int columnWidth) {
		ArrayList<String> result = new ArrayList<String>();
		int readR = 0;
		int readC = 0;
		StringBuilder b = new StringBuilder();
		while (readR < lines.length) {
			if (lines[readR].charAt(readC) != ' ') {
				int wordLen = 1;
				while (readC + wordLen < lines[readR].length() &&
					lines[readR].charAt(readC + wordLen) != ' ') {
					wordLen++;
				}
				if ((b.length() == 0 && wordLen <= columnWidth) ||
					(b.length() + 1 + wordLen <= columnWidth)) {
				} else {
					result.add(b.toString());
					b.delete(0, b.length());
				}
				if (b.length() != 0) {
					b.append(' ');
				}
				for (int i = readC; i < readC + wordLen; ++i) {
					b.append(lines[readR].charAt(i));
				}
				readC += wordLen;
			} else {
				readC++;
			}
			if (readC == lines[readR].length()) {
				readR++;
				readC = 0;
			}
		}
		if (b.length() != 0) {
			result.add(b.toString());
		}
		String[] ret = new String[result.size()];
		for (int i = 0; i < result.size(); ++i) {
			ret[i] = result.get(i);
		}
		return ret;
	}
}
