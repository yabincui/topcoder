// SRM 207, Div II, Level 3
#include <string>
#include <queue>
#include <unordered_set>

using namespace std;

class CaptureThemAll {
	struct Pos {
		int r, c;
		Pos(int r = 0, int c = 0) : r(r), c(c) {}
		Pos(string& val) {
			c = val[0] - 'a';
			r = val[1] - '1';
		}
		Pos(int val) {
			c = val & 0xf;
			r = val >> 4;
		}
		
		int getKey() {
			return (r << 4) | c;
		}
		
		bool operator==(Pos& other) {
			return r == other.r && c == other.c;
		}
	};

	int dr[8] = {2, 2, -2, -2, 1, 1, -1, -1};
	int dc[8] = {1, -1, 1, -1, 2, -2, 2, -2};
	
	int getState(int match, Pos& pos) {
		return (match << 8) | pos.getKey();
	}
	
	void setState(int state, int* match, Pos* pos) {
		*match = state >> 8;
		pos->r = (state >> 4) & 0xf;
		pos->c = state & 0xf;
	}

public:
	int fastKnight(string knight, string rook, string queen) {
		Pos start(knight);
		Pos target1(rook);
		Pos target2(queen);
		
		queue<int> q;
		unordered_set<int> visited;
		q.push(getState(0, start));
		visited.insert(getState(0, start));
		int cur_size = 1;
		int cur_step = 0;
		while (!q.empty()) {
			int state = q.front(); q.pop();
			int match;
			Pos cur;
			setState(state, &match, &cur);
			if (cur == target1) {
				match |= 1;
			}
			if (cur == target2) {
				match |= 2;
			}
			if (match == 3) {
				return cur_step;
			}
			for (int d = 0; d < 8; ++d) {
				Pos next = cur;
				next.r += dr[d];
				next.c += dc[d];
				if (next.r < 0 || next.r >= 8 || next.c < 0 || next.c >= 8) {
					continue;
				}
				int state = getState(match, next);
				if (visited.find(state) == visited.end()) {
					visited.insert(state);
					q.push(state);
				}
			}
			
			if (--cur_size == 0) {
				cur_size = q.size();
				cur_step++;
			}
		}
		return -1;
	}
};
