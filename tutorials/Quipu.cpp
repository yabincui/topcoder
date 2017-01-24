// SRM 155 Div 2 Easy
#include <string>

using namespace std;

class Quipu {
public:
	int readKnots(string knots) {
		int result = 0;
		int cur_value = 0;
		
		int NEED_START = 0;
		int IN_VALUE = 1;
		int state = NEED_START;
		
		for (char* p = &knots[0]; *p != '\0'; ++p) {
			if (state == NEED_START) {
				if (*p == '-') {
					state = IN_VALUE;
					cur_value = 0;
				}
			} else if (state == IN_VALUE) {
				if (*p == 'X') {
					cur_value++;
				} else if (*p == '-') {
					result = result * 10 + cur_value;
					cur_value = 0;
				}
			}
		}
		return result;
	}
};
