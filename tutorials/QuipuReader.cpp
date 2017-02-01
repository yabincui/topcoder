// SRM 155 Div 1 Med
#include <string.h>
#include <string>
#include <vector>
using namespace std;

class QuipuReader {
public:
	vector<int> readKnots(vector<string> knots) {
		int rows = knots.size();
		int cols = knots[0].size();
		vector<int> res(rows, 0);
		int xcount[rows];
		memset(xcount, 0, sizeof(xcount));
		bool prev_has_x = false;
		for (int i = 0; i < cols; ++i) {
			bool has_continue_x = false;
			for (int j = 0; j < rows; ++j) {
				if (knots[j][i] == 'X' && (!prev_has_x || knots[j][i-1] == 'X')) {
					has_continue_x = true;
					break;
				}
			}
			if (has_continue_x) {
				for (int j = 0; j < rows; ++j) {
					if (knots[j][i] == 'X') {
						xcount[j]++;
					}
				}
				prev_has_x = true;
			} else {
				if (prev_has_x) {
					for (int j = 0; j < rows; ++j) {
						res[j] = res[j] * 10 + xcount[j];
						xcount[j] = 0;
					}
				}
				bool has_x = false;
				for (int j = 0; j < rows; ++j) {
					if (knots[j][i] == 'X') {
						has_x = true;
						xcount[j] = 1;
					}
				}
				prev_has_x = has_x;
			}
		}
		if (prev_has_x) {
			for (int j = 0; j < rows; ++j) {
				res[j] = res[j] * 10 + xcount[j];
			}
		}
		return res;
	}
};
