// SRM 177 Div 1 Easy
#include <inttypes.h>
#include <string>
#include <unordered_map>
#include <vector>

using namespace std;

class TickTick {
public:
	int count(vector<string> events) {
		unordered_map<string, bool> hit_map;
		vector<uint64_t> values;
		int n = events.size();
		for (int i = 0; i < n; ++i) {
			values.push_back(getValue(events[i]));
		}
		int res = 0;
		for (int i = 0; i < n; ++i) {
			for (int add = -1; add <= 1; add += 2) {
				uint64_t v = add + values[i] % 100000000;
				string s = getMark(v, values);
				//printf("try v = %llu, s = %s\n", (unsigned long long)v, s.c_str());
				if (hit_map.find(s) == hit_map.end()) {
					hit_map[s] = true;
					res++;
				}
			}
		}
		return res;
	}
	
	string getMark(uint64_t v, const vector<uint64_t>& values) {
		string res;
		uint64_t init_v = v;
		for (auto& value : values) {
			if (value < v) {
				res.push_back('S');
			} else {
				res.push_back('D');
				v = init_v + value - value % 100000000;
				if (v < value) {
					v += 100000000;
				}
			}
		}
		return res;
	}
	
	uint64_t getValue(const string& s) {
		uint64_t mul = 100000000;
		uint64_t value = 0;
		bool has_dot = false;
		for (auto& c : s) {
			if (c == '.') {
				value *= mul;
				mul /= 10;
				has_dot = true;
			} else {
				int t = c - '0';
				if (!has_dot) {
					value = value * 10 + t;
				} else {
					value += t * mul;
					mul /= 10;
				}
			}
		}
		return value;
	}
};
