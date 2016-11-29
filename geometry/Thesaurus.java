import java.util.*;

public class Thesaurus {
	
	class Entry {
		ArrayList<Integer> values = new ArrayList<Integer>();
		
		void addValue(int value) {
			values.add(value);
		}
		
		void sortValues() {
			Collections.sort(values);
		}
		
		void combineEntry(Entry other) {
			ArrayList<Integer> newV = new ArrayList<Integer>();
			for (int i = 0, j = 0; i < values.size() || j < other.values.size();) {
				if (i == values.size()) {
					newV.add(other.values.get(j++));
				} else if (j == other.values.size()) {
					newV.add(values.get(i++));
				} else {
					int a = values.get(i);
					int b = other.values.get(j);
					if (a < b) {
						newV.add(a); i++;
					} else if (a > b) {
						newV.add(b); j++;
					} else {
						newV.add(a); i++; j++;
					}
				}
			}
			values = newV;
		}
		
		boolean canCombine(Entry other) {
			int equalCount = 0;
			for (int i = 0, j = 0; i < values.size() && j < other.values.size();) {
				int a = values.get(i);
				int b = other.values.get(j);
				if (a < b) i++;
				else if (a > b) j++;
				else {
					equalCount++;
					if (equalCount == 2) {
						return true;
					}
					i++; j++;
				}
			}
			return false;
		}
	}
	
	public String[] edit(String[] entry) {
		HashMap<String, Integer> keyMap = new HashMap<String, Integer>();
		ArrayList<String> idToStringMap = new ArrayList<String>();
		int n = entry.length;
		Entry[] items = new Entry[n];
		for (int i = 0; i < n; ++i) {
			items[i] = new Entry();
			StringBuilder sb = new StringBuilder();
			for (int j = 0; j < entry[i].length(); ++j) {
				char c = entry[i].charAt(j);
				if (c != ' ') {
					sb.append(c);
				}
				if (c == ' ' || j == entry[i].length() - 1) {
					String key = sb.toString();
					sb.delete(0, sb.length());
					Integer id = keyMap.get(key);
					if (id == null) {
						id = keyMap.size();
						keyMap.put(key, id);
						idToStringMap.add(key);
					}
					items[i].addValue(id);
				}
			}
			items[i].sortValues();
		}
		while (true) {
			boolean hasChanged = false;
			for (int i = 0; i < n; ++i) {
				if (items[i] == null) {
					continue;
				}
				for (int j = i + 1; j < n; ++j) {
					if (items[j] == null) {
						continue;
					}
					if (items[i].canCombine(items[j])) {
						items[i].combineEntry(items[j]);
						items[j] = null;
						hasChanged = true;
					}
				}
			}
			if (!hasChanged) break;
		}
		ArrayList<String> result = new ArrayList<String>();
		for (int i = 0; i < n; ++i) {
			if (items[i] == null) continue;
			ArrayList<String> tmp = new ArrayList<String>();
			for (Integer value : items[i].values) {
				tmp.add(idToStringMap.get(value));
			}
			Collections.sort(tmp);
			StringBuilder sb = new StringBuilder();
			for (int j = 0; j < tmp.size(); ++j) {
				if (j != 0) sb.append(' ');
				sb.append(tmp.get(j));
			}
			result.add(sb.toString());
		}
		Collections.sort(result);
		String[] ret = new String[result.size()];
		for (int i = 0; i < result.size(); ++i) {
			ret[i] = result.get(i);
			//System.out.printf("ret[%d] = %s\n", i, ret[i]);
		}
		return ret;
	}
}
