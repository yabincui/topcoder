import java.util.*;

public class Smooshing {
	class Identifier {
		String word;
		int count;
		int startPos;
		
		Identifier(String word, int startPos) {
			this.word = word;
			this.count = 1;
			this.startPos = startPos;
		}
	}
	
	class IdentifierComparator implements Comparator<Identifier> {
		public int compare(Identifier id1, Identifier id2) {
			if (id1.count != id2.count) {
				return id2.count - id1.count;
			}
			return id1.startPos - id2.startPos;
		}
	}
	
	public int savings(String[] program) {
		ArrayList<Identifier> ids = new ArrayList<Identifier>();
		HashMap<String, Integer> wordPosition = new HashMap<String, Integer>();
		for (int t = 0; t < program.length; ++t) {
			String s = program[t] + " ";
			int start = -1;
			boolean startWithUp = false;
			for (int i = 0; i < s.length(); ++i) {
				if (s.charAt(i) >= 'A' && s.charAt(i) <= 'Z') {
					if (start == -1) {
						start = i;
						startWithUp = true;
					}
				} else if (s.charAt(i) >= 'a' && s.charAt(i) <= 'z') {
					if (start == -1) {
						start = i;
						startWithUp = false;
					}
				} else {
					if (start != -1 && startWithUp) {
						String word = s.substring(start, i);
						Integer pos = wordPosition.get(word);
						if (pos == null) {
							wordPosition.put(word, wordPosition.size());
							ids.add(new Identifier(word, t * 10000 + i));
						} else {
							ids.get(pos).count++;
						}
					}
					start = -1;
					startWithUp = false;
				}
			}
		}
		Collections.sort(ids, new IdentifierComparator());
		HashSet<String> abbreviationSet = new HashSet<String>();
		int save = 0;
		for (Identifier id : ids) {
			String word = id.word;
			String ab = word.substring(0, 1);
			int pos = 1;
			while (abbreviationSet.contains(ab)) {
				if (pos == word.length()) {
					pos = 0;
				}
				ab += word.charAt(pos);
				pos++;
			}
			abbreviationSet.add(ab);
			//System.out.printf("%s => %s, count = %d\n", word, ab, id.count);
			save += (word.length() - ab.length()) * id.count;
		}
		return save;
	}
}
