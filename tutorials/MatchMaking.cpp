// SRM 203 Div 1 Easy
#include <algorithm>
#include <string>
#include <vector>

using namespace std;

class MatchMaking {
public:
	string makeMatch(vector<string> namesWomen, vector<string> answersWomen,
					 vector<string> namesMen, vector<string> answersMen,
					 string queryWoman) {
		size_t size = namesWomen.size();
		vector<bool> usedWomen(size, false);
		vector<bool> usedMen(size, false);
		for (size_t used = 0; used < size; ++used) {
			size_t selWoman = -1;
			for (size_t i = 0; i < size; ++i) {
				if (!usedWomen[i] && (selWoman == -1 ||
					namesWomen[i] < namesWomen[selWoman])) {
					selWoman = i;
				}
			}
			size_t selMan = -1;
			size_t match = 0;
			for (size_t i = 0; i < size; ++i) {
				if (!usedMen[i]) {
					size_t m = getMatch(answersWomen[selWoman], answersMen[i]);
					if (selMan == -1 || match < m || (match == m &&
						namesMen[i] < namesMen[selMan])) {
						selMan = i;
						match = m;
					}
				}
			}
			usedWomen[selWoman] = true;
			usedMen[selMan] = true;
			if (namesWomen[selWoman] == queryWoman) {
				return namesMen[selMan];
			}
		}
		return "";
	}
	
	size_t getMatch(string a, string b) {
		size_t m = 0;
		for (size_t i = 0; i < a.size(); ++i) {
			if (a[i] == b[i]) {
				m++;
			}
		}
		return m;
	}
};
