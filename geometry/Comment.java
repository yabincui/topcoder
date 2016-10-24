import java.util.*;

public class Comment {
	final int NO_COMMENT = 0;
	final int LINE_COMMENT = 1;
	final int STAR_COMMENT = 2;
	final int IN_STRING = 3;

	class State {
		String[] code;
		ArrayList<String> gen;
		StringBuilder curGen;
		int r, c;
		int state;
		State(String[] code) {
			this.code = code;
			gen = new ArrayList<String>();
			curGen = new StringBuilder();
			r = 0;
			c = 0;
			state = NO_COMMENT;
		}
		
		boolean reachEnd() {
			return r >= code.length;
		}
		
		char getCur() {
			if (c < code[r].length()) {
				return code[r].charAt(c);
			}
			return '\n';
		}
		
		void moveToNext() {
			++c;
			if (c > code[r].length()) {
				r++;
				c = 0;
			}
		}
		
		void putChar(char c) {
			if (c != '\n') {
				curGen.append(c);
			} else if (curGen.length() > 0) {
				gen.add(curGen.toString());
				curGen.delete(0, curGen.length());
			}
		}
	}

	public String[] strip(String[] code) {
		State state = new State(code);
		while (!state.reachEnd()) {
			char c = state.getCur();
			state.moveToNext();
			if (state.state == NO_COMMENT) {
				if (c == '/') {
					char nc = state.getCur();
					if (nc == '*') {
						state.state = STAR_COMMENT;
						state.moveToNext();
					} else if (nc == '/') {
						state.state = LINE_COMMENT;
						state.moveToNext();
					} else {
						state.putChar(c);
					}
				} else if (c == '\"') {
					state.state = IN_STRING;
					state.putChar(c);
				} else {
					state.putChar(c);
				}
			} else if (state.state == LINE_COMMENT) {
				if (c == '\n') {
					state.putChar(c);
					state.state = NO_COMMENT;
				}
			} else if (state.state == STAR_COMMENT) {
				if (c == '\n') {
					state.putChar(c);
				} else if (c == '*') {
					char nc = state.getCur();
					if (nc == '/') {
						state.state = NO_COMMENT;
						state.moveToNext();
					}
				}
			} else if (state.state == IN_STRING) {
				state.putChar(c);
				if (c == '\\') {
					char nc = state.getCur();
					state.putChar(nc);
					state.moveToNext();
				} else if (c == '\"') {
					state.state = NO_COMMENT;
				}
			}
		}
		if (state.curGen.length() > 0) {
			state.gen.add(state.curGen.toString());
		}
		String[] result = new String[state.gen.size()];
		for (int i = 0; i < result.length; ++i) {
			result[i] = state.gen.get(i);
			//System.out.printf("result[%d] = '%s'\n", i, result[i]);
		}
		return result;
	}
}