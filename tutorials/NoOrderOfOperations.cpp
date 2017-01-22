// SRM 200 Div 2 Easy
#include <ctype.h>

#include <string>
using namespace std;

class NoOrderOfOperations {
	public:
		int evaluate(string expr) {
			char* p = &expr[0];
			int cur_value = 0;
			char op = '\0';
			for (; *p != '\0'; ++p) {
				if (isdigit(*p)) {
					int t = *p - '0';
					if (op == '\0') {
						cur_value = t;
					} else if (op == '+') {
						cur_value += t;
					} else if (op == '-') {
						cur_value -= t;
					} else if (op == '*') {
						cur_value *= t;
					} else if (op == '/') {
						cur_value /= t;
					}
				} else {
					op = *p;
				}
			}
			return cur_value;
		}
};
