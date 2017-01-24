// SRM 147 Div 2 Easy
#include <string>

using namespace std;

class CCipher {
public:
	string decode(string cipherText, int shift) {
		string result;
		for (auto& c : cipherText) {
			int d = c - 'A';
			d -= shift;
			if (d < 0) {
				d += 26;
			}
			char t = d + 'A';
			result.push_back(t); 
		}
		return result;
	}
};
