// SRM 197 Div 2 Hard
#include <inttypes.h>
#include <limits.h>
#include <string>
#include <vector>
using namespace std;

class QuickSums {
public:
	int minSums(string numbers, int sum) {
		int res = recur(numbers.c_str(), sum);
		if (res != -1) {
			res--;
		}
		return res;
	}
	
	int recur(const char* p, int sum) {
		if (*p == '\0') {
			return (sum == 0 ? 0 : -1);
		}
		int64_t val = 0;
		int min_res = INT_MAX;
		while (*p != '\0') {
			val = val * 10 + *p - '0';
			if (val > sum) {
				break;
			}
			int tmp = recur(p + 1, sum - val);
			if (tmp != -1 && min_res > tmp + 1) {
				min_res = tmp + 1;
			}
			p++;
		}
		if (min_res == INT_MAX) {
			min_res = -1;
		}
		return min_res;
	}
};
