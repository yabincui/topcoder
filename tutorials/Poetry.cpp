// SRM 170 Div 2 Hard
#include <ctype.h>

#include <map>
#include <string>
#include <vector>

using namespace std;

class Poetry {
public:
	string rhymeScheme(vector<string> poem) {
		map<string, char> rhy_map;
		char cur_char = 'a';
		string result;
		for (auto& s : poem) {
			string word = getLastWord(s);
			if (word.empty()) {
				result.push_back(' ');
			} else {
				string rhy = getRhy(word);
				auto it = rhy_map.find(rhy);
				if (it != rhy_map.end()) {
					result.push_back(it->second);
				} else {
					rhy_map[rhy] = cur_char;
					result.push_back(cur_char);
					if (cur_char == 'z') {
						cur_char = 'A';
					} else {
						cur_char++;
					}
				}
			}
		}
		return result;
	}
	
	string getLastWord(string s) {
		char* non_space_p = nullptr;
		char* start = &s[0];
		for (char* p = start; *p != '\0'; p++) {
			if (*p != ' ' && (p == start || *(p-1) == ' ')) {
				non_space_p = p;
			}
		}
		if (non_space_p == nullptr) {
			return "";
		}
		string result;
		for (char* p = non_space_p; *p != ' ' && *p != '\0'; p++) {
			result.push_back(tolower(*p));
		}
		return result;
	}
	
	string getRhy(string s) {
		char* start = &s[0];
		char* end = start + s.size() - 1;
		char* last_vowel_p = end;
		while (!isVowel(last_vowel_p, start, end)) {
			last_vowel_p--;
		}
		while (last_vowel_p != start && isVowel(last_vowel_p - 1, start, end)) {
			last_vowel_p--;
		}
		return string(last_vowel_p, end + 1);
	}
	
	bool isVowel(char* p, char* start, char* end) {
		if (*p == 'a' || *p == 'e' || *p == 'i' || *p == 'o' || *p == 'u' || (*p == 'y' && p != start && p != end)) {
			return true;
		}
		return false;
	}
};
