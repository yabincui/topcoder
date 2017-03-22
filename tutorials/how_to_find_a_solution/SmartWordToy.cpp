// SRM 233 Div 1, Level Two
#include <string>
#include <unordered_set>
#include <vector>
#include <queue>

using namespace std;

class SmartWordToy {
	unordered_set<int> forbid_set;
	unordered_set<int> visited;

	void parseWord(int value, const char* s) {
		while (isspace(*s)) s++;
		if (*s == '\0') {
			forbid_set.insert(value);
			return;
		}
		const char* next = s;
		while (!isspace(*next) && *next != '\0') {
			next++;
		}
		while (s != next) {
			int next_value = (value << 5) | (*s - 'a');
			parseWord(next_value, next);
			s++;
		}
	}
	
	bool isForbiddenOrVisited(string& s) {
		int value = 0;
		for (int i = 0; i < s.size(); ++i) {
			value = (value << 5) | (s[i] - 'a');
		}
		if (forbid_set.find(value) != forbid_set.end()) {
			return true;
		}
		if (visited.find(value) != visited.end()) {
			return true;
		}
		visited.insert(value);
		return false;
	}

public:
	int minPresses(string start, string finish, vector<string> forbid) {
		forbid_set.clear();
		for (auto& s : forbid) {
			parseWord(0, s.c_str());
		}
		visited.clear();
		queue<string> q;
		if (!isForbiddenOrVisited(start)) {
			q.push(start);
		}
		int cur_size = 1;
		int cur_step = 0;
		while (!q.empty()) {
			string cur = q.front(); q.pop();
			if (cur == finish) return cur_step;
			for (int i = 0; i < cur.size(); ++i) {
				for (int t = -1; t <= 1; t += 2) {
					string next = cur;
					next[i] += t;
					if (next[i] < 'a') next[i] += 26;
					else if (next[i] > 'z') next[i] -= 26;
					if (!isForbiddenOrVisited(next)) {
						q.push(next);
					}
				}
			}
			if (--cur_size == 0) {
				cur_size = q.size();
				cur_step++;
				//printf("cur_size = %d, cur_step = %d\n", cur_size, cur_step);
			}
		}
		return -1;
	}

};
