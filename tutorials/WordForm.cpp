// SRM 173 Div 1 Easy
#include <ctype.h>

#include <string>

using namespace std;

class WordForm {
public:
	string getSequence(string word) {
		string result;
		char prev_add = '\0';
		for (auto& c : word) {
			c = toupper(c);
			char add = isVowel(c, prev_add) ? 'V' : 'C';
			if (result.empty() || result.back() != add) {
				result.push_back(add);
			}
			prev_add = add;
		}
		return result;
	}
	
	bool isVowel(char c, char prev_add) {
		if (c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U' ||
			(c == 'Y' && prev_add != '\0' && prev_add != 'V')) {
			return true;
		}
		return false;
	}
};
