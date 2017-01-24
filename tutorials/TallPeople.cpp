// SRM 208 Div 1 Easy
#include <string>
#include <vector>

using namespace std;

class TallPeople {
public:
	vector<int> getPeople(vector<string> people) {
		vector<vector<int>> m;
		for (auto& s : people) {
			vector<int> v;
			int value = 0;
			for (auto& c : s) {
				if (isdigit(c)) {
					value = value * 10 + c - '0';
				} else if (value != 0) {
					v.push_back(value);
					value = 0;
				}
			}
			if (value != 0) {
				v.push_back(value);
			}
			m.push_back(v);
		}
		vector<int> result;
		// tallest from shortest in each row.
		int tallest = 0;
		for (int i = 0; i < m.size(); ++i) {
			int shortest = m[i][0];
			for (int j = 1; j < m[i].size(); ++j) {
				if (m[i][j] < shortest) {
					shortest = m[i][j];
				}
			}
			if (tallest < shortest) {
				tallest = shortest;
			}
		}
		result.push_back(tallest);
		// shortest from tallest in each column.
		int shortest = -1;
		for (int j = 0; j < m[0].size(); ++j) {
			int tallest = 0;
			for (int i = 0; i < m.size(); ++i) {
				if (tallest < m[i][j]) {
					tallest = m[i][j];
				}
			}
			if (shortest == -1 || shortest > tallest) {
				shortest = tallest;
			}
		}
		result.push_back(shortest);
		return result;
	}
};
